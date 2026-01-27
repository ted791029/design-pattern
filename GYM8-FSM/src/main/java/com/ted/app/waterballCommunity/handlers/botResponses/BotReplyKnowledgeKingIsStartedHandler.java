package com.ted.app.waterballCommunity.handlers.botResponses;

import com.google.gson.JsonObject;
import com.ted.app.waterballCommunity.BotResponseHandler;
import com.ted.app.waterballCommunity.Message;
import com.ted.bot.BotEvent;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.util.JsonUtil;

public class BotReplyKnowledgeKingIsStartedHandler extends BotResponseHandler {


    public BotReplyKnowledgeKingIsStartedHandler(BotResponseHandler next) {
        super(next, BotEventResult.BOT_REPLY_KNOWLEDGE_KING_IS_STARTED);
    }

    @Override
    protected BotEvent<BotEventName> handling(JsonObject jsonObject) {
        String content = jsonObject.get("content").getAsString();
        Message message = new Message(content, null, null);
        return new BotEvent<>(BotEventName.BOT_NEW_MESSAGE, JsonUtil.toJson(message));
    }
}
