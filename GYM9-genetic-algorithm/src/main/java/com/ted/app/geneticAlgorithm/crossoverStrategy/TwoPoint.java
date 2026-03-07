package com.ted.app.geneticAlgorithm.crossoverStrategy;

import com.ted.app.geneticAlgorithm.*;

import java.util.ArrayList;
import java.util.List;

public class TwoPoint implements CrossoverStrategy {
    @Override
    public Population crossover(Individual parent1, Individual parent2, IndividualFactory individualFactory) {
        Population population = new Population();
        Individual minLengthIndividual = parent1.size() >= parent2.size() ? parent2 : parent1;
        int point1 = minLengthIndividual.randomGenesIndex();
        int point2 = minLengthIndividual.randomGenesOtherIndex(point1);

        int start = Math.min(point1, point2);
        int end = Math.max(point1, point2);

        List<Genes> chromosome1 = new ArrayList<>();
        chromosome1.addAll(parent1.getGenes(0, start));
        chromosome1.addAll(parent2.getGenes(start, end + 1));
        chromosome1.addAll(parent1.getGenes(end + 1, parent1.size()));
        Individual child1 = individualFactory.create(chromosome1);

        List<Genes> chromosome2 = new ArrayList<>();
        chromosome2.addAll(parent2.getGenes(0, start));
        chromosome2.addAll(parent1.getGenes(start, end + 1));
        chromosome2.addAll(parent2.getGenes(end + 1, parent2.size()));
        Individual child2 = individualFactory.create(chromosome2);

        population.add(child1);
        population.add(child2);
        return population;
    }
}
