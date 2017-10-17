package com.ctw.service.postrole.impl;



import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.postrole.IPostRoleDao;
import com.ctw.domain.postrole.PostRoleEntity;
        import com.ctw.domain.common.PageResult;
import com.ctw.domain.postrole.PostRole;
import com.ctw.domain.postrole.PostRoleQuery;
import com.ctw.domain.postrole.PostRoleVoConvert;
 import com.ctw.service.postrole.IPostRoleService;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class PostRoleServiceImpl implements IPostRoleService {

    @Autowired
    private IPostRoleDao iPostRoleDao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(PostRole postRole) {
        if (null == postRole) {
            return 0;
        }
      /*  if(StringUtils.isNotBlank(postRole.getId().toString())){
            postRole.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        EntityUtils.setCreateInfo(postRole);
        PostRoleEntity postRoleEntity = PostRoleVoConvert.toEntity(postRole);
        return iPostRoleDao.create(postRoleEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(PostRole postRole) {
        if(StringUtils.isEmpty(postRole.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(postRole);
        PostRoleEntity postRoleEntity = PostRoleVoConvert.toEntity(postRole);
        return iPostRoleDao.update(postRoleEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iPostRoleDao.deleteById(id);
    }
    @Override
    public int batchDelete(String[] ids) {
        return iPostRoleDao.batchDelete(ids);
    }

    /**
     * 删除岗位与角色的关联关系
     *
     * @param postRole
     * @return
     */
    @Override
    public int deleteByPostIdRId(PostRole postRole) {
        return iPostRoleDao.deleteByPostIdRId(postRole);
    }

    @Override
    public PostRole getById(Integer id) {
        return PostRoleVoConvert.toVo(iPostRoleDao.getById(id));
    }

    @Override
    public List<PostRole> findList(PostRoleQuery query) {
        return iPostRoleDao.findList(query);
    }

    /***
     * 根据角色ID 查找拥有本角色的岗位集合
     *
     * @param query
     * @return
     */
    @Override
    public List<PostRole> findListByRoleId(PostRoleQuery query) {
        return iPostRoleDao.findListByRoleId(query);
    }

    @Override
    public PageResult<PostRole> findPage(PostRoleQuery query) {
        return iPostRoleDao.findPage(query);
    }
}