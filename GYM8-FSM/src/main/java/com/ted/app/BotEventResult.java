package com.ted.app;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum BotEventResult {

    BOT_REPLY_MESSAGE("bot reply message"),
    BOT_RECORD_REPLAY("bot record replay"),
    BOT_REPLY_POST("bot reply post"),
    BOT_REPLY_KNOWLEDGE_KING_IS_STARTED("bot reply knowledge king is started"),
    BOT_GENERATE_QUESTION("bot generate question"),
    BOT_REPLY_CORRECT_ANSWER("bot reply correct answer"),

    BOT_GO_BROADCASTING("bot go broadcasting"),

    BOT_SPEAK("bot speak"),

    BOT_STOP_BROADCASTING("bot stop broadcasting"),

    BOT_KNOWLEDGE_KING_START_AGAIN("bot knowledge king start again");


    private static final Map<String, BotEventResult> MAP =
            Arrays.stream(values())
                    .collect(Collectors.toMap(
                            result -> result.name,
                            result -> result
                    ));

    private final String name;

    BotEventResult(String name) {
        this.name = name;
    }

    public static BotEventResult fromName(String name) {
        return MAP.get(name);
    }

    public String getName() {
        return name;
    }
}
