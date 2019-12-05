package genetics.stopConditions;

public class EpochNumberStopCondition extends AbstractStopCondition {
    private int number, currentEpoch;
    public EpochNumberStopCondition(int number){
        currentEpoch = 0;
        this.number = number;
    }

    public void update(){
        currentEpoch++;
    }

    @Override
    public boolean shouldContinue(){
        return currentEpoch < number;
    }
}
