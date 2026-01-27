package com.ted.app.waterballCommunity.handlers.botResponses;

import com.google.gson.JsonObject;
import com.ted.app.waterballCommunity.BotResponseHandler;
import com.ted.bot.BotEvent;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.util.JsonUtil;

public class BotGoBroadcastingHandler extends BotResponseHandler {


    public BotGoBroadcastingHandler(BotResponseHandler next) {
        super(next, BotEventResult.BOT_GO_BROADCASTING);
    }

    @Override
    protected BotEvent<BotEventName> handling(JsonObject jsonObject) {
        return new BotEvent<>(BotEventName.BOT_GO_BROADCASTING, JsonUtil.toJson(jsonObject));
    }
}
