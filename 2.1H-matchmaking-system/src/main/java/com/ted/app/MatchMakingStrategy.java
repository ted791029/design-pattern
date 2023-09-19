package com.ted.app;

import java.util.List;

public interface MatchMakingStrategy {
    public abstract Individual findBestMatch(Individual matcher , List<Individual> matchList);
}
