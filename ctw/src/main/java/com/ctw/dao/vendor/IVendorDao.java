package com.ctw.dao.vendor;

import com.ctw.exception.BusinessException;
import com.ctw.dao.IBaseDao;
import com.ctw.domain.vendor.Vendor;
import com.ctw.domain.vendor.VendorEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.vendor.VendorQuery;

import java.util.List;

public interface IVendorDao extends IBaseDao<Integer, VendorEntity> {
        int batchDelete(String[] ids);
        List<Vendor> findList(VendorQuery query);
        PageResult<Vendor> findPage(VendorQuery query);
}