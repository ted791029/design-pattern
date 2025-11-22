package com.ted.app.lib;

import java.util.ArrayList;
import java.util.List;

public class PatientDatabase {

    List<Patient> patients;

    public PatientDatabase() {
        this.patients = new ArrayList<>();
    }

    private void add(Patient patient) {
        patients.add(patient);
    }

    public Patient findPatient(String id) {
        return patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Patient patient) {
        Patient oldPatient = findPatient(patient.getId());
        update(oldPatient, patient);
    }

    private void update(Patient oldPatient, Patient newPatient) {
        oldPatient.update(
                newPatient.getAge(),
                newPatient.getCases(),
                newPatient.getGender(),
                newPatient.getHeight(),
                newPatient.getId(),
                newPatient.getName(),
                newPatient.getWeight()
        );
    }

    public void updateAll(List<Patient> patients) {
        setPatients(patients);
    }

    //=======================================


    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
