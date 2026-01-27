package com.ted.app.waterballCommunityBot.stateResponseHandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.app.waterballCommunityBot.Community;
import com.ted.app.waterballCommunityBot.StateResponseHandler;
import com.ted.bot.BotContext;
import com.ted.bot.BotEvent;
import com.ted.util.JsonUtil;

import java.util.List;

public class PostWithInteractingHandler extends StateResponseHandler {

    private final Community community;

    public PostWithInteractingHandler(StateResponseHandler next, Community community) {
        super(next);
        this.community = community;
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
        String content = "How do you guys think about it?";
        jsonObject.addProperty("content", content);
        List<String> allOnlineMember = community.allOnlineMemberId();
        allOnlineMember.add(0, "bot");
        JsonArray tagsJsonArray = JsonUtil.toJsonArray(allOnlineMember);
        jsonObject.add("tags", tagsJsonArray);
        return jsonObject.toString();
    }
}
