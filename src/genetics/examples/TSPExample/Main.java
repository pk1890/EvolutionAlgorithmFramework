package genetics.examples.TSPExample;

import genetics.Algorithm;
import genetics.examples.doubleOptimizationExample.SampleBreedingStrategy;
import genetics.factories.TSPGenotypeFactory;
import genetics.genes.StringGene;
import genetics.genes.Population;
import genetics.operators.*;
import genetics.stopConditions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) {

        List<AbstractStopCondition> stopConditions = new ArrayList<>();
        stopConditions.add(new ElapsedTimeStopCondition(10, TimeUnits.SECONDS));
        stopConditions.add(new PlateauStopCondition(10, 1000));
        stopConditions.add(new EpochNumberStopCondition(10));

        List<Operator<StringGene>> operators = new ArrayList<>();
        operators.add(new TSPCrossoverMethod());
        operators.add(new TSPMutation());

        List<String> cities = new ArrayList<>();
        cities.add("Kraków");
        cities.add("Warszawa");
        cities.add("Ciechocinek");
        cities.add("Gdańsk");
        cities.add("Zakopane");

        Map<String, Map<String, Integer>> distances = new HashMap<>();

        Map<String, Integer> distancesKrk = new HashMap<>();
        Map<String, Integer> distancesWaw = new HashMap<>();
        Map<String, Integer> distancesCi = new HashMap<>();
        Map<String, Integer> distancesGd = new HashMap<>();
        Map<String, Integer> distancesZa = new HashMap<>();

        distancesKrk.put("Warszawa", 20);
        distancesKrk.put("Ciechocinek", 1);
        distancesKrk.put("Gdańsk", 5);
        distancesKrk.put("Zakopane", 5);


        distancesWaw.put("Kraków", 20);
        distancesWaw.put("Ciechocinek", 5);
        distancesWaw.put("Gdańsk", 10);
        distancesWaw.put("Zakopane", 5);


        distancesCi.put("Kraków", 1);
        distancesCi.put("Warszawa", 5);
        distancesCi.put("Gdańsk", 2);
        distancesCi.put("Zakopane", 5);


        distancesGd.put("Kraków", 5);
        distancesGd.put("Warszawa", 10);
        distancesGd.put("Ciechocinek", 2);
        distancesGd.put("Zakopane", 30);


        distancesZa.put("Kraków", 5);
        distancesZa.put("Warszawa", 5);
        distancesZa.put("Ciechocinek", 5);
        distancesZa.put("Gdańsk", 30);


        distances.put("Kraków", distancesKrk);
        distances.put("Warszawa", distancesWaw);
        distances.put("Ciechocinek", distancesCi);
        distances.put("Gdańsk", distancesGd);
        distances.put("Zakopane", distancesZa);

        Algorithm<StringGene> sampleAlgorithm = new Algorithm.Builder<StringGene>()
                                                    .populationSize(2)
                                                    .fitnessFunction(new TSPFitnessFunction(distances))
                                                    .genotypeFactory(new TSPGenotypeFactory(cities))
                                                    .stopConditions(stopConditions)
                                                    .selector(new RandomTournamentSelector(10,2))
                                                    .operators(operators)
                                                    .breedingStrategy(new SampleBreedingStrategy())
                                                    .build();

        Population<StringGene> result = sampleAlgorithm.run();
        System.out.println(result.getBestIndividual().toString());
    }
}
