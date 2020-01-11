package genetics.examples.TSPExample;

import genetics.genes.DoubleGene;
import genetics.genes.Genotype;
import genetics.genes.Population;
import genetics.genes.StringGene;
import genetics.operators.CrossoverMethod;
import genetics.utilities.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TSPCrossoverMethod extends CrossoverMethod<StringGene> {
    @Override
    public Population<StringGene> crossbreedPair(Pair<Genotype<StringGene>, Genotype<StringGene>> parents) {

        List<StringGene> firstParentGeneCopy = parents.getFirst().getGenesCopy();
        List<StringGene> secondParentGeneCopy = parents.getSecond().getGenesCopy();

        Random rnd = new Random();
        int cut1 = rnd.nextInt(firstParentGeneCopy.size()/2);
        int cut2 = rnd.nextInt(firstParentGeneCopy.size()/2)+cut1;

        //first child
        for(int i = cut1; i < cut2; i++){
            int index = firstParentGeneCopy.indexOf(secondParentGeneCopy.get(i));
            firstParentGeneCopy.set(i, secondParentGeneCopy.get(i));
            firstParentGeneCopy.set(index, firstParentGeneCopy.get(i));
            firstParentGeneCopy.set(i, secondParentGeneCopy.get(i));
        }
        Genotype<StringGene> child = new Genotype<>(firstParentGeneCopy);
        Population<StringGene> children = new Population<>();
        children.addIndividual(child);
        return children;
    }

    void swap(List<StringGene> list, int index1, int index2){
        StringGene tmp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, tmp);
    }
}
