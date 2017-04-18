package com.scrumers.model;

import com.scrumers.model.enums.StoryStatusEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Task extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private long taskId;
    private String summary;
    private int estimatePre;
    private int estimateReal;
    private long assignee;
    private StoryStatusEnum status;
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

    public StoryStatusEnum getStatus() {
        return status;
    }

    public void setStatus(StoryStatusEnum status) {
        this.status = status;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("taskId", taskId)
                .append("summary", summary)
                .append("estimatePre", estimatePre)
                .append("estimateReal", estimateReal)
                .append("assignee", assignee)
                .append("status", status)
                .append("isDone", isDone)
                .append("devName", devName)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task task = (Task) o;

        return new EqualsBuilder()
                .append(taskId, task.taskId)
                .append(estimatePre, task.estimatePre)
                .append(estimateReal, task.estimateReal)
                .append(assignee, task.assignee)
                .append(isDone, task.isDone)
                .append(summary, task.summary)
                .append(status, task.status)
                .append(devName, task.devName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(taskId)
                .append(summary)
                .append(estimatePre)
                .append(estimateReal)
                .append(assignee)
                .append(status)
                .append(isDone)
                .append(devName)
                .toHashCode();
    }
}
