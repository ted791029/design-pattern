package com.ted.app.mapObjects.Treasures;
import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;
import com.ted.app.states.Invincible;
import com.ted.app.states.State;

public class SuperStar extends Treasure{

    public SuperStar(){
        setSpawnChance(0.1d);
    }

    public SuperStar(Game game, Map map, int row, int col, int no) {
        super(game, map, row, col, no);
        setSpawnChance(0.1d);
    }

    @Override
    protected State createState(Role role) {
        Map map = getMap();
        return new Invincible(role);
    }

    @Override
    public String toString() {
        return "無敵星星";
    }
}
