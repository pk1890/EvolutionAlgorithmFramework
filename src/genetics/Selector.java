package genetics;

public abstract class Selector <G extends Gene>{
    public abstract Population<G> select(Population<G> population);
}
