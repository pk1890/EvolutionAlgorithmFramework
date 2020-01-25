import genetics.factories.SampleDoubleGenotypeFactory;
import genetics.genes.*;
import genetics.utilities.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GenesTests {
    @Test
    void StringGeneTest(){
        Random random = new Random();
        for(int i = 0; i < 100; i++){
            String value = Double.toString(random.nextDouble());
            StringGene stringGene = new StringGene(value);
            Assertions.assertEquals(value, stringGene.getValue());
            StringGene clone = null;
            try {
                clone = (StringGene) stringGene.getClone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            Assertions.assertEquals(clone, stringGene);
            assert clone != null;
            clone.setValue("SPANNISH INQUISITION");
            Assertions.assertEquals(stringGene.toString(), "StringGene{" +
                    "value='" + stringGene.getValue() + '\'' + '}');
            Assertions.assertNotEquals(clone.getValue(), stringGene.getValue());
            Assertions.assertNotEquals(clone, stringGene);
        }
    }

    @Test
    void GenotypeTest(){

    }

    static class testGene extends Gene{

        @Override
        public Gene getClone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }

    @Test
    void GenotypeCopyExceptionTest(){
        Genotype<testGene> genotype = new Genotype<>();
        List<testGene> geneList = new LinkedList<>();
        geneList.add(new testGene());
        genotype.setGenes(geneList);
        Assertions.assertThrows(RuntimeException.class, genotype::getGenesCopy);
    }

    @Test
    void SampleGenotypeFactoryTest(){
        List<Pair<Double, Double>> ranges = new ArrayList<>();
        ranges.add(new Pair<>(0.0, 10.0));
        ranges.add(new Pair<>(0.0, 20.0));

        SampleDoubleGenotypeFactory factory = new SampleDoubleGenotypeFactory(ranges);

        double e = 1e-6;
        Population<DoubleGene> pop = factory.generateMany(10);
        for(Genotype<DoubleGene> g : pop.getIndividuals()){
            Assertions.assertTrue(g.getGenes().get(0).getValue() > 0.0-e && g.getGenes().get(0).getValue() < 10.0+e);
            Assertions.assertTrue(g.getGenes().get(1).getValue() > 0.0-e && g.getGenes().get(1).getValue() < 20.0+e);
        }
    }
}
