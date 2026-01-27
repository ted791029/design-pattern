package com.ted.bot;

import com.ted.bot.status.BotComponentState;
import com.ted.bot.status.BotComponentState.BotComponentStateBuilder.BotComponentStateCreator;
import com.ted.bot.status.BotLeafState;
import com.ted.bot.status.BotLeafState.BotLeafStateBuilder.BotLeafStateCreator;
import com.ted.fsm.AbstractState;
import com.ted.fsm.FiniteStateMachine;

import java.util.*;

public class BotFSMFacade<S, EN> {
    private BotFSMConfigurer<S, EN> configurer;

    private Map<S, BotAbstractState<EN>> stateMap = new HashMap<>();

    private FiniteStateMachine<EN> finiteStateMachine;

    public BotFSMFacade(BotFSMConfigurer<S, EN> configurer) {
        this.configurer = configurer;
        this.finiteStateMachine = initFSM();
    }

    public Map<String, String> sendEvent(BotEvent<EN> event) {
        finiteStateMachine.sendEvent(event);
        Map<String, String> resultMap = new LinkedHashMap<>(finiteStateMachine.getResultMap());
        finiteStateMachine.clearMap();
        return resultMap;
    }

    private FiniteStateMachine<EN> initFSM() {
        StatesConfigurer<S, EN> statesConfigurer = new StatesConfigurer<>(stateMap);
        configurer.configure(statesConfigurer);
        FiniteStateMachineConfigurer<S, EN> finiteStateMachineConfigurer = new FiniteStateMachineConfigurer<>(stateMap);
        configurer.configure(finiteStateMachineConfigurer);
        return finiteStateMachineConfigurer.build();
    }


    //==============================
    public static class StatesConfigurer<S, EN> {
        private Map<S, BotAbstractState<EN>> stateMap;

        public StatesConfigurer(Map<S, BotAbstractState<EN>> stateMap) {
            this.stateMap = stateMap;
        }

        public StatesConfigurer<S, EN> componentState(BotComponentStateCreator<EN, ? extends BotComponentState<EN>> creator, S stateType) {
            BotComponentState<EN> state = new BotComponentState.BotComponentStateBuilder<EN>()
                    .build(creator);
            stateMap.put(stateType, state);
            return this;
        }

        public StatesConfigurer<S, EN> componentState(BotComponentStateCreator<EN, ? extends BotComponentState<EN>> creator, BotAction<EN> enter, S stateType) {
            BotComponentState<EN> state = new BotComponentState.BotComponentStateBuilder<EN>()
                    .enter(enter)
                    .build(creator);
            stateMap.put(stateType, state);
            return this;
        }

        public StatesConfigurer<S, EN> componentState(BotComponentStateCreator<EN, ? extends BotComponentState<EN>> creator, BotAction<EN> enter, BotAction<EN> exit, S stateType) {
            BotComponentState<EN> state = new BotComponentState.BotComponentStateBuilder<EN>()
                    .enter(enter)
                    .exit(exit)
                    .build(creator);
            stateMap.put(stateType, state);
            return this;
        }

        public StatesConfigurer<S, EN> leafState(BotLeafStateCreator<EN, ? extends BotLeafState<EN>> creator, S stateType) {
            BotLeafState<EN> state = new BotLeafState.BotLeafStateBuilder<EN>()
                    .build(creator);
            stateMap.put(stateType, state);
            return this;
        }

        public StatesConfigurer<S, EN> leafState(BotLeafStateCreator<EN, ? extends BotLeafState<EN>> creator, BotAction<EN> enter, S stateType) {
            BotLeafState<EN> state = new BotLeafState.BotLeafStateBuilder<EN>()
                    .enter(enter)
                    .build(creator);
            stateMap.put(stateType, state);
            return this;
        }


        public StatesConfigurer<S, EN> leafState(BotLeafStateCreator<EN, ? extends BotLeafState<EN>> creator, BotAction<EN> enter, BotAction<EN> exit, S stateType) {
            BotLeafState<EN> state = new BotLeafState.BotLeafStateBuilder<EN>()
                    .enter(enter)
                    .exit(exit)
                    .build(creator);
            stateMap.put(stateType, state);
            return this;
        }

        public BotAbstractState<EN> getState(S stateType) {

            if (!stateMap.containsKey(stateType)) {
                throw new RuntimeException("查無此狀態名稱");
            }

            return stateMap.get(stateType);
        }
    }

    public static class FiniteStateMachineConfigurer<S, EN> {
        private AbstractState<EN> initial;

        private FiniteStateMachineConfigurer<S, EN> parent;

        private S childStateType;

        private Map<S, BotAbstractState<EN>> stateMap;

        private List<BotTransition<EN>> transitions = new ArrayList<>();

        public FiniteStateMachineConfigurer(Map<S, BotAbstractState<EN>> stateMap) {
            this.stateMap = stateMap;
        }

        public FiniteStateMachineConfigurer(Map<S, BotAbstractState<EN>> stateMap, S childStateType, FiniteStateMachineConfigurer<S, EN> parent) {
            this.stateMap = stateMap;
            this.childStateType = childStateType;
            this.parent = parent;
        }

        public void addTransition(BotTransition<EN> transition) {
            transitions.add(transition);
        }

        public FiniteStateMachine<EN> build() {
            return new FiniteStateMachine<>(initial, transitions);
        }

        public FiniteStateMachineConfigurer<S, EN> child(S childStateType) {

            if (!stateMap.containsKey(childStateType)) {
                throw new RuntimeException("查無此狀態名稱");
            }

            AbstractState<EN> state = stateMap.get(childStateType);

            if (!state.isComponent()) {
                throw new RuntimeException("此狀態不為複合狀態");
            }

            return new FiniteStateMachineConfigurer<>(stateMap, childStateType, this);
        }

        public void childEnd() {
            FiniteStateMachine<EN> child = (BotComponentState<EN>) stateMap.get(childStateType);
            child.setInitial(initial);
            child.setCurrent(initial);
            child.setTransitionMap(transitions);
        }

        public FiniteStateMachineConfigurer<S, EN> initial(S stateType) {

            if (!stateMap.containsKey(stateType)) {
                throw new RuntimeException("查無此狀態名稱");
            }

            this.initial = stateMap.get(stateType);
            return this;
        }

        public TransitionConfigurer<S, EN> withTransition() {
            return new TransitionConfigurer<>(this, stateMap);
        }
    }

    public static class TransitionConfigurer<S, EN> {

        private BotAction<EN> action;

        private BotEvent<EN> event;

        private BotAbstractState<EN> from;

        private BotGuard<EN> guard;

        private FiniteStateMachineConfigurer<S, EN> parent;

        private Map<S, BotAbstractState<EN>> stateMap;

        private BotAbstractState<EN> to;

        public TransitionConfigurer(FiniteStateMachineConfigurer<S, EN> parent, Map<S, BotAbstractState<EN>> stateMap) {
            this.parent = parent;
            this.stateMap = stateMap;
        }

        public TransitionConfigurer<S, EN> action(BotAction<EN> action) {
            this.action = action;
            return this;
        }

        public FiniteStateMachineConfigurer<S, EN> and() {

            if (parent != null) {
                parent.addTransition(build());
                return parent;
            }

            throw new IllegalStateException("No FiniteStateMachineBuilder parent set");
        }

        public TransitionConfigurer<S, EN> event(BotEvent<EN> event) {
            this.event = event;
            return this;
        }

        public TransitionConfigurer<S, EN> form(S fromStateType) {

            if (!stateMap.containsKey(fromStateType)) {
                throw new RuntimeException("查無此狀態名稱");
            }

            this.from = stateMap.get(fromStateType);
            return this;
        }

        public TransitionConfigurer<S, EN> guard(BotGuard<EN> guard) {
            this.guard = guard;
            return this;
        }

        public TransitionConfigurer<S, EN> to(S toStateType) {

            if (!stateMap.containsKey(toStateType)) {
                throw new RuntimeException("查無此狀態名稱");
            }

            this.to = stateMap.get(toStateType);
            return this;
        }

        private BotTransition<EN> build() {
            return new BotTransition<>(this.action, this.event, this.from, this.guard, this.to);
        }
    }
}
