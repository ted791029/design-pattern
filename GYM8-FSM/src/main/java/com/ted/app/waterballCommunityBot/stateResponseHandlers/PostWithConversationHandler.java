package com.ted.app.waterballCommunityBot.stateResponseHandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.app.waterballCommunityBot.StateResponseHandler;
import com.ted.bot.BotContext;
import com.ted.bot.BotEvent;
import com.ted.util.JsonUtil;

public class PostWithConversationHandler extends StateResponseHandler {

    public PostWithConversationHandler(StateResponseHandler next) {
        super(next);
    }

    @Override
    protected boolean match(BotContext<BotEventName> context) {
        BotEvent<BotEventName> event = context.getEvent();
        return BotEventName.NEW_POST.equals(event.getEventName());
    }

    @Override
    protected void handling(BotContext<BotEventName> context) {
        BotEvent<BotEventName> event = context.getEvent();
        String payload = event.getPayload();
        String result = toResultJson(payload);
        context.addEventResult(BotEventResult.BOT_REPLY_POST.getName(), result);
    }

    private String toResultJson(String payload) {
        JsonObject jsonObject = new JsonObject();
        String postId = JsonUtil.get("id", payload);
        jsonObject.addProperty("postId", postId);
        String content = "Nice post";
        jsonObject.addProperty("content", content);
        String member = JsonUtil.get("member", payload);
        String memberId = JsonUtil.get("id", member);
        JsonArray tagsJsonArray = new JsonArray();
        tagsJsonArray.add(memberId);
        jsonObject.add("tags", tagsJsonArray);
        return jsonObject.toString();
    }
}
