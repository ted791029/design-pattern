package com.ted.app;

import com.ted.app.actionTarget.ActionTargetGroup;
import com.ted.app.actionTarget.ActionTargetType;
import com.ted.app.role.Role;
import com.ted.app.strategy.action.ActionStrategy;

import java.util.ArrayList;
import java.util.List;

public class RPG {

    private Role hero;

    private Troop troop2;

    private List<Troop> troops;

    private void addCandidateWithTroops(Role caster, ActionTargetGroup actionTargetGroup, CandidateGroup candidateGroup) {
        for (Troop troop : troops) {
            List<Role> targets = troop.getRoles();

            for (Role target : targets) {

                if (isCandidate(caster, target, actionTargetGroup)) {
                    candidateGroup.add(target);
                }
            }
        }
    }

    public List<CandidateGroup> getCandidateGroups(Role caster, ActionStrategy actionStrategy) {
        List<CandidateGroup> candidateGroups = new ArrayList<>();
        List<ActionTargetGroup> actionTargetGroups = actionStrategy.getActionTargetGroups();

        //一組ActionTargetGroup會有一組CandidateGroup
        for (ActionTargetGroup actionTargetGroup : actionTargetGroups) {
            CandidateGroup candidateGroup = new CandidateGroup();
            addCandidateWithTroops(caster, actionTargetGroup, candidateGroup);
            candidateGroups.add(candidateGroup);
        }

        return candidateGroups;
    }

    private boolean isCandidate(Role caster, Role target, ActionTargetGroup actionTargetGroup) {
        ActionTargetType actionTargetType = actionTargetGroup.getActionTargetType();

        if (actionTargetType == ActionTargetType.ALLY) {
            return caster.isAlly(target);
        } else if (actionTargetType == ActionTargetType.ENEMY) {
            return caster.isEnemy(target);
        } else if (actionTargetType == ActionTargetType.SELF) {
            return caster.isSelf(target);
        }

        return false;
    }

    public boolean isEnd() {
        return hero.isDead() || troop2.isAnnihilated();
    }

    private void nextRound() {

        for (Troop troop : troops) {
            boolean isEnd = troop.battle();

            if (isEnd) {
                printEndHint();
                return;
            }
        }

        nextRound();
    }

    private void printEndHint() {
        if (hero.isDead()) {
            System.out.println("你失敗了！");
        } else if (troop2.isAnnihilated()) {
            System.out.println("你獲勝了！");
        }
    }

    public void start() {
        nextRound();
    }

    //========================================================================

    public Role getHero() {
        return hero;
    }

    public void setHero(Role hero) {
        this.hero = hero;
    }

    public Troop getTroop2() {
        return troop2;
    }

    public void setTroop2(Troop troop2) {
        this.troop2 = troop2;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }
}

