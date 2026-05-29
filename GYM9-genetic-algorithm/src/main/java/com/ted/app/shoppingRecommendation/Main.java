package com.ted.app.shoppingRecommendation;

import com.ted.app.geneticAlgorithm.*;
import com.ted.app.geneticAlgorithm.crossoverStrategy.SinglePoint;
import com.ted.app.geneticAlgorithm.mutationStrategy.Inversion;
import com.ted.app.geneticAlgorithm.selectionStrategy.Rank;
import com.ted.app.shoppingRecommendation.*;
import com.ted.app.util.RandomUtil;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Product> catalog = productCatalog();
        Customer customer = defaultCustomer();
        customer.setPreferences(defaultPreferences(customer));
        RecommendationFactory individualFactory = new RecommendationFactory(customer);

        int populationSize = 50;
        int maxIterations = 100;
        double mutationRate = 0.15;

        Population initialPopulation = createInitialPopulation(customer, catalog, individualFactory, populationSize);

        SelectionStrategy selectionStrategy = new Rank();
        CrossoverStrategy crossoverStrategy = new SinglePoint();
        MutationStrategy mutationStrategy = new Inversion();
        TerminationConditionStrategy terminationConditionStrategy = () -> false;

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(
                crossoverStrategy,
                individualFactory,
                mutationStrategy,
                selectionStrategy,
                terminationConditionStrategy,
                maxIterations,
                mutationRate
        );

        Individual best = geneticAlgorithm.run(initialPopulation);
        Recommendation bestRecommendation = (Recommendation) best;

        printBestRecommendation(customer, bestRecommendation);
    }

    /** doc F：產品 1~6 的價格、重量、類別 */
    private static List<Product> productCatalog() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1,100, 2, Type.A));  // 產品 1
        products.add(new Product(2,200, 3, Type.A));  // 產品 2
        products.add(new Product(3,150, 5, Type.B));  // 產品 3
        products.add(new Product(4,300, 4, Type.B));  // 產品 4
        products.add(new Product(5,180, 6, Type.C));  // 產品 5
        products.add(new Product(6,250, 7, Type.C));  // 產品 6
        return products;
    }

    /** doc F：預算 700 元、承重 15 公斤、類別 A 80% / B 60% / C 20% */
    private static Customer defaultCustomer() {
        return new Customer(700, new ShoppingBag(15));
    }

    private static List<Preference> defaultPreferences(Customer customer) {
        List<Preference> preferences = new ArrayList<>();
        preferences.add(new Preference(customer, Type.A, 0.8));
        preferences.add(new Preference(customer, Type.B, 0.6));
        preferences.add(new Preference(customer, Type.C, 0.2));
        return preferences;
    }

    private static Population createInitialPopulation(Customer customer, List<Product> catalog,
                                                      RecommendationFactory factory,
                                                      int populationSize) {
        Population population = new Population();
        int budget = customer.getBudget();
        int capacity = customer.getShoppingBagCapacityKg();

        for (int n = 0; n < populationSize; n++) {
            List<Genes> chromosome = new ArrayList<>();
            int totalPrice = 0;
            int totalWeight = 0;

            while (true) {
                Product p = catalog.get(RandomUtil.nextInt(catalog.size()));
                if (totalPrice + p.getPrice() <= budget && totalWeight + p.getWeightKg() <= capacity) {
                    chromosome.add(p);
                    totalPrice += p.getPrice();
                    totalWeight += p.getWeightKg();
                } else {
                    break;
                }
            }

            if (!chromosome.isEmpty()) {
                population.add(factory.create(chromosome));
            }
        }

        return population;
    }

    private static void printBestRecommendation(Customer customer, Recommendation rec) {
        System.out.println("=== 購物網站推薦 (GA) ===");

        int totalPrice = 0;
        int totalWeight = 0;
        Map<Product, Integer> countByProduct = new LinkedHashMap<>();

        for (int i = 0; i < rec.size(); i++) {
            Product p = (Product) rec.get(i);
            totalPrice += p.getPrice();
            totalWeight += p.getWeightKg();
            countByProduct.merge(p, 1, Integer::sum);
        }

        System.out.println("目標值（喜好度總和，越大越好）: " + rec.objectiveFunction());
        System.out.println("Fitness: " + rec.fitness());
        System.out.println("總花費: " + totalPrice + " 元（預算 " + customer.getBudget() + "）");
        System.out.println("總重量: " + totalWeight + " 公斤（上限 " + customer.getShoppingBag().getCapacityKg() + "）");
        System.out.println("--- 推薦清單 ---");
        for (Map.Entry<Product, Integer> e : countByProduct.entrySet()) {
            Product p = e.getKey();
            int qty = e.getValue();
            System.out.println("    名稱 " + p.getId() + ", 價格 " + p.getPrice() + " 元, 重量 " + p.getWeightKg() + " kg, 類別 " + p.getType() + " x " + qty);
        }
    }
}
