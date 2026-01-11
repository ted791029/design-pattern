package com.ted.bot.actions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.bot.BotEventResult;
import com.ted.bot.Community;
import com.ted.bot.states.Questioning;
import com.ted.fsm.Action;
import com.ted.fsm.Context;

public class QuestioningToThanksForJoiningAction implements Action {

    private Community community;

    public QuestioningToThanksForJoiningAction(Community community) {
        this.community = community;
    }

    @Override
    public void execute(Context context){
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

    private void sendByMessage(Context context, String content) {
        String result = toBroadcastResultJson(content);
        context.addEventResult(BotEventResult.BOT_REPLY_KNOWLEDGE_KING_IS_STARTED.getName(), result);
    }

    private void sendBySpeak(Context context, String content){
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
