package com.ted.app.core;

import java.util.ArrayList;
import java.util.List;

public class Breadcrumbs {

    private String currentPath;

    private List<String> prevPaths = new ArrayList<>();

    public Breadcrumbs() {
        this.currentPath = "/";
    }

    public String displayText() {
        StringBuilder path = new StringBuilder();
        for (String segment : prevPaths) {
            if (path.length() > 0) {
                path.append(" / ");
            }
            path.append(segment);
        }
        if (currentPath != null && !currentPath.isBlank()) {
            if (path.length() > 0) {
                path.append(" / ");
            }
            path.append(currentPath);
        }
        return path.length() == 0 ? "/" : path.toString();
    }

    // Getters and setters
    public String getCurrentPath() {
        return currentPath;
    }

    public List<String> getPrevPaths() {
        return prevPaths;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }

    public void setPrevPaths(List<String> previous) {
        prevPaths.clear();
        prevPaths.addAll(previous);
    }
}
