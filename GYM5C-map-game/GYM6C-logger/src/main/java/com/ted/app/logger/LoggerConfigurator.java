package com.ted.app.logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ted.app.logger.composite.export.CompositeExporter;
import com.ted.app.logger.composite.export.ConsoleExporter;
import com.ted.app.logger.composite.export.FileExporter;
import com.ted.app.logger.strategy.layout.StandardLayout;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.ted.app.logger.Logger.Builder;

public class LoggerConfigurator {

    private final String FILE_PATH = "D:\\sourcetree\\design-pattern\\GYM6C-logger\\src\\main\\resources\\";

    private final List<String> LOGGER_DEFAULT_KEYS = Arrays.asList(new String[]{"levelThreshold", "exporter", "layout"});

    private final List<String> EXPORT_DEFAULT_KEYS = Arrays.asList(new String[]{"type", "children"});

    public List<Logger> load(String fileName) {
        Map<String, Object> originMap = getSettingMap(FILE_PATH + fileName);
        return getLoggers(originMap);
    }

    private void childLoggerInit(Map<String, Object> loggersMap, List<Logger> loggers, Logger logger){
        Map<String, Object> childMap = (Map<String, Object>) loggersMap.get(logger.getChildName());
        getLogger(childMap, logger.getChildName(), logger, loggers);
    }

    private Logger currentLoggerInit(Map<String, Object> loggersMap, String loggerName, Logger parent, Builder builder){
        String childName = null;

        for (String key : loggersMap.keySet()) {
            loggerBuilderInit(loggersMap, key, builder);

            if (isNotDefaultKey(key)) {
                childName = key;
            }
        }

        Logger logger;

        if ("Root".equals(loggerName)) {
            logger = builder.root(builder);
        } else {
            logger = builder.build(loggerName, parent);
        }

        logger.setChildName(childName);
        return logger;
    }

    private Exporter getExport(Map<String, Object> exporterMap) {

        Exporter exporter = null;

        for (String key : exporterMap.keySet()) {

            if (!isExportKey(key)) {
                continue;
            }

            if ("type".equals(key)) {
                String type = (String) exporterMap.get(key);
                exporter = getExport(type, exporterMap);
            }
        }

        return exporter;
    }

    private Exporter getExport(String type, Map<String, Object> exporterMap) {
        Exporter exporter = null;

        if ("console".equals(type)) {
            exporter = new ConsoleExporter();
        }

        if ("file".equals(type)) {
            String fileName = (String) exporterMap.get("fileName");
            exporter = new FileExporter(fileName);
        }

        if ("composite".equals(type)) {
            List<Map<String, Object>> childrenExportMap = (List<Map<String, Object>>) exporterMap.get("children");
            List<Exporter> children = getExportChildren(childrenExportMap);
            exporter = new CompositeExporter(children);
        }

        return exporter;
    }

    private List<Exporter> getExportChildren(List<Map<String, Object>> childrenExportMap) {
        List<Exporter> children = new ArrayList<>();

        for (Map<String, Object> childExportMap : childrenExportMap) {
            Exporter exporter = getExport(childExportMap);
            children.add(exporter);
        }

        return children;
    }

    private LayoutStrategy getLayoutStrategy(String layout) {
        LayoutStrategy layoutStrategy = null;

        if ("standard".equals(layout)) {
            layoutStrategy = new StandardLayout();
        }

        return layoutStrategy;
    }

    private LevelThreshold getLevelThreshold(String level) {
        LevelThreshold levelThreshold = null;

        if ("TRACE".equals(level)) {
            levelThreshold = new LevelThreshold(Level.TRACE);
        }

        if ("INFO".equals(level)) {
            levelThreshold = new LevelThreshold(Level.INFO);
        }

        if ("DEBUG".equals(level)) {
            levelThreshold = new LevelThreshold(Level.DEBUG);
        }

        if ("WARN".equals(level)) {
            levelThreshold = new LevelThreshold(Level.WARN);
        }

        if ("ERROR".equals(level)) {
            levelThreshold = new LevelThreshold(Level.ERROR);
        }

        return levelThreshold;
    }


    private void getLogger(Map<String, Object> loggersMap, String loggerName, Logger parent, List<Logger> loggers) {
        Builder builder = new Builder();
        Logger currentLogger  = currentLoggerInit(loggersMap, loggerName, parent, builder);
        loggers.add(currentLogger);

        if (currentLogger.getChildName() != null) {
            childLoggerInit(loggersMap,loggers, currentLogger);
        }
    }

    private List<Logger> getLoggers(Map<String, Object> originMap) {
        Map<String, Object> loggersMap = (Map<String, Object>) originMap.get("loggers");
        List<Logger> loggers = new ArrayList<>();
        getLogger(loggersMap, "Root", null, loggers);
        return loggers;
    }

    private Map<String, Object> getSettingMap(String fullPath) {
        Gson gson = new Gson();
        Map<String, Object> originMap = null;

        try (FileReader reader = new FileReader(fullPath)) {
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            originMap = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return originMap;
    }

    private boolean isNotDefaultKey(String key) {
        return !isLoggerKey(key) && !isExportKey(key);
    }

    private boolean isLoggerKey(String key) {
        return LOGGER_DEFAULT_KEYS.contains(key);
    }

    private boolean isExportKey(String key) {
        return EXPORT_DEFAULT_KEYS.contains(key);
    }

    private void loggerBuilderInit(Map<String, Object> loggersMap, String key, Builder builder) {
        if ("levelThreshold".equals(key)) {
            String level = (String) loggersMap.get(key);
            LevelThreshold levelThreshold = getLevelThreshold(level);
            builder.levelThreshold(levelThreshold);
        }

        if ("exporter".equals(key)) {
            Map<String, Object> exporterMap = (Map<String, Object>) loggersMap.get(key);
            Exporter exporter = getExport(exporterMap);
            builder.exporter(exporter);
        }

        if ("layout".equals(key)) {
            String layout = (String) loggersMap.get(key);
            LayoutStrategy layoutStrategy = getLayoutStrategy(layout);
            builder.layoutStrategy(layoutStrategy);
        }
    }
}
