package com.scrumers.storage.dao;

import java.util.List;
import com.google.common.collect.ImmutableMap;
import com.scrumers.api.dao.TeamDao;
import com.scrumers.model.Team;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TeamDaoImpl extends SqlSessionDaoSupport implements TeamDao {

    @Override
    public Long selectId() {
        return getSqlSession().selectOne("Team.selectId");
    }

    @Override
    public void create(Team t) {
        getSqlSession().insert("Team.create", t);
    }

    @Override
    public void createWithId(Team t) {
        getSqlSession().insert("Team.createWithId", t);
    }

    @Override
    public Team read(Long id) {
        return getSqlSession().selectOne("Team.read", id);
    }

    @Override
    public void update(Team t) {
        getSqlSession().update("Team.update", t);
    }

    @Override
    public void delete(final Long id) {
        getSqlSession().delete("Team.delete", id);
    }

    @Override
    public Team readByName(String name) {
        return getSqlSession().selectOne("Team.readByName", name);
    }

    @Override
    public List<Team> readByUserId(Long uid) {
        return getSqlSession().selectList("Team.readByUserId", uid);
    }

    @Override
    public List<Team> readByUserIdAndOrganizationId(Long uid, Long oid) {
        return getSqlSession().selectList("Team.readByUserIdAndOrgId", ImmutableMap.of("uid", uid, "oid", oid));
    }

    @Override
    public void deleteFromUsersTeam(Long uid, Long tid) {
        getSqlSession().delete("Team.deleteFromUsersTeam", ImmutableMap.of("uid", uid, "tid", tid));
    }

    @Override
    public List<Team> readAll() {
        return getSqlSession().selectList("Team.readAll");
    }

    @Override
    public void addUserToATeam(Long uid, Long tid, Long rid) {
        getSqlSession().insert("Team.addUserToATeam", ImmutableMap.of("uid", uid, "tid", tid, "rid", rid));
    }

    @Override
    public String readTeamRole(Long uid, Long tid) {
        return getSqlSession().selectOne("Team.readTeamRole", ImmutableMap.of("uid", uid, "tid", tid));
    }

    @Override
    public void addProductToATeam(Long tid, Long pid) {
        getSqlSession().insert("Team.addProductToATeam", ImmutableMap.of("pid", pid, "tid", tid));
    }

    @Override
    public void deleteMemberFromTeam(Long uid, Long tid) {
        getSqlSession().delete("Team.deleteMemberFromTeam", ImmutableMap.of("tid", tid, "uid", uid));
    }

    @Override
    public void updateMemberRole(Long uid, Long tid, Long rid) {
        getSqlSession().update("Team.updateMemberRole", ImmutableMap.of("uid", uid, "tid", tid, "rid", rid));
    }

    @Override
    public List<Team> readByProductId(Long pid) {
        return getSqlSession().selectList("Team.readByProductId", pid);
    }

    @Override
    public void deleteFromTeamProductByTeamIdAndProductId(Long tid, Long pid) {
        getSqlSession().delete("Team.deleteFromTeamProductByTeamAndProduct", ImmutableMap.of("pid", pid, "tid", tid));
    }

}
