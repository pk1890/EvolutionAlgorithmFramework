package genetics.examples.doubleOptimizationExample;

import genetics.genes.DoubleGene;
import genetics.genes.Genotype;
import genetics.operators.Mutation;

import java.util.Random;

public class SampleMutation extends Mutation<DoubleGene> {
    @Override
    public void mutate(Genotype<DoubleGene> genotype) {
        DoubleGene modfiedGene = genotype.getGenes().get(new Random().nextInt(genotype.dimensions()));
        modfiedGene.setValue(modfiedGene.getValue() + new Random().nextDouble()-0.5);
    }
}
