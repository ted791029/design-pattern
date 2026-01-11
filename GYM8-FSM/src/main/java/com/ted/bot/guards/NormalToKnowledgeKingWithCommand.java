package com.ted.bot.guards;

import com.ted.bot.Bot;
import com.ted.fsm.Context;
import com.ted.fsm.Guard;
import com.ted.util.JsonUtil;
import java.util.List;

public class NormalToKnowledgeKingWithCommand implements Guard {

    private Bot bot;
    private final String command = "king";
    private final String tagBot = "bot";

    private final int quota = 5;

    public NormalToKnowledgeKingWithCommand(Bot bot) {
        this.bot = bot;
    }

    @Override
    public boolean evaluate(Context context) {
        String payload = context.getEventPayload();
        String content = JsonUtil.get("content", payload);
        String user = JsonUtil.get("user", payload);
        boolean isAdmin = Boolean.parseBoolean(JsonUtil.get("isAdmin", user));
        List<String> tags = JsonUtil.getArray("tags", payload);
        boolean isEvaluate = command.equals(content) && tags.stream().anyMatch(s -> s.contains("\"userId\":\"" + tagBot +"\"")) && bot.getQuota() >= quota && isAdmin;

        if(isEvaluate){
            bot.useQuota(quota);
        }

        return isEvaluate;
    }
}
