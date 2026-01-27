package com.ted.bot;

import com.ted.fsm.Transition;

public class BotTransition<EN> extends Transition<EN> {

    public BotTransition(BotAction<EN> action, BotEvent<EN> event, BotAbstractState<EN> from, BotGuard<EN> guard, BotAbstractState<EN> to) {
        super(action, event, from, guard, to);
    }

}
