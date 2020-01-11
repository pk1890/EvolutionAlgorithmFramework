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
import genetics.stopConditions.EpochNumberStopCondition;
import genetics.stopConditions.TimeUnits;
import genetics.utilities.Pair;
import org.junit.gen5.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.gen5.api.Assertions;

public class StopConditionsTests {

   @Test
   void DoubleExampleConvergenceTest(){
      Random rnd = new Random();
      for (int i = 0; i < 10; i++){


         List<AbstractStopCondition> stopConditions = new ArrayList<>();
         stopConditions.add(new ElapsedTimeStopCondition(10, TimeUnits.SECONDS));

         List<Operator<DoubleGene>> operators = new ArrayList<>();
         operators.add(new SampleCrossoverMethod());
         operators.add(new SampleMutation());



         double [] coefficients = {rnd.nextDouble()*100-50, rnd.nextDouble()*100-50, 0,  rnd.nextDouble()*100-50};

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
}
