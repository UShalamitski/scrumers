package com.scrumers.api.dao;

import java.util.List;

import com.scrumers.model.Team;

public interface TeamDao extends GenericDao<Long, Team> {

    List<Team> readAll();

    Long selectId();

    void createWithId(Team t);

    Team readByName(String name);

    String readTeamRole(Long uid, Long tid);

    List<Team> readByUserId(Long uid);

    List<Team> readByProductId(Long pid);

    List<Team> readByUserIdAndOrganizationId(Long uid, Long oid);

    void deleteFromUsersTeam(Long uid, Long tid);

    void deleteFromTeamProductByTeamIdAndProductId(Long tid, Long pid);

    void addUserToATeam(Long uid, Long tid, Long rid);

    void addProductToATeam(Long tid, Long pid);

    void deleteMemberFromTeam(Long uid, Long tid);

    void updateMemberRole(Long uid, Long tid, Long rid);

}
