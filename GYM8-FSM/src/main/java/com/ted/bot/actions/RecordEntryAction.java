package com.ted.bot.actions;

import com.google.gson.JsonObject;
import com.ted.bot.Community;
import com.ted.bot.states.Record;
import com.ted.fsm.Action;
import com.ted.fsm.Context;
import com.ted.fsm.Event;
import com.ted.util.JsonUtil;

import java.util.Map;

public class RecordEntryAction implements Action {

    private Community community;

    public RecordEntryAction(Community community) {
        this.community = community;
    }

    @Override
    public void execute(Context context) {
        Record record = (Record) context.getState();
        Event event = context.getEvent();
        initRecordId(record, event);
        Map<String, Record> childrenMap = record.getChildrenMap();

        if(!community.isBroadcasting()){
            record.setCurrent(childrenMap.get("Waiting"));
        }else {
            record.setCurrent(childrenMap.get("Recording"));
        }
    }

    private void initRecordId(Record record, Event event){
        String payload = event.getPayload();
        JsonObject userObj = JsonUtil.toJsonObject(JsonUtil.get("user", payload));
        String userId = JsonUtil.get("id", userObj.toString());
        record.setRecorderIdVal(userId);
    }
}
