package com.ted.bot;

import com.ted.fsm.Event;

public class BotEvent<EN> implements Event<EN> {
    private EN eventName;

    private String payload;

    public BotEvent(EN eventName, String payload) {
        this.eventName = eventName;
        this.payload = payload;
    }

    public BotEvent(EN eventName) {
        this.eventName = eventName;
    }


    @Override
    public boolean match(Event event) {
        return getEventName().equals(event.getEventName());
    }


    //=============================

    @Override
    public EN getEventName() {
        return eventName;
    }

    public void setEventName(EN eventName) {
        this.eventName = eventName;
    }

    @Override
    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
