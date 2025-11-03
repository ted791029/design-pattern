package com.ted.app;

import com.ted.app.mapObjects.Obstacle;
import com.ted.app.mapObjects.Roles.Character;
import com.ted.app.mapObjects.Roles.Monster;
import com.ted.app.mapObjects.Treasures.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game {

    public  static final Random rand = new Random();

    private Map map;

    private Character character;

    private List<Monster> monsters;

    private List<Monster> toRemoveMonsters;

    public Game(){
        setMonsters(new ArrayList<>());
        setToRemoveMonsters(new ArrayList<>());
    }

    public void addMonster(Monster monster){
        List<Monster> monsters = getMonsters();
        monsters.add(monster);
    }

    public void addToRemoveMonster(Monster monster){
        List<Monster> monsters = getToRemoveMonsters();
        monsters.add(monster);
    }

    private void characterStage(){
        Character character = getCharacter();
        int actionCount = character.getActionCount();

        for(int i = 0; i < actionCount; i++){
            character.action();
        }
    }

    private void generateCharacter(){
        Map map = getMap();

        if(map == null){
            throw new RuntimeException("地圖未生成");
        }

        int[] points = map.getEmptyGridIndex();
        new Character(this, map, points[0], points[1], 1);
    }

    private void generateMap(int row, int col){
       setMap(new Map(row, col));
    }

    private void generateMonsters(){
        Map map = getMap();

        if(map == null){
            throw new RuntimeException("地圖未生成");
        }

        int monstersCount = (int)(map.getGridsRows() * map.getGridsCols() * 0.05);

        for(int i = 0; i < monstersCount; i++){
            int[] points = map.getEmptyGridIndex();
            new Monster(this, map, points[0], points[1], i + 1);
        }

    }

    private void generateObstacle(){
        Map map = getMap();

        if(map == null){
            throw new RuntimeException("地圖未生成");
        }

        int obstacleCount = (int)(map.getGridsRows() * map.getGridsCols() * 0.025);

        for(int i = 0; i < obstacleCount; i++){
            int[] points = map.getEmptyGridIndex();
            Obstacle obstacle = new Obstacle(this, map, points[0], points[1], i + 1);
            map.insert(obstacle.getRow(), obstacle.getCol(), obstacle);
        }
    }

    private void generateTreasure(){
        Map map = getMap();
        List<Treasure> treasures = getDefaultTreasures();
        int treasureCount = (int)(map.getGridsRows() * map.getGridsCols() * 0.025);

        for(int i = 0; i < treasureCount; i++){
            double r = rand.nextDouble(); // 0.0 ~ 1.0
            double cumulative = 0.0;
            for(int j = 0;  j < treasures.size(); j++){
                Treasure treasure = treasures.get(j);
                cumulative += treasure.getSpawnChance();

                if(r < cumulative){
                    int[] point = map.getEmptyGridIndex();
                    int row = point[0];
                    int col = point[1];
                    //需和Default列表順序一致
                    switch (j){
                        case 0 -> new SuperStar(this, map, row, col, i);
                        case 1 -> new Poison(this, map, row, col, i);
                        case 2 -> new KingsRock(this, map, row, col, i);
                        case 3 -> new HealingPotion(this, map, row, col, i);
                        case 4 -> new DokodemoDoor(this, map, row, col, i);
                        case 5 -> new DevilFruit(this, map, row, col, i);
                        case 6 -> new AcceleratingPotion(this, map, row, col, i);
                    }
                    break;
                }
            }
        }
    }

    private List<Treasure> getDefaultTreasures(){
        List<Treasure> treasures = new ArrayList<>();
        treasures.add(new SuperStar());
        treasures.add(new Poison());
        treasures.add(new KingsRock());
        treasures.add(new HealingPotion());
        treasures.add(new DokodemoDoor());
        treasures.add(new DevilFruit());
        treasures.add(new AcceleratingPotion());
        return treasures;
    }

    public void generateStateEffectStage(){
        Character character = getCharacter();
        character.generateStateEffect();
        List<Monster> monsters = getMonsters();

        for(Monster monster : monsters){
            monster.generateStateEffect();
        }

    }

    private boolean isEnd(){
        List<Monster> monsters = getMonsters();
        return monsters.isEmpty();
    }

    private void monstersStage(){
        List<Monster> monsters = getMonsters();
        Iterator<Monster> it = monsters.iterator();

        while(it.hasNext()){
            Monster monster = it.next();
           int actionCount = monster.getActionCount();

           for(int i = 0; i < actionCount; i++){
               monster.action();
           }
       }
    }

    public void nextRound(){
        try {
            generateStateEffectStage();
            showStage();
            characterStage();
            removeMonsterStage();
            monstersStage();
            nextRound();
        }catch (Exception e){

            if (e.getClass() == GameEndException.class){
                System.out.println("遊戲結束，玩家獲勝");
                return;
            }

            if (e.getClass() == GameOverException.class){
                System.out.println("遊戲結束，玩家輸了");
                return;
            }

            e.printStackTrace();
        }
    }

    private void removeMonster(Monster monster){
        List<Monster> monsters = getMonsters();
        monsters.remove(monster);

        if(isEnd()){
            throw new Game.GameEndException();
        }
    }

    private void removeMonsterStage(){
        List<Monster> monsters = getToRemoveMonsters();

        for (Monster monster : monsters){
            removeMonster(monster);
        }
    }

    private void showStage(){
        Map map = getMap();
        Character character = getCharacter();
        map.show();
        character.show();
    }

    public void start(){
        generateMap(10, 20);
        generateCharacter();
        generateMonsters();
        generateTreasure();
        generateObstacle();
        nextRound();
    }

    /*===================================*/
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Monster> getToRemoveMonsters() {
        return toRemoveMonsters;
    }

    public void setToRemoveMonsters(List<Monster> toRemoveMonsters) {
        this.toRemoveMonsters = toRemoveMonsters;
    }

    public static class GameEndException extends RuntimeException {}

    public static class GameOverException extends RuntimeException {}
}
