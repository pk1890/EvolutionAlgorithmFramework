package genetics.genes;

import java.util.ArrayList;
import java.util.List;

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
