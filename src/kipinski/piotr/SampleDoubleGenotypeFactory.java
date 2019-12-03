package kipinski.piotr;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SampleDoubleGenotypeFactory extends GenotypeFactory<DoubleGene> {
    private List<Pair<Double, Double>> ranges;
    List<DoubleUniformGeneFactory> factories = new ArrayList<>();
    public SampleDoubleGenotypeFactory(List<Pair<Double, Double>> ranges){
        this.ranges = ranges;
        for(Pair<Double, Double> range : ranges){
            factories.add(new DoubleUniformGeneFactory(range.getKey(), range.getValue()));
        }
    }


    @Override
    public Genotype<DoubleGene> generate() {
        Genotype<DoubleGene> genotype = new Genotype<>();
        int i = 0;
        for(Pair<Double, Double> range : ranges){
            genotype.getGenes().add(factories.get(i++).generate());
        }
        return genotype;
    }

    public Population<DoubleGene> generateMany(int n) {
        Population<DoubleGene> population = new Population<>();
        for(int i = 0; i < n; i++){
            population.getIndividuals().add(generate());
        }
        return population;
    }
}
