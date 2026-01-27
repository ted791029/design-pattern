package com.ted.app.waterballCommunity.handlers.printHandler;

import com.google.gson.JsonArray;
import com.ted.app.waterballCommunity.PrintHandler;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

public class PrintBotNewMessageHandler extends PrintHandler {

    public PrintBotNewMessageHandler(PrintHandler next) {
        super(BotEventName.BOT_NEW_MESSAGE, "\uD83E\uDD16", next);
    }

    @Override
    protected String handling(String payload) {
        String content = JsonUtil.get("content", payload);
        JsonArray jsonArray = JsonUtil.getJsonArray("tags", payload);
        StringBuilder sb = new StringBuilder();

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append("@").append(jsonArray.get(i).getAsJsonObject().get("memberId").getAsString());
            }
        }

        String tagStr = sb.toString();
        String result = ": " + content + " " + tagStr;
        return result;
    }
}
