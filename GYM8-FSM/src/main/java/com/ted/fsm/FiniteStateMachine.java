package com.ted.fsm;

import com.ted.fsm.status.ComponentState;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FiniteStateMachine<EN> {

    private AbstractState<EN> current;

    private AbstractState<EN> initial;

    public Map<String, String> resultMap = new LinkedHashMap<>();

    private Map<AbstractState<EN>, List<Transition<EN>>> transitionMap;


    public FiniteStateMachine(AbstractState<EN> initial, List<? extends Transition<EN>> transitions) {
        this.current = initial;
        this.initial = initial;
        setTransitionMap(transitions);
    }

    public void clearMap() {
        resultMap.clear();
    }

    public Map<String, String> sendEvent(Event<EN> event) {
        Context<EN> context = initContext(event);
        sendEventToChildFsm(event);
        current.response(context);
        transfer(context);
        return context.getResultMap();
    }

    private void execute(Action<EN> action, Context<EN> context) {

        if (action == null) {
            return;
        }

        action.execute(context);
    }

    private Optional<Transition<EN>> getTransition(Context<EN> context, List<Transition<EN>> transitions) {
        if (transitions == null) return Optional.empty();
        return transitions.stream()
                .filter(t -> t.match(context))
                .findFirst();
    }

    private Context<EN> initContext(Event<EN> event) {
        return new Context<>(event, resultMap, current);
    }

    private void loopEntryAction(AbstractState<EN> to, Context<EN> context) {
        AbstractState<EN> temp = to;

        while (true) {
            context.setState(temp);
            execute(temp.getEnter(), context);

            if (temp.isComponent()) {
                ComponentState<EN> componentStatus = (ComponentState<EN>) temp;
                temp = componentStatus.getCurrent();
            } else {
                return;
            }
        }

    }


    private void loopExitAction(AbstractState<EN> form, Context<EN> context) {
        AbstractState<EN> temp = form;

        while (true) {
            context.setState(temp);
            execute(temp.getExit(), context);

            if (temp.isComponent()) {
                ComponentState<EN> componentStatus = (ComponentState<EN>) temp;
                temp = componentStatus.getCurrent();
            } else {
                return;
            }
        }

    }

    private void sendEventToChildFsm(Event<EN> event) {
        if (current != null && current.isComponent()) {
            ComponentState<EN> componentStatus = (ComponentState<EN>) current;
            Map<String, String> childResultMap = componentStatus.sendEvent(event);
            resultMap.putAll(childResultMap);
            componentStatus.clearMap();
        }
    }

    private void transfer(Context<EN> context) {

        if (transitionMap == null) {
            return;
        }

        List<Transition<EN>> transitions = transitionMap.get(current);

        //為空需往子FSM找，故transitions == null 防呆於 getTransition 中
        Optional<Transition<EN>> transitionOp = getTransition(context, transitions);

        if (transitionOp.isPresent()) {
            Transition<EN> transition = transitionOp.get();
            loopExitAction(transition.getFrom(), context);
            execute(transition.getAction(), context);
            current = transition.getTo();
            context.setState(current);
            loopEntryAction(transition.getTo(), context);
        }
    }


    private Map<AbstractState<EN>, List<Transition<EN>>> toMap(List<? extends Transition<EN>> transitions) {
        return transitions.stream()
                .collect(Collectors.groupingBy(Transition::getFrom));
    }

    //===========================================


    public AbstractState<EN> getCurrent() {
        return current;
    }

    public void setCurrent(AbstractState<EN> current) {
        this.current = current;
    }

    public AbstractState<EN> getInitial() {
        return initial;
    }

    public void setInitial(AbstractState<EN> initial) {
        this.initial = initial;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<AbstractState<EN>, List<Transition<EN>>> getTransitionMap() {
        return transitionMap;
    }

    public void setTransitionMap(List<? extends Transition<EN>> transitions) {
        if (transitions != null) {
            this.transitionMap = toMap(transitions);
        }
    }
}
