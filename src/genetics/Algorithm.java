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
    private int populationSize;
    private Function<Genotype<G>, Double> fitnessFunction;
    private List<Operator<G>> operators;
    private Population<G> population;
    private Selector<G> selector;
    private GenotypeFactory<G> genotypeFactory;
    private BreedingStrategy<G> breedingStrategy;


    private ElapsedTimeStopCondition elapsedTimeStopCondition = null;
    private EpochNumberStopCondition epochNumberStopCondition = null;
    private PlateauStopCondition plateauStopCondition = null;
    private EvaluationsCountStopCondition evaluationsCountStopCondition = null;

    public static class Builder <G extends Gene> {
        private Algorithm<G> algorithm;

        public Builder populationSize(int size){
            algorithm.populationSize = size;
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

        public Builder addStopCondition(AbstractStopCondition condition){
            if(condition instanceof ElapsedTimeStopCondition){
                algorithm.elapsedTimeStopCondition = (ElapsedTimeStopCondition) condition;
            } else if(condition instanceof EpochNumberStopCondition){
                algorithm.epochNumberStopCondition = (EpochNumberStopCondition) condition;
            } else if (condition instanceof EvaluationsCountStopCondition){
                algorithm.evaluationsCountStopCondition = (EvaluationsCountStopCondition) condition;
            } else if (condition instanceof PlateauStopCondition) {
                algorithm.plateauStopCondition = (PlateauStopCondition) condition;
            } else{
                throw new TypeNotPresentException("Wrong type of stop condition", new Throwable());
            }
            return this;
        }

        public Algorithm<G> build(){

            init();


            return algorithm;
        }

        private void init()
        {
            algorithm.population = new Population<>(algorithm.populationSize);
            for(int i=0;i<algorithm.populationSize;i++)
            {
                algorithm.population.addIndividual(algorithm.genotypeFactory.generate());
            }

        }

    }

    public void run(){
        elapsedTimeStopCondition.startCountdown();
        while(true){
            nextEpoch();
            if(plateauStopCondition != null && !plateauStopCondition.shouldContinue()) break;
            if(epochNumberStopCondition != null && !epochNumberStopCondition.shouldContinue()) break;
            if(evaluationsCountStopCondition != null && !evaluationsCountStopCondition.shouldContinue()) break;
            if(elapsedTimeStopCondition != null && !elapsedTimeStopCondition.shouldContinue()) break;
        }
    }

    public void nextEpoch(){
        for(Genotype<G> genotype : population){
            genotype.setFitness(fitnessFunction.apply(genotype));
        }
        population = selector.select(population);
        if(plateauStopCondition != null)
            plateauStopCondition.pushBest(population.getBestIndividual());

        for(Operator<G> operator : operators)
        {
            if(operator instanceof CrossoverMethod)
            {
                ((CrossoverMethod<G>) operator).crosbreedGroup(breedingStrategy.choose(population));
            }
            else if(operator instanceof Mutation)
            {
                ((Mutation<G>) operator).mutate(population.getIndividuals().get(new Random().nextInt()%populationSize));
            }
        }
        if(epochNumberStopCondition != null)
            epochNumberStopCondition.update();
        if(evaluationsCountStopCondition != null)
            evaluationsCountStopCondition.update();
        if(elapsedTimeStopCondition != null)
            elapsedTimeStopCondition.measure();
    }
}
