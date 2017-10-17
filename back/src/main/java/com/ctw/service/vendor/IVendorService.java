package com.ctw.service.vendor;

import com.ctw.domain.vendor.Vendor;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.vendor.VendorQuery;
import com.ctw.service.IBaseService;
        import java.util.List;

public interface IVendorService extends IBaseService<Integer ,Vendor> {
    int batchDelete(String[] ids);
    List<Vendor> findList(VendorQuery query);
    PageResult<Vendor> findPage(VendorQuery query);
}