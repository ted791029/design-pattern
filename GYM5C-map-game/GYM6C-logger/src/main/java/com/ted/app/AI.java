package com.ted.app;

import com.ted.app.logger.Logger;

import static com.ted.app.logger.LogManager.getLogger;

public class AI {

    private final Logger log = getLogger("app.game.ai").get();

    private String name;

    public AI(String name) {
        this.name = name;
    }

    public void makeDecision() {
        log.trace(name + " starts making decisions...");
        log.warn(name + " decides to give up.");
        log.error("Something goes wrong when AI gives up.");
        log.trace(name + " completes its decision.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
