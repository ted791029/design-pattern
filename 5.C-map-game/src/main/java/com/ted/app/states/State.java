package com.ted.app.states;

import com.ted.app.Map;
import com.ted.app.mapObjects.MapObject;
import com.ted.app.mapObjects.Roles.Role;

import java.util.List;

public abstract class State {

    private int duration;

    private int countdown;

    private Map map;

    private Role role;

    public State(Map map, Role role) {
        setMap(map);
        setRole(role);
    }

    public void action(){

    }

    public List<MapObject> attackRange(){
        return null;
    }

    protected void effect(){

    }

    protected void effectEnd(){
        Map map = getMap();
        Role role = getRole();
        role.enterState(new Normal(map, role));
    }

    public void enterState(){
        int duration = getDuration();
        setCountdown(duration);
    }

    public void exitState(){

    }

    public int[] moveTarget(){
        return null;
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
            Map map = getMap();
            role.enterState(new Invincible(map, role));
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

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
