package com.ted.bot.status;

import com.ted.bot.BotAbstractState;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;
import com.ted.bot.BotEvent;
import com.ted.fsm.Action;
import com.ted.fsm.Context;
import com.ted.fsm.status.LeafState;

public abstract class BotLeafState<EN> extends LeafState<EN> implements BotAbstractState<EN> {

    protected BotLeafState(Action<EN> enter, Action<EN> exit) {
        super(enter, exit);
    }

    protected abstract void response(BotContext<EN> context);

    @Override
    public void response(Context<EN> context) {
        response(new BotContext<>((BotEvent<EN>) context.getEvent(), context.getResultMap(), (BotAbstractState<EN>) context.getState()));
    }

    public static class BotLeafStateBuilder<EN> {
        private BotAction<EN> enter;
        private BotAction<EN> exit;

        public BotLeafStateBuilder<EN> enter(BotAction<EN> enter) {
            this.enter = enter;
            return this;
        }

        public BotLeafStateBuilder<EN> exit(BotAction<EN> exit) {
            this.exit = exit;
            return this;
        }


        public <T extends BotLeafState<EN>> T build(BotLeafStateCreator<EN, T> creator) {
            return creator.create(enter, exit);
        }

        @FunctionalInterface
        public interface BotLeafStateCreator<EN, T extends BotLeafState<EN>> {
            T create(BotAction<EN> enter, BotAction<EN> exit);
        }
    }
}
