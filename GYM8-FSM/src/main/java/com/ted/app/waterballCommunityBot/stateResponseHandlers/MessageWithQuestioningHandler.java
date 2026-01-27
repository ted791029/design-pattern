package com.ted.app.waterballCommunityBot.stateResponseHandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.app.waterballCommunityBot.StateResponseHandler;
import com.ted.app.waterballCommunityBot.states.Questioning;
import com.ted.bot.BotContext;
import com.ted.bot.BotEvent;
import com.ted.util.JsonUtil;

import java.util.List;

public class MessageWithQuestioningHandler extends StateResponseHandler {

    public MessageWithQuestioningHandler(StateResponseHandler next) {
        super(next);
    }

    @Override
    protected boolean match(BotContext<BotEventName> context) {
        Questioning questioning = (Questioning) context.getState();
        BotEvent<BotEventName> event = context.getEvent();
        String payload = context.getEventPayload();
        String content = JsonUtil.get("content", payload);
        List<String> tags = JsonUtil.getArray("tags", payload);

        return BotEventName.NEW_MESSAGE.equals(event.getEventName()) && isTagBot(tags, questioning.getTagBot()) && isCorrect(content, questioning.getAnswer(), questioning.getIndex());
    }

    @Override
    protected void handling(BotContext<BotEventName> context) {
        Questioning questioning = (Questioning) context.getState();
        String payload = context.getEventPayload();
        recordCorrecter(questioning, payload);
        initResponse(context, questioning, payload);
        next(context, questioning);
    }

    private boolean isTagBot(List<String> tags, String tagBot) {
        return tags.stream().anyMatch(s -> s.contains("\"memberId\":\"" + tagBot + "\""));
    }

    private boolean isCorrect(String content, String[] answer, int index) {

        return answer[index].equals(content);
    }

    private void initResponse(BotContext<BotEventName> context, Questioning questioning, String payload) {
        //🤖: Congrats! you got the answer! @1
        questioning.addIndex();
        String result = toResultJson(payload);
        context.addEventResult(BotEventResult.BOT_REPLY_CORRECT_ANSWER.getName(), result);
    }

    private void next(BotContext<BotEventName> context, Questioning questioning) {
        if (questioning.getIndex() >= questioning.getQuestions().length) {
            questioning.setNotMoreQuestion(true);
        } else {
            questioning.generateQuestionEventResult(context);
        }
    }

    private void recordCorrecter(Questioning questioning, String payload) {
        String member = JsonUtil.get("member", payload);
        String memberId = JsonUtil.get("id", member);
        questioning.addCorrecterScore(memberId);
    }

    private String toResultJson(String payload) {
        JsonObject jsonObject = new JsonObject();
        String content = "Congrats! you got the answer!";
        jsonObject.addProperty("content", content);
        String member = JsonUtil.get("member", payload);
        String memberId = JsonUtil.get("id", member);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(memberId);
        jsonObject.add("tags", jsonArray);
        return jsonObject.toString();
    }
}
