package com.scrumers.storage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.scrumers.api.dao.OrganizationDao;
import com.scrumers.model.Organization;

public class OrganizationDaoImpl extends SqlSessionDaoSupport implements
        OrganizationDao {

    @Override
    public Long selectId() {
        return getSqlSession().selectOne("Organization.selectId");
    }

    @Override
    public void create(Organization o) {
        getSqlSession().insert("Organization.create", o);
    }

    @Override
    public void createWithUserId(Organization o) {
        getSqlSession().insert("Organization.createWithUserId", o);
    }

    @Override
    public Organization read(Long id) {
        return getSqlSession().selectOne("Organization.read", id);
    }

    @Override
    public List<Organization> readAll() {
        return getSqlSession().selectList("Organization.readAll");
    }

    @Override
    public List<Organization> readByTeamId(Long tid) {
        return getSqlSession().selectList("Organization.readByTeamId", tid);
    }

    @Override
    public List<Organization> readByUserId(Long uid) {
        return getSqlSession().selectList("Organization.readByUserId", uid);
    }

    @Override
    public List<Organization> readByProductId(Long pid) {
        return getSqlSession().selectList("Organization.readByProductId", pid);
    }

    @Override
    public void update(Organization o) {
        getSqlSession().update("Organization.update", o);
    }

    @Override
    public void delete(final Long id) {
        getSqlSession().delete("Organization.delete", id);
    }

    @Override
    public void addTeamToAnOrganization(Long oid, Long tid) {
        getSqlSession().insert("Organization.addTeamToAnOrganization", ImmutableMap.of("oid", oid, "tid", tid));
    }

    @Override
    public void updateActual(Long uid, Long oid) {
        getSqlSession().update("Organization.updateActual", ImmutableMap.of("oid", oid, "uid", uid));
    }

    @Override
    public List<Long> readIdsByUserAndOrg(Long uid, Long oid) {
        return getSqlSession().selectList("Organization.readIds_UID_OID", ImmutableMap.of("oid", oid, "uid", uid));
    }

}
