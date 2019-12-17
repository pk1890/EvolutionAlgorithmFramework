package genetics.stopConditions;

import genetics.genes.Genotype;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlateauStopCondition extends FitnessBasedStopCondition {

    private final int epochNumber;
    private final double delta;
    public LinkedList<Double> bestFitnesses;
    private int filledFields = 0;

    public PlateauStopCondition(int epochNumber, double delta){
        this.delta = delta;
        this.epochNumber = epochNumber;
        this.bestFitnesses = new LinkedList<>();
    }

    void pushBest(double bestFitness){
        if(filledFields < epochNumber) {
            bestFitnesses.addLast(bestFitness);
        } else {
            bestFitnesses.removeFirst();
            bestFitnesses.addLast(bestFitness);
        }
    }

    @Override
    public void reset() {
        this.bestFitnesses = new LinkedList<>();
        filledFields= 0;
    }

    @Override
    public boolean shouldContinue() {
        return filledFields < epochNumber || (Math.abs(bestFitnesses.getFirst() - bestFitnesses.getLast()) > delta);
    }

    @Override
    public void update(List<Double> fitnesses) {
        fitnesses.sort(Double::compareTo);
        pushBest(fitnesses.get(fitnesses.size()-1));
    }
}
