package com.ted.app.mapObjects.Treasures;
import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;
import com.ted.app.states.Orderless;
import com.ted.app.states.State;

public class DevilFruit extends Treasure{
    public DevilFruit() {
        setSpawnChance(0.1d);
    }

    public DevilFruit(Game game, Map map, int row, int col, int no) {
        super(game, map, row, col, no);
        setSpawnChance(0.1d);
    }

    @Override
    protected State createState(Role role) {
        Map map = getMap();
        return new Orderless(role);
    }

    @Override
    public String toString() {
        return "惡魔果實";
    }
}
