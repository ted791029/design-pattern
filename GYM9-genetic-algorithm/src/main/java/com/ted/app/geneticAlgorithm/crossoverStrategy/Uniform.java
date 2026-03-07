package com.ted.app.geneticAlgorithm.crossoverStrategy;

import com.ted.app.geneticAlgorithm.*;
import com.ted.app.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class Uniform implements CrossoverStrategy {

    @Override
    public Population crossover(Individual parent1, Individual parent2, IndividualFactory individualFactory) {
        Population population = new Population();
        List<Genes> chromosome1 = new ArrayList<>();
        List<Genes> chromosome2 = new ArrayList<>();

        for (int i = 0; i < parent1.size(); i++) {
            Genes genes1;
            Genes genes2;
            //染色體隨機取父或母的基因
            if (RandomUtil.nextInt(2) < 1) {
                genes1 = parent1.get(i);
                genes2 = parent2.get(i);
            } else {
                genes1 = parent2.get(i);
                genes2 = parent1.get(i);
            }
            chromosome1.add(genes1);
            chromosome2.add(genes2);
        }

        Individual child1 = individualFactory.create(chromosome1);
        Individual child2 = individualFactory.create(chromosome2);
        population.add(child1);
        population.add(child2);
        return population;
    }
}
