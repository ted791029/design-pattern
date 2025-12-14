package com.ted.app.logger;

public enum Level {
    TRACE(1),
    INFO(2),
    DEBUG(3),
    WARN(4),
    ERROR(5);

    private final int rank;

    Level(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
