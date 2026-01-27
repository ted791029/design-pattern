package com.ted.fsm;


public class Transition<EN> {

    private Action<EN> action;

    private AbstractState<EN> from;

    private Guard<EN> guard;

    private AbstractState<EN> to;

    private Trigger<EN> trigger;

    public Transition(Action<EN> action, Event<EN> event, AbstractState<EN> from, Guard<EN> guard, AbstractState<EN> to) {
        this.action = action;
        this.trigger = new Trigger<>(event);
        this.from = from;
        this.guard = guard;
        this.to = to;
    }

    public Action<EN> getToEnterAction() {
        return to.getEnter();
    }

    public boolean match(Context<EN> context) {

        AbstractState<EN> state = context.getState();
        Event<EN> event = context.getEvent();

        if (!from.match(state)) {
            return false;
        }

        if (!trigger.match(event)) {
            return false;
        }

        if (guard != null && !guard.evaluate(context)) {
            return false;
        }

        return true;
    }

    //==================================

    public Action<EN> getAction() {
        return action;
    }

    public void setAction(Action<EN> action) {
        this.action = action;
    }

    public AbstractState<EN> getFrom() {
        return from;
    }

    public void setFrom(AbstractState<EN> from) {
        this.from = from;
    }

    public Guard<EN> getGuard() {
        return guard;
    }

    public void setGuard(Guard<EN> guard) {
        this.guard = guard;
    }

    public AbstractState<EN> getTo() {
        return to;
    }

    public void setTo(AbstractState<EN> to) {
        this.to = to;
    }

    public Trigger<EN> getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger<EN> trigger) {
        this.trigger = trigger;
    }
}
