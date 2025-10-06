package com.ted.app.mapObjects.Treasures;
import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;
import com.ted.app.states.State;
import com.ted.app.states.Teleport;

public class DokodemoDoor extends Treasure{
    public DokodemoDoor() {
        setSpawnChance(0.1d);
    }

    public DokodemoDoor(Game game, Map map, int row, int col, int no) {
        super(game, map, row, col, no);
        setSpawnChance(0.1d);
    }

    @Override
    protected State createState(Role role) {
        Map map = getMap();
        return new Teleport(map, role);
    }

    @Override
    public String toString() {
        return "任意門";
    }
}
