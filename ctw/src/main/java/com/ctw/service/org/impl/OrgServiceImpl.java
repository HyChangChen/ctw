package com.ctw.service.org.impl;


import com.ctw.domain.common.VEasyUiTree;
import com.ctw.domain.orgrole.OrgRole;
import com.ctw.domain.orgrole.OrgRoleQuery;
import com.ctw.service.EasyUiTree;
import com.ctw.service.orgrole.IOrgRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.org.IOrgDao;
import com.ctw.domain.org.OrgEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.org.Org;
import com.ctw.domain.org.OrgQuery;
import com.ctw.domain.org.OrgVoConvert;
import com.ctw.service.org.IOrgService;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrgServiceImpl extends EasyUiTree implements IOrgService {

    @Autowired
    private IOrgDao iOrgDao;
    @Autowired
    private IOrgRoleService iOrgRoleService;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(Org org) {
        if (null == org) {
            return 0;
        }
      /*  if(StringUtils.isNotBlank(org.getId().toString())){
            org.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        EntityUtils.setCreateInfo(org);
        OrgEntity orgEntity = OrgVoConvert.toEntity(org);
        return iOrgDao.create(orgEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(Org org) {
        if (StringUtils.isEmpty(org.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(org);
        OrgEntity orgEntity = OrgVoConvert.toEntity(org);
        return iOrgDao.update(orgEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iOrgDao.deleteById(id);
    }

    @Override
    public int batchDelete(String[] ids) {
        return iOrgDao.batchDelete(ids);
    }

    @Override
    public Org getById(Integer id) {
        return OrgVoConvert.toVo(iOrgDao.getById(id));
    }

    @Override
    public List<Org> findList(OrgQuery query) {
        return iOrgDao.findList(query);
    }

    @Override
    public PageResult<Org> findPage(OrgQuery query) {
        return iOrgDao.findPage(query);
    }

    /**
     * 主要是根据partentId findPage脚本存在差役
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<Org> findByPartentId(OrgQuery query) {
        return iOrgDao.findByPartentId(query);
    }

    /**
     * 查询所有的组织树，
     *
     * @param query
     * @return
     */
    @Override
    public List<VEasyUiTree> findOrgTree(OrgQuery query) {

        List<Org> orgList = iOrgDao.findList(query);
        List<VEasyUiTree> vEasyUiTreeTemp;
        List<VEasyUiTree> vEasyUiTree = new ArrayList<VEasyUiTree>();
        if (orgList != null && orgList.size() > 0) {
            //类型转换
            vEasyUiTreeTemp = orgToEasyUi(orgList);
            vEasyUiTree = super.ObjectToTree(vEasyUiTreeTemp);
        }
        return vEasyUiTree;
    }

    /**
     * 查询所有的组织树，
     *
     * @param query
     * @return
     */
    @Override
    public List<VEasyUiTree> findOrgTreeByRoleId(OrgQuery query) {
        List<Org> orgList = iOrgDao.findList(query);
        List<VEasyUiTree> vEasyUiTreeTemp;
        List<VEasyUiTree> vEasyUiTree = new ArrayList<VEasyUiTree>();
        if (orgList != null && orgList.size() > 0) {
            //类型转换
            vEasyUiTreeTemp = orgToEasyUi(orgList);
            //判断是否角色关联里面需要的数据
            if (null != query.getRoleId()) {
                OrgRoleQuery orgRoleQuery = new OrgRoleQuery();
                orgRoleQuery.setRoleId(Integer.parseInt(query.getRoleId()));
                List<OrgRole> orgRoles = iOrgRoleService.findList(orgRoleQuery);
                if (orgRoles.size() > 0) {
                    for (OrgRole o : orgRoles) {
                        Integer orgId = o.getOrgId();
                        for (VEasyUiTree ve : vEasyUiTreeTemp) {
                            if (ve.getId() == orgId) {
                                ve.setChecked(Boolean.TRUE);
                            }
                        }
                    }
                }
            }
            vEasyUiTree = super.ObjectToTree(vEasyUiTreeTemp);
        }
        return vEasyUiTree;
    }


    /*orgList----> EasyUiList格式转换*/
    public List<VEasyUiTree> orgToEasyUi(List<Org> orgList) {
        List<VEasyUiTree> easyList = new ArrayList<VEasyUiTree>();
        for (Org org : orgList) {
            VEasyUiTree v = new VEasyUiTree();
            v.setId(org.getId());
            v.setText(org.getOrgName());
            v.setIconCls(org.getIcon());
            v.setPid(org.getPartentId());
            v.setIsValid(org.getIsValid());
            easyList.add(v);
        }
        return easyList;
    }

}