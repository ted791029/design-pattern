package com.ted.app.observer;

import com.ted.app.role.Role;

public abstract class Observer {

    private Role caster;

    private Role target;

    public Observer(Role caster , Role target){
        this.caster = caster;
        this.target = target;
    }

    public abstract void update();

    //========================================================================

    public Role getCaster() {
        return caster;
    }

    public void setCaster(Role caster) {
        this.caster = caster;
    }

    public Role getTarget() {
        return target;
    }

    public void setTarget(Role target) {
        this.target = target;
    }
}
