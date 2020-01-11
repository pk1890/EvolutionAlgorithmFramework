package genetics.genes;

import java.util.ArrayList;
import java.util.List;

public class Genotype<G extends Gene>{
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
    public int dimensions() {return genes.size();}

    @Override
    public String toString() {
        return "Genotype{" +
                "genes=" + genes +
                ", fitness=" + fitness +
                '}';
    }

    public List<G> getGenesCopy(){
        List<G> genesCopy = new ArrayList<>();
        for(G g : genes){
            try {
                genesCopy.add((G) g.getClone());
            } catch (CloneNotSupportedException e) {
                //should never happen
                System.out.println("ERROR WITH CLONING GENE OCCURED, MAKE SURE TO IMPLEMENT CLONE ON YOUR GENE");
                System.exit(1);
            }
        }
        return genesCopy;
    }
}
