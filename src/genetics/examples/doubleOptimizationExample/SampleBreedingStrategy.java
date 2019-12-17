package genetics.examples.doubleOptimizationExample;

import genetics.genes.DoubleGene;
import genetics.genes.Genotype;
import genetics.genes.Population;
import genetics.utilities.BreedingStrategy;
import genetics.utilities.Pair;

import java.util.Random;

public class SampleBreedingStrategy extends BreedingStrategy<DoubleGene> {
    @Override
    public Pair<Genotype<DoubleGene>, Genotype<DoubleGene>> choose(Population<DoubleGene> population) {
        return new Pair<>(population.getRandomIndividual(), population.getRandomIndividual());
    }
}
