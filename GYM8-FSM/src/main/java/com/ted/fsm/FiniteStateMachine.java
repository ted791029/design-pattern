package com.ted.fsm;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FiniteStateMachine {

    private State current;

    private State initial;

    private Map<String, String> resultMap;

    private Map<State, List<Transition>> transitionMap;


    public FiniteStateMachine(State initial, List<Transition> transitions, Map<String, String> resultMap) {
        this.current = initial;
        this.initial = initial;
        setTransitionMap(transitions);
        this.resultMap = resultMap;
    }

    public void clearResultMap(){
        resultMap.clear();
    }

    public void sendEvent(Event event) {
        Context context = initContext(event);

        if(current != null && current.getCurrent() != null){
            current.sendEvent(event);
        }

        //TODO 子狀態機做了，父狀態機不可做(response和transfer要分開判斷)
        current.response(context);
        transfer(context);
    }

    private void execute(Action action, Context context) {

        if (action == null) {
            return;
        }

        action.execute(context);
    }

    private Optional<Transition> getTransition(Context context, List<Transition> transitions) {
        if (transitions == null) return Optional.empty();
        return transitions.stream()
                .filter(t -> t.match(context))
                .findFirst();
    }

    private Context initContext(Event event) {
        return new Context(event, resultMap, current);
    }

    private void transfer(Context context) {

        if (transitionMap == null) {
            return;
        }

        List<Transition> transitions = transitionMap.get(current);

        //為空需往子FSM找，故transitions == null 防呆於 getTransition 中
        Optional<Transition> transitionOp = getTransition(context, transitions);

        if (transitionOp.isPresent()) {
            Transition transition = transitionOp.get();
            loopExitAction(transition.getFrom(), context);
            execute(transition.getAction(), context);
            current = transition.getTo();
            context.setState(current);
            loopEntryAction(transition.getTo(), context);
        }
    }


    private Map<State, List<Transition>> toMap(List<Transition> transitions) {
        return transitions.stream()
                .collect(Collectors.groupingBy(Transition::getFrom));
    }

    private void loopExitAction(State form, Context context){
        State temp = form;

        while (temp != null){
            context.setState(temp);
            execute(temp.getExit(), context);
            temp = temp.getCurrent();
        }

    }

    private void loopEntryAction(State to, Context context){
        State temp = to;

        while (temp != null){
            context.setState(temp);
            execute(temp.getEnter(), context);
            temp = temp.getCurrent();
        }

    }

    //===========================================


    public State getCurrent() {
        return current;
    }

    public void setCurrent(State current) {
        this.current = current;
    }

    public State getInitial() {
        return initial;
    }

    public void setInitial(State initial) {
        this.initial = initial;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<State, List<Transition>> getTransitionMap() {
        return transitionMap;
    }

    public void setTransitionMap(List<Transition> transitions) {
        if (transitions != null) {
            this.transitionMap = toMap(transitions);
        }
    }
}
