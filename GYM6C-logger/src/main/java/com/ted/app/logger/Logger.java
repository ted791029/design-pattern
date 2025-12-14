package com.ted.app.logger;

import java.util.Optional;

import static com.ted.app.logger.LogManager.getLogger;

public class Logger {

    private Logger child;

    private String childName;

    private Exporter exporter;

    private LayoutStrategy layoutStrategy;

    private LevelThreshold levelThreshold;

    private String name;

    private Logger(Builder builder) {
        this.exporter = builder.exporter != null ? builder.exporter : builder.parent.getExporter();
        this.layoutStrategy = builder.layoutStrategy != null ? builder.layoutStrategy : builder.parent.getLayoutStrategy();
        this.levelThreshold = builder.levelThreshold != null ? builder.levelThreshold : builder.parent.getLevelThreshold();
        this.name = builder.name;

        if (builder.parent != null) {
            builder.parent.setChild(this);
        }
    }

    public void debug(String msg) {

        if (isPass(Level.DEBUG)) {
            return;
        }

        action(msg, Level.DEBUG);
    }

    public void error(String msg) {

        if (isPass(Level.ERROR)) {
            return;
        }

        action(msg, Level.ERROR);
    }

    public void info(String msg) {

        if (isPass(Level.INFO)) {
            return;
        }

        action(msg, Level.INFO);
    }

    public void trace(String msg) {

        if (isPass(Level.TRACE)) {
            return;
        }

        action(msg, Level.TRACE);
    }

    public void warn(String msg) {

        if (isPass(Level.WARN)) {
            return;
        }

        action(msg, Level.WARN);
    }

    private void action(String msg, Level level) {
        Message message = new Message(msg, level, name);
        String formattedMessage = format(message);
        exporter.export(formattedMessage);
    }

    private String format(Message message) {
        return layoutStrategy.format(message);
    }

    private boolean isPass(Level level) {

        if (levelThreshold.isPass(level)) {
            return false;
        }

        return true;
    }

    //======================================

    public Logger getChild() {
        return child;
    }

    public void setChild(Logger child) {
        this.child = child;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public Exporter getExporter() {
        return exporter;
    }

    public void setExporter(Exporter exporter) {
        this.exporter = exporter;
    }

    public LayoutStrategy getLayoutStrategy() {
        return layoutStrategy;
    }

    public void setLayoutStrategy(LayoutStrategy layoutStrategy) {
        this.layoutStrategy = layoutStrategy;
    }

    public LevelThreshold getLevelThreshold() {
        return levelThreshold;
    }

    public void setLevelThreshold(LevelThreshold levelThreshold) {
        this.levelThreshold = levelThreshold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Optional<Logger> log = getLogger(name);

        if (log.isPresent()) {
            throw new RuntimeException("Logger name重複");
        }

        this.name = name;
    }

    public static class Builder {

        private String childName;

        private Exporter exporter;

        private LayoutStrategy layoutStrategy;

        private LevelThreshold levelThreshold;

        private String name;

        private Logger parent;

        public Logger root(Exporter exporter, LayoutStrategy layoutStrategy, LevelThreshold levelThreshold) {
            this.exporter = exporter;
            this.layoutStrategy = layoutStrategy;
            this.levelThreshold = levelThreshold;
            this.name = "Root";
            return new Logger(this);
        }

        public Logger root(Builder builder) {
            if (builder.exporter == null || builder.levelThreshold == null || builder.layoutStrategy == null) {
                throw new RuntimeException("Root 必要參數缺少");
            }
            return root(builder.exporter, builder.layoutStrategy, builder.levelThreshold);
        }

        public Builder childName(String childName){
            this.childName = childName;
            return this;
        }

        public Builder exporter(Exporter exporter) {
            this.exporter = exporter;
            return this;
        }

        public Builder layoutStrategy(LayoutStrategy layoutStrategy) {
            this.layoutStrategy = layoutStrategy;
            return this;
        }

        public Builder levelThreshold(LevelThreshold levelThreshold) {
            this.levelThreshold = levelThreshold;
            return this;
        }

        public Logger build(String name, Logger parent) {
            this.name = name;
            this.parent = parent;
            return new Logger(this);
        }
    }
}
