package com.ted.app.handler.onePunch;

import com.ted.app.role.Role;

public class HighHpDamage extends OnePunchHandler{
    public HighHpDamage(OnePunchHandler next) {
        super(next);
    }

    @Override
    protected void doHandling(Role caster, Role target) {
        target.damage(caster, 300);
    }

    @Override
    protected boolean match(Role target) {
        return target.getHp() >= 500;
    }
}
