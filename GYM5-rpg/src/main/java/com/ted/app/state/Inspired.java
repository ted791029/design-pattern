package com.ted.app.state;

import com.ted.app.role.Role;

public class Inspired extends State{

    public Inspired(Role role) {
        super(role);
        setDuration(3);
    }

    @Override
    public int damageBonus(int damage){
        return damage + 50;
    }


    @Override
    public String toString(){
        return "受到鼓舞";
    }
}
