package com.ted.app.waterballCommunity.handlers.printHandler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.app.waterballCommunity.PrintHandler;
import com.ted.app.BotEventName;
import com.ted.util.JsonUtil;

public class PrintNewPostHandler extends PrintHandler {

    public PrintNewPostHandler(PrintHandler next) {
        super(BotEventName.NEW_POST, "", next);
    }

    @Override
    protected String handling(String payload) {
        String title = JsonUtil.get("title", payload);
        String content = JsonUtil.get("content", payload);
        JsonObject memberObj = JsonUtil.toJsonObject(JsonUtil.get("member", payload));
        String memberId = JsonUtil.get("id", memberObj.toString());
        JsonArray jsonArray = JsonUtil.getJsonArray("tags", payload);
        StringBuilder sb = new StringBuilder();

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append("@").append(jsonArray.get(i).getAsJsonObject().get("memberId").getAsString());
            }
        }

        String tagStr = sb.toString();
        String result = memberId + ": 【" + title + "】" + content + " " + tagStr;
        return result;
    }
}
