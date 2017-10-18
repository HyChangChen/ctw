<#assign clazzNameLower = clazzName?uncap_first>
package ${basepackage}.Dao.${moduleName};

import com.gzkit.common.exception.BusinessException;
import ${basepackage}.domain.${moduleName}.${clazzName};
import com.gzkit.common.domain.PageResult;
import ${basepackage}.domain.${moduleName}.${clazzName}Query;

import java.util.List;

public interface ${clazzName}Dao {
        int create(${clazzName}Entity entity);
        int update(${clazzName}Entity entity);
        int deleteById(String id);
        ${clazzName}Entity getById(String id);
        List<${clazzName}> findList(${clazzName}Query query);
        PageResult<${clazzName}> findPage(${clazzName}Query query);
}