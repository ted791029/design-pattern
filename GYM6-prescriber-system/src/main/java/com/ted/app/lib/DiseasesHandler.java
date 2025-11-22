package com.ted.app.lib;

import java.util.List;

public abstract class DiseasesHandler {

    private Disease disease;
    private boolean isEnable;
    private DiseasesHandler next;

    public DiseasesHandler(DiseasesHandler next) {
        this.next = next;
    }

    public Prescription handle(Patient patient, List<String> symptoms) {
        if (isEnable && match(patient, symptoms)) {
            return handling();
        } else if (next != null) {
            return next.handle(patient, symptoms);
        }
        return null;
    }

    protected abstract Prescription handling();

    protected abstract boolean match(Patient patient, List<String> symptoms);

    //=======================================


    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public DiseasesHandler getNext() {
        return next;
    }

    public void setNext(DiseasesHandler next) {
        this.next = next;
    }
}
