package com.scrumers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Team extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Long numOfUsers;

    private Long teamRole;

    private String scrumMaster;

    private String scrumMasterId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumOfUsers() {
        return numOfUsers;
    }

    public void setNumOfUsers(Long numOfUsers) {
        this.numOfUsers = numOfUsers;
    }

    public Long getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(Long teamRole) {
        this.teamRole = teamRole;
    }

    public String getScrumMaster() {
        return scrumMaster;
    }

    public void setScrumMaster(String scrumMaster) {
        this.scrumMaster = scrumMaster;
    }

    public String getScrumMasterId() {
        return scrumMasterId;
    }

    public void setScrumMasterId(String scrumMasterId) {
        this.scrumMasterId = scrumMasterId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", name)
                .append("numOfUsers", numOfUsers)
                .append("teamRole", teamRole)
                .append("scrumMaster", scrumMaster)
                .append("scrumMasterId", scrumMasterId)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Team)) {
            return false;
        }

        Team team = (Team) o;

        return new EqualsBuilder()
                .append(name, team.name)
                .append(numOfUsers, team.numOfUsers)
                .append(teamRole, team.teamRole)
                .append(scrumMaster, team.scrumMaster)
                .append(scrumMasterId, team.scrumMasterId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(numOfUsers)
                .append(teamRole)
                .append(scrumMaster)
                .append(scrumMasterId)
                .toHashCode();
    }
}
