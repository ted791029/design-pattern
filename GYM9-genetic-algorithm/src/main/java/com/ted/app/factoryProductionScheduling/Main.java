package com.ted.app.factoryProductionScheduling;


import com.ted.app.geneticAlgorithm.*;
import com.ted.app.geneticAlgorithm.crossoverStrategy.Uniform;
import com.ted.app.geneticAlgorithm.mutationStrategy.Inversion;
import com.ted.app.geneticAlgorithm.selectionStrategy.Tournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Factory factory = defaultFactory();

        int populationSize = 30;
        int maxIterations = 100;
        double mutationRate = 0.1;

        FactoryScheduleFactory individualFactory = new FactoryScheduleFactory(factory);
        Population initialPopulation = createInitialPopulation(factory, individualFactory, populationSize);

        SelectionStrategy selectionStrategy = new Tournament(3);
        CrossoverStrategy crossoverStrategy = new Uniform();
        MutationStrategy mutationStrategy = new Inversion();
        TerminationConditionStrategy terminationConditionStrategy = () -> false;

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(
                crossoverStrategy,
                individualFactory,
                mutationStrategy,
                selectionStrategy,
                terminationConditionStrategy,
                maxIterations,
                mutationRate
        );

        Individual best = geneticAlgorithm.run(initialPopulation);
        FactorySchedule bestSchedule = (FactorySchedule) best;

        printBestSchedule(factory, bestSchedule);
    }

    public static Factory defaultFactory() {
        List<Machine> machines = new ArrayList<>();
        machines.add(new Machine("M1"));
        machines.add(new Machine("M2"));

        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("W1"));
        workers.add(new Worker("W2"));
        workers.add(new Worker("W3"));
        workers.add(new Worker("W4"));

        List<Product> products = new ArrayList<>();
        Product productA = new Product("A", 2);
        Product productB = new Product("B", 4);
        Product productC = new Product("C", 6);
        products.add(productA);
        products.add(productB);
        products.add(productC);

        Map<String, Integer> requiredQuantities = new HashMap<>();
        requiredQuantities.put(productA.getName(), 100);
        requiredQuantities.put(productB.getName(), 200);
        requiredQuantities.put(productC.getName(), 300);

        return new Factory(machines, workers, products, requiredQuantities);
    }

    private static Population createInitialPopulation(Factory factory,
                                                      FactoryScheduleFactory individualFactory,
                                                      int populationSize) {
        Population population = new Population();

        List<Genes> baseChromosome = new ArrayList<>();
        for (Product product : factory.getProducts()) {
            int required = factory.getRequiredQuantity(product.getName());
            for (int i = 0; i < required; i++) {
                baseChromosome.add(product);
            }
        }

        for (int i = 0; i < populationSize; i++) {
            List<Genes> chromosome = new ArrayList<>(baseChromosome);
            Collections.shuffle(chromosome);
            population.add(individualFactory.create(chromosome));
        }

        return population;
    }

    private static void printBestSchedule(Factory factory, FactorySchedule schedule) {
        System.out.println("=== Factory production scheduling (GA) ===");
        System.out.println("Objective value (lower is better): " + schedule.objectiveFunction());
        System.out.println("Fitness: " + schedule.fitness());

        int[] machineTimes = new int[factory.getMachineCount()];
        Map<String, Integer> producedCounts = new HashMap<>();

        for (int i = 0; i < schedule.size(); i++) {
            Product product = (Product) schedule.get(i);

            int machineIndex = findMachineWithEarliestFinishTime(machineTimes);
            machineTimes[machineIndex] += product.getProductionTimeHours();

            producedCounts.merge(product.getName(), 1, Integer::sum);
        }

        System.out.println("--- Machine workloads (hours) ---");
        for (int i = 0; i < machineTimes.length; i++) {
            System.out.println("Machine " + (i + 1) + ": " + machineTimes[i]);
        }

        System.out.println("--- Produced quantities ---");
        for (Product product : factory.getProducts()) {
            String name = product.getName();
            int required = factory.getRequiredQuantity(name);
            int produced = producedCounts.getOrDefault(name, 0);
            System.out.println("Product " + name + ": " + produced + " / required " + required);
        }
    }

    private static int findMachineWithEarliestFinishTime(int[] machineTimes) {
        int index = 0;
        int minTime = machineTimes[0];

        for (int i = 1; i < machineTimes.length; i++) {
            if (machineTimes[i] < minTime) {
                minTime = machineTimes[i];
                index = i;
            }
        }

        return index;
    }
}

