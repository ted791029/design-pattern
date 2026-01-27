package com.ted.fsm;

public class Trigger<EN> {

    private Event<EN> event;

    public Trigger(Event<EN> event) {
        this.event = event;
    }

    public boolean match(Event<EN> event) {
        return this.event.match(event);
    }
}
