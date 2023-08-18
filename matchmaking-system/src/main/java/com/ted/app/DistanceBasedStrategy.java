package com.ted.app;

import java.util.List;

public class DistanceBasedStrategy implements  MatchMakingStrategy{
    @Override
    public Individual findBestMatch(Individual matcher, List<Individual> matchList) {
        if(matchList.size() == 0) return null;
        Individual bestMatch = matchList.get(0);
        int bestDistance = matcher.getCoord().distance(bestMatch.getCoord());
        for(int i = 1; i < matchList.size(); i++){
            Individual individual = matchList.get(i);
            int tempDistance = matcher.getCoord().distance(individual.getCoord());
            if( tempDistance < bestDistance ){
                bestMatch = individual;
                bestDistance = tempDistance;
            } else if(tempDistance == bestDistance && individual.getId() < bestMatch.getId()) {
                bestMatch = individual;
                bestDistance = tempDistance;
            }
        }
        return bestMatch;
    }
}
