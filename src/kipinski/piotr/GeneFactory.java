package kipinski.piotr;

public abstract class GeneFactory<G extends Gene> {
    public abstract G generate();
}
