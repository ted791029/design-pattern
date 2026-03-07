package com.ted.app.factoryProductionScheduling;

import com.ted.app.geneticAlgorithm.FitnessType;
import com.ted.app.geneticAlgorithm.Genes;
import com.ted.app.geneticAlgorithm.Individual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactorySchedule extends Individual {

    private Factory factory;

    public FactorySchedule(FitnessType fitnessType, List<Genes> chromosome, Factory factory) {
        super(fitnessType, chromosome);
        this.factory = factory;
    }

    @Override
    public double objectiveFunction() {
        int[] machineTimes = new int[factory.getMachineCount()];

        Map<String, Integer> producedCounts = new HashMap<>();

        for (int i = 0; i < size(); i++) {
            Product product = (Product) get(i);

            int machineIndex = findMachineWithEarliestFinishTime(machineTimes);
            machineTimes[machineIndex] += product.getProductionTimeHours();

            producedCounts.merge(product.getName(), 1, Integer::sum);
        }

        int makespan = 0;
        for (int time : machineTimes) {
            if (time > makespan) {
                makespan = time;
            }
        }

        int penalty = calculateDemandPenalty(producedCounts);

        // FitnessType.MIN_VALUE 時會在父類別中取負值，因此此處回傳的值越小越好。
        return makespan + penalty;
    }

    private int findMachineWithEarliestFinishTime(int[] machineTimes) {
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

    private int calculateDemandPenalty(Map<String, Integer> producedCounts) {
        int penalty = 0;

        for (Product product : factory.getProducts()) {
            String name = product.getName();
            int required = factory.getRequiredQuantity(name);
            int produced = producedCounts.getOrDefault(name, 0);
            int diff = Math.abs(required - produced);

            // 每少生產或多生產一個產品，都給予較大的懲罰，促使演算法逼近正確需求量。
            penalty += diff * 1000;
        }

        return penalty;
    }
}

