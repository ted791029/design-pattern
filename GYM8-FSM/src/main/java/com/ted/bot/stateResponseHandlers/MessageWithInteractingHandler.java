package com.ted.bot.stateResponseHandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.bot.BotEventName;
import com.ted.bot.BotEventResult;
import com.ted.bot.states.Interacting;
import com.ted.bot.StateResponseHandler;
import com.ted.fsm.Context;
import com.ted.fsm.Event;
import com.ted.util.JsonUtil;

public class MessageWithInteractingHandler extends StateResponseHandler{

    public MessageWithInteractingHandler(StateResponseHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Context context) {
        Event event = context.getEvent();
        return BotEventName.NEW_MESSAGE.getName().equals(event.getEventName());
    }

    @Override
    protected void handling(Context context) {
        Interacting Interacting = (Interacting) context.getState();
        Event event = context.getEvent();
        String payload = event.getPayload();
        String[] conversations = Interacting.getConversations();
        int index = Interacting.getCurrentConversationIndex();
        String content = conversations[index];
        String result = toResultJson(payload, content);
        context.addEventResult(BotEventResult.BOT_REPLY_MESSAGE.getName(), result);
        Interacting.addIndex();
    }

    private String toResultJson(String payload, String content){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", content);
        String user = JsonUtil.get("user", payload);
        String userId = JsonUtil.get("id", user);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(userId);
        jsonObject.add("tags", jsonArray);
        return jsonObject.toString();
    }
}
