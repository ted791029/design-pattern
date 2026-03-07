package com.ted.app.geneticAlgorithm.crossoverStrategy;

import com.ted.app.geneticAlgorithm.*;

import java.util.ArrayList;
import java.util.List;

public class SinglePoint implements CrossoverStrategy {
    @Override
    public Population crossover(Individual parent1, Individual parent2, IndividualFactory individualFactory) {
        Population population = new Population();
        Individual minLengthIndividual = parent1.size() >= parent2.size() ? parent2 : parent1;
        int point = minLengthIndividual.randomGenesIndex();
        //到point前，取parent1的基因
        List<Genes> chromosome1 = new ArrayList<>();
        chromosome1.addAll(parent1.getGenes(0, point));
        chromosome1.addAll(parent2.getGenes(point, parent2.size()));
        population.add(individualFactory.create(chromosome1));
        //point後，取parent2的基因
        List<Genes> chromosome2 = new ArrayList<>();
        chromosome2.addAll(parent2.getGenes(0, point));
        chromosome2.addAll(parent1.getGenes(point, parent1.size()));
        population.add(individualFactory.create(chromosome2));

        return population;
    }
}
