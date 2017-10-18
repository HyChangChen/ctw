package com.ctw.service.depot;

import com.ctw.domain.depot.Depot;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.depot.DepotQuery;
import com.ctw.service.IBaseService;
        import java.util.List;

public interface IDepotService extends IBaseService<Integer ,Depot> {
    int batchDelete(String[] ids);
    List<Depot> findList(DepotQuery query);
    PageResult<Depot> findPage(DepotQuery query);
}