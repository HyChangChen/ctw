package com.ctw.service.role.impl;


import com.ctw.domain.orgrole.OrgRole;
import com.ctw.domain.orgrole.OrgRoleQuery;
import com.ctw.domain.postrole.PostRole;
import com.ctw.domain.postrole.PostRoleQuery;
import com.ctw.domain.roleuser.RoleUser;
import com.ctw.domain.roleuser.RoleUserQuery;
import com.ctw.service.orgrole.IOrgRoleService;
import com.ctw.service.postrole.IPostRoleService;
import com.ctw.service.roleuser.IRoleUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.role.IRoleDao;
import com.ctw.domain.role.RoleEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.role.Role;
import com.ctw.domain.role.RoleQuery;
import com.ctw.domain.role.RoleVoConvert;
import com.ctw.service.role.IRoleService;
import com.ctw.utils.EntityUtils;

import java.util.*;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    @Qualifier(value = "iRoleDao")
    private IRoleDao iRoleDao;
    @Autowired
    private IOrgRoleService iOrgRoleService;
    @Autowired
    private IRoleUserService iRoleUserService;
    @Autowired
    private IPostRoleService iPostRoleService;


    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(Role role) {
        if (null == role) {
            return 0;
        }

        EntityUtils.setCreateInfo(role);
        RoleEntity roleEntity = RoleVoConvert.toEntity(role);
        return iRoleDao.create(roleEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(Role role) {
        if (StringUtils.isEmpty(role.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(role);
        RoleEntity roleEntity = RoleVoConvert.toEntity(role);
        return iRoleDao.update(roleEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iRoleDao.deleteById(id);
    }

    @Override
    public int batchDelete(String[] ids) {
        return iRoleDao.batchDelete(ids);
    }

    @Override
    public Role getById(Integer id) {
        return RoleVoConvert.toVo(iRoleDao.getById(id));
    }

    @Override
    public List<Role> findList(RoleQuery query) {
        return iRoleDao.findList(query);
    }

    @Override
    public PageResult<Role> findPage(RoleQuery query) {
        return iRoleDao.findPage(query);
    }

    /**
     * 查找拥有的的角色列表 组织或者用户角色
     *
     * @param query
     */
    @Override
    public PageResult<Role> findHaveListPage(RoleQuery query) {
        List<Integer> rolds = new ArrayList<Integer>();
        Map<Integer, String> map = new HashMap<Integer, String>();
        if (null != query.getUserId()) {
            //根据用户查找直属角色查找
            RoleUserQuery ruq = new RoleUserQuery();
            ruq.setUid(query.getUserId());
            List<RoleUser> userRoleIds = iRoleUserService.findList(ruq);
            if (userRoleIds.size() > 0 && null != userRoleIds) {
                for (RoleUser rus : userRoleIds) {
                    rolds.add(rus.getRoleId());
                    map.put(rus.getRoleId(), "my");
                }

            }
        }
        if (null != query.getOrgId()) {
            //查询组织拥有的角色列表
            OrgRoleQuery orq = new OrgRoleQuery();
            orq.setOrgId(query.getOrgId());
            List<OrgRole> orgRoleList = iOrgRoleService.findList(orq);
            if (orgRoleList.size() > 0 && null != orgRoleList) {
                for (OrgRole or : orgRoleList) {
                    rolds.add(or.getRoleId());
                    map.put(or.getRoleId(), "org");
                }
            }
        }
        if (null != query.getPostId()) {
            //查询岗位拥有的角色列表
            PostRoleQuery prq = new PostRoleQuery();
            prq.setPsotId(query.getPostId());
            List<PostRole> postRoleList = iPostRoleService.findList(prq);
            if (postRoleList.size() > 0 && null != postRoleList) {
                for (PostRole p : postRoleList) {
                    rolds.add(p.getRoleId());
                    map.put(p.getRoleId(), "post");
                }
            }
        }

        if (rolds.size() > 0) {
            query.setRoleIds(rolds);
            PageResult<Role> prTemp = iRoleDao.findPage(query);
            List<Role> lr = prTemp.getRows();
            List<Role> roleList = new ArrayList<Role>();
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                Integer key = entry.getKey();
                String val = entry.getValue();
                for (Role r : lr) {
                    if (r.getId() == key) {
                        r.setFromSource(val);
                    }
                }

            }
            // prTemp.setRows(roleList);
            return prTemp;//iRoleDao.findPage(query);
        } else {
            if (!"".equals(query.getFindType()) && null != query.getFindType()) {
                return iRoleDao.findPage(query);
            } else {
                PageResult<Role> pr = new PageResult<Role>();
                pr.setRows(new ArrayList<Role>());
                pr.setTotal(0);
                return pr;
            }

        }
    }

    /**
     * 统计角色ID 是否关联了用户 组织 岗位
     *
     * @param ids
     */
    @Override
    public int getCountByRole(String[] ids) {
        List<String> idsTemp = Arrays.asList(ids);
        int num = 0;
        for (String id : idsTemp) {
            Integer queryId = Integer.parseInt(id);
            //查询角色ID 是否与用户存在关联关系
            RoleUserQuery roleUserQuery = new RoleUserQuery();
            roleUserQuery.setRoleId(queryId);
            List<RoleUser> list1 = iRoleUserService.findList(roleUserQuery);
            num += list1 == null ? 0 : list1.size();
            //查询角色ID 是否与岗位存在关联关系
            PostRoleQuery postRoleQuery = new PostRoleQuery();
            postRoleQuery.setRoleId(queryId);
            List<PostRole> list2 =iPostRoleService.findList(postRoleQuery);
            num += list2 == null ? 0 : list2.size();
            //查询角色ID 是否与组织存在关联关系
            OrgRoleQuery orgRoleQuery= new OrgRoleQuery();
            orgRoleQuery.setRoleId(queryId);
            List<OrgRole> list3=iOrgRoleService.findList(orgRoleQuery);
            num += list3 == null ? 0 : list3.size();
        }
        return num;
    }
}