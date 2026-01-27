package com.ted.fsm;

public interface Event<EN> {

    public EN getEventName();

    public String getPayload();

    public boolean match(Event<EN> event);
}
