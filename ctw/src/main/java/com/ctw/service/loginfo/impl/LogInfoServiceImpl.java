package com.ctw.service.loginfo.impl;



import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.loginfo.ILogInfoDao;
import com.ctw.domain.loginfo.LogInfoEntity;
        import com.ctw.domain.common.PageResult;
import com.ctw.domain.loginfo.LogInfo;
import com.ctw.domain.loginfo.LogInfoQuery;
import com.ctw.domain.loginfo.LogInfoVoConvert;
 import com.ctw.service.loginfo.ILogInfoService;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class LogInfoServiceImpl implements ILogInfoService {

    @Autowired
    private ILogInfoDao iLogInfoDao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(LogInfo logInfo) {
        if (null == logInfo) {
            return 0;
        }
      /*  if(StringUtils.isNotBlank(logInfo.getId().toString())){
            logInfo.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        EntityUtils.setCreateInfo(logInfo);
        LogInfoEntity logInfoEntity = LogInfoVoConvert.toEntity(logInfo);
        return iLogInfoDao.create(logInfoEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(LogInfo logInfo) {
        if(StringUtils.isEmpty(logInfo.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(logInfo);
        LogInfoEntity logInfoEntity = LogInfoVoConvert.toEntity(logInfo);
        return iLogInfoDao.update(logInfoEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iLogInfoDao.deleteById(id);
    }
    @Override
    public int batchDelete(String[] ids) {
        return iLogInfoDao.batchDelete(ids);
    }

    @Override
    public LogInfo getById(Integer id) {
        return LogInfoVoConvert.toVo(iLogInfoDao.getById(id));
    }

    @Override
    public List<LogInfo> findList(LogInfoQuery query) {
        return iLogInfoDao.findList(query);
    }

    @Override
    public PageResult<LogInfo> findPage(LogInfoQuery query) {
        return iLogInfoDao.findPage(query);
    }
}