package com.ted.app.factoryProductionScheduling;

import com.ted.app.geneticAlgorithm.FitnessType;
import com.ted.app.geneticAlgorithm.Genes;
import com.ted.app.geneticAlgorithm.Individual;
import com.ted.app.geneticAlgorithm.IndividualFactory;

import java.util.List;

public class FactoryScheduleFactory implements IndividualFactory {

    private final Factory factory;

    public FactoryScheduleFactory(Factory factory) {
        this.factory = factory;
    }

    @Override
    public Individual create(List<Genes> chromosome) {
        return new FactorySchedule(FitnessType.MIN_VALUE, chromosome, factory);
    }
}

