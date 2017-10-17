package com.ctw.dao.vendor;

import com.ctw.exception.BusinessException;
import com.ctw.dao.IBaseDao;
import com.ctw.domain.vendor.VendorCompcpmtact;
import com.ctw.domain.vendor.VendorCompcpmtactEntity;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.vendor.VendorCompcpmtactQuery;

import java.util.List;

public interface IVendorCompcpmtactDao extends IBaseDao<Integer, VendorCompcpmtactEntity> {
        int batchDelete(String[] ids);
        List<VendorCompcpmtact> findList(VendorCompcpmtactQuery query);
        PageResult<VendorCompcpmtact> findPage(VendorCompcpmtactQuery query);
}