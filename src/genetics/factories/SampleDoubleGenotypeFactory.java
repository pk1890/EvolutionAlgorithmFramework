package genetics.factories;

import genetics.genes.DoubleGene;
import genetics.genes.Genotype;
import genetics.genes.Population;
import genetics.utilities.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SampleDoubleGenotypeFactory extends GenotypeFactory<DoubleGene> {

    List<DoubleUniformGeneFactory> factories = new ArrayList<>();

    public SampleDoubleGenotypeFactory(List<Pair<Double, Double>> ranges){
        for(Pair<Double, Double> range : ranges){
            factories.add(new DoubleUniformGeneFactory(range.getFirst(), range.getSecond()));
        }
    }
    public SampleDoubleGenotypeFactory(Pair<Double, Double> range, int dimensions){
        for(int i = 0; i < dimensions; i++){
            factories.add(new DoubleUniformGeneFactory(range.getFirst(), range.getSecond()));
        }
    }


    @Override
    public Genotype<DoubleGene> generate() {
        Genotype<DoubleGene> genotype = new Genotype<>();
        for(DoubleUniformGeneFactory f : factories){
            genotype.getGenes().add(f.generate());
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
