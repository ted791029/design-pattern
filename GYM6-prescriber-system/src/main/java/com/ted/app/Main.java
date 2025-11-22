package com.ted.app;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        PrescriptionDemandFacade prescriptionDemandFacade = new PrescriptionDemandFacade();

        //單純診斷
        prescriptionDemandFacade.request(
                new PrescriptionDemandRequest.Builder()
                        .prescriptionDemand("U107455663", Arrays.asList(new String[]{"sneeze", "headache", "cough"}))
                        .export("U107455663_病例", ExportType.CSV)
                        .build()
        );

        //更新支援疾病 + 診斷
        prescriptionDemandFacade.request(
                new PrescriptionDemandRequest.Builder()
                        .updateSupportedDiseases("supportedDiseases.txt")
                        .prescriptionDemand("U107455663", Arrays.asList(new String[]{"sneeze", "headache", "cough"}))
                        .export("U107455663_病例", ExportType.JSON)
                        .build()
        );


        //更新資料庫 + 診斷
        prescriptionDemandFacade.request(
                new PrescriptionDemandRequest.Builder()
                        .updateDatabase("database.json")
                        .prescriptionDemand("P208317507", Arrays.asList(new String[]{"sneeze"}))
                        .export("P208317507_病例", ExportType.JSON)
                        .build()
        );
    }
}
