package com.ted.app.handler.onePunch;

import com.ted.app.role.Role;
import com.ted.app.state.Poisoned;

public class InPoisonedDamage extends OnePunchHandler{
    public InPoisonedDamage(OnePunchHandler next) {
        super(next);
    }

    @Override
    protected void doHandling(Role caster, Role target) {
        for (int i = 0; i < 3; i++){
            target.damage(caster, 80);
        }
    }

    @Override
    protected boolean match(Role target) {
        return Poisoned.class.equals(target.getState().getClass());
    }
}
