package com.ctw.dao.post.impl;


import com.ctw.dao.AbstractBaseDao;
import com.ctw.domain.common.PageResult;
import com.ctw.dao.post.IPostDao;

import org.springframework.stereotype.Repository;

import com.ctw.domain.post.Post;
import com.ctw.domain.post.PostEntity;
import com.ctw.domain.post.PostQuery;


import java.util.List;
import java.util.Arrays;

@Repository("iPostDao")
public class PostDaoImpl extends AbstractBaseDao<Integer, PostEntity> implements IPostDao {
    private static final String NAMESPACE = "com.ctw.dao.post.IPostDao";

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    private void rewriteSortColumns(PostQuery query) {

    }

    @Override
    public List<Post> findList(PostQuery query) {
        rewriteSortColumns(query);
        return selectList("findList", query);
    }

    @Override
    public PageResult<Post> findPage(PostQuery query) {
        rewriteSortColumns(query);
        return pageQuery("findList", query);
    }

    /***
     * 角色ID关联岗位职责时候搜索未关联的岗位
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<Post> seachNotLinkPost(PostQuery query) {
        rewriteSortColumns(query);
        return pageQuery("seachNotLinkPost", query);
    }

    @Override
    public int create(PostEntity entity) {
        return super.create(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return super.deleteById(id);
    }

    @Override
    public int update(PostEntity entity) {
        return super.update(entity);
    }

    @Override
    public PostEntity getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public int batchDelete(String[] ids) {
        int i = 0;
        List<String> idsTemp = Arrays.asList(ids);
        if (idsTemp.size() > 0) {
            for (String id : idsTemp) {
                i += super.deleteById(Integer.parseInt(id));
            }
        }
        return i;
    }

}