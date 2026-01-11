package com.ted.app;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.bot.BotEvent;
import com.ted.bot.BotEventName;
import com.ted.util.JsonUtil;

public class Printer {

    public void print(BotEvent botEvent) {
        BotEventName botEventName = BotEventName.fromName(botEvent.getEventName());
        String icon = getIcon(botEventName);
        String content = getContent(botEventName, botEvent.getPayload());
        String msg = icon + content;

        if (!msg.isEmpty()) {
            System.out.println(msg);
        }
    }

    private String getIcon(BotEventName botEventName) {
        String icon = "";

        if (botEventName == BotEventName.NEW_MESSAGE) {
            icon = "\uD83D\uDCAC";
        } else if (botEventName == BotEventName.NEW_POST) {
            icon = "";
        } else if (botEventName == BotEventName.SPEAK
                || botEventName == BotEventName.GO_BROADCASTING
                || botEventName == BotEventName.STOP_BROADCASTING) {
            icon = "\uD83D\uDCE2";
        } else if (botEventName == BotEventName.BOT_NEW_MESSAGE
                || botEventName == BotEventName.BOT_NEW_COMMENT
                || botEventName == BotEventName.BOT_SPEAK
                || botEventName == BotEventName.BOT_GO_BROADCASTING
                || botEventName == BotEventName.BOT_STOP_BROADCASTING
                || botEventName == BotEventName.BOT_KNOWLEDGE_KING_START_AGAIN
        ) {
            icon = "\uD83E\uDD16";
        }else if (botEventName == BotEventName.TIME_ELAPSED ){
            icon = "\uD83D\uDD51";
        }

        return icon;
    }

    private String getContent(BotEventName botEventName, String payload) {
        //💬 3: record @bot
        String result = "";

        if (botEventName == BotEventName.NEW_MESSAGE) {
            String content = JsonUtil.get("content", payload);
            JsonObject userObj = JsonUtil.toJsonObject(JsonUtil.get("user", payload));
            String userId = JsonUtil.get("id", userObj.toString());

            JsonArray jsonArray = JsonUtil.getJsonArray("tags", payload);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < jsonArray.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append("@").append(jsonArray.get(i).getAsJsonObject().get("userId").getAsString());
            }

            String tagStr = sb.toString();

            result = " " + userId + ": " + content + " " + tagStr;
        } else if (botEventName == BotEventName.BOT_NEW_MESSAGE) {
            String content = JsonUtil.get("content", payload);
            JsonArray jsonArray = JsonUtil.getJsonArray("tags", payload);
            StringBuilder sb = new StringBuilder();

            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    if (i > 0) sb.append(", ");
                    sb.append("@").append(jsonArray.get(i).getAsJsonObject().get("userId").getAsString());
                }
            }

            String tagStr = sb.toString();

            result = ": " + content + " " + tagStr;
        } else if (botEventName == BotEventName.GO_BROADCASTING) {
            String userId = JsonUtil.get("id", payload);
            result = " " + userId + " is broadcasting...";
        } else if (botEventName == BotEventName.SPEAK) {
            String content = JsonUtil.get("content", payload);
            JsonObject userObj = JsonUtil.toJsonObject(JsonUtil.get("user", payload));
            String userId = JsonUtil.get("id", userObj.toString());
            result = " " + userId + ": " + content;
        } else if (botEventName == BotEventName.STOP_BROADCASTING) {
            String userId = JsonUtil.get("id", payload);
            result = " " + userId + " stop broadcasting";
        } else if (botEventName == botEventName.NEW_POST) {
            String title = JsonUtil.get("title", payload);
            String content = JsonUtil.get("content", payload);
            JsonObject userObj = JsonUtil.toJsonObject(JsonUtil.get("user", payload));
            String userId = JsonUtil.get("id", userObj.toString());
            JsonArray jsonArray = JsonUtil.getJsonArray("tags", payload);
            StringBuilder sb = new StringBuilder();

            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    if (i > 0) sb.append(", ");
                    sb.append("@").append(jsonArray.get(i).getAsJsonObject().get("userId").getAsString());
                }
            }

            String tagStr = sb.toString();
            result = userId + ": 【" + title + "】" + content + " " + tagStr;
        } else if (botEventName == botEventName.BOT_NEW_COMMENT) {
            //🤖 comment in post 201: How do you guys think about it? @bot, @1, @2, @3, @4, @5, @6, @7, @8, @9, @10
            String postId = JsonUtil.get("postId", payload);
            String content = JsonUtil.get("content", payload);
            JsonArray jsonArray = JsonUtil.getJsonArray("tags", payload);
            StringBuilder sb = new StringBuilder();

            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    if (i > 0) sb.append(", ");
                    sb.append("@").append(jsonArray.get(i).getAsJsonObject().get("userId").getAsString());
                }
            }

            String tagStr = sb.toString();
            result = " comment in post " + postId + ": " + content + " " + tagStr;
        }else if (botEventName == BotEventName.TIME_ELAPSED){
            //🕑 1 hours elapsed...
            String content = JsonUtil.get("content", payload);
            result = " " + content;
        }else if(botEventName == BotEventName.BOT_GO_BROADCASTING){
            String content = JsonUtil.get("content", payload);
            result = " " + content;
        }else if(botEventName == BotEventName.BOT_SPEAK){
            String content = JsonUtil.get("content", payload);
            result = " " + content;
        }
        else if(botEventName == BotEventName.BOT_KNOWLEDGE_KING_START_AGAIN){
            String content = JsonUtil.get("content", payload);
            result = ": " + content;
        }


        return result;
    }
}
