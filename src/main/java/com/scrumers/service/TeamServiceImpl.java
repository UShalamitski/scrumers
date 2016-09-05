package com.scrumers.service;

import java.util.List;

import com.scrumers.api.dao.OrganizationDao;
import com.scrumers.api.dao.TeamDao;
import com.scrumers.api.dao.UserDao;
import com.scrumers.api.service.TeamService;
import com.scrumers.model.Team;
import com.scrumers.model.TeamMember;

public class TeamServiceImpl implements TeamService {

    private TeamDao teamDao;

    private UserDao userDao;

    private OrganizationDao organizationDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    public void saveTeam(Team t, Long oid, Long rid) {
        if (t.getId() == null) {
            Long tid = teamDao.selectId();
            t.setId(tid);
            teamDao.createWithId(t);
            teamDao.addUserToATeam(t.getIdCreator(), tid, rid);
            organizationDao.addTeamToAnOrganization(oid, tid);
        } else {
            teamDao.update(t);
        }
    }

    @Override
    public void deleteTeamByOwner(Long[] id) {
        if (id != null)
            for (Long i : id) {
                teamDao.delete(i);
            }
    }

    @Override
    public void deleteTeamByMember(Long uid, Long tid) {
        teamDao.deleteFromUsersTeam(uid, tid);
    }

    @Override
    public List<Team> getTeams() {
        return teamDao.readAll();
    }

    @Override
    public Team getTeam(Long id) {
        return teamDao.read(id);
    }

    @Override
    public Team getTeam(String name) {
        return teamDao.readByName(name);
    }

    @Override
    public List<Team> getTeamsByUserId(Long uid) {
        return teamDao.readByUserId(uid);
    }

    @Override
    public List<Team> getByProductId(Long pid) {
        return teamDao.readByProductId(pid);
    }

    @Override
    public List<Team> getTeamsByUserIdAndOrganizationId(Long uid, Long oid) {
        return teamDao.readByUserIdAndOrganizationId(uid, oid);
    }

    @Override
    public void addUserToATeam(Long uid, Long tid, Long rid) {
        if (uid != null && tid != null && rid != null) {
            List<TeamMember> members = userDao.findByTeamId(tid);
            boolean flag = false;
            for (TeamMember member : members) {
                if (member.getId() == uid) {
                    flag = true;
                }
            }
            if (flag) {
                if (rid == 1L) {
                    for (TeamMember tm : members)
                        if (tm.getTeamRoleId() == 1L) {
                            teamDao.updateMemberRole(tm.getId(), tid, 2L);
                        }
                }
                teamDao.updateMemberRole(uid, tid, rid);
            } else {
                if (rid == 1L) {
                    for (TeamMember tm : members)
                        if (tm.getTeamRoleId() == 1L) {
                            teamDao.updateMemberRole(tm.getId(), tid, 2L);
                        }
                }
                teamDao.addUserToATeam(uid, tid, rid);
            }
        }
    }

    @Override
    public String getTeamRole(Long uid, Long tid) {
        return teamDao.readTeamRole(uid, tid);
    }

    @Override
    public void deleteMemberFromTeam(Long uid, Long tid) {
        teamDao.deleteMemberFromTeam(uid, tid);
    }

}
