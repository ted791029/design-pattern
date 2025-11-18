package com.ted.app.state;

import com.ted.app.CandidateGroup;
import com.ted.app.RPG;
import com.ted.app.role.Role;
import com.ted.app.strategy.action.ActionStrategy;

import java.util.List;

public class State {

    private int duration;

    private int countdown;

    private Role role;

    public State(Role role) {
        this.role = role;
    }

    public void countDown() {
        if (countdown == 0) {
            role.enterState(new Normal(role));
            return;
        }
        countdown--;
    }

    public int damageBonus(int damage) {
        return damage;
    }

    public void effect() {

    }

    public void enterState() {
        countdown = duration;
    }

    public void exitState() {

    }

    public void takeTurn() {
        ActionStrategy actionStrategy = null;

        while (true) {
            role.printGetActionHint();
            actionStrategy = role.getAction();
            int mpCost = actionStrategy.getMpCost();

            if (role.hasEnoughMP(mpCost)) {
                break;
            }
        }

        RPG rpg = role.getRpg();
        List<CandidateGroup> candidateGroups = rpg.getCandidateGroups(role, actionStrategy);
        List<Role> actionTargets = role.getActionTargets(actionStrategy, candidateGroups);
        role.executeAction(actionStrategy, actionTargets);
    }

    //========================================================================

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
