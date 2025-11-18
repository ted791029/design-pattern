package com.ted.app.strategy.action.skill;

import com.ted.app.actionTarget.ActionTargetGroup;
import com.ted.app.actionTarget.ActionTargetType;
import com.ted.app.handler.onePunch.*;
import com.ted.app.role.Role;

import java.util.ArrayList;
import java.util.List;

public class OnePunch extends Skill{

    private OnePunchHandler handler;


    public OnePunch(){
        List<ActionTargetGroup> actionTargetGroups = new ArrayList<>();
        actionTargetGroups.add(new ActionTargetGroup(ActionTargetType.ENEMY, 1));
        setActionTargetGroups(actionTargetGroups);
        setMpCost(180);

        handler = new HighHpDamage(
                new InPoisonedDamage(
                        new InPetrifiedDamage(
                                new InInspiredDamage(
                                        new InNormalDamage(
                                                null
                                        )
                                )
                        )
                )
        );
    }

    @Override
    protected void applyToTarget(Role caster, Role target) {
        handler.handle(caster, target);
    }

    @Override
    public String toString(){
        return "一拳攻擊";
    }
}
