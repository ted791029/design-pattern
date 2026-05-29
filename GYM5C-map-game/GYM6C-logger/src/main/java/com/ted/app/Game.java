package com.ted.app;

import com.ted.app.logger.Logger;

import static com.ted.app.logger.LogManager.getLogger;

public class Game {

    private final Logger log = getLogger("app.game").get();

    private final AI[] players = {new AI("AI 1"), new AI("AI 2"), new AI("AI 3"), new AI("AI 4")};

    public void start(){
        log.info("The game begins.");

        for (AI ai: players) {
            log.trace("The player "+ ai.getName() +" begins his turn.");
            ai.makeDecision();
            log.trace("The player "+ ai.getName() +" finishes his turn.");
        }

        log.debug("Game ends.");
    }
}
