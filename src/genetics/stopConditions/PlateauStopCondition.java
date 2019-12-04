package genetics.stopConditions;

import genetics.genes.Genotype;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlateauStopCondition extends AbstractStopCondition {

    private final int epochNumber;
    private final double delta;
    public LinkedList<Double> bestFitnesses;
    private int filledFields = 0;

    public PlateauStopCondition(int epochNumber, double delta){
        this.delta = delta;
        this.epochNumber = epochNumber;
        this.bestFitnesses = new LinkedList<>();
    }

    public void pushBest(Genotype bestGenotype){
        if(filledFields < epochNumber) {
            bestFitnesses.addLast(bestGenotype.getFitness());
        } else {
            bestFitnesses.removeFirst();
            bestFitnesses.addLast(bestGenotype.getFitness());
        }
    }

    @Override
    public boolean shouldContinue() {
        return filledFields < epochNumber || (Math.abs(bestFitnesses.getFirst() - bestFitnesses.getLast()) > delta);
    }
}
