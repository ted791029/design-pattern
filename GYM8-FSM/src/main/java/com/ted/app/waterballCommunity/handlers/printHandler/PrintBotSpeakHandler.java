package com.ted.app.waterballCommunity.handlers.printHandler;

import com.ted.app.waterballCommunity.PrintHandler;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

public class PrintBotSpeakHandler extends PrintHandler {

    public PrintBotSpeakHandler(PrintHandler next) {
        super(BotEventName.BOT_SPEAK, "\uD83E\uDD16", next);
    }

    @Override
    protected String handling(String payload) {
        String content = JsonUtil.get("content", payload);
        String result = " " + content;
        return result;
    }
}
