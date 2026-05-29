package com.ted.app.core;

public class Definition {

    private String explanation;

    private PartOfSpeech PoS;

    public Definition(PartOfSpeech poS, String explanation) {
        this.PoS = poS;
        this.explanation = explanation;
    }

    // Getters and setters
    public String getExplanation() {
        return explanation;
    }

    public PartOfSpeech getPoS() {
        return PoS;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setPoS(PartOfSpeech PoS) {
        this.PoS = PoS;
    }
}
