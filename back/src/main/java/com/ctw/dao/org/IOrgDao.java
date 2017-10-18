package com.ctw.dao.org;

import com.ctw.exception.BusinessException;
import com.ctw.dao.IBaseDao;
import com.ctw.domain.org.Org;
import com.ctw.domain.org.OrgEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.org.OrgQuery;

import java.util.List;

public interface IOrgDao extends IBaseDao<Integer, OrgEntity> {
        int batchDelete(String[] ids);
        List<Org> findList(OrgQuery query);
        PageResult<Org> findPage(OrgQuery query);
        /**
         * 主要是根据partentId findPage脚本存在差役
         * @param query
         * @return
         */
        PageResult<Org> findByPartentId(OrgQuery query);
}