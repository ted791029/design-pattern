package com.ted.app.waterballCommunity;

import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.bot.BotEvent;
import com.ted.app.BotEventResult;

public abstract class BotResponseHandler {

    private BotResponseHandler next;

    private BotEventResult eventResult;

    public BotResponseHandler(BotResponseHandler next, BotEventResult eventResult) {
        this.next = next;
        this.eventResult = eventResult;
    }

    public void handle(BotEventResult eventResult, JsonObject jsonObject) throws InterruptedException {

        if (match(eventResult)) {
            BotEvent<BotEventName> botEvent = handling(jsonObject);
            EventManager.submit(botEvent);
        } else if (next != null) {
            next.handle(eventResult, jsonObject);
        }

    }

    protected boolean match(BotEventResult eventResult) {
        return this.eventResult == eventResult;
    }

    abstract protected BotEvent<BotEventName> handling(JsonObject jsonObject);

    //========================

    public BotResponseHandler getNext() {
        return next;
    }

    public void setNext(BotResponseHandler next) {
        this.next = next;
    }

    public BotEventResult getEventResult() {
        return eventResult;
    }

    public void setEventResult(BotEventResult eventResult) {
        this.eventResult = eventResult;
    }
}
