package com.ted.app.waterballCommunityBot;

import com.ted.app.BotEventName;
import com.ted.bot.BotContext;
 public abstract class StateResponseHandler {

    private StateResponseHandler next;

    public StateResponseHandler(StateResponseHandler next) {
        this.next = next;
    }

    public void handle(BotContext<BotEventName> context) {

        if (match(context)) {
            handling(context);
        }

        if (next != null) {
            next.handle(context);
        }

    }

    abstract protected boolean match(BotContext<BotEventName> context);

    abstract protected void handling(BotContext<BotEventName> context);
}
