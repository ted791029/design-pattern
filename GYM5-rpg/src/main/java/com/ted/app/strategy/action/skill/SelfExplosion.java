package com.ted.app.strategy.action.skill;

import com.ted.app.actionTarget.ActionTargetGroup;
import com.ted.app.actionTarget.ActionTargetType;
import com.ted.app.role.Role;

import java.util.ArrayList;
import java.util.List;

public class SelfExplosion extends Skill{

    public SelfExplosion(){
        List<ActionTargetGroup> actionTargetGroups = new ArrayList<>();
        actionTargetGroups.add(new ActionTargetGroup(ActionTargetType.ALLY, -1));
        actionTargetGroups.add(new ActionTargetGroup(ActionTargetType.ENEMY, -1));
        setActionTargetGroups(actionTargetGroups);
        setMpCost(200);
    }

    @Override
    public void execute(Role caster){
        caster.dead();
    }
    @Override
    protected void applyToTarget(Role caster, Role target) {
        target.damage(caster, 150);
    }

    @Override
    public String toString(){
        return "自爆";
    }
}
