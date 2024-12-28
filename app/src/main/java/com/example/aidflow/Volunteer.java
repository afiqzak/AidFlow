package com.example.aidflow;

public class Volunteer {

    private final String name;
    private final String district;
    private final String dueDate;
    private final int progress;


    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getProgress() {
        return progress;
    }


    public Volunteer(String name, String district, String dueDate, int progress) {
        this.name = name;
        this.district = district;
        this.dueDate = dueDate;
        this.progress = progress;
    }
}
