import genetics.examples.TSPExample.TSPGenotypeFactory;
import genetics.genes.Genotype;
import genetics.genes.Population;
import genetics.genes.StringGene;
//import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;


public class TSPTests {
//    @Test
    public void genotypeFactoryTest(){
        List<String> cities = new ArrayList<>();
        cities.add("Kraków");
        cities.add("Warszawa");
        cities.add("Ciechocinek");
        cities.add("Gdańsk");
        cities.add("Zakopane");

        TSPGenotypeFactory factory = new TSPGenotypeFactory(cities);
        Population<StringGene> population = factory.generateMany(30);
        for(Genotype<StringGene> g : population.getIndividuals()){
//           assertEquals(g.getGenes().size(), cities.size());
           for(String city : cities){
//               assertTrue(g.getGenes().contains(city));
           }
        }
    }
}
