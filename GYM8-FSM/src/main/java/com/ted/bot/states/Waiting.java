package com.ted.bot.states;

import com.ted.bot.Id;
import com.ted.fsm.*;

import java.util.List;
import java.util.Map;

public class Waiting extends Record{

    public Waiting(State initial, List<Transition> transitions, Map<String, String> resultMap, Action enter, Action exit, Id recorderId, Map<String, Record> childrenMap) {
        super(initial, transitions, resultMap, enter, exit, recorderId, childrenMap);
    }

    @Override
    public void response(Context context) {

    }
}
