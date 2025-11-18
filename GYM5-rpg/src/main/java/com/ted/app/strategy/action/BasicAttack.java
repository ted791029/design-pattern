package com.ted.app.strategy.action;

import com.ted.app.actionTarget.ActionTargetGroup;
import com.ted.app.actionTarget.ActionTargetType;
import com.ted.app.role.Role;

import java.util.ArrayList;
import java.util.List;

public class BasicAttack extends ActionStrategy{

    public BasicAttack(){
        List<ActionTargetGroup> actionTargetGroups = new ArrayList<>();
        actionTargetGroups.add(new ActionTargetGroup(ActionTargetType.ENEMY, 1));
        setActionTargetGroups(actionTargetGroups);
        setMpCost(0);
    }
    @Override
    protected void applyToTarget(Role caster, Role target){
        target.damage(caster, caster.getStr());
    }

    @Override
    protected void printApplyToTargetsHint(Role caster, List<Role> targets){
        System.out.printf("%s 攻擊 %s。\n", caster, targets.get(0));
    }

    @Override
    public String toString(){
        return "普通攻擊";
    }
}
