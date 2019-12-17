package genetics.stopConditions;

import genetics.genes.Population;

import java.util.List;

public abstract class FitnessBasedStopCondition extends AbstractStopCondition{

    public abstract void update(List<Double> fitnesses);
}
