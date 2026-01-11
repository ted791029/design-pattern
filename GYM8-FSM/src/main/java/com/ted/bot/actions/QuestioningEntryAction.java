package com.ted.bot.actions;

import com.google.gson.JsonObject;
import com.ted.bot.BotEventResult;
import com.ted.bot.states.Questioning;
import com.ted.fsm.Action;
import com.ted.fsm.Context;
import com.ted.util.JsonUtil;

public class QuestioningEntryAction implements Action {

    private final String command = "king";
    @Override
    public void execute(Context context) {
        Questioning questioning = (Questioning) context.getState();
        String payload = context.getEventPayload();
        String content = JsonUtil.get("content", payload);
        questioning.reset();
        if(command.equals(content)){
            entryMessage(context);
        }
        questioning.generateQuestionEventResult(context);
    }

    private void entryMessage(Context context){
        //🤖: KnowledgeKing is started!
        JsonObject jsonObject = new JsonObject();
        String content = "KnowledgeKing is started!";
        jsonObject.addProperty("content", content);
        String result = jsonObject.toString();
        context.addEventResult(BotEventResult.BOT_REPLY_KNOWLEDGE_KING_IS_STARTED.getName(), result);
    }
}
