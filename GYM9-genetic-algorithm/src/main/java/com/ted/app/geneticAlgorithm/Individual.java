package com.ted.app.geneticAlgorithm;

import com.ted.app.util.RandomUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Individual {

    private FitnessType FITNESS_TYPE;

    private List<Genes> chromosome;

    public Individual(FitnessType FITNESS_TYPE, List<Genes> chromosome) {
        this.FITNESS_TYPE = FITNESS_TYPE;
        this.chromosome = chromosome;
    }

    public double fitness() {
        double value = objectiveFunction();

        if (FITNESS_TYPE == FitnessType.MAX_VALUE) {
            return value;
        } else {
            return -value;
        }
    }

    public Genes get(int index) {
        return chromosome.get(index);
    }

    public List<Genes> getGenes(int fromIndex, int toIndex) {
        return chromosome.subList(fromIndex, toIndex);
    }

    public abstract double objectiveFunction();

    public void set(int index, Genes genes) {
        chromosome.set(index, genes);
    }

    public int randomGenesIndex() {
        return RandomUtil.nextInt(chromosome.size());
    }

    public List<Integer> randomGenesIndices(int count) {
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            indices.add(randomGenesIndex());
        }

        return indices;
    }

    public int randomGenesOtherIndex(int index) {
        int index2 = -1;

        while (index2 == -1 || index == index2) {
            index2 = randomGenesIndex();
        }

        return index2;
    }

    public void reverse(int start, int end) {
        while (start < end) {
            Collections.swap(chromosome, start, end);
            start++;
            end--;
        }
    }

    public int size() {
        return chromosome.size();
    }
}
