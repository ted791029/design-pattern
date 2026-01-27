package com.ted.bot;

import com.ted.fsm.Context;

import java.util.Map;

public class BotContext<EN> extends Context<EN> {

    private BotEvent<EN> event;

    private Map<String, String> resultMap;

    private BotAbstractState<EN> state;


    public BotContext(BotEvent<EN> event, Map<String, String> resultMap, BotAbstractState<EN> state) {
        super(event, resultMap, state);
        this.event = event;
        this.resultMap = resultMap;
        this.state = state;
    }


    public void addEventResult(String key, String result) {
        resultMap.put(key, result);
    }

    public String getEventPayload() {
        return event.getPayload();
    }

    //===================================

    public BotEvent<EN> getEvent() {
        return event;
    }

    public void setEvent(BotEvent<EN> event) {
        this.event = event;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public BotAbstractState<EN> getState() {
        return state;
    }

    public void setState(BotAbstractState<EN> state) {
        this.state = state;
    }
}
