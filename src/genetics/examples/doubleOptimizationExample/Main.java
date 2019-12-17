package genetics.examples.doubleOptimizationExample;

import genetics.Algorithm;
import genetics.factories.SampleDoubleGenotypeFactory;
import genetics.genes.DoubleGene;
import genetics.genes.Genotype;
import genetics.operators.Operator;
import genetics.operators.RandomTournamentSelector;
import genetics.stopConditions.AbstractStopCondition;
import genetics.stopConditions.EpochNumberStopCondition;
import genetics.utilities.Pair;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        List<AbstractStopCondition> stopConditions = new ArrayList<>();
        stopConditions.add(new EpochNumberStopCondition(100));

        List<Operator<DoubleGene>> operators = new ArrayList<>();
        operators.add(new SampleCrossoverMethod());
        operators.add(new SampleMutation());


        Algorithm<DoubleGene> sampleAlgorithm = new Algorithm.Builder<DoubleGene>()
                                                    .populationSize(100)
                                                    .fitnessFunction(new SampleFitnessFunction())
                                                    .genotypeFactory(new SampleDoubleGenotypeFactory(
                                                                        new Pair<>(-100.0, 100.0),
                                                                        5))
                                                    .stopConditions(stopConditions)
                                                    .selector(new RandomTournamentSelector(10, 0.5))
                                                    .operators(operators)
                                                    .breedingStrategy(new SampleBreedingStrategy())
                                                    .build();

        var result = sampleAlgorithm.run();
        System.out.println(result.getBestIndividual().getGenes().toString());
    }
}
