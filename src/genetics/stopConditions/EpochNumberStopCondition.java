package genetics.stopConditions;

public class EpochNumberStopCondition extends CountingBasedStopCondition {
    private int number, currentEpoch;
    public EpochNumberStopCondition(int number){
        this.number = number;
    }

    @Override
    public void reset() {
        currentEpoch = 0;
    }

    @Override
    public void update(){
        currentEpoch++;
    }

    @Override
    public boolean shouldContinue(){
        return currentEpoch < number;
    }

    public int getCurrentEpoch(){
        return this.currentEpoch;
    }
}
