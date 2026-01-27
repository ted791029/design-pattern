package com.ted.app.waterballCommunityBot.states;

import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.app.waterballCommunity.EventManager;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;
import com.ted.bot.BotEvent;
import com.ted.bot.status.BotLeafState;
import com.ted.util.JsonUtil;

import java.util.Timer;
import java.util.TimerTask;

public class ThanksForJoining extends BotLeafState<BotEventName> {

    private long countdownTime;

    private final long overTime = 20 * 1000;

    private long startTime;

    private Timer timer = new Timer();

    public ThanksForJoining(BotAction<BotEventName> enter, BotAction<BotEventName> exit) {
        super(enter, exit);
    }

    public void elapsed(long time) {
        setCountdownTime(countdownTime - time);

        if (countdownTime == 0) {
            sendKnowledgeKingIsEndEvent();
        }
    }

    public void initTimer() {
        countdownTime = overTime;
        initStartTime();
        initTimerTask();

    }

    @Override
    protected void response(BotContext<BotEventName> context) {
        BotEvent<BotEventName> event = context.getEvent();

        if (BotEventName.TIME_ELAPSED.equals(event.getEventName())) {
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
            EventManager.submit(new BotEvent<>(BotEventName.KNOWLEDGE_KING_IS_END, JsonUtil.toJson(jsonObject)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void initStartTime() {
        startTime = System.currentTimeMillis();
    }

    private void initTimerTask() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //發送超時事件
                sendKnowledgeKingIsEndEvent();
            }
        };

        timer.schedule(task, overTime);
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
