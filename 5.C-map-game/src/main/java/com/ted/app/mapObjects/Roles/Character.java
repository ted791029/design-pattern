package com.ted.app.mapObjects.Roles;

import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.MapObject;
import com.ted.app.states.Normal;
import com.ted.app.states.State;
import com.ted.app.util.ScannerUtil;


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
    protected void dead() {
        System.out.println(this + " 已死亡");
        throw new Game.GameOverException();
    }

    @Override
    protected boolean isAttackTarget(MapObject object) {
        return object != null && object.isMonster();
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
