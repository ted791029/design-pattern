package com.ted.app.waterballCommunity.handlers.botResponses;

import com.google.gson.JsonObject;
import com.ted.app.waterballCommunity.BotResponseHandler;
import com.ted.bot.BotEvent;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.util.JsonUtil;

public class BotSpeakHandler extends BotResponseHandler {

    public BotSpeakHandler(BotResponseHandler next) {
        super(next, BotEventResult.BOT_SPEAK);
    }

    @Override
    protected BotEvent<BotEventName> handling(JsonObject jsonObject) {
        String content = jsonObject.get("content").getAsString();
        content = "speaking: " + content;
        jsonObject.addProperty("content", content);
        return new BotEvent<>(BotEventName.BOT_SPEAK, JsonUtil.toJson(jsonObject));
    }
}
