package com.ted.app.states;

import com.ted.app.Map;
import com.ted.app.mapObjects.MapObject;
import com.ted.app.mapObjects.Roles.Role;

public class Stockpile extends State{
    public Stockpile(Map map, Role role) {
        super(map, role);
        setDuration(2);
    }

    public void enterState(){
        int duration = getDuration();
        setCountdown(duration);
        Role role = getRole();
        role.generateStateEffect();
    }
    @Override
    protected void effectEnd(){
        Map map = getMap();
        Role role = getRole();
        role.enterState(new Erupting(map, role));
    }

    @Override
    public void takeDamage(MapObject attacker, int damage){
        Role role = getRole();
        role.setHp(role.getHp() - damage);
        System.out.println(role + " 被 " + attacker + " 攻擊 ，受到了" + damage + "傷害");
        Map map = getMap();
        role.enterState(new Normal(map, role));
    }

    @Override
    public String show() {
        return "蓄力";
    }
}
