package com.ctw.service.post;

import com.ctw.domain.post.Post;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.post.PostQuery;
import com.ctw.service.IBaseService;
        import java.util.List;

public interface IPostService extends IBaseService<Integer ,Post> {
    int batchDelete(String[] ids);
    List<Post> findList(PostQuery query);
    PageResult<Post> findPage(PostQuery query);

    /***
     * 角色ID关联岗位职责时候搜索未关联的岗位
     *
     * @param query
     * @return
     */
    PageResult<Post> seachNotLinkPost(PostQuery query);
}