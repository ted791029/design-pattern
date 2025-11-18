package com.ted.app.handler.onePunch;

import com.ted.app.role.Role;
import com.ted.app.state.Petrified;
import com.ted.app.strategy.action.skill.Poison;

public class InPetrifiedDamage extends OnePunchHandler{
    public InPetrifiedDamage(OnePunchHandler next) {
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
        return Petrified.class.equals(target.getState().getClass());
    }
}
