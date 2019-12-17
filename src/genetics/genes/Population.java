package genetics.genes;

import java.util.*;
import java.util.stream.Collectors;

public class Population <G extends Gene> implements Iterable<Genotype<G>>{
    private List<Genotype<G>> individuals;

    public List<Genotype<G>> getIndividuals() {
        return individuals;
    }

    public void addIndividual(Genotype<G> individual)
    {
        individuals.add(individual);
    }

    @Override
    public Iterator<Genotype<G>> iterator() {
        return individuals.iterator();
    }

    public Population(int size){
        List<Genotype<G>> individuals = new ArrayList<>();
        for(int i = 0; i < size; i++){
            individuals.add(new Genotype<G>());
        }
    }

    public Genotype<G> getBestIndividual(){
        individuals.sort(Comparator.comparingDouble(Genotype::getFitness));
        return individuals.get(individuals.size()-1);
    }


    public List<Double> getFitnesses(){
        return individuals.stream().map(Genotype::getFitness).collect(Collectors.toList());
    }

    public Population(){
        List<Genotype<G>> individuals = new ArrayList<>();
    }

    public void setIndividuals(List<Genotype<G>> individuals) {
        this.individuals = individuals;
    }
}
