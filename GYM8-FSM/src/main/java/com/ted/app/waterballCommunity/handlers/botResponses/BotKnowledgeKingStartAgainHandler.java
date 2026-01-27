package com.ted.app.waterballCommunity.handlers.botResponses;

import com.google.gson.JsonObject;
import com.ted.app.waterballCommunity.BotResponseHandler;
import com.ted.bot.BotEvent;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.util.JsonUtil;

public class BotKnowledgeKingStartAgainHandler extends BotResponseHandler {

    public BotKnowledgeKingStartAgainHandler(BotResponseHandler next) {
        super(next, BotEventResult.BOT_KNOWLEDGE_KING_START_AGAIN);
    }

    @Override
    protected BotEvent<BotEventName> handling(JsonObject jsonObject) {
        return new BotEvent<>(BotEventName.BOT_NEW_MESSAGE, JsonUtil.toJson(jsonObject));
    }
}
