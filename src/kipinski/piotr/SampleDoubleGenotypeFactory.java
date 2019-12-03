package kipinski.piotr;

import javafx.util.Pair;

import java.util.List;

public class SampleDoubleGenotypeFactory extends GenotypeFactory<DoubleGene> {
    private List<Pair<Double, Double>> ranges;

    public SampleDoubleGenotypeFactory(List<Pair<Double, Double>> ranges){
        this.ranges = ranges;
    }


    @Override
    public Genotype<DoubleGene> generate() {
        Genotype<DoubleGene> genotype = new Genotype<>();
        for(Pair<Double, Double> range : ranges){
            //genotype.
        }
    }
}
