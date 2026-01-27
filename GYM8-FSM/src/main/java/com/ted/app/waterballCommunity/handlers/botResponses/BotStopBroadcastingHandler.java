package com.ted.app.waterballCommunity.handlers.botResponses;

import com.google.gson.JsonObject;
import com.ted.app.waterballCommunity.BotResponseHandler;
import com.ted.bot.BotEvent;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.util.JsonUtil;

public class BotStopBroadcastingHandler extends BotResponseHandler {


    public BotStopBroadcastingHandler(BotResponseHandler next) {
        super(next, BotEventResult.BOT_STOP_BROADCASTING);
    }

    @Override
    protected BotEvent<BotEventName> handling(JsonObject jsonObject) {
        return new BotEvent<> (BotEventName.BOT_STOP_BROADCASTING, JsonUtil.toJson(jsonObject));
    }
}
