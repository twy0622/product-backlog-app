package com.example.software.provider;

public class Sprint {

    private int id;
    private String sprintName;
    private String sprintDate;

    public Sprint(int id, String sprintName, String sprintDate) {
        this.id = id;
        this.sprintName = sprintName;
        this.sprintDate = sprintDate;
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", sprintName='" + sprintName + '\'' +
                ", sprintDate='" + sprintDate + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public String getSprintDate() {
        return sprintDate;
    }

    public void setSprintDate(String sprintDate) {
        this.sprintDate = sprintDate;
    }
}
