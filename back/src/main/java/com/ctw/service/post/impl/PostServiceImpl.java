package com.ctw.service.post.impl;


import com.ctw.domain.postrole.PostRole;
import com.ctw.domain.postrole.PostRoleQuery;
import com.ctw.service.postrole.IPostRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.post.IPostDao;
import com.ctw.domain.post.PostEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.post.Post;
import com.ctw.domain.post.PostQuery;
import com.ctw.domain.post.PostVoConvert;
import com.ctw.service.post.IPostService;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostDao iPostDao;
    @Autowired
    private IPostRoleService iPostRoleService;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(Post post) {
        if (null == post) {
            return 0;
        }
        EntityUtils.setCreateInfo(post);
        PostEntity postEntity = PostVoConvert.toEntity(post);
        return iPostDao.create(postEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(Post post) {
        if (StringUtils.isEmpty(post.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(post);
        PostEntity postEntity = PostVoConvert.toEntity(post);
        return iPostDao.update(postEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iPostDao.deleteById(id);
    }

    @Override
    public int batchDelete(String[] ids) {
        return iPostDao.batchDelete(ids);
    }

    @Override
    public Post getById(Integer id) {
        return PostVoConvert.toVo(iPostDao.getById(id));
    }

    @Override
    public List<Post> findList(PostQuery query) {
        return iPostDao.findList(query);
    }

    @Override
    public PageResult<Post> findPage(PostQuery query) {
        return iPostDao.findPage(query);
    }

    /***
     * 角色ID关联岗位职责时候搜索未关联的岗位
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<Post> seachNotLinkPost(PostQuery query) {
        PostRoleQuery postRoleQuery = new PostRoleQuery();
        postRoleQuery.setRoleId(query.getRoleId());
        List<Integer> postIds = new ArrayList<Integer>();
        List<PostRole> postRoleList = iPostRoleService.findList(postRoleQuery);
        if (postRoleList.size() > 0) {
            for (PostRole pr : postRoleList) {
                postIds.add(pr.getPsotId());
            }
            query.setPostIds(postIds);
        }
        if(null!=query.getOrgId()&&query.getOrgId()==1){
            query.setOrgId(null);
        }
        return iPostDao.seachNotLinkPost(query);
    }
}