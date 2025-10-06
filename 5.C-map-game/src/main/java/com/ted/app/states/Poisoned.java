package com.ted.app.states;

import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;
import com.ted.app.mapObjects.Treasures.Poison;

public class Poisoned extends State{
    public Poisoned(Map map, Role role) {
        super(map, role);
        setDuration(3);
    }

    protected void effect(){
        Role role = getRole();
        int damage = 15;
        role.takeDamage(new Poison(), damage);
    }
    @Override
    public String show() {
        return "中毒";
    }
}
