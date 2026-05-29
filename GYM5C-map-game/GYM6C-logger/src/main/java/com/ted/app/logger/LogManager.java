package com.ted.app.logger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LogManager {
    private static Map<String, Logger> loggerMap;

    private static LoggerConfigurator loggerConfigurator = new LoggerConfigurator();

    public static void declareRootLogger(Logger... loggers) {
        List<Logger> loggerList = Arrays.stream(loggers).collect(Collectors.toList());
        declareRootLogger(loggerList);
    }

    public static void declareRootLogger(List<Logger> loggers) {
        loggerMap = loggers.stream().collect(Collectors.toMap(Logger::getName, Function.identity()));
    }

    public static Optional<Logger> getLogger(String name) {
        return Optional.of(loggerMap.get(name));
    }

    public static List<Logger> loadConfig(String fileName) {
        return loggerConfigurator.load(fileName);
    }
}
