package com.ted.app.geneticAlgorithm;

import com.ted.app.util.RandomUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Population {

    private List<Individual> individuals;

    public Population() {
        this.individuals = new ArrayList<>();
    }

    public void add(Individual individual){
        individuals.add(individual);
    }

    public void addAll(Population population){
        this.individuals.addAll(population.individuals);
    }

    public Individual findBest(){
        return individuals.stream()
                .max(Comparator.comparing(Individual::fitness))
                .orElse(null);
    }

    public Individual get(int index){

        if(index < 0 || index >= size()){
            throw new RuntimeException("超出範圍");
        }

        return individuals.get(index);
    }

    public List<Individual> getIndividuals (int count) {
        List<Individual> individuals = new ArrayList<>();

        for(int i = 0; i < count; i++){
            Individual currentIndividual = null;

            while (currentIndividual == null || individuals.contains(currentIndividual)){
                currentIndividual = get(RandomUtil.nextInt(this.individuals.size()));
            }

            individuals.add(currentIndividual);
        }

        return individuals;
    }

    public int size(){
        return individuals.size();
    }

    public void sort(Comparator comparator){
        individuals.sort(comparator);
    }


    //================================
    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }
}
