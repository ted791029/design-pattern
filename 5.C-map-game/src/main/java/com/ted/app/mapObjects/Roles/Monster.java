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

    @Override
    public void action() {
        State state = getState();
        state.action();

        boolean isStateEffect = isStateEffect();

        if(!isStateEffect){
            List<MapObject> attackTargets = attackRange();

            if(!attackTargets.isEmpty()){
                attack();
            }else {
                move();
            }
        }

        setStateEffect(false);
    }

    @Override
    protected List<MapObject> attackRange() {
        List<MapObject> attackTargets;
        State state = getState();
        attackTargets = state.attackRange();

        boolean isStateEffect = isStateEffect();

        if(!isStateEffect){
            attackTargets = new ArrayList<>();
            int row = getRow();
            int col = getCol();
            Map map = getMap();
            MapObject mapObject;

            //上方
            mapObject = map.getMapObject(row - 1, col);
            if (mapObject != null && mapObject.isCharacter()) {
                attackTargets.add(mapObject);
            }

            // 下方
            mapObject = map.getMapObject(row + 1, col);
            if(mapObject != null && mapObject.isCharacter()){
                attackTargets.add(mapObject);
            }


            // 左方
            mapObject = map.getMapObject(row, col - 1);
            if (mapObject != null && mapObject.isCharacter()) {
                attackTargets.add(mapObject);
            }

            //右方
            mapObject = map.getMapObject(row, col + 1);
            if (mapObject != null && mapObject.isCharacter()) {
                attackTargets.add(mapObject);
            }
        }

        setStateEffect(false);
        return attackTargets;
    }

    protected void dead(){
        System.out.println(this + " 已死亡");
        Game game = getGame();
        Map map = getMap();
        int row = getRow();
        int col = getCol();

        map.remove(row, col);
        game.removeMonster(this);

    }

    @Override
    protected int[] moveTarget() {
        int[] moveTarget;
        State state = getState();
        moveTarget = state.moveTarget();

        boolean isStateEffect = isStateEffect();

        if(!isStateEffect){
            moveTarget = new int[2];
            int row = getRow();
            int col = getCol();

            while (true){
                int chance = rand.nextInt(4); // 0 ~ 3
                switch (chance) {
                    case 0 -> {
                        moveTarget[0] = row - 1;
                        moveTarget[1] = col;
                    }
                    case 1 -> {
                        moveTarget[0] = row + 1;
                        moveTarget[1] = col;
                    }
                    case 2 -> {
                        moveTarget[0] = row;
                        moveTarget[1] = col - 1;
                    }
                    case 3 -> {
                        moveTarget[0] = row;
                        moveTarget[1] = col + 1;
                    }
                }

                Map map = getMap();

                if(!map.isOutSide(moveTarget[0] , moveTarget[1])){
                    break;
                }
            }
        }
        setStateEffect(false);
        return moveTarget;
    }

    @Override
    protected void updateDirection(int originRow, int originCol, int targetRow, int targetCol) {

    }
}
