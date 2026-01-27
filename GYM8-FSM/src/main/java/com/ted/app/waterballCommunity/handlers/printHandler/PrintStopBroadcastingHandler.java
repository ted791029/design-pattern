package com.ted.app.waterballCommunity.handlers.printHandler;

import com.ted.app.waterballCommunity.PrintHandler;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

public class PrintStopBroadcastingHandler extends PrintHandler {

    public PrintStopBroadcastingHandler(PrintHandler next) {
        super(BotEventName.STOP_BROADCASTING, "\uD83D\uDCE2", next);
    }

    @Override
    protected String handling(String payload) {
        String memberId = JsonUtil.get("id", payload);
        String result = " " + memberId + " stop broadcasting";
        return result;
    }
}
