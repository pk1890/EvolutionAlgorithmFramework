package genetics.examples.TSPExample;

import genetics.Algorithm;
import genetics.examples.doubleOptimizationExample.SampleBreedingStrategy;
import genetics.examples.doubleOptimizationExample.SampleCrossoverMethod;
import genetics.examples.doubleOptimizationExample.SampleFitnessFunction;
import genetics.examples.doubleOptimizationExample.SampleMutation;
import genetics.factories.SampleDoubleGenotypeFactory;
import genetics.genes.DoubleGene;
import genetics.genes.Population;
import genetics.operators.Operator;
import genetics.operators.RandomTournamentSelector;
import genetics.stopConditions.AbstractStopCondition;
import genetics.stopConditions.ElapsedTimeStopCondition;
import genetics.stopConditions.PlateauStopCondition;
import genetics.stopConditions.TimeUnits;
import genetics.utilities.Pair;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        List<AbstractStopCondition> stopConditions = new ArrayList<>();
        stopConditions.add(new ElapsedTimeStopCondition(10, TimeUnits.SECONDS));
        stopConditions.add(new PlateauStopCondition(10, 1000));

        List<Operator<DoubleGene>> operators = new ArrayList<>();
        operators.add(new SampleCrossoverMethod());
        operators.add(new SampleMutation());

        double [] coefficients = {30.42, 20.10, 633.4, -73};

        Algorithm<DoubleGene> sampleAlgorithm = new Algorithm.Builder<DoubleGene>()
                                                    .populationSize(100)
                                                    .fitnessFunction(new SampleFitnessFunction(coefficients, 4))
                                                    .genotypeFactory(new SampleDoubleGenotypeFactory(
                                                                        new Pair<>(-100.0, 100.0),
                                                                        5))
                                                    .stopConditions(stopConditions)
                                                    .selector(new RandomTournamentSelector(10,3))
                                                    .operators(operators)
                                                    .breedingStrategy(new SampleBreedingStrategy())
                                                    .build();

        Population<DoubleGene> result = sampleAlgorithm.run();
        System.out.println(result.getBestIndividual().getGenes().toString());
    }
}
