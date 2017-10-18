package com.ctw.service.apps;

import com.ctw.domain.apps.Apps;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.apps.AppsQuery;
import com.ctw.service.IBaseService;
        import java.util.List;

public interface IAppsService extends IBaseService<Integer ,Apps> {
    int batchDelete(String[] ids);
    List<Apps> findList(AppsQuery query);
    PageResult<Apps> findPage(AppsQuery query);
}