package com.ctw.service.vendor.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.vendor.IVendorDao;
import com.ctw.domain.vendor.VendorEntity;
        import com.ctw.domain.common.PageResult;
import com.ctw.domain.vendor.Vendor;
import com.ctw.domain.vendor.VendorQuery;
import com.ctw.domain.vendor.VendorVoConvert;
 import com.ctw.service.vendor.IVendorService;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class VendorServiceImpl implements IVendorService {

    @Autowired
    private IVendorDao iVendorDao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(Vendor vendor) {
        if (null == vendor) {
            return 0;
        }
      /*  if(StringUtils.isNotBlank(vendor.getId().toString())){
            vendor.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        EntityUtils.setCreateInfo(vendor);
        VendorEntity vendorEntity = VendorVoConvert.toEntity(vendor);
        return iVendorDao.create(vendorEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(Vendor vendor) {
        if(StringUtils.isEmpty(vendor.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(vendor);
        VendorEntity vendorEntity = VendorVoConvert.toEntity(vendor);
        return iVendorDao.update(vendorEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iVendorDao.deleteById(id);
    }
    @Override
    public int batchDelete(String[] ids) {
        return iVendorDao.batchDelete(ids);
    }

    @Override
    public Vendor getById(Integer id) {
        return VendorVoConvert.toVo(iVendorDao.getById(id));
    }

    @Override
    public List<Vendor> findList(VendorQuery query) {
        return iVendorDao.findList(query);
    }

    @Override
    public PageResult<Vendor> findPage(VendorQuery query) {
        return iVendorDao.findPage(query);
    }
}