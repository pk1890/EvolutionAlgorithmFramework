package kipinski.piotr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Population <G extends Gene> implements Iterable<Genotype<G>>{
    private List<Genotype<G>> individuals;

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
}
