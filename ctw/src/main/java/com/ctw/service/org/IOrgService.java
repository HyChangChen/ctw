package com.ctw.service.org;

import com.ctw.domain.common.Select2Type;
import com.ctw.domain.common.VEasyUiTree;
import com.ctw.domain.org.Org;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.org.OrgQuery;
import com.ctw.domain.user.User;
import com.ctw.service.IBaseService;
        import java.util.List;

public interface IOrgService extends IBaseService<Integer ,Org> {
    int batchDelete(String[] ids);
    List<Org> findList(OrgQuery query);
    PageResult<Org> findPage(OrgQuery query);
    /**
     * 主要是根据partentId findPage脚本存在差役
     * @param query
     * @return
     */
    PageResult<Org> findByPartentId(OrgQuery query);

    /**
     * 查询所有的组织树，
     * @param query
     * @return
     */
    List<VEasyUiTree> findOrgTree(OrgQuery query);  /**
     * 查询所有的组织树，
     * @param query
     * @return
     */
    List<VEasyUiTree> findOrgTreeByRoleId(OrgQuery query);


}