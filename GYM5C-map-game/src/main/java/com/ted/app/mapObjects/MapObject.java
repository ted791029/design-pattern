package com.ted.app.mapObjects;

import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Character;
import com.ted.app.mapObjects.Roles.Monster;
import com.ted.app.mapObjects.Roles.Role;

public abstract class MapObject {

    private int col;

    private Game game;

    private Map map;

    private int no;

    private int row;

    private char symbol;

    public MapObject(){
    }

    public MapObject(Game game, Map map, int row, int col, int no, char symbol){
        setGame(game);
        setMap(map);
        setRow(row);
        setCol(col);
        setNo(no);
        setSymbol(symbol);
        map.insert(row, col, this);
    }

    public boolean isCharacter(){
        return getClass() == Character.class;
    }

    public boolean isMonster(){
        return getClass() == Monster.class;
    }

    public boolean isObstacle(){
        return getClass() == Obstacle.class;
    }

    public abstract void onTouched(Role role);

    @Override
    public String toString() {
        return symbol + String.valueOf(no);
    }

    /*===================================*/

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
