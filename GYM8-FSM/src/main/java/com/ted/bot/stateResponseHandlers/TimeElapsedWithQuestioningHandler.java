package com.ted.bot.stateResponseHandlers;

import com.ted.bot.BotEventName;
import com.ted.bot.StateResponseHandler;
import com.ted.bot.states.Questioning;
import com.ted.fsm.Context;
import com.ted.fsm.Event;
import com.ted.util.JsonUtil;

public class TimeElapsedWithQuestioningHandler extends StateResponseHandler {
    public TimeElapsedWithQuestioningHandler(StateResponseHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Context context) {
        Event event = context.getEvent();
        return BotEventName.TIME_ELAPSED.getName().equals(event.getEventName());
    }

    @Override
    protected void handling(Context context) {
        Questioning questioning = (Questioning) context.getState();
        String payload = context.getEventPayload();
        long time = Long.parseLong(JsonUtil.get("time", payload));
        questioning.elapsed(time);
    }
}
