package com.ted.bot.states;

import com.google.gson.JsonObject;
import com.ted.app.EventManager;
import com.ted.bot.BotEvent;
import com.ted.bot.BotEventName;
import com.ted.fsm.*;
import com.ted.util.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

public class ThanksForJoining extends KnowledgeKing {

    private long countdownTime;

    private final long overTime = 20 * 1000;

    public ThanksForJoining(State initial, List<Transition> transitions, Map<String, String> resultMap, Action enter, Action exit, Map<String, Integer> scoreMap) {
        super(initial, transitions, resultMap, enter, exit, scoreMap);
    }

    public void elapsed(long time) {
        setCountdownTime(countdownTime - time);

        if(countdownTime == 0){
            sendKnowledgeKingIsEndEvent();
        }
    }

    public void initTimer() {
        countdownTime = overTime;
        initStartTime();
        initTimerTask();

    }

    @Override
    public void response(Context context){
        Event event = context.getEvent();

        if(BotEventName.TIME_ELAPSED.getName().equals(event.getEventName())){
            ThanksForJoining thanksForJoining = (ThanksForJoining) context.getState();
            String payload = context.getEventPayload();
            long time = Long.parseLong(JsonUtil.get("time", payload));
            thanksForJoining.elapsed(time);
        }
    }

    public void sendKnowledgeKingIsEndEvent() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("content", "");
            EventManager.submit(new BotEvent(BotEventName.KNOWLEDGE_KING_IS_END.getName(), JsonUtil.toJson(jsonObject)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void initStartTime() {
        setStartTime(System.currentTimeMillis());
    }

    private void initTimerTask() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //發送超時事件
                sendKnowledgeKingIsEndEvent();
            }
        };

        getTimer().schedule(task, overTime);
    }

    //===================================

    public long getCountdownTime() {
        return countdownTime;
    }

    public void setCountdownTime(long countdownTime) {
        this.countdownTime = countdownTime;
    }

    public long getOverTime() {
        return overTime;
    }
}
