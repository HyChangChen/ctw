package com.ctw.service.vendor.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.vendor.IVendorCompcpmtactDao;
import com.ctw.domain.vendor.VendorCompcpmtactEntity;
        import com.ctw.domain.common.PageResult;
import com.ctw.domain.vendor.VendorCompcpmtact;
import com.ctw.domain.vendor.VendorCompcpmtactQuery;
import com.ctw.domain.vendor.VendorCompcpmtactVoConvert;
 import com.ctw.service.vendor.IVendorCompcpmtactService;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class VendorCompcpmtactServiceImpl implements IVendorCompcpmtactService {

    @Autowired
    private IVendorCompcpmtactDao iVendorCompcpmtactDao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(VendorCompcpmtact vendorCompcpmtact) {
        if (null == vendorCompcpmtact) {
            return 0;
        }
      /*  if(StringUtils.isNotBlank(vendorCompcpmtact.getId().toString())){
            vendorCompcpmtact.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        EntityUtils.setCreateInfo(vendorCompcpmtact);
        VendorCompcpmtactEntity vendorCompcpmtactEntity = VendorCompcpmtactVoConvert.toEntity(vendorCompcpmtact);
        return iVendorCompcpmtactDao.create(vendorCompcpmtactEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(VendorCompcpmtact vendorCompcpmtact) {
        if(StringUtils.isEmpty(vendorCompcpmtact.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(vendorCompcpmtact);
        VendorCompcpmtactEntity vendorCompcpmtactEntity = VendorCompcpmtactVoConvert.toEntity(vendorCompcpmtact);
        return iVendorCompcpmtactDao.update(vendorCompcpmtactEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iVendorCompcpmtactDao.deleteById(id);
    }
    @Override
    public int batchDelete(String[] ids) {
        return iVendorCompcpmtactDao.batchDelete(ids);
    }

    @Override
    public VendorCompcpmtact getById(Integer id) {
        return VendorCompcpmtactVoConvert.toVo(iVendorCompcpmtactDao.getById(id));
    }

    @Override
    public List<VendorCompcpmtact> findList(VendorCompcpmtactQuery query) {
        return iVendorCompcpmtactDao.findList(query);
    }

    @Override
    public PageResult<VendorCompcpmtact> findPage(VendorCompcpmtactQuery query) {
        return iVendorCompcpmtactDao.findPage(query);
    }
}