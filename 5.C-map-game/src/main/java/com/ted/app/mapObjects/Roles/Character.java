package com.ted.app.mapObjects.Roles;

import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.Util.ScannerUtil;
import com.ted.app.mapObjects.MapObject;
import com.ted.app.states.Normal;
import com.ted.app.states.State;

import java.util.ArrayList;
import java.util.List;


public class Character extends Role{

    public Character(Game game, Map map, int row, int col, int no) {
       super(game, map, row, col, no, '↑');
        setAttackDamage(1);
        setHpUpLimit(300);
        setHp(300);
        setOriginDamage(1);
        game.setCharacter(this);
    }

    @Override
    public void action() {
        State state = getState();
        state.action();

        boolean isStateEffect = isStateEffect();

        if (!isStateEffect) {
            String msg = "請選擇 1: 攻擊 2: 移動";
            int input = ScannerUtil.getInputInteger(msg, 1, 2);

            if(1 == input){
                attack();
            }

            if(2 == input){
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
            char symbol = getSymbol();

            if('↑' == symbol){
                int startRow = getRow();
                Map map = getMap();
                int endRow = 0;
                int col = getCol();

                for(int i = startRow - 1; i >= endRow; i--){

                    MapObject mapObject = map.getMapObject(i, col);

                    if(mapObject == null){
                        continue;
                    }

                    if(mapObject.isObstacle()){
                        break;
                    }

                    attackTargets.add(mapObject);
                }
            }else if('↓' == symbol){
                int startRow = getRow();
                Map map = getMap();
                int endRow = map.getGridsRows() - 1;
                int col = getCol();

                for(int i = startRow + 1; i <= endRow; i++){
                    MapObject mapObject = map.getMapObject(i, col);

                    if(mapObject == null){
                        continue;
                    }

                    if(mapObject.isObstacle()){
                        break;
                    }

                    attackTargets.add(mapObject);
                }
            }else if('←' == symbol){
                int startCol = getCol();
                Map map = getMap();
                int endCol = 0;
                int row = getRow();

                for(int i = startCol - 1; i >= endCol; i--){

                    MapObject mapObject = map.getMapObject(row, i);

                    if(mapObject == null){
                        continue;
                    }

                    if(mapObject.isObstacle()){
                        break;
                    }

                    attackTargets.add(mapObject);
                }
            }else if('→' == symbol){
                int startCol = getCol();
                Map map = getMap();
                int endCol = map.getGridsCols() - 1;
                int row = getRow();

                for(int i = startCol + 1; i <= endCol; i++){

                    MapObject mapObject = map.getMapObject(row, i);

                    if(mapObject == null){
                        continue;
                    }

                    if(mapObject.isObstacle()){
                        break;
                    }

                    attackTargets.add(mapObject);
                }
            }
        }
        setStateEffect(false);
        return attackTargets;
    }

    @Override
    protected void dead() {
        System.out.println(this + " 已死亡");
        throw new Game.GameOverException();
    }

    @Override
    protected int[] moveTarget() {
        int[] moveTarget;
        State state = getState();
        moveTarget = state.moveTarget();

        boolean isStateEffect = isStateEffect();
        if(!isStateEffect){
            moveTarget = new int[2];
            int row;
            int col;
            while (true){
                row = getRow();
                col = getCol();
                String msg = "請選擇方向 1: 上 2: 下 3: 左 4: 右";
                int input = ScannerUtil.getInputInteger(msg, 1, 4);

                if(1 == input){
                    row--;
                }

                if(2 == input){
                    row++;
                }

                if(3 == input){
                    col--;
                }

                if(4 == input){
                    col++;
                }

                Map map = getMap();

                if(map.isOutSide(row, col)){
                    System.out.println("超過邊界，無法移動，請重新選擇");
                }else {
                    break;
                }
            }
            moveTarget[0] = row;
            moveTarget[1] = col;
        }

        setStateEffect(false);
        return moveTarget;
    }

    @Override
    protected void updateDirection(int originRow, int originCol, int targetRow, int targetCol) {

        if(originRow > targetRow && originCol == targetCol){
            setSymbol('↑');
        }

        if(originRow < targetRow && originCol == targetCol){
            setSymbol('↓');
        }

        if(originRow == targetRow && originCol > targetCol){
            setSymbol('←');
        }

        if(originRow == targetRow && originCol < targetCol){
            setSymbol('→');
        }
    }

    public void show(){
        State state = getState();
        int hp = getHp();
        if(state.getClass() == Normal.class){
            System.out.println(this + " HP: " + hp + " 目前狀態: " + state.show());
        }else{
            System.out.println(this + " HP: " + hp + " 目前狀態: " + state.show() + " 狀態剩餘回合: " + (state.getCountdown() + 1));
        }
    }
}
