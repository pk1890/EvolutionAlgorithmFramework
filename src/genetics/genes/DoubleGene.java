package genetics.genes;

public class DoubleGene extends Gene {
    private double value;
    private double min;
    private double max;

    public DoubleGene(double value, double min, double max){
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DoubleGene{" +
                "value=" + value +
                '}';
    }
    @Override
    public Gene getClone() throws CloneNotSupportedException {
        return (DoubleGene)super.clone();
    }

}
