package com.ted.fsm;

import java.util.List;
import java.util.Map;

public abstract class State extends FiniteStateMachine{

    private Action enter;

    private Action exit;

    public State(State initial, List<Transition> transitions, Map<String, String> resultMap, Action enter, Action exit) {
        super(initial, transitions, resultMap);
        this.enter = enter;
        this.exit = exit;
    }

    public boolean match(State state){
        return state == state;
    }

    abstract public void response(Context context);

    public Action getEnter() {
        return enter;
    }

    public void setEnter(Action enter) {
        this.enter = enter;
    }

    public Action getExit() {
        return exit;
    }

    public void setExit(Action exit) {
        this.exit = exit;
    }
}
