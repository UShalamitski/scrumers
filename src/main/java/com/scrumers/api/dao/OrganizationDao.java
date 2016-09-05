package com.scrumers.api.dao;

import java.util.List;

import com.scrumers.model.Organization;

public interface OrganizationDao extends GenericDao<Long, Organization> {

    void createWithUserId(Organization o);

    Long selectId();

    void updateActual(Long uid, Long oid);

    List<Organization> readAll();

    List<Long> readIdsByUserAndOrg(Long uid, Long oid);

    List<Organization> readByProductId(Long pid);

    List<Organization> readByUserId(Long uid);

    List<Organization> readByTeamId(Long tid);

    void addTeamToAnOrganization(Long oid, Long tid);

    void addProductToAnOrganization(Long oid, Long pid);
}
