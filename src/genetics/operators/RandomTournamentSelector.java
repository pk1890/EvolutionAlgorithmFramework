package genetics.operators;

import genetics.genes.Gene;
import genetics.genes.Genotype;
import genetics.genes.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTournamentSelector<G extends Gene> extends Selector<G>{
    private int tournamentSize;
    private double selectionRatio;
    private Random random = new Random();

    @Override
    public Population<G> select(Population<G> population) {
        int desiredPopulationSize = (int)(population.getIndividuals().size()/selectionRatio);
        Population<G> selectedPopulation = new Population<G>();
        while(selectedPopulation.getIndividuals().size() != desiredPopulationSize){
            selectedPopulation.getIndividuals().add(playTournament(population));
        }
        return selectedPopulation;
    }

    public RandomTournamentSelector(int tournamentSize, double selectionRatio){
        this.tournamentSize = tournamentSize;
        this.selectionRatio = selectionRatio;
    }

    private Genotype<G> playTournament(Population<G> population){
        List<Genotype<G>> candidates = population.getIndividuals();
        List<Genotype<G>> fighters = new ArrayList<>();
        for(int i = 0; i < tournamentSize; i++){
            fighters.add(candidates.get(random.nextInt(candidates.size())));
        }
        Genotype<G> bestFighter = fighters.get(0);
        for(Genotype<G> g : fighters){
            if(g.getFitness() > bestFighter.getFitness()){
                bestFighter = g;
            }
        }
        return bestFighter;
    }
}
