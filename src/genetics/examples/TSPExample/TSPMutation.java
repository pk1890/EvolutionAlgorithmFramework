package genetics.examples.TSPExample;

import genetics.genes.Genotype;
import genetics.genes.StringGene;
import genetics.operators.Mutation;

import java.util.Random;

public class TSPMutation extends Mutation<StringGene> {
    @Override
    public void mutate(Genotype<StringGene> genotype) {
        StringGene tmp;
        Random rnd = new Random();
        int index = rnd.nextInt(genotype.getGenes().size()-1);
        tmp = genotype.getGenes().get(index);
        genotype.getGenes().set(index, genotype.getGenes().get(index+1));
        genotype.getGenes().set(index+1, tmp);
    }
}
