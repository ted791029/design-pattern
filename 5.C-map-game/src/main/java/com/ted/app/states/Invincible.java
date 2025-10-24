package com.ted.app.states;

import com.ted.app.Map;
import com.ted.app.mapObjects.MapObject;
import com.ted.app.mapObjects.Roles.Role;

public class Invincible extends State{
    public Invincible(Role role) {
        super(role);
        setDuration(2);
    }

    public void enterState(){
        int duration = getDuration();
        setCountdown(duration);
        Role role = getRole();
        role.generateStateEffect();
    }

    @Override
    public void takeDamage(MapObject attacker, int damage){
        Role role = getRole();
        System.out.println(role + " 被 " + attacker + " 攻擊，但目前為狀態為 " + show() + " 未受到傷害");
    }

    @Override
    public String show() {
        return "無敵";
    }
}
