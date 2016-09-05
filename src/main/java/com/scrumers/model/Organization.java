package com.scrumers.model;

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

}
