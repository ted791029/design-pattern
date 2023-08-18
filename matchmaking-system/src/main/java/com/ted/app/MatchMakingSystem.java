package com.ted.app;

import java.util.List;

public class MatchMakingSystem {
    private List<Individual> individuals;

    public MatchMakingSystem(List<Individual> individuals) {
        this.setIndividuals(individuals);
    }

    public Individual match(Individual matcher, MatchMakingStrategy strategy){
        return this.findBestMatch(matcher, strategy);
    }

    private Individual findBestMatch(Individual matcher, MatchMakingStrategy strategy){
        return strategy.findBestMatch(matcher, individuals);
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }
}
