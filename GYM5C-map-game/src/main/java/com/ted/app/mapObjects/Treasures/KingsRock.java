package com.ted.app.mapObjects.Treasures;
import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;
import com.ted.app.states.State;
import com.ted.app.states.Stockpile;


public class KingsRock extends Treasure{

    public KingsRock() {
        setSpawnChance(0.1d);
    }

    public KingsRock(Game game, Map map, int row, int col, int no) {
        super(game, map, row, col, no);
        setSpawnChance(0.1d);
    }

    @Override
    protected State createState(Role role) {
        Map map = getMap();
        return new Stockpile(role);
    }

    @Override
    public String toString() {
        return "王者之印";
    }
}
