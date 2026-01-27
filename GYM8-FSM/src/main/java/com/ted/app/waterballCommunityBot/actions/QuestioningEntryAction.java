package com.ted.app.waterballCommunityBot.actions;

import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.app.waterballCommunityBot.states.Questioning;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;
import com.ted.util.JsonUtil;

public class QuestioningEntryAction extends BotAction<BotEventName>{

    private final String command = "king";

    @Override
    public void execute(BotContext<BotEventName> context) {
        Questioning questioning = (Questioning) context.getState();
        String payload = context.getEventPayload();
        String content = JsonUtil.get("content", payload);
        questioning.reset();
        if (command.equals(content)) {
            entryMessage(context);
        }
        questioning.generateQuestionEventResult(context);
    }

    private void entryMessage(BotContext<BotEventName> context) {
        //🤖: KnowledgeKing is started!
        JsonObject jsonObject = new JsonObject();
        String content = "KnowledgeKing is started!";
        jsonObject.addProperty("content", content);
        String result = jsonObject.toString();
        context.addEventResult(BotEventResult.BOT_REPLY_KNOWLEDGE_KING_IS_STARTED.getName(), result);
    }
}
