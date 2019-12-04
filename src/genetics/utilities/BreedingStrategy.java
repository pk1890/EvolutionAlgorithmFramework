package genetics.utilities;

import genetics.genes.Gene;
import genetics.genes.Genotype;
import genetics.genes.Population;
import javafx.util.Pair;

public abstract class BreedingStrategy<G extends Gene> {

    public abstract Pair<Genotype<G>, Genotype<G>> choose(Population<G> population);

}
