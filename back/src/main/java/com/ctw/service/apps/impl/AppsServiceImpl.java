package com.ctw.service.apps.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.apps.IAppsDao;
import com.ctw.domain.apps.AppsEntity;
        import com.ctw.domain.common.PageResult;
import com.ctw.domain.apps.Apps;
import com.ctw.domain.apps.AppsQuery;
import com.ctw.domain.apps.AppsVoConvert;
 import com.ctw.service.apps.IAppsService;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AppsServiceImpl implements IAppsService {

    @Autowired
    private IAppsDao iAppsDao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(Apps apps) {
        if (null == apps) {
            return 0;
        }
      /*  if(StringUtils.isNotBlank(apps.getId().toString())){
            apps.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        EntityUtils.setCreateInfo(apps);
        AppsEntity appsEntity = AppsVoConvert.toEntity(apps);
        return iAppsDao.create(appsEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(Apps apps) {
        if(StringUtils.isEmpty(apps.getAppsid().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(apps);
        AppsEntity appsEntity = AppsVoConvert.toEntity(apps);
        return iAppsDao.update(appsEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iAppsDao.deleteById(id);
    }
    @Override
    public int batchDelete(String[] ids) {
        return iAppsDao.batchDelete(ids);
    }

    @Override
    public Apps getById(Integer id) {
        return AppsVoConvert.toVo(iAppsDao.getById(id));
    }

    @Override
    public List<Apps> findList(AppsQuery query) {
        return iAppsDao.findList(query);
    }

    @Override
    public PageResult<Apps> findPage(AppsQuery query) {
        return iAppsDao.findPage(query);
    }
}