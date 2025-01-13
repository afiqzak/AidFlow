package com.example.aidflow;

public class NewsProjects {
    private String projectsName;
    private String projectsDesc;
    private String projectGoals;
    private String sDate;
    private String eDate;
    private int progressRate;
    private String imageUrl;

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

    public NewsProjects() {
        // Default constructor required for calls to DataSnapshot.getValue(NewsProjects.class)
    }

    public String getProjectsName() {
        return projectsName;
    }

    public String getProjectsDesc() {
        return projectsDesc;
    }

    public String getProjectGoals() {
        return projectGoals;
    }

    public String getSDate() {return sDate;}

    public String getEDate() {
        return eDate;
    }

    public int getProgressRate() {
        return progressRate;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setEDate(String eDate) {
        this.eDate = eDate;
    }

    public void setSDate(String sDate) {
        this.sDate = sDate;
    }
}
