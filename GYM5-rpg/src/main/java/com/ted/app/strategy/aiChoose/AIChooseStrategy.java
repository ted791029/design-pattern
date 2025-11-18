package com.ted.app.strategy.aiChoose;

import com.ted.app.role.Role;
import com.ted.app.strategy.action.ActionStrategy;

import java.util.List;

public interface AIChooseStrategy {

    ActionStrategy getAction(List<ActionStrategy> actionStrategies, Role caster);

    List<Role> choose(List<Role> candidates, int targetCount);
}
