package com.ted.app.states;

import com.ted.app.Map;
import com.ted.app.mapObjects.MapObject;
import com.ted.app.mapObjects.Roles.Role;
import com.ted.app.util.ScannerUtil;

import java.util.ArrayList;
import java.util.List;

import static com.ted.app.Game.rand;

public abstract class State {

    private int duration;

    private int countdown;

    private Role role;

    public State(Role role) {
        setRole(role);
    }

    public void action(){
        Role role = getRole();

        if(role.isCharacter()){
            String msg = "請選擇 1: 攻擊 2: 移動";
            int input = ScannerUtil.getInputInteger(msg, 1, 2);

            if(1 == input){
                role.attack();
            }

            if(2 == input){
                role.move();
            }
        }else if(role.isMonster()){
            List<MapObject> attackTargets = role.attackTargets();

            if(!attackTargets.isEmpty()){
                role.attack();
            }else {
                role.move();
            }
        }
    }

    public List<MapObject> attackRange(Map map){
        List<MapObject> result = new ArrayList<>();
        Role role = getRole();

        if(role.isCharacter()){
            char symbol = role.getSymbol();

            if('↑' == symbol){
                int startRow = role.getRow();
                int endRow = 0;
                int col = role.getCol();

                for(int i = startRow - 1; i >= endRow; i--){

                    MapObject mapObject = map.getMapObject(i, col);

                    if(mapObject == null){
                        continue;
                    }

                    if(mapObject.isObstacle()){
                        break;
                    }

                    result.add(mapObject);
                }
            }else if('↓' == symbol){
                int startRow = role.getRow();
                int endRow = map.getGridsRows() - 1;
                int col = role.getCol();

                for(int i = startRow + 1; i <= endRow; i++){
                    MapObject mapObject = map.getMapObject(i, col);

                    if(mapObject == null){
                        continue;
                    }

                    if(mapObject.isObstacle()){
                        break;
                    }

                    result.add(mapObject);
                }
            }else if('←' == symbol){
                int startCol = role.getCol();
                int endCol = 0;
                int row = role.getRow();

                for(int i = startCol - 1; i >= endCol; i--){

                    MapObject mapObject = map.getMapObject(row, i);

                    if(mapObject == null){
                        continue;
                    }

                    if(mapObject.isObstacle()){
                        break;
                    }

                    result.add(mapObject);
                }
            }else if('→' == symbol){
                int startCol = role.getCol();
                int endCol = map.getGridsCols() - 1;
                int row = role.getRow();

                for(int i = startCol + 1; i <= endCol; i++){

                    MapObject mapObject = map.getMapObject(row, i);

                    if(mapObject == null){
                        continue;
                    }

                    if(mapObject.isObstacle()){
                        break;
                    }

                    result.add(mapObject);
                }
            }
        }else if(role.isMonster()){

            int row = role.getRow();
            int col = role.getCol();
            MapObject mapObject;

            //上方
            mapObject = map.getMapObject(row - 1, col);
            result.add(mapObject);

            // 下方
            mapObject = map.getMapObject(row + 1, col);
            result.add(mapObject);

            // 左方
            mapObject = map.getMapObject(row, col - 1);
            result.add(mapObject);

            //右方
            mapObject = map.getMapObject(row, col + 1);
            result.add(mapObject);
        }

        return  result;
    }

    protected void effect(){

    }

    protected void effectEnd(){
        Role role = getRole();
        role.enterState(new Normal(role));
    }

    public void enterState(){
        int duration = getDuration();
        setCountdown(duration);
    }

    public void exitState(){

    }

    public int[] moveTarget(Map map){
        Role role = getRole();
        int[] moveTarget = new int[2];

        if(role.isCharacter()){
            int row;
            int col;
            while (true){
                row = role.getRow();
                col = role.getCol();
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

                if(map.isOutSide(row, col)){
                    System.out.println("超過邊界，無法移動，請重新選擇");
                }else {
                    break;
                }
            }
            moveTarget[0] = row;
            moveTarget[1] = col;
        }else if(role.isMonster()){
            int row = role.getRow();
            int col = role.getCol();

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

                if(!map.isOutSide(moveTarget[0] , moveTarget[1])){
                    break;
                }
            }
        }
        return moveTarget;
    }

    public void generateEffect(){
        int countdown = getCountdown();

        if(countdown == 0){
            effectEnd();
            return;
        }

        effect();
        setCountdown(countdown - 1);
    }

    public abstract String show();

    public void touch(MapObject mapObject){
        Role role = getRole();
        mapObject.onTouched(role);
    }

    public void takeDamage(MapObject attacker, int damage){
        Role role = getRole();
        role.setHp(role.getHp() - damage);
        System.out.println(role + " 被 " + attacker + " 攻擊 ，受到了" + damage + "傷害");

        if(attacker!= null && attacker.isMonster()){
            role.enterState(new Invincible(role));
        }
    }

    /*===================================*/
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
