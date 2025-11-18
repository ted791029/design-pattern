package com.ted.app.role.ai;

import com.ted.app.RPG;
import com.ted.app.Troop;
import com.ted.app.observer.SummonEffect;
import com.ted.app.role.Role;
import com.ted.app.strategy.action.ActionStrategy;
import com.ted.app.strategy.aiChoose.ChooseBySeed;

import java.util.ArrayList;

public class Slime extends AI {

    public Slime(Role caster, String name, RPG rpg, Troop troop) {
        super(100, name, 0, rpg, 50, new ArrayList<>(), troop, new ChooseBySeed());
        register(new SummonEffect(caster, this));
    }


    @Override
    public ActionStrategy getAction() {
        return BASIC_ATTACK;
    }
}
