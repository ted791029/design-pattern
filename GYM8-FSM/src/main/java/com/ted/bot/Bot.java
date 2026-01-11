package com.ted.bot;

import com.ted.fsm.Event;
import com.ted.fsm.FiniteStateMachine;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bot{

    private Community community;

    private FiniteStateMachine finiteStateMachine;

    private int quota;

    public void useQuota(int quota){
        setQuota(this.quota - quota);
    }

    public Map<String, String> sendEvent(Event event){
        finiteStateMachine.sendEvent(event);
        Map<String, String> resultMap =  new LinkedHashMap<>(finiteStateMachine.getResultMap());;
        finiteStateMachine.clearResultMap();
        return resultMap;
    }

    //=======================

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public FiniteStateMachine getFiniteStateMachine() {
        return finiteStateMachine;
    }

    public void setFiniteStateMachine(FiniteStateMachine finiteStateMachine) {
        this.finiteStateMachine = finiteStateMachine;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {

        if(quota <= 0){
            this.quota = 0;
            return;
        }

        this.quota = quota;
    }
}
