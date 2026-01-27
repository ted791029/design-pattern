package com.ted.app.waterballCommunityBot.states;

import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.StateResponseHandler;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;
import com.ted.bot.status.BotLeafState;

public class Interacting extends BotLeafState<BotEventName> {

    private String[] conversations = {"Hi hi\uD83D\uDE01", "I like your idea!"};

    private int currentConversationIndex = 0;

    private StateResponseHandler handler;

    public Interacting(BotAction<BotEventName> enter, BotAction<BotEventName> exit, StateResponseHandler handler) {
        super(enter, exit);
        this.handler = handler;
    }

    public void addIndex() {
        currentConversationIndex = (currentConversationIndex + 1) % conversations.length;
    }

    public void resetIndex() {
        currentConversationIndex = 0;
    }

    @Override
    protected void response(BotContext<BotEventName> context) {
        handler.handle(context);
    }

    //===================================

    public String[] getConversations() {
        return conversations;
    }

    public void setConversations(String[] conversations) {
        this.conversations = conversations;
    }

    public int getCurrentConversationIndex() {
        return currentConversationIndex;
    }

    public void setCurrentConversationIndex(int currentConversationIndex) {
        this.currentConversationIndex = currentConversationIndex;
    }

    public StateResponseHandler getHandler() {
        return handler;
    }

    public void setHandler(StateResponseHandler handler) {
        this.handler = handler;
    }
}
