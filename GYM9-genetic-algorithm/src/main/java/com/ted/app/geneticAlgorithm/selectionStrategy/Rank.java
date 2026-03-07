package com.ted.app.geneticAlgorithm.selectionStrategy;

import com.ted.app.geneticAlgorithm.Individual;
import com.ted.app.geneticAlgorithm.Population;
import com.ted.app.geneticAlgorithm.SelectionStrategy;
import com.ted.app.util.RandomUtil;

import java.util.Comparator;

public class Rank implements SelectionStrategy {


    @Override
    public Population selection(Population population) {
        Population parents = new Population();
        int n = population.size();
        population.sort(Comparator.comparing(Individual::fitness));
        double[] probs = getProbs(n);
        double[] cumulativeProbs = getCumulativeProbs(n, probs);


        for (int i = 0; i < n; i++) {
            double r = RandomUtil.nextDouble(1.0); // 產生 0.0 ~ 1.0 的隨機數
            for (int j = 0; j < n; j++) {
                if (r <= cumulativeProbs[j]) {
                    parents.add(population.get(j));
                    break;
                }
            }
        }

        return parents;
    }

    private double[] getCumulativeProbs(int n, double[] probs){
        double[] cumulativeProbs = new double[n];
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += probs[i];
            cumulativeProbs[i] = sum;
        }
        return cumulativeProbs;
    }

    private double[] getProbs(int n){
        double totalRankWeight = n * (n + 1) / 2.0;
        double[] probs = new double[n];

        for (int i = 0; i < n; i++) {
            probs[i] = (i + 1) / totalRankWeight;
        }
        return probs;
    }
}
