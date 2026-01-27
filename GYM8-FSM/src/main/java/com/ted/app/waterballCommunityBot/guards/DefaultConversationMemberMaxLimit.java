package com.ted.app.waterballCommunityBot.guards;

import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.Community;
import com.ted.bot.BotContext;
import com.ted.bot.BotGuard;

public class DefaultConversationMemberMaxLimit extends BotGuard<BotEventName> {

    private final Community community;

    public DefaultConversationMemberMaxLimit(Community community) {
        this.community = community;
    }

    @Override
    public boolean evaluate(BotContext<BotEventName> context) {
        // 加上bot為10人
        return community.totalOnlineMember() >= 9;
    }
}
