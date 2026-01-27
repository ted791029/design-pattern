package com.ted.fsm;

public interface Guard<EN> {

    public boolean evaluate(Context<EN> context);
}
