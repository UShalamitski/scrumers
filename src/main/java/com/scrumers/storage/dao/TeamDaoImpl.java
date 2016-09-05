package com.scrumers.storage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.scrumers.api.dao.TeamDao;
import com.scrumers.model.Team;

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
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("uid", uid);
        map.put("oid", oid);
        return getSqlSession().selectList("Team.readByUserIdAndOrganizationId",
                map);
    }

    @Override
    public void deleteFromUsersTeam(Long uid, Long tid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("uid", uid);
        map.put("tid", tid);
        getSqlSession().delete("Team.deleteFromUsersTeam", map);
    }

    @Override
    public List<Team> readAll() {
        return getSqlSession().selectList("Team.readAll");
    }

    @Override
    public void addUserToATeam(Long uid, Long tid, Long rid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("uid", uid);
        map.put("tid", tid);
        map.put("rid", rid);
        getSqlSession().insert("Team.addUserToATeam", map);
    }

    @Override
    public String readTeamRole(Long uid, Long tid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("uid", uid);
        map.put("tid", tid);
        return getSqlSession().selectOne("Team.readTeamRole", map);
    }

    @Override
    public void addProductToATeam(Long tid, Long pid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("pid", pid);
        map.put("tid", tid);
        getSqlSession().insert("Team.addProductToATeam", map);
    }

    @Override
    public void deleteMemberFromTeam(Long uid, Long tid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("tid", tid);
        map.put("uid", uid);
        getSqlSession().delete("Team.deleteMemberFromTeam", map);
    }

    @Override
    public void updateMemberRole(Long uid, Long tid, Long rid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("tid", tid);
        map.put("uid", uid);
        map.put("rid", rid);
        getSqlSession().update("Team.updateMemberRole", map);
    }

    @Override
    public List<Team> readByProductId(Long pid) {
        return getSqlSession().selectList("Team.readByProductId", pid);
    }

    @Override
    public void deleteFromTeamProductByTeamIdAndProductId(Long tid, Long pid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("tid", tid);
        map.put("pid", pid);
        getSqlSession().delete(
                "Team.deleteFromTeamProductByTeamIdAndProductId", map);
    }

}
