package genetics.stopConditions;

public class EvaluationsCountStopCondition extends AbstractStopCondition{
    private int count, number;

    public EvaluationsCountStopCondition(int count){
        this.count = count;
        number = 0;
    }

    public void update(){
        number++;
    }

    public boolean shouldContinue(){
        return number < count;
    }
}
