package com.ted.app;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ted.bot.BotEvent;
import com.ted.bot.BotEventName;
import com.ted.bot.BotEventResult;
import com.ted.util.JsonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BotResponseHandler {

    public void handle(Map<String, String> resultMap) throws InterruptedException {

        if(resultMap.size() == 0){
            return;
        }

        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            JsonObject jsonObject = JsonParser.parseString(entry.getValue()).getAsJsonObject();
            handleByEventName(entry.getKey(), jsonObject);
        }
    }

    private void handleByEventName(String name, JsonObject jsonObject) throws InterruptedException {

        BotEventResult eventResult = BotEventResult.fromName(name);

        switch (eventResult) {
            case BOT_REPLY_MESSAGE:
                handleReplyMessage(jsonObject);
                break;
            case BOT_REPLY_POST:
                handleReplayPost(jsonObject);
                break;
            case BOT_RECORD_REPLAY:
                handleReplayRecord(jsonObject);
                break;
            case BOT_REPLY_KNOWLEDGE_KING_IS_STARTED:
                handleReplyKnowledgeKingIsStarted(jsonObject);
                break;
            case BOT_GENERATE_QUESTION:
                handleGenerateQuestion(jsonObject);
                break;
            case BOT_REPLY_CORRECT_ANSWER:
                handleReplyCorrectAnswer(jsonObject);
                break;
            case BOT_GO_BROADCASTING:
                handleBotGoBroadcasting(jsonObject);
                break;
            case BOT_SPEAK:
                handleBotSpeak(jsonObject);
                break;
            case BOT_STOP_BROADCASTING:
                handleBotStopBroadcasting(jsonObject);
                break;
            case BOT_KNOWLEDGE_KING_START_AGAIN:
                handleBotKnowledgeKingStartAgain(jsonObject);
                break;

        }
    }

    private void handleReplyMessage(JsonObject jsonObject) throws InterruptedException {
        String content = jsonObject.get("content").getAsString();
        JsonArray tagsArray = jsonObject.getAsJsonArray("tags");
        User user = new User("", false);

        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < tagsArray.size(); i++) {
            String tagValue = tagsArray.get(i).getAsString();
            tags.add(new Tag(tagValue));
        }

        Message message = new Message(content, tags, user);
        EventManager.submit(new BotEvent(BotEventName.BOT_NEW_MESSAGE.getName(), JsonUtil.toJson(message)));
    }

    private void handleReplayPost(JsonObject jsonObject) throws InterruptedException {
        String postId = jsonObject.get("postId").getAsString();
        String content = jsonObject.get("content").getAsString();
        JsonArray tagsArray = jsonObject.getAsJsonArray("tags");
        User user = new User("", false);

        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < tagsArray.size(); i++) {
            String tagValue = tagsArray.get(i).getAsString();
            tags.add(new Tag(tagValue));
        }

        Comment comment = new Comment(content, postId, tags, user);
        EventManager.submit(new BotEvent(BotEventName.BOT_NEW_COMMENT.getName(), JsonUtil.toJson(comment)));
    }

    private void handleReplayRecord(JsonObject jsonObject) throws InterruptedException {
        String content = jsonObject.get("content").getAsString();
        JsonArray tagsArray = jsonObject.getAsJsonArray("tags");
        User user = new User("", false);

        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < tagsArray.size(); i++) {
            String tagValue = tagsArray.get(i).getAsString();
            tags.add(new Tag(tagValue));
        }

        Message message = new Message(content, tags, user);
        EventManager.submit(new BotEvent(BotEventName.BOT_NEW_MESSAGE.getName(), JsonUtil.toJson(message)));
    }

    private void handleReplyKnowledgeKingIsStarted(JsonObject jsonObject) throws InterruptedException {
        String content = jsonObject.get("content").getAsString();
        Message message = new Message(content, null, null);
        EventManager.submit(new BotEvent(BotEventName.BOT_NEW_MESSAGE.getName(), JsonUtil.toJson(message)));
    }

    private void handleGenerateQuestion(JsonObject jsonObject) throws InterruptedException {
        String content = jsonObject.get("content").getAsString();
        Message message = new Message(content, null, null);
        EventManager.submit(new BotEvent(BotEventName.BOT_NEW_MESSAGE.getName(), JsonUtil.toJson(message)));
    }

    private void handleReplyCorrectAnswer(JsonObject jsonObject) throws InterruptedException {
        //🤖: Congrats! you got the answer! @1
        String content = jsonObject.get("content").getAsString();
        JsonArray tagsArray = jsonObject.getAsJsonArray("tags");
        List<Tag> tags = new ArrayList<>();

        for (int i = 0; i < tagsArray.size(); i++) {
            String tagValue = tagsArray.get(i).getAsString();
            tags.add(new Tag(tagValue));
        }

        Message message = new Message(content, tags, null);
        EventManager.submit(new BotEvent(BotEventName.BOT_NEW_MESSAGE.getName(), JsonUtil.toJson(message)));
    }

    private void handleBotGoBroadcasting(JsonObject jsonObject) throws InterruptedException {
        EventManager.submit(new BotEvent(BotEventName.BOT_GO_BROADCASTING.getName(), JsonUtil.toJson(jsonObject)));
    }
    private void handleBotSpeak(JsonObject jsonObject) throws InterruptedException {
        String content = jsonObject.get("content").getAsString();
        content = "speaking: " + content;
        jsonObject.addProperty("content", content);
        EventManager.submit(new BotEvent(BotEventName.BOT_GO_BROADCASTING.getName(), JsonUtil.toJson(jsonObject)));
    }

    private void handleBotStopBroadcasting(JsonObject jsonObject) throws InterruptedException {
        EventManager.submit(new BotEvent(BotEventName.BOT_GO_BROADCASTING.getName(), JsonUtil.toJson(jsonObject)));
    }

    private void handleBotKnowledgeKingStartAgain(JsonObject jsonObject) throws InterruptedException {
        EventManager.submit(new BotEvent(BotEventName.BOT_KNOWLEDGE_KING_START_AGAIN.getName(), JsonUtil.toJson(jsonObject)));
    }

    //==========================
}
