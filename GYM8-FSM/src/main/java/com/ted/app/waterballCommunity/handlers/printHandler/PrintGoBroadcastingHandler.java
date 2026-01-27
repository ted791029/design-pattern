package com.ted.app.waterballCommunity.handlers.printHandler;

import com.ted.app.waterballCommunity.PrintHandler;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

public class PrintGoBroadcastingHandler extends PrintHandler {
    public PrintGoBroadcastingHandler(PrintHandler next) {
        super(BotEventName.GO_BROADCASTING, "\uD83D\uDCE2", next);
    }

    @Override
    protected String handling(String payload) {
        String memberId = JsonUtil.get("id", payload);
        String result = " " + memberId + " is broadcasting...";
        return result;
    }
}
