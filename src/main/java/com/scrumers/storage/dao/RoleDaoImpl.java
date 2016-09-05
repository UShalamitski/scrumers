package com.scrumers.storage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scrumers.storage.mapper.RoleMapper;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.scrumers.api.dao.RoleDao;
import com.scrumers.model.Role;

import javax.annotation.Resource;

public class RoleDaoImpl extends SqlSessionDaoSupport implements RoleDao {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public List<Role> findByUserId(final Long id) {
        return roleMapper.findByUserId(id);
    }

    @Override
    public void addUserRole(final Long uid, final Long rid) {
        roleMapper.add(uid, rid);
    }

    @Override
    public void deleteUserRoles(final Long uid) {
        roleMapper.delete(uid);
    }

}
