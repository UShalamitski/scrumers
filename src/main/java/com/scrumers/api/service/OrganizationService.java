package com.scrumers.api.service;

import java.util.List;

import com.scrumers.model.Organization;

public interface OrganizationService {

    Organization getOrganization(Long id);

    void saveOrganization(Organization o);

    void saveOrganizationWithUserId(Organization o);

    void deleteOrganizationAsAnOwner(Long[] id);

    void deleteOrganizationAsAMember(Long uid, Long oid);

    List<Organization> getOrganizations();

    List<Organization> getOrganizationsByTeamId(Long tid);

    List<Organization> getOrganizationsByUserId(Long uid);

    List<Organization> getOrganizationsByProductId(Long pid);

    void addTeamToAnOrganization(Long oid, Long tid);

    void updateActual(Long uid, Long oid);

}