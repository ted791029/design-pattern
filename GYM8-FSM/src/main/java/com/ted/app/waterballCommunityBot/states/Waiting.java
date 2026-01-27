package com.ted.app.waterballCommunityBot.states;

import com.ted.app.BotEventName;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;
import com.ted.bot.status.BotLeafState;

public class Waiting extends BotLeafState<BotEventName> {

    public Waiting(BotAction<BotEventName> enter, BotAction<BotEventName> exit) {
        super(enter, exit);
    }

    @Override
    protected void response(BotContext<BotEventName> context) {

    }
}
