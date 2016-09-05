package com.scrumers.model;

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

}
