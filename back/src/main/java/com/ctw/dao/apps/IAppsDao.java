package com.ctw.dao.apps;

import com.ctw.exception.BusinessException;
import com.ctw.dao.IBaseDao;
import com.ctw.domain.apps.Apps;
import com.ctw.domain.apps.AppsEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.apps.AppsQuery;

import java.util.List;

public interface IAppsDao extends IBaseDao<Integer, AppsEntity> {
        int batchDelete(String[] ids);
        List<Apps> findList(AppsQuery query);
        PageResult<Apps> findPage(AppsQuery query);
}