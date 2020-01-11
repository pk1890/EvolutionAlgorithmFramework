package genetics.examples.TSPExample;

import genetics.factories.GeneFactory;
import genetics.genes.StringGene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TSPGeneFactory extends GeneFactory<StringGene> {

    public List<String> cities;
    int index;
    public TSPGeneFactory(List<String> cities){
        this.cities = cities;
        reset();
    }

    public void reset(){
        Collections.shuffle(cities);
        index=0;
    }

    @Override
    public StringGene generate() {
        if(index < cities.size()){
            StringGene gene = new StringGene(cities.get(index));
            index++;
            return gene;
        }else {
            System.out.println("TSP GENE FACTORY: Already used all of cities. Null returned.");
            return null;
        }

    }

}
