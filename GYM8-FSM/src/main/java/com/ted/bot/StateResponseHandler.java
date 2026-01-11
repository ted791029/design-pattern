package com.ted.bot;

import com.ted.fsm.Context;

abstract public class StateResponseHandler {

    private StateResponseHandler next;

    public StateResponseHandler(StateResponseHandler next) {
        this.next = next;
    }

    public void handle(Context context){

        if(match(context)){
            handling(context);
        }

        if(next != null){
            next.handle(context);
        }

    }

    abstract protected boolean match(Context context);

    abstract protected void handling(Context context);
}
