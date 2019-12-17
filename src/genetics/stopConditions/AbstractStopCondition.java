package genetics.stopConditions;

public abstract class AbstractStopCondition {
    public abstract void reset();
    public abstract boolean shouldContinue();
}
