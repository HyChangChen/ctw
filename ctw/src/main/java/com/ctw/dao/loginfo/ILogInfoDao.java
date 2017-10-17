package com.ctw.dao.loginfo;

import com.ctw.exception.BusinessException;
import com.ctw.dao.IBaseDao;
import com.ctw.domain.loginfo.LogInfo;
import com.ctw.domain.loginfo.LogInfoEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.loginfo.LogInfoQuery;

import java.util.List;

public interface ILogInfoDao extends IBaseDao<Integer, LogInfoEntity> {
        int batchDelete(String[] ids);
        List<LogInfo> findList(LogInfoQuery query);
        PageResult<LogInfo> findPage(LogInfoQuery query);
}