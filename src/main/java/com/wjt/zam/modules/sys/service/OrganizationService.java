package com.wjt.zam.modules.sys.service;

import java.util.List;

import com.wjt.zam.modules.sys.model.Organization;

/**     
 * @Description:组织机构-业务层   
 * @author: wangjintao 
 * @date:   2018年6月24日      上午11:17:50   
 */  
public interface OrganizationService {

    public Organization createOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);
    List<Organization> findAll();

    Object findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
}
