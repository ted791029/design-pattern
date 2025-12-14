package com.ted.app.logger;

public class Message {

    private String content;

    private Level level;

    private String loggerName;

    public Message(String content, Level level, String loggerName){
        this.content = content;
        this.level = level;
        this.loggerName = loggerName;
    }

    //======================================

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
}
