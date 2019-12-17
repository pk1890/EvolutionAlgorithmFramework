package genetics.examples.doubleOptimizationExample;

import genetics.genes.DoubleGene;
import genetics.genes.Genotype;

import java.util.function.Function;

public class SampleFitnessFunction implements Function<Genotype<DoubleGene>, Double> {
    @Override
    public Double apply(Genotype<DoubleGene> geneGenotype) {
        //f(x1, x2, ..., xn) = sum(xi^2)
        return geneGenotype.getGenes().stream().map((x) -> Math.pow(x.getValue(), 2)).reduce(0.0, Double::sum);
    }
}
