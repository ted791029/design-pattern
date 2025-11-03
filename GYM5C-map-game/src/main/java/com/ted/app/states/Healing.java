package com.ted.app.states;

import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;

public class Healing extends State{
    public Healing(Role role) {
        super(role);
        setDuration(5);
    }

    protected void effect(){
        Role role = getRole();
        role.receiveHealing(30);
    }
    @Override
    public String show() {
        return "恢復";
    }
}
