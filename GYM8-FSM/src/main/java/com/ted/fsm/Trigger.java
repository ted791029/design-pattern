package com.ted.fsm;

public class Trigger {

    private Event event;

    public Trigger(Event event) {
        this.event = event;
    }

    public boolean match(Event event){
        return this.event.match(event);
    }
}
