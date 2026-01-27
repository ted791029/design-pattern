package com.ted.app.waterballCommunityBot.states;

import com.ted.app.BotEventName;
import com.ted.bot.BotAbstractState;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;
import com.ted.bot.BotTransition;
import com.ted.bot.status.BotComponentState;

import java.util.List;
import java.util.Map;

public class KnowledgeKing extends BotComponentState<BotEventName> {

    public KnowledgeKing(BotAbstractState<BotEventName> initial, List<BotTransition<BotEventName>> transitions, BotAction<BotEventName> enter, BotAction<BotEventName> exit, Map<String, Integer> scoreMap) {
        super(initial, transitions, enter, exit);
    }

    @Override
    protected void response(BotContext<BotEventName> context) {

    }
}
