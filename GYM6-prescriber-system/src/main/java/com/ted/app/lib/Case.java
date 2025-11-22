package com.ted.app.lib;

import java.util.Date;
import java.util.List;

public class Case {

    private Date caseTime;

    private Prescription prescription;

    private List<String> symptoms;

    public Case(Date caseTime, Prescription prescription, List<String> symptoms) {
        this.caseTime = caseTime;
        this.prescription = prescription;
        this.symptoms = symptoms;
    }

    //=======================================


    public Date getCaseTime() {
        return caseTime;
    }

    public void setCaseTime(Date caseTime) {
        this.caseTime = caseTime;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }
}
