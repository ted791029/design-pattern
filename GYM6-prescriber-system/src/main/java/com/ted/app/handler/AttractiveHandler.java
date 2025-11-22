package com.ted.app.handler;

import com.ted.app.lib.Disease;
import com.ted.app.lib.DiseasesHandler;
import com.ted.app.lib.Patient;
import com.ted.app.lib.Prescription;

import java.util.Arrays;
import java.util.List;

public class AttractiveHandler extends DiseasesHandler {

    public AttractiveHandler(DiseasesHandler next) {
        super(next);
        String[] symptomArr = {"sneeze"};
        setDisease(new Disease("有人想你了", Arrays.asList(symptomArr)));
        setEnable(true);
    }

    @Override
    protected Prescription handling() {
        return new Prescription("假鬢角、臭味", "青春抑制劑", "有人想你了", "把假鬢角黏在臉的兩側，讓自己異性緣差一點，自然就不會有人想妳了。");
    }

    @Override
    protected boolean match(Patient patient, List<String> symptoms) {
        return getDisease().isMatchDiseaseSymptoms(symptoms) && patient.getAge() == 18;
    }
}
