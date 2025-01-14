package com.example.aidflow;

public class NewsProjects {
    private String projectsName; // Name of the project
    private String projectsDesc; // Description of the project
    private String projectGoals; // Goals of the project
    private String sDate; // Start date of the project
    private String eDate; // End date of the project
    private int progressRate; // Progress rate of the project
    private String imageUrl; // URL of the project's image

    // Constructor with parameters to initialize the project details
    public NewsProjects(String projectsName, String projectsDesc, String projectsGoals, String sDate,
                        String eDate, int progressRate, String imageUrl) {
        this.projectsName = projectsName;
        this.projectsDesc = projectsDesc;
        this.projectGoals = projectsGoals;
        this.sDate = sDate;
        this.eDate = eDate;
        this.progressRate = progressRate;
        this.imageUrl = imageUrl;
    }

    // Default constructor required for calls to DataSnapshot.getValue(NewsProjects.class)
    public NewsProjects() {
    }

    // Getter for project name
    public String getProjectsName() {
        return projectsName;
    }

    // Getter for project description
    public String getProjectsDesc() {
        return projectsDesc;
    }

    // Getter for project goals
    public String getProjectGoals() {
        return projectGoals;
    }

    // Getter for start date
    public String getSDate() {
        return sDate;
    }

    // Getter for end date
    public String getEDate() {
        return eDate;
    }

    // Getter for progress rate
    public int getProgressRate() {
        return progressRate;
    }

    // Getter for image URL
    public String getImageUrl() {
        return imageUrl;
    }

    // Setter for end date
    public void setEDate(String eDate) {
        this.eDate = eDate;
    }

    // Setter for start date
    public void setSDate(String sDate) {
        this.sDate = sDate;
    }
}
