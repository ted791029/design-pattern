package com.ted.app;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MatchMakingSystemTest {

    List<Individual> individuals;
    MatchMakingSystem system;

    @BeforeEach
    public void setUp() throws Exception {
        individuals = individualsInit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        individuals = null;
    }

    @Test
    public void givenMatchSystemHasIndividuals_WhenIndividualAMatchByDistance_ThenShouldGetIndividualE(){
        this.givenMatchSystemHasIndividuals();
        Individual matcher = individuals.get(0);
        individuals.remove(0);
        Individual bestMatcher = this.whenIndividualMatchByStrategy(matcher, new DistanceBasedStrategy());
        this.thenShouldGetTargetId(bestMatcher, 5);
    }

    @Test
    public void givenMatchSystemHasIndividuals_WhenIndividualAMatchByHabit_ThenShouldGetIndividualB(){
        this.givenMatchSystemHasIndividuals();
        Individual matcher = individuals.get(0);
        individuals.remove(0);
        Individual bestMatcher = this.whenIndividualMatchByStrategy(matcher, new HabitBasedStrategy());
        this.thenShouldGetTargetId(bestMatcher, 2);
    }

    private List<Individual> individualsInit(){
        List<Individual> individuals = new ArrayList<>();
        individuals.add(new Individual(1, Gender.MALE, 20, "A", "跳水,爬山,游泳", new Coord(0, 0)));
        individuals.add(new Individual(2, Gender.FEMALE, 29, "B", "逛街,爬山,游泳", new Coord(100, 100)));
        individuals.add(new Individual(3, Gender.MALE, 25, "C", "爬山,打遊戲", new Coord(80, 80)));
        individuals.add(new Individual(4, Gender.FEMALE, 30, "D", "逛街,吃飯,買東西", new Coord(50, 50)));
        individuals.add(new Individual(5, Gender.MALE, 50, "E", "讀書,寫字", new Coord(20, 20)));
        return individuals;
    }

    private void givenMatchSystemHasIndividuals(){
        system = new MatchMakingSystem(individuals);
    }

    private Individual whenIndividualMatchByStrategy(Individual matcher, MatchMakingStrategy strategy){
        return system.match(matcher, strategy);
    }

    private void thenShouldGetTargetId(Individual bestMatcher, int targetId){
        assertTrue(bestMatcher.getId() == targetId);
    }
}