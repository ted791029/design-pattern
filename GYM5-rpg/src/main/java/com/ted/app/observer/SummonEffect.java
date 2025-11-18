package com.ted.app.observer;

import com.ted.app.role.Role;

public class SummonEffect extends Observer{
    public SummonEffect(Role caster , Role target) {
        super(caster, target);
    }

    @Override
    public void update() {
        Role caster = getCaster();

        if(caster.isDead()){
            return;
        }

        caster.addHp(30);
    }
}
