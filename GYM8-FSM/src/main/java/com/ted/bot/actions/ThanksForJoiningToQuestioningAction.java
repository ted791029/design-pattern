package com.ted.bot.actions;

import com.google.gson.JsonObject;
import com.ted.bot.BotEventResult;
import com.ted.fsm.Action;
import com.ted.fsm.Context;

public class ThanksForJoiningToQuestioningAction implements Action {
    @Override
    public void execute(Context context) {
        //🤖: KnowledgeKing is gonna start again!
        JsonObject jsonObject = new JsonObject();
        String content = "KnowledgeKing is gonna start again!";
        jsonObject.addProperty("content", content);
        String result = jsonObject.toString();
        context.addEventResult(BotEventResult.BOT_KNOWLEDGE_KING_START_AGAIN.getName(), result);
    }
}
