package com.ted.app.logger.strategy.layout;

import com.ted.app.logger.LayoutStrategy;
import com.ted.app.logger.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StandardLayout implements LayoutStrategy {

    @Override
    public String format(Message message) {
        String now = getNow();
        return now + " ├" + message.getLevel() + " " + message.getLoggerName() + " - " + message.getContent();
    }

    private String getNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return now.format(formatter);
    }
}
