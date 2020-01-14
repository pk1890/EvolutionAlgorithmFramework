package genetics.factories;

import genetics.genes.Genotype;
import genetics.genes.Population;
import genetics.genes.StringGene;

import java.util.List;

public class TSPGenotypeFactory extends GenotypeFactory<StringGene> {

    private List<String> cities;
    private TSPGeneFactory geneFactory;

    public TSPGenotypeFactory(List<String> cities){
        this.cities = cities;
        geneFactory = new TSPGeneFactory(cities);
    }

    @Override
    public Genotype<StringGene> generate() {
        Genotype<StringGene> genotype = new Genotype<>();
        geneFactory.reset();
        for(int i = 0; i < cities.size(); i++){
            genotype.getGenes().add(geneFactory.generate());
        }
        return genotype;
    }

    public Population<StringGene> generateMany(int n) {
        Population<StringGene> population = new Population<>();
        for(int i = 0; i < n; i++){
            population.getIndividuals().add(generate());
        }
        return population;
    }
}
