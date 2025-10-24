package com.ted.app.states;

import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;

public class Normal extends State{
    public Normal(Role role) {
        super(role);
        setDuration(0);
    }


    @Override
    protected void effectEnd(){

    }

    @Override
    public void enterState(){
        int duration = getDuration();
        Role role = getRole();
        setCountdown(duration);
        role.setActionCount(1);

    }

    @Override
    public String show() {
        return "一般";
    }
}
