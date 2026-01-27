package com.ted.fsm;

public interface AbstractState<EN> {

    public boolean isComponent();

    public boolean match(AbstractState<EN> state);

    public void response(Context<EN> context);

    public Action<EN> getEnter();

    public void setEnter(Action<EN> enter);

    public Action<EN> getExit();

    public void setExit(Action<EN> exit);
}
