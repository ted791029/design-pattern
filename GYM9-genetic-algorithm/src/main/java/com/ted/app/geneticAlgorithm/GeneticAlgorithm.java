package com.ted.app.geneticAlgorithm;

public class GeneticAlgorithm {

    private CrossoverStrategy crossoverStrategy;

    private IndividualFactory individualFactory;

    private MutationStrategy mutationStrategy;

    private SelectionStrategy selectionStrategy;

    private TerminationConditionStrategy terminationConditionStrategy;

    private final int MAX_NUMBER_OF_ITERATIONS;

    private final double MUTATION_RATE;

    public GeneticAlgorithm(CrossoverStrategy crossoverStrategy, IndividualFactory individualFactory, MutationStrategy mutationStrategy, SelectionStrategy selectionStrategy, TerminationConditionStrategy terminationConditionStrategy, int MAX_NUMBER_OF_ITERATIONS, double MUTATION_RATE) {
        this.crossoverStrategy = crossoverStrategy;
        this.individualFactory = individualFactory;
        this.mutationStrategy = mutationStrategy;
        this.selectionStrategy = selectionStrategy;
        this.terminationConditionStrategy = terminationConditionStrategy;
        this.MAX_NUMBER_OF_ITERATIONS = MAX_NUMBER_OF_ITERATIONS;
        this.MUTATION_RATE = MUTATION_RATE;
    }

    public Individual run(Population population) {
        Population currentPopulation = population;

        for (int i = 0; i < MAX_NUMBER_OF_ITERATIONS; i++) {
            Population parents = selection(currentPopulation);
            Population offspring = crossover(parents);
            Population newPopulation = mutation(offspring);
            currentPopulation = newPopulation;

            if (terminationConditionStrategy.condition()) {
                break;
            }
        }

        return findBestIndividual(currentPopulation);
    }

    private Population crossover(Population parents) {
        Population offspring = new Population();

        for (int i = 0; i < parents.size(); i += 2) {
            if (i + 1 < parents.size()) {
                Individual parent1 = parents.get(i);
                Individual parent2 = parents.get(i + 1);
                offspring.addAll(crossoverStrategy.crossover(parent1, parent2, individualFactory));
            } else {
                offspring.add(parents.get(i));
            }
        }

        return offspring;
    }

    private Individual findBestIndividual(Population population) {
        return population.findBest();
    }

    private Population mutation(Population offspring) {
        Population newPopulation = new Population();

        for (int i = 0; i < offspring.size(); i++) {
            Individual individual = offspring.get(i);

            if (Math.random() < MUTATION_RATE) {
                mutationStrategy.mutation(individual);
            }

            newPopulation.add(individual);
        }

        return newPopulation;
    }

    private Population selection(Population currentPopulation) {
        return selectionStrategy.selection(currentPopulation);
    }
}
