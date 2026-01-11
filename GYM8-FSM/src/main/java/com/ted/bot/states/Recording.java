package com.ted.bot.states;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ted.bot.BotEventName;
import com.ted.bot.Id;
import com.ted.fsm.Action;
import com.ted.fsm.Context;
import com.ted.fsm.Event;
import com.ted.fsm.State;
import com.ted.fsm.Transition;
import com.ted.util.JsonUtil;

public class Recording extends Record{

    private List<String> recordingFile = new ArrayList<>();

    public Recording(State initial, List<Transition> transitions, Map<String, String> resultMap, Action enter, Action exit, Id recorderId, Map<String, Record> childrenMap) {
        super(initial, transitions, resultMap, enter, exit, recorderId, childrenMap);
    }

    public void clear(){
        recordingFile = new ArrayList<>();
    }

    @Override
    public void response(Context context) {
        Event event = context.getEvent();

        if(BotEventName.SPEAK.getName().equals(event.getEventName())){
            String payload = event.getPayload();
            String content = JsonUtil.get("content", payload);
            recordingFile.add(content);
        }
    }

    //====================================

    public List<String> getRecordingFile() {
        return recordingFile;
    }

    public void setRecordingFile(List<String> recordingFile) {
        this.recordingFile = recordingFile;
    }
}
