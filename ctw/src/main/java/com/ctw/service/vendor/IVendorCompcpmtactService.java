package com.ctw.service.vendor;

import com.ctw.domain.vendor.VendorCompcpmtact;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.vendor.VendorCompcpmtactQuery;
import com.ctw.service.IBaseService;
        import java.util.List;

public interface IVendorCompcpmtactService extends IBaseService<Integer ,VendorCompcpmtact> {
    int batchDelete(String[] ids);
    List<VendorCompcpmtact> findList(VendorCompcpmtactQuery query);
    PageResult<VendorCompcpmtact> findPage(VendorCompcpmtactQuery query);
}