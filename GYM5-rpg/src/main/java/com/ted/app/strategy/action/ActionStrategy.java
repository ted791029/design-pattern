package com.ted.app.strategy.action;

import com.ted.app.actionTarget.ActionTargetGroup;
import com.ted.app.role.Role;

import java.util.List;

public abstract class ActionStrategy {

    private List<ActionTargetGroup> actionTargetGroups;

    private int mpCost;

    public void execute(Role caster) {

    }

    protected abstract void applyToTarget(Role caster, Role target);

    public void applyToTargets(Role caster, List<Role> targets) {
        printApplyToTargetsHint(caster, targets);

        for (Role target : targets) {
            applyToTarget(caster, target);
        }
    }

    protected void printApplyToTargetsHint(Role caster, List<Role> targets) {
        System.out.printf("%s ", caster);

        for (int i = 0; i < targets.size(); i++) {
            if (i == 0) {
                System.out.printf("對 ");
            }

            System.out.printf("%s", targets.get(i));

            if (i < targets.size() - 1) {
                System.out.printf(",");
            }
            System.out.printf(" ");
        }
        System.out.printf("使用了 %s。", this);
        System.out.println();
    }

    //========================================================================

    public List<ActionTargetGroup> getActionTargetGroups() {
        return actionTargetGroups;
    }

    public void setActionTargetGroups(List<ActionTargetGroup> actionTargetGroups) {
        this.actionTargetGroups = actionTargetGroups;
    }

    public int getMpCost() {
        return mpCost;
    }

    public void setMpCost(int mpCost) {
        this.mpCost = mpCost;
    }
}
