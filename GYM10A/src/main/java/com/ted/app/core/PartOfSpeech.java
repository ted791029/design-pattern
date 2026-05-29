package com.ted.app.core;

public class PartOfSpeech {

    private String abbr;

    private String fullName;

    public PartOfSpeech(String abbr, String fullName) {
        this.abbr = abbr;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return fullName + " (" + abbr + ")";
    }

    // Getters and setters
    public String getAbbr() {
        return abbr;
    }

    public String getFullName() {
        return fullName;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
