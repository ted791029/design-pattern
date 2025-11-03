package com.ted.app.mapObjects.Treasures;
import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;
import com.ted.app.states.Accelerated;
import com.ted.app.states.State;

public class AcceleratingPotion extends Treasure{
    public AcceleratingPotion() {
        setSpawnChance(0.2d);
    }

    public AcceleratingPotion(Game game, Map map, int row, int col, int no) {
        super(game, map, row, col, no);
        setSpawnChance(0.2d);
    }

    @Override
    protected State createState(Role role) {
        Map map = getMap();
        return new Accelerated(role);
    }

    @Override
    public String toString() {
        return "加速藥水";
    }
}
