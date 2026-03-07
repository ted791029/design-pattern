package com.ted.app.geneticAlgorithm;

public interface CrossoverStrategy {

    public Population crossover(Individual parent1, Individual parent2, IndividualFactory individualFactory);
}
