package genetics.operators;

import genetics.genes.Gene;
import genetics.genes.Genotype;

public abstract class Mutation<G extends Gene> extends Operator<G>{
    public abstract Genotype<G> mutate(Genotype<G> genotype);
}
