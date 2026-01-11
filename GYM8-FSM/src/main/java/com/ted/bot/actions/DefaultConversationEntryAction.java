package com.ted.bot.actions;

import com.ted.bot.states.DefaultConversation;
import com.ted.fsm.Action;
import com.ted.fsm.Context;

public class DefaultConversationEntryAction implements Action {
    @Override
    public void execute(Context context) {
        DefaultConversation defaultConversation = (DefaultConversation) context.getState();
        defaultConversation.resetIndex();
    }
}
