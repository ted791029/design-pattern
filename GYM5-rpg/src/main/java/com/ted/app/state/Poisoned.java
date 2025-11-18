package com.ted.app.state;

import com.ted.app.role.Role;
import com.ted.app.strategy.action.skill.Poison;

public class Poisoned extends State{

    public Poisoned(Role role) {
        super(role);
        setDuration(3);
    }

    @Override
    public void effect(){
        Role role = getRole();
        role.damage(new Poison(), 30);
    }

    @Override
    public String toString(){
        return "中毒";
    }
}
