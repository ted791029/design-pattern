package com.ted.app.geneticAlgorithm.selectionStrategy;

import com.ted.app.geneticAlgorithm.Individual;
import com.ted.app.geneticAlgorithm.Population;
import com.ted.app.geneticAlgorithm.SelectionStrategy;

import java.util.Comparator;
import java.util.List;

public class Tournament implements SelectionStrategy {

    private int tournamentSize = 2;

    public Tournament() {

    }

    public Tournament(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public Population selection(Population population) {
        Population parents = new Population();

        for (int i = 0; i < population.size(); i++) {
            List<Individual> individuals = population.getIndividuals(tournamentSize);
            Individual bestIndividual = individuals.stream()
                    .max(Comparator.comparing(Individual::fitness))
                    .orElse(null);
            parents.add(bestIndividual);
        }

        return parents;
    }
}
