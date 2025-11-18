package com.ted.app.handler.onePunch;

import com.ted.app.role.Role;
import com.ted.app.state.Inspired;
import com.ted.app.state.Normal;

public class InInspiredDamage extends OnePunchHandler{
    public InInspiredDamage(OnePunchHandler next) {
        super(next);
    }

    @Override
    protected void doHandling(Role caster, Role target) {
        target.damage(caster, 100);
        target.enterState(new Normal(target));
    }

    @Override
    protected boolean match(Role target) {
        return Inspired.class.equals(target.getState().getClass());
    }
}
