package genetics.factories;

import genetics.genes.DoubleGene;

import java.util.Random;

public class DoubleUniformGeneFactory extends GeneFactory<DoubleGene>{
    private Random random;
    private double min, max;
    public DoubleUniformGeneFactory(double min, double max){
        random = new Random();
    }

    @Override
    public DoubleGene generate() {
        return new DoubleGene(min + (max - min) * random.nextDouble(), min, max);
    }
}
