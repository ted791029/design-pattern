package com.ted.app.geneticAlgorithm.mutationStrategy;

import com.ted.app.geneticAlgorithm.Individual;
import com.ted.app.geneticAlgorithm.MutationStrategy;

public class Inversion implements MutationStrategy {
    @Override
    public void mutation(Individual individual) {
        int point1 = individual.randomGenesIndex();
        int point2 = individual.randomGenesOtherIndex(point1);
        individual.reverse(point1, point2);
    }
}
