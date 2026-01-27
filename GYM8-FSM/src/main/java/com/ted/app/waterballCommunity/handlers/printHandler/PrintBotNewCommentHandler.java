package com.ted.app.waterballCommunity.handlers.printHandler;

import com.google.gson.JsonArray;
import com.ted.app.waterballCommunity.PrintHandler;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

public class PrintBotNewCommentHandler extends PrintHandler {

    public PrintBotNewCommentHandler(PrintHandler next) {
        super(BotEventName.BOT_NEW_COMMENT, "\uD83E\uDD16", next);
    }

    @Override
    protected String handling(String payload) {
        //🤖 comment in post 201: How do you guys think about it? @bot, @1, @2, @3, @4, @5, @6, @7, @8, @9, @10
        String postId = JsonUtil.get("postId", payload);
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
        String result = " comment in post " + postId + ": " + content + " " + tagStr;
        return result;
    }
}
