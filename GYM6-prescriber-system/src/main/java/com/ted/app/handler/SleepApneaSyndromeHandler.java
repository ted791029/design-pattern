package com.ted.app.handler;

import com.ted.app.lib.Disease;
import com.ted.app.lib.DiseasesHandler;
import com.ted.app.lib.Patient;
import com.ted.app.lib.Prescription;

import java.util.Arrays;
import java.util.List;

public class SleepApneaSyndromeHandler extends DiseasesHandler {

    public SleepApneaSyndromeHandler(DiseasesHandler next) {
        super(next);
        String[] symptomArr = {"snore"};
        setDisease(new Disease("睡眠呼吸中止症", Arrays.asList(symptomArr)));
    }

    @Override
    protected Prescription handling() {
        return new Prescription("一捲膠帶", "打呼抑制劑", "睡眠呼吸中止症", "睡覺時，撕下兩塊膠帶，將兩塊膠帶交錯黏在關閉的嘴巴上，就不會打呼了。");
    }

    @Override
    protected boolean match(Patient patient, List<String> symptoms) {
        return getDisease().isMatchDiseaseSymptoms(symptoms) && patient.bmi() > 26;
    }
}
