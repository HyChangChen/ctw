package com.ctw.service.resource;

import com.ctw.domain.common.VEasyUiTree;
import com.ctw.domain.resource.ResourceQuery;
import com.ctw.domain.resource.ResourceVo;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.user.User;
import com.ctw.service.IBaseService;

import javax.servlet.ServletRequest;
import java.util.List;

public interface IResourceService extends IBaseService<Integer, ResourceVo> {
    int batchDelete(String[] ids);

    List<ResourceVo> findList(ResourceQuery query);

    PageResult<ResourceVo> findPage(ResourceQuery query);
    /**
     * 查询所有的
     *
     * @param query
     * @return
     */
    PageResult<ResourceVo> findAllPage(ResourceQuery query);

    /***
     * 根据角色查找资源信息
     * @param query
     * @return
     */
    List<VEasyUiTree> findResourceTree(ResourceQuery query);

    /**
     * 根据用户的信息查询出用户的菜单列表并组装成相对应的HTML
     *
     * @param user
     * @param request
     * @return
     */
    String findResourceHtml(User user, ServletRequest request);
}