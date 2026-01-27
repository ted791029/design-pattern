package com.ted.app.waterballCommunityBot.guards;

import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.Bot;
import com.ted.bot.BotContext;
import com.ted.bot.BotGuard;
import com.ted.util.JsonUtil;

import java.util.List;

public class NormalToRecordWithCommand extends BotGuard<BotEventName> {

    private final Bot bot;
    private final String command = "record";
    private final String tagBot = "bot";

    private final int quota = 3;

    public NormalToRecordWithCommand(Bot bot) {
        this.bot = bot;
    }

    @Override
    public boolean evaluate(BotContext<BotEventName> context) {
        String payload = context.getEventPayload();
        String content = JsonUtil.get("content", payload);
        List<String> tags = JsonUtil.getArray("tags", payload);
        boolean isEvaluate = command.equals(content) && tags.stream().anyMatch(s -> s.contains("\"memberId\":\"" + tagBot + "\"")) && bot.getQuota() >= quota;

        if (isEvaluate) {
            bot.useQuota(quota);
        }

        return isEvaluate;
    }
}
