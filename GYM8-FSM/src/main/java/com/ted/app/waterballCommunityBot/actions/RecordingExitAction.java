package com.ted.app.waterballCommunityBot.actions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.app.waterballCommunityBot.states.Recording;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;

import java.util.List;
import java.util.stream.Collectors;

public class RecordingExitAction extends BotAction<BotEventName> {

    @Override
    public void execute(BotContext<BotEventName> context) {
        Recording recording = (Recording) context.getState();
        List<String> recordingFile = recording.getRecordingFile();
        String result = toResultJson(recording.getRecorderIdVal(), recordingFile);
        context.addEventResult(BotEventResult.BOT_RECORD_REPLAY.getName(), result);
        recording.clear();
    }

    private String toResultJson(String recorderId, List<String> recordingFile) {
        JsonObject jsonObject = new JsonObject();
        String content = "[Record Replay] ";
        content += recordingFile.stream()
                .collect(Collectors.joining("\n"))
                .toString();
        jsonObject.addProperty("content", content);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(recorderId);
        jsonObject.add("tags", jsonArray);
        return jsonObject.toString();
    }
}
