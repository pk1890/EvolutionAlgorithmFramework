package genetics.genes;

public class StringGene extends Gene {

    private String value;

    public StringGene(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
    public void setValue(String value){
        this.value = value;
    }

    @Override
    public Gene getClone() throws CloneNotSupportedException {
        StringGene gene = (StringGene)super.clone();
        gene.value = new String(this.value);
        return gene;
    }
}
