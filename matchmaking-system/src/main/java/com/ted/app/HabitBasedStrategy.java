package com.ted.app;

import java.util.List;

public class HabitBasedStrategy implements  MatchMakingStrategy{
    @Override
    public Individual findBestMatch(Individual matcher, List<Individual> matchList) {
        if(matchList.size() == 0) return null;
        Individual bestMatch = matchList.get(0);
        int bestCount = matcher.getRepeatedHabitsCounts(bestMatch);
        for(int i = 1; i < matchList.size(); i++){
            Individual individual = matchList.get(i);
            int tempCount = matcher.getRepeatedHabitsCounts(individual);
            if( tempCount > bestCount ){
                bestMatch = individual;
                bestCount = tempCount;
            } else if(tempCount == bestCount && individual.getId() < bestMatch.getId()) {
                bestMatch = individual;
                bestCount = tempCount;
            }
        }
        return bestMatch;
    }
}
