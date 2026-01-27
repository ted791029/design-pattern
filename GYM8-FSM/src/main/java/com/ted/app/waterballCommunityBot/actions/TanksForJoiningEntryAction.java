package com.ted.app.waterballCommunityBot.actions;

import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.states.ThanksForJoining;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;

public class TanksForJoiningEntryAction extends BotAction<BotEventName> {
    @Override
    public void execute(BotContext<BotEventName> context) {
        ThanksForJoining thanksForJoining = (ThanksForJoining) context.getState();
        thanksForJoining.initTimer();
    }
}
