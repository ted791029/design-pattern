package com.ted.app.waterballCommunity.handlers.printHandler;

import com.ted.app.waterballCommunity.PrintHandler;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

public class PrintBotStopBroadcastingHandler extends PrintHandler {

    public PrintBotStopBroadcastingHandler(PrintHandler next) {
        super(BotEventName.BOT_STOP_BROADCASTING, "\uD83E\uDD16", next);
    }

    @Override
    protected String handling(String payload) {
        String content = JsonUtil.get("content", payload);
        String result = " " + content;
        return result;
    }
}
