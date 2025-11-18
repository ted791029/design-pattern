package com.ted.app.handler.onePunch;

import com.ted.app.role.Role;

public abstract class OnePunchHandler {
    private OnePunchHandler next;

    public OnePunchHandler(OnePunchHandler next){
        this.next = next;
    }

    protected abstract void doHandling(Role caster, Role target);

    public void handle(Role caster, Role target){
        if(match(target)){
            doHandling(caster, target);
        }else {
            if(next != null){
                next.handle(caster, target);
            }
        }
    }

    protected abstract boolean match(Role target);
}
