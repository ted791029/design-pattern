package com.ted.app.waterballCommunityBot.states;

import com.google.gson.JsonObject;
import com.ted.app.BotEventName;
import com.ted.app.BotEventResult;
import com.ted.app.waterballCommunity.EventManager;
import com.ted.app.waterballCommunityBot.StateResponseHandler;
import com.ted.bot.BotAction;
import com.ted.bot.BotContext;
import com.ted.bot.BotEvent;
import com.ted.bot.status.BotLeafState;
import com.ted.util.JsonUtil;

import java.util.*;

public class Questioning extends BotLeafState<BotEventName> {

    private String[] answer = {"A", "C", "A"};

    private long countdownTime;

    private int index;

    private boolean isNotMoreQuestion = false;

    private String[] questions = {
            "請問哪個 SQL 語句用於選擇所有的行？\nA) SELECT *\nB) SELECT ALL\nC) SELECT ROWS\nD) SELECT DATA",
            "請問哪個 CSS 屬性可用於設置文字的顏色？ \nA) text-align \nB) font-size \nC) color \nD) padding",
            "請問在計算機科學中，「XML」代表什麼？ \nA) Extensible Markup Language\nB) Extensible Modeling Language\nC) Extended Markup Language\nD) Extended Modeling Language"
    };

    private final long overTime = 60 * 60 * 1000;

    private final Map<String, Integer> scoreMap;

    private StateResponseHandler stateResponseHandler;

    private long startTime;

    private final String tagBot = "bot";

    private Timer timer = new Timer();

    public Questioning(BotAction<BotEventName> enter, BotAction<BotEventName> exit, Map<String, Integer> scoreMap, StateResponseHandler stateResponseHandler) {
        super(enter, exit);
        this.scoreMap = scoreMap;
        this.stateResponseHandler = stateResponseHandler;
    }

    public void addCorrecterScore(String memberId) {
        int score = scoreMap.getOrDefault(memberId, 0);
        scoreMap.put(memberId, ++score);
    }

    public void addIndex() {
        index++;
    }

    public void elapsed(long time) {
        setCountdownTime(countdownTime - time);

        if (countdownTime == 0) {
            sendQuestioningIsEndEvent();
        }
    }

    public String generateQuestion() {
        return questions[index];
    }

    public void generateQuestionEventResult(BotContext<BotEventName> context) {
        //🤖: 0. 請問哪個 SQL 語句用於選擇所有的行？
        //A) SELECT *
        //B) SELECT ALL
        //C) SELECT ROWS
        //D) SELECT DATA
        JsonObject jsonObject = new JsonObject();
        Questioning questioning = (Questioning) context.getState();
        String content = questioning.getIndex() + ". " + questioning.generateQuestion();
        jsonObject.addProperty("content", content);
        String result = jsonObject.toString();
        context.addEventResult(BotEventResult.BOT_GENERATE_QUESTION.getName(), result);
    }


    public String getWinner() {
        int highestScore = 0;
        List<String> winners = new ArrayList<>();

        for (String id : scoreMap.keySet()) {
            int score = scoreMap.get(id);

            if (score > highestScore) {
                highestScore = score;
                winners = new ArrayList<>();
                winners.add(id);
            } else if (score == highestScore) {
                winners.add(id);
            }
        }

        return winners.size() == 1 ? winners.get(0) : null;
    }

    public boolean isEnd() {
        return isNotMoreQuestion || isOverTime();
    }

    public void reset() {
        initTimer();
        index = 0;
        isNotMoreQuestion = false;
    }

    @Override
    protected void response(BotContext<BotEventName> context) {
        stateResponseHandler.handle(context);
    }

    public void sendQuestioningIsEndEvent() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("content", "");
            EventManager.submit(new BotEvent<>(BotEventName.QUESTIONING_IS_END, JsonUtil.toJson(jsonObject)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void initTimer() {
        countdownTime = overTime;
        initStartTime();
        initTimerTask();
    }

    private void initStartTime() {
        startTime = System.currentTimeMillis();
    }

    private void initTimerTask() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //發送超時事件
                sendQuestioningIsEndEvent();
            }
        };

        timer.schedule(task, overTime);
    }

    private boolean isOverTime() {
        long now = System.currentTimeMillis();
        boolean isOvertime = (now - startTime) >= countdownTime;
        return isOvertime;
    }

    //======================


    public String[] getQuestions() {
        return questions;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public String[] getAnswer() {
        return answer;
    }

    public long getCountdownTime() {
        return countdownTime;
    }

    public void setCountdownTime(long countdownTime) {
        if (countdownTime <= 0) {
            this.countdownTime = 0;
            return;
        }

        this.countdownTime = countdownTime;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isNotMoreQuestion() {
        return isNotMoreQuestion;
    }

    public void setNotMoreQuestion(boolean notMoreQuestion) {
        isNotMoreQuestion = notMoreQuestion;
    }

    public long getOverTime() {
        return overTime;
    }

    public String getTagBot() {
        return tagBot;
    }

    public StateResponseHandler getStateResponseHandler() {
        return stateResponseHandler;
    }

    public void setStateResponseHandler(StateResponseHandler stateResponseHandler) {
        this.stateResponseHandler = stateResponseHandler;
    }
}
