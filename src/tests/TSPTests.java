import genetics.examples.TSPExample.TSPCrossoverMethod;
import genetics.examples.TSPExample.TSPFitnessFunction;
import genetics.factories.TSPGeneFactory;
import genetics.factories.TSPGenotypeFactory;
import genetics.examples.TSPExample.TSPMutation;
import genetics.genes.Genotype;
import genetics.genes.Population;
import genetics.genes.StringGene;
import genetics.utilities.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TSPTests {
    private static List<String> cities;
    private static TSPGenotypeFactory factory;

    @BeforeAll
    static void setCities(){
        cities = new ArrayList<>();
        cities.add("Kraków");
        cities.add("Warszawa");
        cities.add("Ciechocinek");
        cities.add("Gdańsk");
        cities.add("Zakopane");
        factory = new TSPGenotypeFactory(cities);
    }

    @Test
    public void genotypeFactoryTest(){
        Population<StringGene> population = factory.generateMany(30);

        for(Genotype<StringGene> g : population){
           Assertions.assertEquals(cities.size(), g.getGenes().size(), "Invalid number of cities in a genotype!");
           for(String city : cities){
               Assertions.assertTrue(g.getGenes().toString().contains(city), "Genotype doesnt contain every city!");
           }
        }
    }

    @Test
    public void crossoverMethodTest(){
        Population<StringGene> population = factory.generateMany(2);

        TSPCrossoverMethod crossoverMethod = new TSPCrossoverMethod();
        Pair<Genotype<StringGene>, Genotype<StringGene>> parents = new Pair<>(population.getIndividuals().get(0), population.getIndividuals().get(1));
        population.concatenate(crossoverMethod.crossbreedPair(parents));

        Assertions.assertTrue(population.size() > 2, "Population didnt grow!");
    }

    @Test
    public void mutationTest(){
        Population<StringGene> population = factory.generateMany(1);

        TSPMutation mutation = new TSPMutation();
        Genotype<StringGene> genotype = population.getRandomIndividual();
        List<StringGene> startingGenes = genotype.getGenesCopy();
        mutation.mutate(genotype);

        boolean changedGene = false;
        for(int i = 0; i < cities.size(); i++){
            if(startingGenes.get(i) != genotype.getGenes().get(i)){
                changedGene = true;
                break;
            }
        }
        Assertions.assertTrue(changedGene, "Genes didnt mutate in any way!");
    }

    @Test
    public void fitnessFunctionTest(){
        List<StringGene> genes = new ArrayList<>();
        for(String c: cities){
            genes.add(new StringGene(c));
        }
        Genotype<StringGene> genotype = new Genotype<>(genes);

        Map<String, Map<String, Integer>> distances = new LinkedHashMap<>();

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

        TSPFitnessFunction fitnessFunction = new TSPFitnessFunction(distances);
        double result = fitnessFunction.apply(genotype);

        int sum = 0;
        for(int i = 0; i < genes.size()-1; i++){
            sum += distances.get(genes.get(i).getValue()).get(genes.get(i+1).getValue());
        }
        sum += distances.get(genes.get(genes.size()-1).getValue()).get(genes.get(0).getValue());

        Assertions.assertEquals(-sum, result, "Fitness function didnt calculate properly!");
    }

    @Test
    void testTSPGeneFactoryUniqueness(){
        TSPGeneFactory geneFactory = new TSPGeneFactory(cities);
        for(String city : cities){
            Assertions.assertNotEquals(null, geneFactory.generate());
        }
        Assertions.assertNull(geneFactory.generate());
        geneFactory.reset();
        Assertions.assertNotEquals(null, geneFactory.generate());
    }
}
