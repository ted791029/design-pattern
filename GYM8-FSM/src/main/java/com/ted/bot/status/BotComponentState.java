package com.ted.bot.status;

import com.ted.bot.*;
import com.ted.fsm.Context;
import com.ted.fsm.status.ComponentState;

import java.util.ArrayList;
import java.util.List;

public abstract class BotComponentState<EN> extends ComponentState<EN> implements BotAbstractState<EN> {

    protected BotComponentState(BotAbstractState<EN> initial, List<BotTransition<EN>> transitions, BotAction<EN> enter, BotAction<EN> exit) {
        super(initial, transitions, enter, exit);
    }

    protected abstract void response(BotContext<EN> context);

    @Override
    public void response(Context<EN> context) {
        response(new BotContext<>((BotEvent<EN>) context.getEvent(), context.getResultMap(), (BotAbstractState<EN>) context.getState()));
    }


    public static class BotComponentStateBuilder<EN> {
        private BotAbstractState<EN> initial;
        private List<BotTransition<EN>> transitions = new ArrayList<>();
        private BotAction<EN> enter;
        private BotAction<EN> exit;

        public BotComponentStateBuilder<EN> initial(BotAbstractState<EN> initial) {
            this.initial = initial;
            return this;
        }

        public BotComponentStateBuilder<EN> enter(BotAction<EN> enter) {
            this.enter = enter;
            return this;
        }

        public BotComponentStateBuilder<EN> exit(BotAction<EN> exit) {
            this.exit = exit;
            return this;
        }

        public <T extends BotComponentState<EN>> T build(BotComponentStateCreator<EN, T> creator) {
            return creator.create(initial, transitions, enter, exit);
        }

        @FunctionalInterface
        public interface BotComponentStateCreator<EN, T extends BotComponentState<EN>> {
            T create(BotAbstractState<EN> initial, List<BotTransition<EN>> transitions, BotAction<EN> enter, BotAction<EN> exit);
        }
    }
}
