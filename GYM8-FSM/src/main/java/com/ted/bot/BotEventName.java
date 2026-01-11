package com.ted.bot;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum BotEventName {
    STARTED("started"),
    LOGIN("login"),
    LOGOUT("logout"),
    NEW_MESSAGE("new message"),
    NEW_POST("new post"),
    SPEAK("speak"),
    GO_BROADCASTING("go broadcasting"),
    STOP_BROADCASTING("stop broadcasting"),
    BOT_NEW_MESSAGE("bot new message"),
    BOT_NEW_COMMENT("bot new comment"),
    TIME_ELAPSED("time elapsed"),
    BOT_GO_BROADCASTING("bot go broadcasting"),
    BOT_SPEAK("bot speak"),
    BOT_STOP_BROADCASTING("bot stop broadcasting"),
    QUESTIONING_IS_END("questioning is end"),
    KNOWLEDGE_KING_IS_END("knowledge king is end"),
    BOT_KNOWLEDGE_KING_START_AGAIN("bot knowledge king start again");


    private static final Map<String, BotEventName> MAP =
            Arrays.stream(values())
                    .collect(Collectors.toMap(
                            eventName -> eventName.name,
                            eventName -> eventName
                    ));

    private final String name;

    BotEventName(String name) {
        this.name = name;
    }

    public static BotEventName fromName(String name) {
        return MAP.get(name);
    }

    public String getName() {
        return name;
    }
}
