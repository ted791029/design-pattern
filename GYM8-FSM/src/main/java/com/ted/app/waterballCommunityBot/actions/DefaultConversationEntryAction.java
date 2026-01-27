package com.ted.app.waterballCommunityBot.actions;

import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.states.DefaultConversation;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;

public class DefaultConversationEntryAction extends BotAction<BotEventName> {
    @Override
    public void execute(BotContext<BotEventName> context) {
        DefaultConversation defaultConversation = (DefaultConversation) context.getState();
        defaultConversation.resetIndex();
    }
}
