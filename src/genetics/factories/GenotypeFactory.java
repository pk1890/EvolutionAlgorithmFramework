package genetics.factories;

import genetics.genes.Gene;
import genetics.genes.Genotype;

import java.util.List;

public abstract class GenotypeFactory<G extends Gene> {
    private List<GeneFactory<G>> geneFactoryList;

    public abstract Genotype<G> generate();
}
