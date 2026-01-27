package com.ted.app.waterballCommunityBot.actions;

import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.states.Interacting;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;

public class InteractingEntryAction extends BotAction<BotEventName> {
    @Override
    public void execute(BotContext<BotEventName> context) {
        Interacting interacting = (Interacting) context.getState();
        interacting.resetIndex();
    }
}
