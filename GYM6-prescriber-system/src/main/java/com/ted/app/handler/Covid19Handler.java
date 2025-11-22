package com.ted.app.handler;

import com.ted.app.lib.Disease;
import com.ted.app.lib.DiseasesHandler;
import com.ted.app.lib.Patient;
import com.ted.app.lib.Prescription;

import java.util.Arrays;
import java.util.List;

public class Covid19Handler extends DiseasesHandler {

    public Covid19Handler(DiseasesHandler next) {
        super(next);
        String[] symptomArr = {"sneeze", "headache", "cough"};
        setDisease(new Disease("清冠肺炎", Arrays.asList(symptomArr)));
        setEnable(true);
    }

    @Override
    protected Prescription handling() {
        return new Prescription("清冠一號", "清冠一號", "新冠肺炎", "將相關藥材裝入茶包裡，使用500 mL 溫、熱水沖泡悶煮1~3 分鐘後即可飲用。");
    }

    @Override
    protected boolean match(Patient patient, List<String> symptoms) {
        return getDisease().isMatchDiseaseSymptoms(symptoms);
    }
}
