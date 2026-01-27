package com.ted.app.waterballCommunity;

import com.ted.app.BotEventName;
import com.ted.bot.BotEvent;

import java.util.Map;

public interface CommunityBot {

    public Map<String, String> sendEvent(BotEvent<BotEventName> event);
}
