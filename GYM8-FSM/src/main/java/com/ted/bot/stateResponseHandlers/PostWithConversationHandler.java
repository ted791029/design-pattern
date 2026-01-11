package com.ted.bot.stateResponseHandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.bot.BotEventName;
import com.ted.bot.BotEventResult;
import com.ted.bot.StateResponseHandler;
import com.ted.fsm.Context;
import com.ted.fsm.Event;
import com.ted.util.JsonUtil;

public class PostWithConversationHandler extends StateResponseHandler {

    public PostWithConversationHandler(StateResponseHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Context context) {
        Event event = context.getEvent();
        return BotEventName.NEW_POST.getName().equals(event.getEventName());
    }

    @Override
    protected void handling(Context context) {
        Event event = context.getEvent();
        String payload = event.getPayload();
        String result = toResultJson(payload);
        context.addEventResult(BotEventResult.BOT_REPLY_POST.getName(), result);
    }

    private String toResultJson(String payload){
        JsonObject jsonObject = new JsonObject();
        String postId = JsonUtil.get("id", payload);
        jsonObject.addProperty("postId", postId);
        String content = "Nice post";
        jsonObject.addProperty("content", content);
        String user = JsonUtil.get("user", payload);
        String userId = JsonUtil.get("id", user);
        JsonArray tagsJsonArray = new JsonArray();
        tagsJsonArray.add(userId);
        jsonObject.add("tags", tagsJsonArray);
        return jsonObject.toString();
    }
}
