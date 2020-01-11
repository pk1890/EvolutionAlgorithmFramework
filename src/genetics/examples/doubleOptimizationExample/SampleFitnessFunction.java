package genetics.examples.doubleOptimizationExample;

import genetics.genes.DoubleGene;
import genetics.genes.Genotype;

import java.util.function.Function;

public class SampleFitnessFunction implements Function<Genotype<DoubleGene>, Double> {

    double[] coefficients;
    int coeffSize;

    public SampleFitnessFunction(double[] coefficients, int coeffSize){
        this.coefficients = coefficients;
        this.coeffSize = coeffSize;
    }

    @Override
    public Double apply(Genotype<DoubleGene> geneGenotype) {
        //f(x1, x2, ..., xn) = sum(xi^2)
//        return geneGenotype.getGenes().stream().map((x) -> -Math.pow(x.getValue()-400, 2)).reduce(0.0, Double::sum);
        double sum = 0.0;
        for(int i = 0; i < geneGenotype.getGenes().size(); i++){
            double coef = i < coeffSize? coefficients[i] : 0;
            sum += -Math.pow(geneGenotype.getGenes().get(i).getValue()-coef, 2);
        }

        return sum;
    }
}
