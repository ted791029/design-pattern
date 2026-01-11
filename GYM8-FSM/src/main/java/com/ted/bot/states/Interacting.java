package com.ted.bot.states;

import com.ted.bot.StateResponseHandler;
import com.ted.fsm.*;

import java.util.List;
import java.util.Map;

public class Interacting extends Normal{

    private String[] conversations = {"Hi hi\uD83D\uDE01", "I like your idea!"};

    private int currentConversationIndex = 0;

    private StateResponseHandler handler;

    public Interacting(State initial, List<Transition> transitions, Map<String, String> resultMap, Action enter, Action exit, StateResponseHandler handler) {
        super(initial, transitions, resultMap, enter, exit);
        this.handler = handler;
    }

    public void addIndex(){
        currentConversationIndex = (currentConversationIndex + 1) % conversations.length;
    }

    public void resetIndex(){
        currentConversationIndex = 0;
    }

    @Override
    public void response(Context context) {
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
