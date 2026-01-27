package com.ted.app.waterballCommunityBot.guards;

import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.states.Questioning;
import com.ted.bot.BotContext;
import com.ted.bot.BotGuard;

public class QuestioningToThanksForJoining extends BotGuard<BotEventName> {

    @Override
    public boolean evaluate(BotContext<BotEventName> context) {
        Questioning questioning = (Questioning) context.getState();
        return questioning.isEnd();
    }

}
