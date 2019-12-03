package kipinski.piotr;

public class DoubleGene extends Gene {
    private double value;
    private double min;
    private double max;

    public DoubleGene(double value, double min, double max){
        this.value = value;
        this.min = min;
        this.max = max;
    }
}
