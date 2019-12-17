package genetics.stopConditions;

import genetics.stopConditions.AbstractStopCondition;

import java.sql.Timestamp;

public class ElapsedTimeStopCondition extends CountingBasedStopCondition {
    private long miliseconds, startTimestamp, currentTimestamp;
    public ElapsedTimeStopCondition(int value, TimeUnits unit){
        switch (unit){
            case SECONDS:
                miliseconds = value*1000;
                break;
            case MILISECONDS:
                miliseconds = value;
                break;
        }
    }

    @Override
    public void update(){
        this.currentTimestamp = System.nanoTime();
    }

    @Override
    public void reset(){
        startTimestamp = System.nanoTime();
    }

    @Override
    public boolean shouldContinue(){
        return startTimestamp + miliseconds > currentTimestamp;
    }
}
