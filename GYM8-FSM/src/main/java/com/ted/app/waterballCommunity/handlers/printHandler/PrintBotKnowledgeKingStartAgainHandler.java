package com.ted.app.waterballCommunity.handlers.printHandler;

import com.ted.app.waterballCommunity.PrintHandler;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

public class PrintBotKnowledgeKingStartAgainHandler extends PrintHandler {

    public PrintBotKnowledgeKingStartAgainHandler(PrintHandler next) {
        super(BotEventName.BOT_KNOWLEDGE_KING_START_AGAIN, "\uD83E\uDD16", next);
    }

    @Override
    protected String handling(String payload) {
        String content = JsonUtil.get("content", payload);
        String result = " " + content;
        return result;
    }
}
