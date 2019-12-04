package genetics;

public abstract class Mutation<G extends Gene> extends Operator<G>{
    public abstract Genotype<G> mutate(Genotype<G> genotype);
}
