package com.ted.app.mapObjects.Treasures;
import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;
import com.ted.app.states.Healing;
import com.ted.app.states.State;

public class HealingPotion extends Treasure{

    public HealingPotion() {
        setSpawnChance(0.15d);
    }

    public HealingPotion(Game game, Map map, int row, int col, int no) {
        super(game, map, row, col, no);
        setSpawnChance(0.15d);
    }

    @Override
    protected State createState(Role role) {
        Map map = getMap();
        return new Healing(role);
    }

    @Override
    public String toString() {
        return "補血罐";
    }
}
