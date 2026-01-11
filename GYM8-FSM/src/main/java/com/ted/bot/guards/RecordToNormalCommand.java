package com.ted.bot.guards;

import com.google.gson.JsonObject;
import com.ted.bot.states.Record;
import com.ted.fsm.Context;
import com.ted.fsm.Guard;
import com.ted.util.JsonUtil;

import java.util.List;

public class RecordToNormalCommand implements Guard {

    private final String command = "stop-recording";
    private final String tagBot = "bot";


    @Override
    public boolean evaluate(Context context) {
        Record record = (Record) context.getState();
        String payload = context.getEventPayload();
        String content = JsonUtil.get("content", payload);
        JsonObject userObj = JsonUtil.toJsonObject(JsonUtil.get("user", payload));
        String userId = JsonUtil.get("id", userObj.toString());
        List<String> tags = JsonUtil.getArray("tags", payload);
        boolean isEvaluate = command.equals(content) && tags.stream().anyMatch(s -> s.contains("\"userId\":\"" + tagBot + "\"")) && record.getRecorderIdVal().equals(userId);
        return isEvaluate;
    }
}
