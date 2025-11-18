package com.ted.app.role;

import com.ted.app.RPG;
import com.ted.app.Troop;
import com.ted.app.strategy.action.ActionStrategy;
import com.ted.app.strategy.action.skill.Skill;

import java.util.ArrayList;
import java.util.List;

import static com.ted.app.util.ScannerUtil.getInputInteger;
import static com.ted.app.util.ScannerUtil.getInputIntegers;

public class Hero extends Role {
    private char symbol;

    public Hero(int hp, String name, int mp, RPG rpg, int str, List<Skill> skills, Troop troop) {
        super(hp, name, mp, rpg, str, skills, troop);
        this.symbol = 'h';
    }

    @Override
    protected List<Role> choose(List<Role> candidates, int targetCount) {
        List<Role> result = new ArrayList<>();
        int max = candidates.size() - 1;
        List<Integer> chosen = getInputIntegers(0, max, ",", targetCount);

        for (int index : chosen) {
            result.add(candidates.get(index));
        }
        return result;
    }

    @Override
    public ActionStrategy getAction() {
        List<ActionStrategy> actionStrategies = new ArrayList<>();
        actionStrategies.add(BASIC_ATTACK);
        actionStrategies.addAll(getSkills());
        int max = actionStrategies.size() - 1;
        int index = getInputInteger(0, max);
        return actionStrategies.get(index);
    }


    //========================================================================

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
