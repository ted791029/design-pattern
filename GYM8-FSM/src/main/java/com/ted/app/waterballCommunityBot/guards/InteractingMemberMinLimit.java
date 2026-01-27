package com.ted.app.waterballCommunityBot.guards;

import com.ted.app.BotEventName;
import com.ted.app.waterballCommunityBot.Community;
import com.ted.bot.BotContext;
import com.ted.bot.BotGuard;

public class InteractingMemberMinLimit extends BotGuard<BotEventName> {

    private final Community community;

    public InteractingMemberMinLimit(Community community) {
        this.community = community;
    }

    @Override
    public boolean evaluate(BotContext<BotEventName> botContext) {
        //加上bot
        return community.totalOnlineMember() < 9;
    }
}
