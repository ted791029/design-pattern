package com.ted.app.logger.composite.export;

import com.ted.app.logger.Exporter;

public class ConsoleExporter implements Exporter {

    @Override
    public void export(String msg) {
        System.out.println(msg);
    }

}
