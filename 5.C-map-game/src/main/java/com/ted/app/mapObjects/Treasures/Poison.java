package com.ted.app.mapObjects.Treasures;
import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;
import com.ted.app.states.Poisoned;
import com.ted.app.states.State;

public class Poison extends Treasure{
    public Poison() {
        setSpawnChance(0.25d);
    }

    public Poison(Game game, Map map, int row, int col, int no) {
        super(game,map, row, col, no);
        setSpawnChance(0.25d);
    }

    @Override
    protected State createState(Role role) {
        Map map = getMap();
        return new Poisoned(map, role);
    }

    @Override
    public String toString() {
        return "毒藥";
    }
}
