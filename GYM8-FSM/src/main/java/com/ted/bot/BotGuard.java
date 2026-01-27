package com.ted.bot;

import com.ted.fsm.Context;
import com.ted.fsm.Guard;

public abstract class BotGuard<EN> implements Guard<EN> {

    public abstract boolean evaluate(BotContext<EN> context);

    @Override
    public boolean evaluate(Context<EN> context) {
        return evaluate(new BotContext<>((BotEvent<EN>) context.getEvent(), context.getResultMap(), (BotAbstractState<EN>) context.getState()));
    }
}
