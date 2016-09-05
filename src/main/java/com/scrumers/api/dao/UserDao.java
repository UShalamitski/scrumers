package com.scrumers.api.dao;

import java.util.List;

import com.scrumers.model.Role;
import com.scrumers.model.TeamMember;
import com.scrumers.model.User;

public interface UserDao extends GenericDao<Long, User> {

    List<User> findAll();

    User findByName(String name);

    List<User> find(String name);

    List<User> readUsersByProductId(Long pid);

    List<TeamMember> findByTeamId(Long tid);

    User findByPasswd(User user);

    void updatePasswd(User user);

    void deleteActualOrganization(Long oid);

    void updateInfo(User user);

    List<Role> findAllTeamRoles();
}
