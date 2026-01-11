package com.ted.bot.stateResponseHandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.bot.BotEventName;
import com.ted.bot.BotEventResult;
import com.ted.bot.StateResponseHandler;
import com.ted.bot.states.Questioning;
import com.ted.fsm.Context;
import com.ted.fsm.Event;
import com.ted.util.JsonUtil;

import java.util.List;

public class MessageWithQuestioningHandler extends StateResponseHandler {

    public MessageWithQuestioningHandler(StateResponseHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Context context) {
        Questioning questioning = (Questioning) context.getState();
        Event event = context.getEvent();
        String payload = context.getEventPayload();
        String content = JsonUtil.get("content", payload);
        List<String> tags = JsonUtil.getArray("tags", payload);

        return BotEventName.NEW_MESSAGE.getName().equals(event.getEventName()) && isTagBot(tags, questioning.getTagBot()) && isCorrect(content, questioning.getAnswer(), questioning.getIndex());
    }

    @Override
    protected void handling(Context context) {
        Questioning questioning = (Questioning) context.getState();
        String payload = context.getEventPayload();
        recordCorrecter(questioning, payload);
        initResponse(context, questioning, payload);
        next(context, questioning);
    }

    private boolean isTagBot(List<String> tags, String tagBot){
        return tags.stream().anyMatch(s -> s.contains("\"userId\":\"" + tagBot +"\""));
    }

    private boolean isCorrect(String content, String[] answer, int index){

        return answer[index].equals(content);
    }

    private void initResponse(Context context, Questioning questioning, String payload){
        //🤖: Congrats! you got the answer! @1
        questioning.addIndex();
        String result = toResultJson(payload);
        context.addEventResult(BotEventResult.BOT_REPLY_CORRECT_ANSWER.getName(), result);
    }

    private void next(Context context, Questioning questioning){
        if(questioning.getIndex() >= questioning.getQuestions().length){
            questioning.setNotMoreQuestion(true);
        }else {
            questioning.generateQuestionEventResult(context);
        }
    }

    private void recordCorrecter(Questioning questioning, String payload){
        String user = JsonUtil.get("user", payload);
        String userId = JsonUtil.get("id", user);
        questioning.addCorrecterScore(userId);
    }

    private String toResultJson(String payload){
        JsonObject jsonObject = new JsonObject();
        String content = "Congrats! you got the answer!";
        jsonObject.addProperty("content", content);
        String user = JsonUtil.get("user", payload);
        String userId = JsonUtil.get("id", user);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(userId);
        jsonObject.add("tags", jsonArray);
        return jsonObject.toString();
    }
}
