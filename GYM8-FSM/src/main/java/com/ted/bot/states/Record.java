package com.ted.bot.states;

import com.ted.bot.Id;
import com.ted.fsm.Action;
import com.ted.fsm.Context;
import com.ted.fsm.State;
import com.ted.fsm.Transition;

import java.util.List;
import java.util.Map;

public class Record extends State {

    private Map<String, Record> childrenMap;

    private Id recorderId;

    public Record(State initial, List<Transition> transitions, Map<String, String> resultMap, Action enter, Action exit, Id recorderId, Map<String, Record> childrenMap) {
        super(initial, transitions, resultMap, enter, exit);
        this.recorderId = recorderId;
        this.childrenMap = childrenMap;
    }

    @Override
    public void response(Context context) {

    }

    public String getRecorderIdVal(){
        return recorderId.getVal();
    }

    public void setRecorderIdVal(String id){
        recorderId.setVal(id);
    }



    //=============================================

    public Id getRecorderId() {
        return recorderId;
    }

    public void setRecorderId(Id recorderId) {
        this.recorderId = recorderId;
    }

    public Map<String, Record> getChildrenMap() {
        return childrenMap;
    }

    public void setChildrenMap(Map<String, Record> childrenMap) {
        this.childrenMap = childrenMap;
    }
}

