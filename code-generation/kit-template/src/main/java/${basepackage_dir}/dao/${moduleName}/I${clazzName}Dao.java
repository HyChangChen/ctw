<#assign clazzNameLower = clazzName?uncap_first>
package ${basepackage}.Dao.${moduleName};

import com.ctw.exception.BusinessException;
import com.ctw.dao.IBaseDao;
import ${basepackage}.domain.${moduleName}.${clazzName};
import ${basepackage}.domain.${moduleName}.${clazzName}Entity;
import com.ctw.domain.common.PageResult;
import ${basepackage}.domain.${moduleName}.${clazzName}Query;

import java.util.List;

public interface I${clazzName}Dao extends IBaseDao<Integer, ${clazzName}Entity> {
        int batchDelete(String[] ids);
        List<${clazzName}> findList(${clazzName}Query query);
        PageResult<${clazzName}> findPage(${clazzName}Query query);
}