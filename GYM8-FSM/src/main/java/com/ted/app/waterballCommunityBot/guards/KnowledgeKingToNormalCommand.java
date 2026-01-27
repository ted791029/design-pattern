package com.ted.app.waterballCommunityBot.guards;

import com.ted.app.BotEventName;
import com.ted.bot.BotContext;
import com.ted.bot.BotGuard;
import com.ted.util.JsonUtil;

import java.util.List;

public class KnowledgeKingToNormalCommand extends BotGuard<BotEventName> {
    private final String command = "king-stop";
    private final String tagBot = "bot";


    @Override
    public boolean evaluate(BotContext<BotEventName> context) {
        String payload = context.getEventPayload();
        String content = JsonUtil.get("content", payload);
        String member = JsonUtil.get("member", payload);
        boolean isAdmin = Boolean.parseBoolean(JsonUtil.get("isAdmin", member));
        List<String> tags = JsonUtil.getArray("tags", payload);
        boolean isEvaluate = command.equals(content) && tags.stream().anyMatch(s -> s.contains("\"memberId\":\"" + tagBot + "\"")) && isAdmin;
        return isEvaluate;
    }
}
