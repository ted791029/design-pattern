package com.ted.app.mapObjects.Roles;

import com.ted.app.Game;
import com.ted.app.Map;
import com.ted.app.mapObjects.MapObject;
import com.ted.app.states.Normal;
import com.ted.app.states.State;

import java.util.ArrayList;
import java.util.List;

import static com.ted.app.Game.rand;

public abstract class Role extends MapObject {

    private int actionCount = 1;

    private int attackDamage;

    private int hp;

    private String direction;

    private boolean isStateEffect = false;

    private int hpUpLimit;

    private int originDamage;

    private State state = new Normal(this);

    public Role(Game game, Map map, int row, int col, int no, char symbol) {
        super(game, map, row, col, no, symbol);
    }

    public void action(){
        State state = getState();
        state.action();
    }

    public void attack(){
        List<MapObject> attackTargets = attackTargets();

        for (MapObject attackTarget : attackTargets){

            if(attackTarget instanceof Role attackTargetRole){
                int attackDamage = getAttackDamage();
                attackTargetRole.takeDamage(this, attackDamage);
            }
        }
    }

    private List<MapObject> attackRange(){
        State state = getState();
        Map map = getMap();
        return state.attackRange(map);
    }

    public List<MapObject> attackTargets(){
        List<MapObject> targets = new ArrayList<>();
        List<MapObject> objects = attackRange();

        for(MapObject object : objects){

            if(isAttackTarget(object)){
                targets.add(object);
            }
        }
        return targets;
    }

    protected abstract  void dead();

    public void enterState(State state){
        State originState = getState();
        originState.exitState();
        setState(state);
        State newState = getState();
        newState.enterState();
    }

    public void generateStateEffect(){
        State state = getState();
        state.generateEffect();
    }

    protected abstract boolean isAttackTarget(MapObject object);

    private boolean isDead(){
        int hp = getHp();
        return hp <= 0;
    }

    public void move(){
        int[] targetPoints = moveTarget();
        int originRow = getRow();
        int originCol = getCol();
        int targetRow = targetPoints[0];
        int targetCol = targetPoints[1];
        updateDirection(originRow, originCol, targetRow, targetCol);
        Map map = getMap();
        MapObject mapObject = map.getMapObject(targetRow, targetCol);

        if(mapObject != null){
            touch(mapObject);
        }else{
            setRow(targetRow);
            setCol(targetCol);
            map.remove(originRow, originCol);
            map.insert(targetRow, targetCol, this);
        }

    }

    private int[] moveTarget(){
        State state = getState();
        Map map = getMap();
        return state.moveTarget(map);
    }

    @Override
    public void onTouched(Role role) {
        System.out.println(role + " 觸碰了 " + this);
    }

    public void receiveHealing(int healing) {
        int hp = getHp();
        setHp(hp + healing);
        hp = getHp();
        int hpUpLimit = getHpUpLimit();

        if(hp == hpUpLimit){
            Map map = getMap();
            enterState(new Normal(this));
        }
    }

    public void takeDamage(MapObject attacker, int damage){
        State state = getState();
        state.takeDamage(attacker, damage);
        if(isDead()){
            dead();
        }
    }

    public void teleportTo(){
        Map map = getMap();
        int mapRows = map.getGridsRows();
        int mapCols = map.getGridsCols();
        int originRow = getRow();
        int originCol = getCol();
        int targetRow;
        int targetCol;

        while (true){
            targetRow = rand.nextInt(mapRows);
            targetCol = rand.nextInt(mapCols);

            MapObject mapObject = map.getMapObject(targetRow, targetCol);

            if(mapObject == null){
                break;
            }
        }

        map.remove(originRow, originCol);
        setRow(targetRow);
        setCol(targetCol);
        map.insert(targetRow, targetCol, this);
    }

    protected abstract void updateDirection(int originRow, int originCol, int targetRow, int targetCol);

    private void touch(MapObject object){
        State state = getState();
        state.touch(object);
    }

    /*===================================*/

    public int getActionCount() {
        return actionCount;
    }

    public void setActionCount(int actionCount) {
        this.actionCount = actionCount;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {

        if(hp > hpUpLimit){
            hp = hpUpLimit;
        }

        this.hp = hp;
    }

    public boolean isStateEffect() {
        return isStateEffect;
    }

    public void setStateEffect(boolean stateEffect) {
        isStateEffect = stateEffect;
    }

    public int getHpUpLimit() {
        return hpUpLimit;
    }

    public void setHpUpLimit(int hpUpLimit) {
        this.hpUpLimit = hpUpLimit;
    }

    public int getOriginDamage() {
        return originDamage;
    }

    public void setOriginDamage(int originDamage) {
        this.originDamage = originDamage;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
