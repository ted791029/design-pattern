package com.ted.app.states;

import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;

public class Teleport extends State{
    public Teleport(Role role) {
        super(role);
        setDuration(1);
    }

    protected void effect(){
        Role role = getRole();
        role.teleportTo();

    }

    @Override
    public String show() {
        return "瞬身";
    }
}
