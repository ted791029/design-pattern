package com.ted.app;

import com.ted.app.logger.Level;
import com.ted.app.logger.LevelThreshold;
import com.ted.app.logger.Logger;
import com.ted.app.logger.composite.export.CompositeExporter;
import com.ted.app.logger.composite.export.ConsoleExporter;
import com.ted.app.logger.composite.export.FileExporter;
import com.ted.app.logger.strategy.layout.StandardLayout;

import java.util.List;

import static com.ted.app.logger.LogManager.declareRootLogger;
import static com.ted.app.logger.LogManager.loadConfig;

public class Main {

    public static void main(String[] args) {

//        Logger root = new Logger.Builder()
//                .root(new ConsoleExporter(), new StandardLayout(), new LevelThreshold(Level.DEBUG));
//
//        Logger gameLogger = new Logger.Builder()
//                .levelThreshold(new LevelThreshold(Level.INFO))
//                .exporter(new CompositeExporter(
//                                new ConsoleExporter(),
//                                new CompositeExporter(
//                                        new FileExporter("game.log"),
//                                        new FileExporter("game.backup.log")
//                                )
//                        )
//                )
//                .build("app.game", root);
//
//        Logger aiLogger = new Logger.Builder()
//                .levelThreshold(new LevelThreshold(Level.TRACE))
//                .layoutStrategy(new StandardLayout())
//                .build("app.game.ai", gameLogger);
//        declareRootLogger(root, gameLogger, aiLogger);

        List<Logger> loggers = loadConfig("setting.json");
        declareRootLogger(loggers);

        Game game = new Game();
        game.start();
    }
}
