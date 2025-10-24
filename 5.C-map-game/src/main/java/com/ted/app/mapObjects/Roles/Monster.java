package com.ted.app.mapObjects.Roles;

import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.MapObject;
import com.ted.app.states.State;

import java.util.ArrayList;
import java.util.List;

import static com.ted.app.Game.rand;

public class Monster extends Role{

    public Monster(Game game, Map map, int row, int col, int no){
        super(game,map, row, col, no, 'M');
        setAttackDamage(50);
        setHpUpLimit(1);
        setHp(1);
        setOriginDamage(50);
        game.addMonster(this);
    }

    protected void dead(){
        System.out.println(this + " 已死亡");
        Game game = getGame();
        Map map = getMap();
        int row = getRow();
        int col = getCol();

        map.remove(row, col);
        game.addToRemoveMonster(this);

    }

    @Override
    protected boolean isAttackTarget(MapObject object) {
        return object != null && object.isCharacter();
    }

    @Override
    protected void updateDirection(int originRow, int originCol, int targetRow, int targetCol) {

    }
}
