package com.ted.bot.guards;

import com.ted.bot.Bot;
import com.ted.fsm.Context;
import com.ted.fsm.Guard;
import com.ted.util.JsonUtil;

import java.util.List;

public class ThanksForJoiningToQuestioningCommand implements Guard {
    private Bot bot;
    private final String command = "play again";
    private final String tagBot = "bot";
    private final int quota = 5;

    public ThanksForJoiningToQuestioningCommand(Bot bot) {
        this.bot = bot;
    }

    @Override
    public boolean evaluate(Context context) {
        String payload = context.getEventPayload();
        String content = JsonUtil.get("content", payload);
        List<String> tags = JsonUtil.getArray("tags", payload);
        boolean isEvaluate = command.equals(content) && tags.stream().anyMatch(s -> s.contains("\"userId\":\"" + tagBot +"\"")) && bot.getQuota() >= quota;

        if(isEvaluate){
            bot.useQuota(quota);
        }

        return isEvaluate;
    }
}
