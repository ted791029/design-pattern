package com.ted.app.shoppingRecommendation;

import com.ted.app.geneticAlgorithm.FitnessType;
import com.ted.app.geneticAlgorithm.Genes;
import com.ted.app.geneticAlgorithm.Individual;

import java.util.List;
import java.util.Map;

public class Recommendation extends Individual {

    private final Customer customer;

    public Recommendation(FitnessType fitnessType, List<Genes> chromosome, Customer customer) {
        super(fitnessType, chromosome);
        this.customer = customer;
    }

    @Override
    public double objectiveFunction() {
        double score = 0;
        for (int i = 0; i < size(); i++) {
            Product p = (Product) get(i);
            Preference preference = customer.getPreferences().stream()
                    .filter(pre -> p.getType() == pre.getType())
                    .findFirst()
                    .get();
            score += preference.getVal();
        }
        double penalty = calculateDemandPenalty();
        return score - penalty;
    }

    private double calculateDemandPenalty() {
        double k = 500.0; // 根據情況調整，建議先測試不同量級
        int totalPrice = 0;
        int totalWeight = 0;

        for (int i = 0; i < size(); i++) {
            Product p = (Product) get(i);
            totalPrice += p.getPrice();
            totalWeight += p.getWeightKg();
        }

        // 用絕對偏離比例（高低都懲罰）
        double priceDeviation = Math.abs((double)(totalPrice - customer.getBudget()) / customer.getBudget());
        double weightDeviation = Math.abs((double)(totalWeight - customer.getShoppingBagCapacityKg()) / customer.getShoppingBagCapacityKg());

        // 懲罰為兩者平方型偏離比例的總和乘上係數
        double penalty = k * (Math.pow(priceDeviation, 2) + Math.pow(weightDeviation, 2));
        return penalty;
    }
}
