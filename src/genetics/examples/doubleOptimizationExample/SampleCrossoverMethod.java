package genetics.examples.doubleOptimizationExample;

import genetics.genes.DoubleGene;
import genetics.genes.Genotype;
import genetics.genes.Population;
import genetics.operators.CrossoverMethod;
import genetics.utilities.Pair;

import java.util.List;
import java.util.Random;

public class SampleCrossoverMethod extends CrossoverMethod<DoubleGene> {

    @Override
    public Population<DoubleGene> crossbreedPair(Pair<Genotype<DoubleGene>, Genotype<DoubleGene>> parents) {
        Population<DoubleGene> children = new Population<>();
        int dimension = parents.getFirst().dimensions();
        int cut = new Random().nextInt(dimension);
        List<DoubleGene> firstChildGenes = parents.getFirst().getGenes();

        firstChildGenes.subList(0, cut).addAll(
                parents.getSecond().getGenes().subList(cut, dimension)
        );

        List<DoubleGene> secondChildGenes = parents.getSecond().getGenes();

        secondChildGenes.subList(0, cut).addAll(
                parents.getFirst().getGenes().subList(cut, dimension)
        );

        children.addIndividual(new Genotype<DoubleGene>(firstChildGenes));
        children.addIndividual(new Genotype<DoubleGene>(secondChildGenes));

        return children;

    }
}
