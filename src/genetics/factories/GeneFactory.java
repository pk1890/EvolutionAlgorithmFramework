package genetics.factories;

import genetics.genes.Gene;

public abstract class GeneFactory<G extends Gene> {
    public abstract G generate();
}
