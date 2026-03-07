package com.ted.app.geneticAlgorithm.mutationStrategy;

import com.ted.app.geneticAlgorithm.Genes;
import com.ted.app.geneticAlgorithm.Individual;
import com.ted.app.geneticAlgorithm.MutationStrategy;
import com.ted.app.util.RandomUtil;

import java.util.List;

public class RandomReplacement implements MutationStrategy {

    private int replacementSize = 2;

    public RandomReplacement(List<Genes> genesPool) {
        this.genesPool = genesPool;
    }

    public RandomReplacement(int replacementSize, List<Genes> genesPool) {
        this.replacementSize = replacementSize;
        this.genesPool = genesPool;
    }

    private List<Genes> genesPool;

    @Override
    public void mutation(Individual individual) {
        List<Integer> indices = individual.randomGenesIndices(replacementSize);

        for (int index : indices) {
            Genes genes = generateRandomGenes();
            individual.set(index, genes);
        }
    }

    private Genes generateRandomGenes() {
        int index = RandomUtil.nextInt(genesPool.size());
        return genesPool.get(index);
    }
}
