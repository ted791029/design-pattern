package com.ted.app.waterballCommunity.handlers.printHandler;

import com.ted.app.waterballCommunity.PrintHandler;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

public class PrintTimeElapsedHandler extends PrintHandler {

    public PrintTimeElapsedHandler(PrintHandler next) {
        super(BotEventName.TIME_ELAPSED, "\uD83D\uDD51", next);
    }

    @Override
    protected String handling(String payload) {
        //🕑 1 hours elapsed...
        String content = JsonUtil.get("content", payload);
        String result = " " + content;
        return result;
    }
}
