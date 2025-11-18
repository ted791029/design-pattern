package com.ted.app.strategy.action.skill;

import com.ted.app.actionTarget.ActionTargetGroup;
import com.ted.app.actionTarget.ActionTargetType;
import com.ted.app.observer.CurseEffect;
import com.ted.app.observer.Observer;
import com.ted.app.role.Role;

import java.util.ArrayList;
import java.util.List;

public class Curse extends Skill{

    public Curse(){
        List<ActionTargetGroup> actionTargetGroups = new ArrayList<>();
        actionTargetGroups.add(new ActionTargetGroup(ActionTargetType.ENEMY, 1));
        setActionTargetGroups(actionTargetGroups);
        setMpCost(100);
    }
    @Override
    protected void applyToTarget(Role caster, Role target) {
        Observer observer = new CurseEffect(caster, target);
        target.register(observer);
    }

    @Override
    public String toString(){
        return "詛咒";
    }
}
