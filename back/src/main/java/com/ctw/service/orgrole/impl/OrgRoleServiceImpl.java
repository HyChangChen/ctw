package com.ctw.service.orgrole.impl;



import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.orgrole.IOrgRoleDao;
import com.ctw.domain.orgrole.OrgRoleEntity;
        import com.ctw.domain.common.PageResult;
import com.ctw.domain.orgrole.OrgRole;
import com.ctw.domain.orgrole.OrgRoleQuery;
import com.ctw.domain.orgrole.OrgRoleVoConvert;
 import com.ctw.service.orgrole.IOrgRoleService;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class OrgRoleServiceImpl implements IOrgRoleService {

    @Autowired
    private IOrgRoleDao iOrgRoleDao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(OrgRole orgRole) {
        if (null == orgRole) {
            return 0;
        }
       /* if(StringUtils.isNotBlank(orgRole.getId().toString())){
            orgRole.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        EntityUtils.setCreateInfo(orgRole);
        OrgRoleEntity orgRoleEntity = OrgRoleVoConvert.toEntity(orgRole);
        return iOrgRoleDao.create(orgRoleEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(OrgRole orgRole) {
        if(StringUtils.isEmpty(orgRole.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(orgRole);
        OrgRoleEntity orgRoleEntity = OrgRoleVoConvert.toEntity(orgRole);
        return iOrgRoleDao.update(orgRoleEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iOrgRoleDao.deleteById(id);
    }
    @Override
    public int batchDelete(String[] ids) {
        return iOrgRoleDao.batchDelete(ids);
    }

    @Override
    public OrgRole getById(Integer id) {
        return OrgRoleVoConvert.toVo(iOrgRoleDao.getById(id));
    }

    @Override
    public List<OrgRole> findList(OrgRoleQuery query) {
        return iOrgRoleDao.findList(query);
    }

    /**
     * 根据角色ID 查找拥有本角色的组织列表
     *
     * @param query
     * @return
     */
    @Override
    public List<OrgRole> findListByRoleId(OrgRoleQuery query) {
        return iOrgRoleDao.findListByRoleId(query);
    }

    @Override
    public PageResult<OrgRole> findPage(OrgRoleQuery query) {
        return iOrgRoleDao.findPage(query);
    }

    @Override
    public int deleteByOrgIdRId(OrgRole orgRole) {
        return iOrgRoleDao.deleteByOrgIdRId(orgRole);
    }
}