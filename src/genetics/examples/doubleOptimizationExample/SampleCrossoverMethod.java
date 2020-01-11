package genetics.examples.doubleOptimizationExample;

import genetics.genes.DoubleGene;
import genetics.genes.Genotype;
import genetics.genes.Population;
import genetics.operators.CrossoverMethod;
import genetics.utilities.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleCrossoverMethod extends CrossoverMethod<DoubleGene> {

    @Override
    public Population<DoubleGene> crossbreedPair(Pair<Genotype<DoubleGene>, Genotype<DoubleGene>> parents) {
        Population<DoubleGene> children = new Population<>();
        int dimension = parents.getFirst().dimensions();
        int cut = new Random().nextInt(dimension);

        List<DoubleGene> firstParentGeneCopy = parents.getFirst().getGenesCopy();
        List<DoubleGene> secondParentGeneCopy = parents.getSecond().getGenesCopy();

        List<DoubleGene> firstChildGenes = new ArrayList<>(firstParentGeneCopy.subList(0, cut));

        firstChildGenes.addAll(secondParentGeneCopy.subList(cut, dimension));

        List<DoubleGene> secondChildGenes =new ArrayList<>(secondParentGeneCopy.subList(0, cut));

        secondChildGenes.addAll(firstParentGeneCopy.subList(cut, dimension));

        children.addIndividual(new Genotype<DoubleGene>(firstChildGenes));
        children.addIndividual(new Genotype<DoubleGene>(secondChildGenes));

        return children;

    }
}
