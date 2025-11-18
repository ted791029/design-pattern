package com.ted.app.handler.onePunch;

import com.ted.app.role.Role;
import com.ted.app.state.Normal;

public class InNormalDamage extends OnePunchHandler{
    public InNormalDamage(OnePunchHandler next) {
        super(next);
    }

    @Override
    protected void doHandling(Role caster, Role target) {
        target.damage(caster, 100);
    }

    @Override
    protected boolean match(Role target) {
        return Normal.class.equals(target.getState().getClass());
    }
}
