package com.ted.app.states;

import com.ted.app.Map;
import com.ted.app.mapObjects.MapObject;
import com.ted.app.mapObjects.Roles.Role;

public class Accelerated extends State{
    public Accelerated(Role role) {
        super(role);
        setDuration(3);
    }

    @Override
    public void enterState(){
        int duration = getDuration();
        Role role = getRole();
        setCountdown(duration);
        role.setActionCount(2);
    }

    @Override
    public void exitState(){
        Role role = getRole();
        role.setActionCount(1);
    }

    @Override
    public void takeDamage(MapObject attacker, int damage){
        Role role = getRole();
        role.setHp(role.getHp() - damage);
        System.out.println(role + " 被 " + attacker + " 攻擊 ，受到了" + damage + "傷害");
        role.enterState(new Normal(role));
    }

    @Override
    public String show() {
        return "加速";
    }
}
