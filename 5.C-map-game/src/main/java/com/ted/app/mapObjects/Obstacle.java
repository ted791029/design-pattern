package com.ted.app.mapObjects;

import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;

public class Obstacle extends MapObject{
    public Obstacle(Game game, Map map, int row, int col, int no){
        super(game, map, row, col, no, '□');
    }

    @Override
    public void onTouched(Role role) {
        System.out.println(role + " 觸碰了 " + this);
    }
}
