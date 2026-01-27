package com.ted.app.waterballCommunityBot.guards;

import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.states.Record;
import com.ted.bot.BotContext;
import com.ted.bot.BotGuard;
import com.ted.util.JsonUtil;

import java.util.List;

public class RecordToNormalCommand extends BotGuard<BotEventName> {

    private final String command = "stop-recording";
    private final String tagBot = "bot";


    @Override
    public boolean evaluate(BotContext<BotEventName> context) {
        Record record = (Record) context.getState();
        String payload = context.getEventPayload();
        String content = JsonUtil.get("content", payload);
        JsonObject memberObj = JsonUtil.toJsonObject(JsonUtil.get("member", payload));
        String memberId = JsonUtil.get("id", memberObj.toString());
        List<String> tags = JsonUtil.getArray("tags", payload);
        boolean isEvaluate = command.equals(content) && tags.stream().anyMatch(s -> s.contains("\"memberId\":\"" + tagBot + "\"")) && record.getRecorderIdVal().equals(memberId);
        return isEvaluate;
    }
}
