package com.scrumers.api.service;

import java.util.List;

import com.scrumers.model.Team;

public interface TeamService {

    Team getTeam(Long id);

    Team getTeam(String login);

    String getTeamRole(Long uid, Long tid);

    void saveTeam(Team t, Long oid, Long rid);

    void deleteTeamByOwner(Long[] id);

    void deleteTeamByMember(Long uid, Long tid);

    List<Team> getByProductId(Long pid);

    List<Team> getTeams();

    List<Team> getTeamsByUserId(Long uid);

    List<Team> getTeamsByUserIdAndOrganizationId(Long uid, Long oid);

    void addUserToATeam(Long uid, Long tid, Long rid);

    void deleteMemberFromTeam(Long uid, Long tid);

}