package com.ted.fsm.status;

import com.ted.fsm.*;

import java.util.List;

public abstract class ComponentState<EN> extends FiniteStateMachine<EN> implements AbstractState<EN> {
    private Action<EN> enter;

    private Action<EN> exit;

    public ComponentState(AbstractState<EN> initial, List<? extends Transition<EN>> transitions, Action<EN> enter, Action<EN> exit) {
        super(initial, transitions);
        this.enter = enter;
        this.exit = exit;
    }


    @Override
    public boolean isComponent() {
        return true;
    }

    @Override
    public boolean match(AbstractState<EN> state) {
        return this == state;
    }

    @Override
    public abstract void response(Context<EN> context);

    //==========================
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
