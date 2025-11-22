package com.ted.app.lib;

import java.util.List;

public class Disease {

    private String name;

    private List<String> symptoms;

    public Disease(String name, List<String> symptoms) {
        this.name = name;
        this.symptoms = symptoms;
    }


    public boolean isMatchDiseaseSymptoms(List<String> symptoms) {

        for (String symptom : symptoms) {

            if (!symptoms.contains(symptom)) {
                return false;
            }

        }
        return true;
    }

    //=======================================


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }
}
