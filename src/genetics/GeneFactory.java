package genetics;

public abstract class GeneFactory<G extends Gene> {
    public abstract G generate();
}
