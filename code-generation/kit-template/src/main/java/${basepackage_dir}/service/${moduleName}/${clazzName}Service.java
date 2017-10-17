<#assign clazzNameLower = clazzName?uncap_first>
package ${basepackage}.service.${moduleName};

import com.gzkit.common.exception.BusinessException;
import ${basepackage}.domain.${moduleName}.${clazzName};
import com.gzkit.common.domain.PageResult;
import ${basepackage}.domain.${moduleName}.${clazzName}Query;

import java.util.List;

public interface ${clazzName}Service {
    int create(${clazzName} ${clazzNameLower});
    int deleteById(String id);
    int update(${clazzName} ${clazzNameLower});
    ${clazzName} getById(String id);
    List<${clazzName}> findList(${clazzName}Query query);
    PageResult<${clazzName}> findPage(${clazzName}Query query);
}