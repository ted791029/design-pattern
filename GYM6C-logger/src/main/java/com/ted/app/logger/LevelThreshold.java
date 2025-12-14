package com.ted.app.logger;

public class LevelThreshold {

    private Level level;

    public LevelThreshold(Level level) {
        this.level = level;
    }

    public boolean isPass(Level level){
        return level.getRank() >= this.level.getRank();
    }

    //======================================

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
