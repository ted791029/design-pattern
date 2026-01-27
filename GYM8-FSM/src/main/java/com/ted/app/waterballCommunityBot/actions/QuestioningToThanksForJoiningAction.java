package com.ted.app.waterballCommunityBot.actions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.app.waterballCommunityBot.Community;
import com.ted.app.waterballCommunityBot.states.Questioning;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;

public class QuestioningToThanksForJoiningAction extends BotAction<BotEventName> {

    private final Community community;

    public QuestioningToThanksForJoiningAction(Community community) {
        this.community = community;
    }

    @Override
    public void execute(BotContext<BotEventName> context) {
        Questioning questioning = (Questioning) context.getState();
        String winner = questioning.getWinner();
        String content = getContent(winner);

        if (community.isBroadcasting()) {
            sendByMessage(context, content);
        } else {
            sendBySpeak(context, content);
        }
    }

    private String getContent(String winner) {
        if (winner == null) {
            return "Tie!";
        }
        return "The winner is " + winner;
    }

    private void sendByMessage(BotContext<BotEventName> context, String content) {
        String result = toBroadcastResultJson(content);
        context.addEventResult(BotEventResult.BOT_REPLY_KNOWLEDGE_KING_IS_STARTED.getName(), result);
    }

    private void sendBySpeak(BotContext<BotEventName> context, String content) {
        //🤖 go broadcasting...
        //🤖 speaking: Tie!
        //🤖 stop broadcasting..
        // 廣播應該必須分開
        community.botGoBroadcasting();
        String result = toBroadcastResultJson("go broadcasting...");
        context.addEventResult(BotEventResult.BOT_GO_BROADCASTING.getName(), result);
        result = toBroadcastResultJson(content);
        context.addEventResult(BotEventResult.BOT_SPEAK.getName(), result);
        community.botStopBroadcasting();
        result = toBroadcastResultJson("stop broadcasting...");
        context.addEventResult(BotEventResult.BOT_STOP_BROADCASTING.getName(), result);
    }


    private String toBroadcastResultJson(String content) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", content);
        return jsonObject.toString();
    }

    private String toMessageResultJson(String content) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", content);
        JsonArray jsonArray = new JsonArray();
        jsonObject.add("tags", jsonArray);
        return jsonObject.toString();
    }
}
