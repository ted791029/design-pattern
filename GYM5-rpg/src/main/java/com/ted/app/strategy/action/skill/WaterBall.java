package com.ted.app.strategy.action.skill;

import com.ted.app.actionTarget.ActionTargetGroup;
import com.ted.app.actionTarget.ActionTargetType;
import com.ted.app.role.Role;

import java.util.ArrayList;
import java.util.List;

public class WaterBall extends Skill{

    public WaterBall(){
        List<ActionTargetGroup> actionTargetGroups = new ArrayList<>();
        actionTargetGroups.add(new ActionTargetGroup(ActionTargetType.ENEMY, 1));
        setActionTargetGroups(actionTargetGroups);
        setMpCost(50);
    }
    @Override
    protected void applyToTarget(Role caster, Role target) {
        target.damage(caster, 120);
    }

    @Override
    public String toString(){
        return "水球";
    }
}
