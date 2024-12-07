package com.example.donationmodulefrontend;

import androidx.fragment.app.Fragment;

public class Donation {
    private final String name;
    private final String projectName;
    private final String dueDate;
    private final int progress;
    private final String urgency;

    public Donation(String name, String projectName, String dueDate, int progress, String urgency) {
        this.name = name;
        this.projectName = projectName;
        this.dueDate = dueDate;
        this.progress = progress;
        this.urgency = urgency;
    }

    public String getName() {
        return name;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getProgress() {
        return progress;
    }

    public String getUrgency() {
        return urgency;
    }
}

