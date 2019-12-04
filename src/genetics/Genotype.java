package genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Genotype<G extends Gene> {
    private List<G> genes;
    private double fitness;

    public List<G> getGenes(){
        return genes;
    }
    public void setGenes(List<G> genes){
        this.genes = genes;
    }
    public double getFitness() {
        return fitness;
    }
    public Genotype(){
        this.genes = new ArrayList<>();
    }

    public Genotype(List<G> genes){
        this.genes = genes;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
