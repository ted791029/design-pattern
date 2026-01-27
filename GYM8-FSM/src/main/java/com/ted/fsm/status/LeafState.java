package com.ted.fsm.status;

import com.ted.fsm.AbstractState;
import com.ted.fsm.Action;
import com.ted.fsm.Context;

public abstract class LeafState<EN> implements AbstractState<EN> {

    private Action<EN> enter;

    private Action<EN> exit;

    public LeafState(Action<EN> enter, Action<EN> exit) {
        this.enter = enter;
        this.exit = exit;
    }

    @Override
    public boolean isComponent() {
        return false;
    }

    @Override
    public boolean match(AbstractState<EN> state) {
        return this == state;
    }

    @Override
    public abstract void response(Context<EN> context);


    //==========================================
    @Override
    public Action<EN> getEnter() {
        return enter;
    }

    @Override
    public void setEnter(Action<EN> enter) {
        this.enter = enter;
    }

    @Override
    public Action<EN> getExit() {
        return exit;
    }

    @Override
    public void setExit(Action<EN> exit) {
        this.exit = exit;
    }
}
