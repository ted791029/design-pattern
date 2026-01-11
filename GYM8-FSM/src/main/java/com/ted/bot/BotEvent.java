package com.ted.bot;

import com.ted.fsm.Event;

public class BotEvent implements Event {
    private String eventName;

    private String payload;

    public BotEvent(String eventName, String payload) {
        this.eventName = eventName;
        this.payload = payload;
    }

    @Override
    public boolean match(Event event) {
        return getEventName().equals(event.getEventName());
    }


    //=============================

    @Override
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
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
