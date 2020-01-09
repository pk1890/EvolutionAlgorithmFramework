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
        //najpierw musimy uciac, a potem dodac, bo tak to dodawalismy do listy 5 elementowej dodatkowe geny i wychodzilo poza dimensions=5
        //TODO ogolnie to trzeba tu  dodac niemutowalne listy genow, bo rodzic i dziecko maja geny z tymi samymi referencjami, co przy mutowaniu zmutuje rodzicowi i dziecku
        //dodalem przynajmniej kopie na glowna referencje listy, bo wczesniej rodzic i dziecko mieli takie same listy genow(referencje)
        List<DoubleGene> firstChildGenes = new ArrayList<>(parents.getFirst().getGenes().subList(0, cut));

        firstChildGenes.addAll(
                parents.getSecond().getGenes().subList(cut, dimension)
        );

        List<DoubleGene> secondChildGenes =new ArrayList<>( parents.getSecond().getGenes().subList(0, cut));

        secondChildGenes.addAll(
                parents.getFirst().getGenes().subList(cut, dimension)
        );

        children.addIndividual(new Genotype<DoubleGene>(firstChildGenes));
        children.addIndividual(new Genotype<DoubleGene>(secondChildGenes));

        return children;

    }
}
