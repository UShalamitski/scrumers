package com.scrumers.model;

import com.scrumers.model.enums.StoryStatusEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
    private StoryStatusEnum status;
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

    public long getAssignee() {
        return assignee;
    }

    public void setAssignee(long assignee) {
        this.assignee = assignee;
    }

    public StoryStatusEnum getStatus() {
        return status;
    }

    public void setStatus(StoryStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("storyId", storyId)
                .append("name", name)
                .append("descr", descr)
                .append("storyPoints", storyPoints)
                .append("estimate", estimate)
                .append("assignee", assignee)
                .append("howToDemo", howToDemo)
                .append("track", track)
                .append("isDone", isDone)
                .append("devName", devName)
                .append("allTasks", allTasks)
                .append("doneTasks", doneTasks)
                .append("status", status)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Story)) {
            return false;
        }

        Story story = (Story) o;

        return new EqualsBuilder()
                .append(storyId, story.storyId)
                .append(estimate, story.estimate)
                .append(assignee, story.assignee)
                .append(isDone, story.isDone)
                .append(name, story.name)
                .append(descr, story.descr)
                .append(storyPoints, story.storyPoints)
                .append(howToDemo, story.howToDemo)
                .append(track, story.track)
                .append(devName, story.devName)
                .append(allTasks, story.allTasks)
                .append(doneTasks, story.doneTasks)
                .append(status, story.status)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(storyId)
                .append(name)
                .append(descr)
                .append(storyPoints)
                .append(estimate)
                .append(assignee)
                .append(howToDemo)
                .append(track)
                .append(isDone)
                .append(devName)
                .append(allTasks)
                .append(doneTasks)
                .append(status)
                .toHashCode();
    }
}
