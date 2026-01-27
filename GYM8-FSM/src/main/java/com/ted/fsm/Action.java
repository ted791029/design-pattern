package com.ted.fsm;

public interface Action<EN> {

    public void execute(Context<EN> context);
}
