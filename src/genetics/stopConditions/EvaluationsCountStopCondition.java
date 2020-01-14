package genetics.stopConditions;

public class EvaluationsCountStopCondition extends CountingBasedStopCondition{
    private int count, number;

    public EvaluationsCountStopCondition(int count){
        this.count = count;
    }

    @Override
    public void reset() {
        number = 0;
    }

    @Override
    public void update(){
        number++;
    }

    @Override
    public boolean shouldContinue(){
        return number < count;
    }

    public int getNumber(){
        return this.number;
    }
}
