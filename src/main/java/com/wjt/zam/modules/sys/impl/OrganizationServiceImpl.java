package com.wjt.zam.modules.sys.impl;

import com.wjt.zam.modules.sys.model.Organization;
import com.wjt.zam.modules.sys.service.OrganizationService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="organizationServiceImpl")
public class OrganizationServiceImpl implements OrganizationService {

    @Override
    public Organization createOrganization(Organization organization) {
		return organization;
        /*return organizationDao.createOrganization(organization);*/
    }

    @Override
    public Organization updateOrganization(Organization organization) {
		return organization;
       /* return organizationDao.updateOrganization(organization);*/
    }

    @Override
    public void deleteOrganization(Long organizationId) {
       /* organizationDao.deleteOrganization(organizationId);*/
    }

    @Override
    public Organization findOne(Long organizationId) {
		return null;
        /*return organizationDao.findOne(organizationId);*/
    }

    @Override
    public List<Organization> findAll() {
		return null;
        /*return organizationDao.findAll();*/
    }

    @Override
    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
		return null;
        /*return organizationDao.findAllWithExclude(excludeOraganization);*/
    }

    @Override
    public void move(Organization source, Organization target) {
        /*organizationDao.move(source, target);*/
    }
}
