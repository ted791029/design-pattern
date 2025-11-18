package com.ted.app.state;

import com.ted.app.role.Role;

public class Petrified extends State{

    public Petrified(Role role) {
        super(role);
        setDuration(3);
    }

    @Override
    public void takeTurn(){

    }

    @Override
    public String toString(){
        return "石化";
    }
}
