package com.scrumers.model;

public class TeamMember extends User {

    private static final long serialVersionUID = 1L;

    private Long teamRoleId;

    public Long getTeamRoleId() {
        return teamRoleId;
    }

    public void setTeamRoleId(Long teamRoleId) {
        this.teamRoleId = teamRoleId;
    }

}
