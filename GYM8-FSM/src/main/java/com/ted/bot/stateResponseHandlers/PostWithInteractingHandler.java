package com.ted.bot.stateResponseHandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.bot.BotEventName;
import com.ted.bot.BotEventResult;
import com.ted.bot.Community;
import com.ted.bot.StateResponseHandler;
import com.ted.fsm.Context;
import com.ted.fsm.Event;
import com.ted.util.JsonUtil;

import java.util.List;

public class PostWithInteractingHandler extends StateResponseHandler {

    private Community community;

    public PostWithInteractingHandler(StateResponseHandler next, Community community) {
        super(next);
        this.community = community;
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
        String content = "How do you guys think about it?";
        jsonObject.addProperty("content", content);
        List<String> allOnlineUser = community.allOnlineUserId();
        allOnlineUser.add(0, "bot");
        JsonArray tagsJsonArray = JsonUtil.toJsonArray(allOnlineUser);
        jsonObject.add("tags", tagsJsonArray);
        return jsonObject.toString();
    }
}
