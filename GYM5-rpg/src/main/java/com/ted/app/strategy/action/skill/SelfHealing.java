package com.ted.app.strategy.action.skill;

import com.ted.app.actionTarget.ActionTargetGroup;
import com.ted.app.actionTarget.ActionTargetType;
import com.ted.app.role.Role;

import java.util.ArrayList;
import java.util.List;

public class SelfHealing extends Skill{

    public SelfHealing(){
        List<ActionTargetGroup> actionTargetGroups = new ArrayList<>();
        actionTargetGroups.add(new ActionTargetGroup(ActionTargetType.SELF, 1));
        setActionTargetGroups(actionTargetGroups);
        setMpCost(50);
    }

    @Override
    protected void applyToTarget(Role caster, Role target) {
        target.addHp(150);
    }

    @Override
    protected void printApplyToTargetsHint(Role caster, List<Role> targets){
        System.out.printf("%s 使用了 %s。\n", caster, this);
    }

    @Override
    public String toString(){
        return "自我治療";
    }
}
