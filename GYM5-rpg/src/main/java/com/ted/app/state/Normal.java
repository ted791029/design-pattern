package com.ted.app.state;

import com.ted.app.role.Role;

public class Normal extends State{
    public Normal(Role role) {
        super(role);
    }

    @Override
    public String toString(){
        return "正常";
    }
}
