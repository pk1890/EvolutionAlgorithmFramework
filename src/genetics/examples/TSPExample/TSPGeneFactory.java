package genetics.examples.TSPExample;

import genetics.factories.GeneFactory;
import genetics.genes.StringGene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TSPGeneFactory extends GeneFactory<StringGene> {

    public List<String> cities;
    public List<Boolean> isUsed;

    public TSPGeneFactory(List<String> cities){
        this.cities = cities;
        isUsed = new ArrayList<>();
        reset();
    }

    public void reset(){
        for(String s : cities){
            isUsed.add(false);
        }
    }

    @Override
    public StringGene generate() {
        Random rnd = new Random();
        if(isUsed.contains(false)){
            int index = rnd.nextInt(cities.size());
            while (isUsed.get(index)){
                index = rnd.nextInt(cities.size());
                System.out.println("hej");
            }
            isUsed.set(index, true);
            return new StringGene(cities.get(index));
        }else {
            System.out.println("TSP GENE FACTORY: Already used all of cities. Null returned.");
            return null;
        }

    }

}
