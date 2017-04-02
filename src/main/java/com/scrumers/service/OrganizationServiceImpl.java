package com.scrumers.service;

import java.util.List;

import com.scrumers.api.dao.OrganizationDao;
import com.scrumers.api.dao.TeamDao;
import com.scrumers.api.dao.UserDao;
import com.scrumers.api.service.OrganizationService;
import com.scrumers.model.Organization;
import com.scrumers.model.Team;
import com.scrumers.model.User;

public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationDao organizationDao;

    private UserDao userDao;

    private TeamDao teamDao;

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveOrganization(Organization o) {
        if (o.getId() == null) {
            organizationDao.create(o);
        } else {
            organizationDao.update(o);
        }
    }

    @Override
    public void saveOrganizationWithUserId(Organization o) {
        if (o.getId() == null) {
            Long oid = organizationDao.selectId();
            Long tid = teamDao.selectId();
            Long uid = o.getIdCreator();
            o.setId(oid);
            Team t = new Team();
            t.setId(tid);
            t.setName("My team");
            t.setIdCreator(uid);

            organizationDao.createWithUserId(o);
            teamDao.createWithId(t);
            teamDao.addUserToATeam(uid, tid, 1L);
            organizationDao.addTeamToAnOrganization(oid, tid);
            organizationDao.updateActual(uid, oid);
            User u = userDao.read(uid);
            u.setActualOrganization(oid);
            u.setActualProduct(0L);
            u.setActualIteration(0L);
            u.setId(uid);
            userDao.update(u);

        } else {
            organizationDao.update(o);
        }
    }

    @Override
    public void deleteOrganizationAsAnOwner(Long[] id) {
        if (id != null)
            for (Long oid : id) {
                organizationDao.delete(oid);
                userDao.deleteActualOrganization(oid);
            }
    }

    @Override
    public void deleteOrganizationAsAMember(Long uid, Long oid) {
        List<Long> teams = organizationDao.readIdsByUserAndOrg(uid, oid);
        for (Long tid : teams) {
            teamDao.deleteFromUsersTeam(uid, tid);
        }
    }

    @Override
    public List<Organization> getOrganizations() {
        return organizationDao.readAll();
    }

    @Override
    public Organization getOrganization(Long id) {
        return organizationDao.read(id);
    }

    @Override
    public List<Organization> getOrganizationsByTeamId(Long tid) {
        return organizationDao.readByTeamId(tid);
    }

    @Override
    public List<Organization> getOrganizationsByUserId(Long uid) {
        return organizationDao.readByUserId(uid);
    }

    @Override
    public List<Organization> getOrganizationsByProductId(Long pid) {
        return organizationDao.readByProductId(pid);
    }

    @Override
    public void addTeamToAnOrganization(Long oid, Long tid) {
        organizationDao.addTeamToAnOrganization(oid, tid);
    }

    @Override
    public void updateActual(Long uid, Long oid) {
        organizationDao.updateActual(uid, oid);
    }

}
