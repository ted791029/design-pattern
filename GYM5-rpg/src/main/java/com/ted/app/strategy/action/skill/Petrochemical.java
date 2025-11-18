package com.ted.app.strategy.action.skill;

import com.ted.app.actionTarget.ActionTargetGroup;
import com.ted.app.actionTarget.ActionTargetType;
import com.ted.app.role.Role;
import com.ted.app.state.Petrified;

import java.util.ArrayList;
import java.util.List;

public class Petrochemical extends Skill{

    public Petrochemical(){
        List<ActionTargetGroup> actionTargetGroups = new ArrayList<>();
        actionTargetGroups.add(new ActionTargetGroup(ActionTargetType.ENEMY, 1));
        setActionTargetGroups(actionTargetGroups);
        setMpCost(100);
    }
    @Override
    protected void applyToTarget(Role caster, Role target) {
        target.enterState(new Petrified(target));
    }

    @Override
    public String toString(){
        return "石化";
    }
}
