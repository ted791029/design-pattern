package com.ted.bot.states;

import java.util.List;
import java.util.Map;

import com.ted.bot.StateResponseHandler;
import com.ted.fsm.Action;
import com.ted.fsm.Context;
import com.ted.fsm.State;
import com.ted.fsm.Transition;

public class DefaultConversation extends Normal{

    private String[] conversations = {"good to hear", "thank you", "How are you"};

    private int currentConversationIndex = 0;

    private StateResponseHandler handler;

    public DefaultConversation(State initial, List<Transition> transitions, Map<String, String> resultMap, Action enter, Action exit, StateResponseHandler handler) {
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

    //========================================


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
