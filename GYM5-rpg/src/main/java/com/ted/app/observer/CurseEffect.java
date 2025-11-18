package com.ted.app.observer;

import com.ted.app.role.Role;

public class CurseEffect extends Observer{
    public CurseEffect(Role caster , Role target) {
        super(caster, target);
    }

    @Override
    public void update() {
        Role caster = getCaster();

        if(caster.isDead()){
            return;
        }

        Role target = getTarget();

        caster.addHp(target.getMp());
    }
}
