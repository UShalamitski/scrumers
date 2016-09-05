package com.scrumers.model;

public class Story extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private long storyId;

    private String name;

    private String descr;

    private String storyPoints;

    private int estimate;

    private long assignee;

    private String howToDemo;

    private String track;

    private Long statusId;

    private boolean isDone;

    private String devName;

    private Long allTasks;

    private Long doneTasks;

    public Long getAllTasks() {
        return allTasks;
    }

    public void setAllTasks(Long allTasks) {
        this.allTasks = allTasks;
    }

    public Long getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(Long doneTasks) {
        this.doneTasks = doneTasks;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public long getStoryId() {
        return storyId;
    }

    public void setStoryId(long storyId) {
        this.storyId = storyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public String getHowToDemo() {
        return howToDemo;
    }

    public void setHowToDemo(String howToDemo) {
        this.howToDemo = howToDemo;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(String storyPoints) {
        this.storyPoints = storyPoints;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public long getAssignee() {
        return assignee;
    }

    public void setAssignee(long assignee) {
        this.assignee = assignee;
    }

}
