package com.ted.app.lib;

import com.ted.app.lib.util.ValidUtil;

public class Prescription {

    private String medicines;

    private String name;

    private String potentialDisease;

    private String usage;

    public Prescription(String medicines, String name, String potentialDisease, String usage) {
        setMedicines(medicines);
        setName(name);
        setPotentialDisease(potentialDisease);
        setUsage(usage);
    }

    //=======================================
    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {

        if (!ValidUtil.isInRange(3, 30, medicines.length())) {
            System.out.println("食用藥物名長度為3-30");
        }

        this.medicines = medicines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (!ValidUtil.isInRange(4, 30, name.length())) {
            System.out.println("處方名長度為4-30");
        }

        this.name = name;
    }

    public String getPotentialDisease() {
        return potentialDisease;
    }

    public void setPotentialDisease(String potentialDisease) {

        if (!ValidUtil.isInRange(3, 100, potentialDisease.length())) {
            System.out.println("潛在疾病名長度為3-100");
        }

        this.potentialDisease = potentialDisease;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {

        if (!ValidUtil.isInRange(0, 1000, usage.length())) {
            System.out.println("使用方法長度為3-100");
        }

        this.usage = usage;
    }
}
