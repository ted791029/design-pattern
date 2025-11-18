package com.ted.app.strategy.action.skill;

import com.ted.app.actionTarget.ActionTargetGroup;
import com.ted.app.actionTarget.ActionTargetType;
import com.ted.app.role.Role;
import com.ted.app.state.Inspired;

import java.util.ArrayList;
import java.util.List;

public class CheerUp extends Skill {

    public CheerUp() {
        List<ActionTargetGroup> actionTargetGroups = new ArrayList<>();
        actionTargetGroups.add(new ActionTargetGroup(ActionTargetType.ALLY, 3));
        setActionTargetGroups(actionTargetGroups);
        setMpCost(100);
    }

    @Override
    protected void applyToTarget(Role caster, Role target) {
        target.enterState(new Inspired(target));
    }

    @Override
    public String toString(){
        return "鼓舞";
    }
}
