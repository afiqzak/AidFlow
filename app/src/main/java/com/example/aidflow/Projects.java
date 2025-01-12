package com.example.aidflow;

public class Projects {

    private final String projectsName;
    private final String projectsDesc;
    private final String projectsGoal;
    private final String startDate;
    private final String endDate;
    private final int progress;
    private final String imageUrl;

    public Projects(String projectsName, String projectsDesc, String projectsGoal, String startDate, String endDate, int progress, String imageUrl) {
        this.projectsName = projectsName;
        this.projectsDesc = projectsDesc;
        this.projectsGoal = projectsGoal;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
        this.imageUrl = imageUrl;
    }

    public String getProjectsName() {
        return projectsName;
    }

    public String getProjectsDesc() {
        return projectsDesc;
    }

    public String getProjectsGoal() {
        return projectsGoal;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getProgress() {
        return progress;
    }

    public String getImageUrl(){
        return imageUrl;
    }

}
