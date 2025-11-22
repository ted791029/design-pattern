package com.ted.app.lib;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PrescriptionDemandInfo {
    private Patient patient;

    private CompletableFuture<Prescription> resultFuture;

    private List<String> symptoms;

    public PrescriptionDemandInfo(Patient patient, List<String> symptoms) {
        this.patient = patient;
        resultFuture = new CompletableFuture<>();
        this.symptoms = symptoms;
    }

    //=======================================
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    public CompletableFuture<Prescription> getResultFuture() {
        return resultFuture;
    }

    public void setResultFuture(CompletableFuture<Prescription> resultFuture) {
        this.resultFuture = resultFuture;
    }
}
