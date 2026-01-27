package com.ted.app.waterballCommunityBot.states;

import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.Id;
import com.ted.bot.BotAbstractState;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;
import com.ted.bot.BotTransition;
import com.ted.bot.status.BotComponentState;

import java.util.List;
import java.util.Map;

public class Record extends BotComponentState<BotEventName> {

    private Map<String, BotAbstractState<BotEventName>> childrenMap;

    private Id recorderId;

    public Record(BotAbstractState<BotEventName> initial, List<BotTransition<BotEventName>> transitions, BotAction<BotEventName> enter, BotAction<BotEventName> exit, Id recorderId, Map<String, BotAbstractState<BotEventName>> childrenMap) {
        super(initial, transitions, enter, exit);
        this.recorderId = recorderId;
        this.childrenMap = childrenMap;
    }

    @Override
    protected void response(BotContext<BotEventName> context) {

    }

    public String getRecorderIdVal() {
        return recorderId.getVal();
    }

    public void setRecorderIdVal(String id) {
        recorderId.setVal(id);
    }


    //=============================================

    public Id getRecorderId() {
        return recorderId;
    }

    public void setRecorderId(Id recorderId) {
        this.recorderId = recorderId;
    }

    public Map<String, BotAbstractState<BotEventName>> getChildrenMap() {
        return childrenMap;
    }

    public void setChildrenMap(Map<String, BotAbstractState<BotEventName>> childrenMap) {
        this.childrenMap = childrenMap;
    }
}

