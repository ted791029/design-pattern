package com.ted.app.waterballCommunityBot.actions;

import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;

public class ThanksForJoiningToQuestioningAction extends BotAction<BotEventName> {
    @Override
    public void execute(BotContext<BotEventName> context) {
        //🤖: KnowledgeKing is gonna start again!
        JsonObject jsonObject = new JsonObject();
        String content = "KnowledgeKing is gonna start again!";
        jsonObject.addProperty("content", content);
        String result = jsonObject.toString();
        context.addEventResult(BotEventResult.BOT_KNOWLEDGE_KING_START_AGAIN.getName(), result);
    }
}
