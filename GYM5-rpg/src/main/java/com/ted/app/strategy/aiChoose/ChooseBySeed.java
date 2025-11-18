package com.ted.app.strategy.aiChoose;

import com.ted.app.role.Role;
import com.ted.app.strategy.action.ActionStrategy;

import java.util.ArrayList;
import java.util.List;

public class ChooseBySeed implements AIChooseStrategy{
    private int seed = 0;

    @Override
    public ActionStrategy getAction(List<ActionStrategy> actionStrategies, Role caster) {
        int index = seed++ % actionStrategies.size();
        return actionStrategies.get(index);
    }

    @Override
    public List<Role> choose(List<Role> candidates, int targetCount) {
        List<Role> targets = new ArrayList<>();
        int n = candidates.size();
        for (int i = 0; i < targetCount; i++) {
            int index = (seed + i) % n;
            targets.add(candidates.get(index));
        }
        seed++; // 選完目標後 seed +1
        return targets;
    }

    //========================================================================

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }
}
