package com.ted.app.waterballCommunityBot.stateResponseHandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.app.waterballCommunityBot.StateResponseHandler;
import com.ted.app.waterballCommunityBot.states.DefaultConversation;
import com.ted.bot.BotContext;
import com.ted.bot.BotEvent;
import com.ted.util.JsonUtil;

public class MessageWithConversationHandler extends StateResponseHandler {

    public MessageWithConversationHandler(StateResponseHandler next) {
        super(next);
    }

    @Override
    protected boolean match(BotContext<BotEventName> context) {
        BotEvent<BotEventName> event = context.getEvent();
        return BotEventName.NEW_MESSAGE.equals(event.getEventName());
    }

    @Override
    protected void handling(BotContext<BotEventName> context) {
        DefaultConversation defaultConversation = (DefaultConversation) context.getState();
        BotEvent<BotEventName> event = context.getEvent();
        String payload = event.getPayload();
        String[] conversations = defaultConversation.getConversations();
        int index = defaultConversation.getCurrentConversationIndex();
        String content = conversations[index];
        String result = toResultJson(payload, content);
        context.addEventResult(BotEventResult.BOT_REPLY_MESSAGE.getName(), result);
        defaultConversation.addIndex();
    }

    private String toResultJson(String payload, String content) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", content);
        String member = JsonUtil.get("member", payload);
        String memberId = JsonUtil.get("id", member);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(memberId);
        jsonObject.add("tags", jsonArray);
        return jsonObject.toString();
    }
}
