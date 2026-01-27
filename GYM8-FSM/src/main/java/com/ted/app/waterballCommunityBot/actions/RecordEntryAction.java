package com.ted.app.waterballCommunityBot.actions;

import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.Community;
import com.ted.app.waterballCommunityBot.states.Record;
import com.ted.bot.BotAbstractState;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;
import com.ted.bot.BotEvent;
import com.ted.util.JsonUtil;

import java.util.Map;

public class RecordEntryAction extends BotAction<BotEventName> {

    private final Community community;

    public RecordEntryAction(Community community) {
        this.community = community;
    }

    @Override
    public void execute(BotContext<BotEventName> context) {
        Record record = (Record) context.getState();
        BotEvent<BotEventName> event = context.getEvent();
        initRecordId(record, event);
        Map<String, BotAbstractState<BotEventName>> childrenMap = record.getChildrenMap();

        if (!community.isBroadcasting()) {
            record.setCurrent(childrenMap.get("Waiting"));
        } else {
            record.setCurrent(childrenMap.get("Recording"));
        }
    }

    private void initRecordId(Record record, BotEvent<BotEventName> event) {
        String payload = event.getPayload();
        JsonObject memberObj = JsonUtil.toJsonObject(JsonUtil.get("member", payload));
        String memberId = JsonUtil.get("id", memberObj.toString());
        record.setRecorderIdVal(memberId);
    }
}
