package com.ted.app.waterballCommunity.handlers.printHandler;

import com.google.gson.JsonObject;
import com.ted.app.waterballCommunity.PrintHandler;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

public class PrintSpeakHandler extends PrintHandler {

    public PrintSpeakHandler(PrintHandler next) {
        super(BotEventName.SPEAK, "\uD83D\uDCE2", next);
    }

    @Override
    protected String handling(String payload) {
        String content = JsonUtil.get("content", payload);
        JsonObject memberObj = JsonUtil.toJsonObject(JsonUtil.get("member", payload));
        String memberId = JsonUtil.get("id", memberObj.toString());
        String result = " " + memberId + ": " + content;
        return result;
    }
}
