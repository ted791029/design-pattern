package com.ted.app.geneticAlgorithm;

import java.util.List;

public interface IndividualFactory {

    public Individual create(List<Genes> chromosome);
}
