package com.ted.app.role.ai;

import com.ted.app.RPG;
import com.ted.app.Troop;
import com.ted.app.role.Role;
import com.ted.app.strategy.action.ActionStrategy;
import com.ted.app.strategy.action.skill.Skill;
import com.ted.app.strategy.aiChoose.AIChooseStrategy;

import java.util.ArrayList;
import java.util.List;

public class AI extends Role {

    private AIChooseStrategy aiChooseStrategy;

    public AI(int hp, String name, int mp, RPG rpg, int str, List<Skill> skills, Troop troop, AIChooseStrategy aiChooseStrategy) {
        super(hp, name, mp, rpg, str, skills, troop);
        setAiChooseStrategy(aiChooseStrategy);
    }

    @Override
    protected List<Role> choose(List<Role> candidates, int targetCount) {
        return aiChooseStrategy.choose(candidates, targetCount);
    }

    @Override
    public ActionStrategy getAction() {
        List<ActionStrategy> actionStrategies = new ArrayList<>();
        actionStrategies.add(BASIC_ATTACK);
        actionStrategies.addAll(getSkills());
        return aiChooseStrategy.getAction(actionStrategies, this);
    }

    @Override
    public void printChooseHint(List<Role> roles, int targetCount) {

    }

    //========================================================================

    public AIChooseStrategy getAiChooseStrategy() {
        return aiChooseStrategy;
    }

    public void setAiChooseStrategy(AIChooseStrategy aiChooseStrategy) {
        this.aiChooseStrategy = aiChooseStrategy;
    }
}
