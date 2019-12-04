package genetics.operators;

import genetics.genes.Gene;
import genetics.genes.Population;

public abstract class Selector <G extends Gene>{
    public abstract Population<G> select(Population<G> population);
}
