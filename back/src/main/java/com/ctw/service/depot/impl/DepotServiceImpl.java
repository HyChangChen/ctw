package com.ctw.service.depot.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.depot.IDepotDao;
import com.ctw.domain.depot.DepotEntity;
        import com.ctw.domain.common.PageResult;
import com.ctw.domain.depot.Depot;
import com.ctw.domain.depot.DepotQuery;
import com.ctw.domain.depot.DepotVoConvert;
 import com.ctw.service.depot.IDepotService;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class DepotServiceImpl implements IDepotService {

    @Autowired
    private IDepotDao iDepotDao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(Depot depot) {
        if (null == depot) {
            return 0;
        }
      /*  if(StringUtils.isNotBlank(depot.getId().toString())){
            depot.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        EntityUtils.setCreateInfo(depot);
        DepotEntity depotEntity = DepotVoConvert.toEntity(depot);
        return iDepotDao.create(depotEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(Depot depot) {
        if(StringUtils.isEmpty(depot.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(depot);
        DepotEntity depotEntity = DepotVoConvert.toEntity(depot);
        return iDepotDao.update(depotEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iDepotDao.deleteById(id);
    }
    @Override
    public int batchDelete(String[] ids) {
        return iDepotDao.batchDelete(ids);
    }

    @Override
    public Depot getById(Integer id) {
        return DepotVoConvert.toVo(iDepotDao.getById(id));
    }

    @Override
    public List<Depot> findList(DepotQuery query) {
        return iDepotDao.findList(query);
    }

    @Override
    public PageResult<Depot> findPage(DepotQuery query) {
        return iDepotDao.findPage(query);
    }
}