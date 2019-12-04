package genetics.operators;

import genetics.genes.Gene;
import genetics.genes.Genotype;
import genetics.genes.Population;
import javafx.util.Pair;

public abstract class CrossoverMethod<G extends Gene> extends Operator<G>{
    public abstract Population<G> crosbreedGroup(Pair<Genotype<G>, Genotype<G>> parents);
}
