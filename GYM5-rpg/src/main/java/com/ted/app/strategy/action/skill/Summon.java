package com.ted.app.strategy.action.skill;

import com.ted.app.RPG;
import com.ted.app.Troop;
import com.ted.app.actionTarget.ActionTargetGroup;
import com.ted.app.actionTarget.ActionTargetType;
import com.ted.app.role.Role;
import com.ted.app.role.ai.Slime;

import java.util.ArrayList;
import java.util.List;

public class Summon extends Skill{
    private int count;

    public Summon(){
        List<ActionTargetGroup> actionTargetGroups = new ArrayList<>();
        actionTargetGroups.add(new ActionTargetGroup(ActionTargetType.None, 0));
        setActionTargetGroups(actionTargetGroups);
        setMpCost(150);
        this.count = 1;
    }

    @Override
    public void execute(Role caster){
        RPG rpg = caster.getRpg();
        Troop troop = caster.getTroop();
        String name = "Slime";
        Slime slime = new Slime(caster, name, rpg, troop);
        troop.add(slime);
    }

    @Override
    protected void applyToTarget(Role caster, Role target) {

    }

    @Override
    public String toString(){
        return "召喚";
    }
}
