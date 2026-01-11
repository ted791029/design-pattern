package com.ted.fsm;

public class Transition {

    private Action action;

    private State from;

    private Guard guard;

    private State to;

    private Trigger trigger;


    public Transition(Action action, Event event, State from, Guard guard, State to) {
        this.action = action;
        this.trigger = new Trigger(event);
        this.from = from;
        this.guard = guard;
        this.to = to;
    }

    public Action getToEnterAction(){
        return to.getEnter();
    }

    public boolean match(Context context){

        State state = context.getState();
        Event event = context.getEvent();

        if(!from.match(state)){
            return false;
        }

        if(!trigger.match(event)){
            return false;
        }

        if(guard != null && !guard.evaluate(context)){
            return false;
        }

        return true;
    }

    //==================================

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public State getFrom() {
        return from;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    public Guard getGuard() {
        return guard;
    }

    public void setGuard(Guard guard) {
        this.guard = guard;
    }

    public State getTo() {
        return to;
    }

    public void setTo(State to) {
        this.to = to;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
}
