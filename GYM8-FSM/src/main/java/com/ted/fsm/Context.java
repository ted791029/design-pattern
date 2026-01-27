package com.ted.fsm;

import java.util.Map;

public class Context<EN> {

    private Event<EN> event;

    public Map<String, String> resultMap;

    private AbstractState<EN> state;

    public Context(Event<EN> event, Map<String, String> resultMap, AbstractState<EN> state) {
        this.event = event;
        this.resultMap = resultMap;
        this.state = state;
    }


    //===================================


    public Event<EN> getEvent() {
        return event;
    }

    public void setEvent(Event<EN> event) {
        this.event = event;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public AbstractState<EN> getState() {
        return state;
    }

    public void setState(AbstractState<EN> state) {
        this.state = state;
    }
}
