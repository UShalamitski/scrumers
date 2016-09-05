package com.scrumers.model;

public class Task extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private long taskId;

    private String summary;

    private int estimatePre;

    private int estimateReal;

    private long assignee;

    private Long statusId;

    private boolean isDone;

    private String devName;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getEstimatePre() {
        return estimatePre;
    }

    public void setEstimatePre(int estimatePre) {
        this.estimatePre = estimatePre;
    }

    public int getEstimateReal() {
        return estimateReal;
    }

    public void setEstimateReal(int estimateReal) {
        this.estimateReal = estimateReal;
    }

    public long getAssignee() {
        return assignee;
    }

    public void setAssignee(long assignee) {
        this.assignee = assignee;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

}
