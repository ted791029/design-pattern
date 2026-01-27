package com.ted.app.waterballCommunity.handlers.printHandler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.app.waterballCommunity.PrintHandler;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

public class PrintNewMessageHandler extends PrintHandler {
    public PrintNewMessageHandler(PrintHandler next) {
        super(BotEventName.NEW_MESSAGE, "\uD83D\uDCAC", next);
    }

    @Override
    protected String handling(String payload) {
        String content = JsonUtil.get("content", payload);
        JsonObject memberObj = JsonUtil.toJsonObject(JsonUtil.get("member", payload));
        String memberId = JsonUtil.get("id", memberObj.toString());
        JsonArray jsonArray = JsonUtil.getJsonArray("tags", payload);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jsonArray.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append("@").append(jsonArray.get(i).getAsJsonObject().get("memberId").getAsString());
        }

        String tagStr = sb.toString();
        String result = " " + memberId + ": " + content + " " + tagStr;
        return result;
    }
}
