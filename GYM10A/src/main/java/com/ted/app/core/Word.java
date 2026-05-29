package com.ted.app.core;

import java.util.ArrayList;
import java.util.List;

public class Word {

    private List<Definition> definitions;

    private String description;

    private String name;

    public Word(String name, String description, List<Definition> definitions) {
        this.name = name;
        this.description = description;
        this.definitions = new ArrayList<>(definitions);
    }

    public Word copy() {
        return new Word(name, description, definitions);
    }

    // Getters and setters
    public List<Definition> getDefinitions() {
        return new ArrayList<>(definitions);
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}
