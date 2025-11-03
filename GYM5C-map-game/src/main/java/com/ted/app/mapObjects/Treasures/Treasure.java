package com.ted.app.mapObjects.Treasures;

import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.MapObject;
import com.ted.app.mapObjects.Roles.Role;
import com.ted.app.states.State;

public abstract class Treasure extends MapObject {

    private double spawnChance;

    public Treasure(){
    }

    public Treasure(Game game, Map map, int row, int col, int no) {
        super(game, map, row, col, no, 'x');
    }

    protected abstract State createState(Role role);

    @Override
    public void onTouched(Role role){
        System.out.println(role + " 觸碰了 " + this);
        State state = createState(role);
        role.enterState(state);
        int row = getRow();
        int col = getCol();
        remove(row, col);
    }

    private void remove(int row, int col){
        Map map = getMap();
        map.remove(row, col);
    }

    /*===================================*/

    public double getSpawnChance() {
        return spawnChance;
    }

    public void setSpawnChance(double spawnChance) {
        this.spawnChance = spawnChance;
    }
}
