package com.ctw.service.loginfo;

import com.ctw.domain.loginfo.LogInfo;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.loginfo.LogInfoQuery;
import com.ctw.service.IBaseService;
        import java.util.List;

public interface ILogInfoService extends IBaseService<Integer ,LogInfo> {
    int batchDelete(String[] ids);
    List<LogInfo> findList(LogInfoQuery query);
    PageResult<LogInfo> findPage(LogInfoQuery query);
}