package com.ted.bot.states;

import java.util.List;
import java.util.Map;

import com.ted.fsm.Action;
import com.ted.fsm.Context;
import com.ted.fsm.State;
import com.ted.fsm.Transition;

public class Normal extends State {

    public Normal(State initial, List<Transition> transitions, Map<String, String> resultMap, Action enter, Action exit) {
        super(initial, transitions, resultMap, enter, exit);
    }

    @Override
    public void response(Context context) {
        
    }

}
