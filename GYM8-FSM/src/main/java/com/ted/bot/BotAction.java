package com.ted.bot;

import com.ted.fsm.Action;
import com.ted.fsm.Context;

public abstract class BotAction<EN> implements Action<EN> {

    public abstract void execute(BotContext<EN> context);

    @Override
    public void execute(Context<EN> context){
        execute(new BotContext<>((BotEvent<EN>) context.getEvent(), context.getResultMap(), (BotAbstractState<EN>) context.getState()));
    }
}
