package genetics.examples.TSPExample;

import genetics.genes.Genotype;
import genetics.genes.StringGene;
import genetics.utilities.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TSPFitnessFunction implements Function<Genotype<StringGene>, Double> {

    public Map<String, Map<String, Integer>> distances;

    public TSPFitnessFunction(Map<String, Map<String, Integer>> distances){
        this.distances = distances;
    }


    @Override
    public Double apply(Genotype<StringGene> stringGeneGenotype) {
        int sum = 0;
        List<StringGene> genes = stringGeneGenotype.getGenes();
        for(int i = 0; i < genes.size()-1; i++){
            sum += distances.get(genes.get(i).getValue()).get(genes.get(i+1).getValue());
        }
        sum += distances.get(genes.get(genes.size()-1).getValue()).get(genes.get(0).getValue());
        return (double) -sum;
    }
}
