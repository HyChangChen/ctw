<#assign clazzNameLower = clazzName?uncap_first>
package ${basepackage}.service.${moduleName};

import ${basepackage}.domain.${moduleName}.${clazzName};
import com.ctw.domain.common.PageResult;
import ${basepackage}.domain.${moduleName}.${clazzName}Query;
import com.ctw.service.IBaseService;
        import java.util.List;

public interface I${clazzName}Service extends IBaseService<Integer ,${clazzName}> {
    int batchDelete(String[] ids);
    List<${clazzName}> findList(${clazzName}Query query);
    PageResult<${clazzName}> findPage(${clazzName}Query query);
}