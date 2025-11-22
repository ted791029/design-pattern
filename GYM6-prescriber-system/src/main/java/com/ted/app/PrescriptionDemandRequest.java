package com.ted.app;

import java.util.List;

import static com.ted.app.PrescriptionDemandFacade.*;

public class PrescriptionDemandRequest {

    private int updateAction;
    private String patientTableName;
    private String supportedDiseasesTable;
    private String id;
    private List<String> symptoms;
    private String exportFileName;
    private ExportType exportType;

    // 建構子或 Builder
    public static class Builder {
        private String id;
        private List<String> symptoms;
        private String patientTableName;
        private String supportedDiseasesTable;
        private String exportFileName;
        private ExportType exportType = ExportType.CSV;
        private int updateAction = 0;

        public Builder export(String fileName, ExportType type) {
            this.exportFileName = fileName;
            this.exportType = type;
            return this;
        }


        public Builder prescriptionDemand(String id, List<String> symptoms) {
            this.updateAction |= PRESCRIPTION_DEMAND;
            this.id = id;
            this.symptoms = symptoms;
            return this;
        }

        public Builder updateDatabase(String patientTableName) {
            this.updateAction |= UPDATE_DATABASE;
            this.patientTableName = patientTableName;
            return this;
        }

        public Builder updateSupportedDiseases(String supportedDiseasesTable) {
            this.updateAction |= UPDATE_SUPPORTED_DISEASES;
            this.supportedDiseasesTable = supportedDiseasesTable;
            return this;
        }

        public PrescriptionDemandRequest build() {
            PrescriptionDemandRequest r = new PrescriptionDemandRequest();
            r.updateAction = updateAction;
            r.patientTableName = patientTableName;
            r.supportedDiseasesTable = supportedDiseasesTable;
            r.id = id;
            r.symptoms = symptoms;
            r.exportFileName = exportFileName;
            r.exportType = exportType;
            return r;
        }
    }

    //=======================================

    public int getUpdateAction() {
        return updateAction;
    }

    public String getPatientTableName() {
        return patientTableName;
    }

    public String getSupportedDiseasesTable() {
        return supportedDiseasesTable;
    }

    public String getId() {
        return id;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    public ExportType getExportType() {
        return exportType;
    }
}
