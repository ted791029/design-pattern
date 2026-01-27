package com.ted.app.waterballCommunityBot.states;

import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.Id;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;
import com.ted.bot.BotEvent;
import com.ted.bot.status.BotLeafState;
import com.ted.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class Recording extends BotLeafState<BotEventName> {

    private Id recorderId;

    private List<String> recordingFile = new ArrayList<>();

    public Recording(BotAction<BotEventName> enter, BotAction<BotEventName> exit, Id recorderId) {
        super(enter, exit);
        this.recorderId = recorderId;
    }

    public void clear() {
        recordingFile = new ArrayList<>();
    }

    @Override
    protected void response(BotContext<BotEventName> context) {
        BotEvent<BotEventName> event = context.getEvent();

        if (BotEventName.SPEAK.equals(event.getEventName())) {
            String payload = event.getPayload();
            String content = JsonUtil.get("content", payload);
            recordingFile.add(content);
        }
    }

    public String getRecorderIdVal() {
        return recorderId.getVal();
    }

    public void setRecorderIdVal(String id) {
        recorderId.setVal(id);
    }

    //====================================

    public List<String> getRecordingFile() {
        return recordingFile;
    }

    public void setRecordingFile(List<String> recordingFile) {
        this.recordingFile = recordingFile;
    }

    public Id getRecorderId() {
        return recorderId;
    }

    public void setRecorderId(Id recorderId) {
        this.recorderId = recorderId;
    }
}
