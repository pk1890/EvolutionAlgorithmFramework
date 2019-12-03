package kipinski.piotr;

import java.util.List;
import java.util.function.Function;

public class Algorithm <G extends Gene> {
    private int populationSize;
    private Function<Genotype<G>, Double> fitnessFunction;
    private List<Operator<G>> operators;
    private Population<G> population;
    private Selector<G> selector;

    public static class Builder <G extends Gene> {
        private Algorithm<G> algorithm;

        public Builder populationSize(int size){
            algorithm.populationSize = size;
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
        public Algorithm<G> build(){
            algorithm.population = new Population<>(algorithm.populationSize);

            return algorithm;
        }

    }

    public void nextEpoch(){
        for(Genotype<G> genotype : population){
            genotype.setFitness(fitnessFunction.apply(genotype));
        }
    }
}
