import genetics.Algorithm;
import genetics.examples.doubleOptimizationExample.SampleBreedingStrategy;
import genetics.examples.doubleOptimizationExample.SampleCrossoverMethod;
import genetics.examples.doubleOptimizationExample.SampleFitnessFunction;
import genetics.examples.doubleOptimizationExample.SampleMutation;
import genetics.factories.SampleDoubleGenotypeFactory;
import genetics.genes.DoubleGene;
import genetics.operators.Operator;
import genetics.operators.RandomTournamentSelector;
import genetics.stopConditions.*;
import genetics.utilities.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class StopConditionsTests {
   private Random rnd = new Random();

   private Algorithm.Builder<DoubleGene> createSampleAlgorithmBuilder(){
      List<Operator<DoubleGene>> operators = new ArrayList<>();
      operators.add(new SampleCrossoverMethod());
      operators.add(new SampleMutation());

      double [] coefficients = {rnd.nextDouble()*100-50, rnd.nextDouble()*100-50, 0,  rnd.nextDouble()*100-50};

      Algorithm.Builder<DoubleGene> sampleAlgorithm = new Algorithm.Builder<DoubleGene>()
              .populationSize(100)
              .fitnessFunction(new SampleFitnessFunction(coefficients, 4))
              .genotypeFactory(new SampleDoubleGenotypeFactory(
                      new Pair<>(-100.0, 100.0),
                      5))
              .selector(new RandomTournamentSelector(10,3))
              .operators(operators)
              .breedingStrategy(new SampleBreedingStrategy());
      return sampleAlgorithm;
   }

   @Test
   void ElapsedTimeStopConditionTest(){
      int milisEpsilon = 1; // miliseconds of difference between the actual result and expected one
      int minTime = 1; // in seconds
      int maxTime = 3;
      int noOfTests = 1;
      for (int i = 0; i < noOfTests; i++){
         int stopConditionTime = rnd.nextInt(maxTime - minTime + 1) + minTime;

         List<AbstractStopCondition> stopConditions = new ArrayList<>();
         stopConditions.add(new ElapsedTimeStopCondition(stopConditionTime, TimeUnits.SECONDS));

         Algorithm<DoubleGene> algorithm = createSampleAlgorithmBuilder().stopConditions(stopConditions).build();

         long startTime = System.currentTimeMillis();
         algorithm.run();

         Assertions.assertEquals(startTime + stopConditionTime*1000, System.currentTimeMillis(), milisEpsilon, "Elapsed time didnt match expected time (within epsilon)!");
      }

      for (int i = 0; i < noOfTests; i++){
         int stopConditionTime = rnd.nextInt(maxTime - minTime + 1) + minTime;

         List<AbstractStopCondition> stopConditions = new ArrayList<>();
         stopConditions.add(new ElapsedTimeStopCondition(stopConditionTime, TimeUnits.MILISECONDS));

         Algorithm<DoubleGene> algorithm = createSampleAlgorithmBuilder().stopConditions(stopConditions).build();

         long startTime = System.currentTimeMillis();
         algorithm.run();

         Assertions.assertEquals(startTime + stopConditionTime, System.currentTimeMillis(), milisEpsilon, "Elapsed time didnt match expected time (within epsilon)!");
      }
   }

   @Test
   void EpochNumberStopConditionTest(){
      int minEpochs = 1000;
      int maxEpochs = 1000;
      int noOfTests = 3;
      for (int i = 0; i < noOfTests; i++){
         int stopConditionEpochs = rnd.nextInt(maxEpochs - minEpochs + 1) + minEpochs;

         List<AbstractStopCondition> stopConditions = new ArrayList<>();
         EpochNumberStopCondition stopCondition = new EpochNumberStopCondition(stopConditionEpochs);
         stopConditions.add(stopCondition);

         Algorithm<DoubleGene> algorithm = createSampleAlgorithmBuilder().stopConditions(stopConditions).build();

         algorithm.run();

         Assertions.assertEquals(stopConditionEpochs, stopCondition.getCurrentEpoch(), "Epoch number didnt match expected value!");
      }
   }
   @Test
   void EvaluationsCountStopConditionTest(){
      int minEvaluations = 1000;
      int maxEvaluations = 10000;
      int noOfTests = 3;
      for (int i = 0; i < noOfTests; i++){
         int stopConditionEvaluations = rnd.nextInt(maxEvaluations - minEvaluations + 1) + minEvaluations;

         List<AbstractStopCondition> stopConditions = new ArrayList<>();
         EvaluationsCountStopCondition stopCondition = new EvaluationsCountStopCondition(stopConditionEvaluations);
         stopConditions.add(stopCondition);

         Algorithm<DoubleGene> algorithm = createSampleAlgorithmBuilder().stopConditions(stopConditions).build();

         algorithm.run();

         Assertions.assertEquals(stopConditionEvaluations, stopCondition.getNumber(), "Number of evaluations didnt match expected value!");
      }
   }
   @Test
   void PlateauStopConditionTest(){
      int minPlateau = 1;
      int maxPlateau = 10;
      int noOfEpochs = 100;
      int noOfTests = 3;
      for (int i = 0; i < noOfTests; i++){
         int plateauValue = rnd.nextInt(maxPlateau - minPlateau + 1) + minPlateau;

         List<AbstractStopCondition> stopConditions = new ArrayList<>();
         PlateauStopCondition stopCondition = new PlateauStopCondition(noOfEpochs, plateauValue);
         stopConditions.add(stopCondition);

         Algorithm<DoubleGene> algorithm = createSampleAlgorithmBuilder().stopConditions(stopConditions).build();

         algorithm.run();

         LinkedList<Double> bestFitnesses = stopCondition.getBestFitnesses();
         Assertions.assertEquals(noOfEpochs, stopCondition.getFilledFields(), "Wrong number of epochs!");
         Assertions.assertTrue((Math.abs(bestFitnesses.getFirst() - bestFitnesses.getLast()) < plateauValue), "Fitness values didnt reach plateau!");
      }
   }
}
