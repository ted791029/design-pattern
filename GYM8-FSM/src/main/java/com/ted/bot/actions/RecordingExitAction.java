package com.ted.bot.actions;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ted.bot.BotEventResult;
import com.ted.bot.states.Recording;
import com.ted.fsm.Action;
import com.ted.fsm.Context;

public class RecordingExitAction implements Action {

    @Override
    public void execute(Context context) {
        Recording recording = (Recording) context.getState();
        List<String> recordingFile = recording.getRecordingFile();
        String result = toResultJson(recording.getRecorderIdVal(), recordingFile);
        context.addEventResult(BotEventResult.BOT_RECORD_REPLAY.getName(), result);
        recording.clear();
    }

    private String toResultJson(String recorderId, List<String> recordingFile){
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
