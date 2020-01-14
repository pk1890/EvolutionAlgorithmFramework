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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StopConditionsTests {

   @Test
   void ElapsedTimeStopConditionTest(){
      Random rnd = new Random();
      int milisEpsilon = 1; // miliseconds of difference between the actual result and expected one
      int maxTime = 5; // in seconds
      int noOfTests = 5;
      for (int i = 0; i < noOfTests; i++){
         int stopConditionTime = rnd.nextInt(maxTime) + 1;

         List<AbstractStopCondition> stopConditions = new ArrayList<>();
         stopConditions.add(new ElapsedTimeStopCondition(stopConditionTime, TimeUnits.SECONDS));

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

         long startTime = System.currentTimeMillis();
         Population<DoubleGene> result = sampleAlgorithm.run();
         Assertions.assertEquals(startTime + stopConditionTime*1000, System.currentTimeMillis(), milisEpsilon);
      }


   }
}
