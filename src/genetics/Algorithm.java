package genetics;

import genetics.factories.GenotypeFactory;
import genetics.genes.Gene;
import genetics.genes.Genotype;
import genetics.genes.Population;
import genetics.operators.CrossoverMethod;
import genetics.operators.Mutation;
import genetics.operators.Operator;
import genetics.operators.Selector;
import genetics.utilities.BreedingStrategy;
import genetics.stopConditions.*;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class Algorithm <G extends Gene> {
    private int startPopulationSize;
    private Function<Genotype<G>, Double> fitnessFunction;
    private List<Operator<G>> operators;
    private Population<G> population;
    private Selector<G> selector;
    private GenotypeFactory<G> genotypeFactory;
    private BreedingStrategy<G> breedingStrategy;
    private List<AbstractStopCondition> stopConditions;
    private boolean shouldContinue = true;

    public static class Builder <G extends Gene> {
        private Algorithm<G> algorithm;

        public Builder(){
            algorithm = new Algorithm<>();
        }

        public Builder populationSize(int size){
            algorithm.startPopulationSize = size;
            return this;
        }

        public Builder breedingStrategy(BreedingStrategy<G> breedingStrategy){
            algorithm.breedingStrategy = breedingStrategy;
            return this;
        }

        public Builder genotypeFactory(GenotypeFactory<G> genotypeFactory)
        {
            algorithm.genotypeFactory = genotypeFactory;
            return this;
        }

        public Builder fitnessFunction(Function<Genotype<G>, Double> function){
            algorithm.fitnessFunction = function;
            return this;
        }

        public Builder operators(List<Operator<G>> operators){
            algorithm.operators = operators;
            return this;
        }

        public Builder selector(Selector<G> selector){
            algorithm.selector = selector;
            return this;
        }

        public Builder stopConditions(List<AbstractStopCondition> abstractStopConditions){
            algorithm.stopConditions = abstractStopConditions;
            return this;
        }

        public Algorithm<G> build(){

            init();


            return algorithm;
        }

        private void init()
        {
            algorithm.population = new Population<>();
            for(int i = 0; i<algorithm.startPopulationSize; i++)
            {
                algorithm.population.addIndividual(algorithm.genotypeFactory.generate());
            }

        }

    }

    private void updateStopConditions() {
        stopConditions.forEach((x) -> {
            if(x instanceof CountingBasedStopCondition) ((CountingBasedStopCondition) x).update();
            if(x instanceof FitnessBasedStopCondition) ((FitnessBasedStopCondition) x).update(population.getFitnesses());
        });
    }
    public Population<G> run(){
        stopConditions.forEach(AbstractStopCondition::reset);
        while(shouldContinue){
            nextEpoch();
        }
        return population;
    }


    public void nextEpoch(){
        for(Genotype<G> genotype : population){
            genotype.setFitness(fitnessFunction.apply(genotype));
        }
        population = selector.select(population);

        updateStopConditions();

        applyOperators();

        System.out.println(population.getBestIndividual().getGenes());

        shouldContinue = stopConditions.stream()
                        .map(AbstractStopCondition::shouldContinue)
                        .reduce(true, (x, y) -> x & y);
    }

    private void applyOperators() {
        for(Operator<G> operator : operators)
        {
            if(operator instanceof CrossoverMethod)
            {
                population.concatenate(((CrossoverMethod<G>) operator).crossbreed(breedingStrategy, population));
            }
            else if(operator instanceof Mutation)
            {
                ((Mutation<G>) operator).mutate(population.getIndividuals().get(new Random().nextInt(population.size())));
            }
        }
    }

}
