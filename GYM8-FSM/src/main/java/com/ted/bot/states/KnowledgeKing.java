package com.ted.bot.states;

import com.ted.fsm.Action;
import com.ted.fsm.Context;
import com.ted.fsm.State;
import com.ted.fsm.Transition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

public class KnowledgeKing extends State {

    private final Map<String, Integer> scoreMap;

    private Timer timer = new Timer();

    private long startTime;

    public KnowledgeKing(State initial, List<Transition> transitions, Map<String, String> resultMap, Action enter, Action exit, Map<String, Integer> scoreMap) {
        super(initial, transitions, resultMap, enter, exit);
        this.scoreMap = scoreMap;
    }

    @Override
    public void response(Context context) {

    }

    //=========================
    public Map<String, Integer> getScoreMap() {
        return scoreMap;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
