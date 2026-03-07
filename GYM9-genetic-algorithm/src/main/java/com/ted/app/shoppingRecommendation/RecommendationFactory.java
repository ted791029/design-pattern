package com.ted.app.shoppingRecommendation;

import com.ted.app.geneticAlgorithm.FitnessType;
import com.ted.app.geneticAlgorithm.Genes;
import com.ted.app.geneticAlgorithm.Individual;
import com.ted.app.geneticAlgorithm.IndividualFactory;

import java.util.List;

public class RecommendationFactory implements IndividualFactory {

    private final Customer customer;

    public RecommendationFactory(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Individual create(List<Genes> chromosome) {
        return new Recommendation(FitnessType.MAX_VALUE, chromosome, customer);
    }
}
