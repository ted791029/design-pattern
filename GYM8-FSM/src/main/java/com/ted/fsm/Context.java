package com.ted.fsm;

import java.util.Map;

public class Context {

    private Event event;

    private Map<String, String> resultMap;

    private State state;

    public Context(Event event, Map<String, String> resultMap, State state) {
        this.event = event;
        this.resultMap = resultMap;
        this.state = state;
    }

    public void addEventResult(String key, String result){
        resultMap.put(key, result);
    }

    public String getEventPayload(){
        return event.getPayload();
    }


    //===================================


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
