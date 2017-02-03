package com.scrumers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Organization extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Long numOfTeams;

    private Long numOfProjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumOfTeams() {
        return numOfTeams;
    }

    public void setNumOfTeams(Long numOfTeams) {
        this.numOfTeams = numOfTeams;
    }

    public Long getNumOfProjects() {
        return numOfProjects;
    }

    public void setNumOfProjects(Long numOfProjects) {
        this.numOfProjects = numOfProjects;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", name)
                .append("numOfTeams", numOfTeams)
                .append("numOfProjects", numOfProjects)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Organization)) {
            return false;
        }

        Organization that = (Organization) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .append(numOfTeams, that.numOfTeams)
                .append(numOfProjects, that.numOfProjects)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(numOfTeams)
                .append(numOfProjects)
                .toHashCode();
    }
}
