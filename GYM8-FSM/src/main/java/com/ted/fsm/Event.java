package com.ted.fsm;

public interface Event {

    public String getEventName();

    public String getPayload();

    public  boolean match(Event event);
}
