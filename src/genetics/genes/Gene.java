package genetics.genes;

public abstract class Gene implements Cloneable{
    public abstract Gene getClone() throws CloneNotSupportedException;
}
