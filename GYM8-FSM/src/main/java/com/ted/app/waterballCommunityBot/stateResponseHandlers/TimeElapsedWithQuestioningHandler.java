package com.ted.app.waterballCommunityBot.stateResponseHandlers;

import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.StateResponseHandler;
import com.ted.app.waterballCommunityBot.states.Questioning;
import com.ted.bot.BotContext;
import com.ted.bot.BotEvent;
import com.ted.util.JsonUtil;

public class TimeElapsedWithQuestioningHandler extends StateResponseHandler {
    public TimeElapsedWithQuestioningHandler(StateResponseHandler next) {
        super(next);
    }

    @Override
    protected boolean match(BotContext<BotEventName> context) {
        BotEvent<BotEventName> event = context.getEvent();
        return BotEventName.TIME_ELAPSED.equals(event.getEventName());
    }

    @Override
    protected void handling(BotContext<BotEventName> context) {
        Questioning questioning = (Questioning) context.getState();
        String payload = context.getEventPayload();
        long time = Long.parseLong(JsonUtil.get("time", payload));
        questioning.elapsed(time);
    }
}
