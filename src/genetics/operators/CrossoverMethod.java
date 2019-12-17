package genetics.operators;

import genetics.genes.Gene;
import genetics.genes.Genotype;
import genetics.genes.Population;
import genetics.utilities.BreedingStrategy;
import genetics.utilities.Pair;

public abstract class CrossoverMethod<G extends Gene> extends Operator<G>{
    public abstract Population<G> crossbreedPair(Pair<Genotype<G>, Genotype<G>> parents);
    public Population<G> crossbreed(BreedingStrategy<G> bs, Population<G> population){
        Population<G> pop = new Population<>();
        for(int i =0; i < population.getIndividuals().size(); i++){
            pop.concatenate(crossbreedPair(bs.choose(population)));
        }
        return population;
    }
}
