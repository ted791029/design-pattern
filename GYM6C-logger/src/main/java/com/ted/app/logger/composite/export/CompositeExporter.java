package com.ted.app.logger.composite.export;

import com.ted.app.logger.Exporter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeExporter implements Exporter {

    private List<Exporter> children;

    public CompositeExporter(Exporter... exports){
        this.children = Arrays.stream(exports).collect(Collectors.toList());
    }

    public CompositeExporter(List<Exporter> children){
        this.children = children;
    }

    @Override
    public void export(String msg) {
        children.forEach(exporter -> exporter.export(msg));
    }


    //======================================
    public List<Exporter> getChildren() {
        return children;
    }

    public void setChildren(List<Exporter> children) {
        this.children = children;
    }
}
