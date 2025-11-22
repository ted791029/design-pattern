package com.ted.app.lib;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class PrescriberSystem {
    private final BlockingQueue<PrescriptionDemandInfo> queue;

    private PatientDatabase patientDatabase;

    private Prescriber prescriber;

    public PrescriberSystem(PatientDatabase patientDatabase, Prescriber prescriber, BlockingQueue<PrescriptionDemandInfo> queue) {
        this.patientDatabase = patientDatabase;
        this.prescriber = prescriber;
        this.queue = queue;
    }

    public Case prescriptionDemand(String id, List<String> symptoms) {
        Patient patient = patientDatabase.findPatient(id);
        PrescriptionDemandInfo info = new PrescriptionDemandInfo(patient, symptoms);
        queue.add(info);
        Prescription prescription = info.getResultFuture().join();
        Case c = new Case(new Date(), prescription, symptoms);
        patient.addCase(c);
        patientDatabase.save(patient);
        return c;
    }

    public void updateDatabase(List<Patient> patients) throws IOException {
        patientDatabase.updateAll(patients);
    }

    public void updateSupportDiseases(List<String> supportedDiseases) throws IOException {
        prescriber.update(supportedDiseases);
    }
}
