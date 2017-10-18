<#assign clazzNameLower = clazzName?uncap_first>
package ${basepackage}.service.${moduleName}.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.${moduleName}.I${clazzName}Dao;
import ${basepackage}.domain.${moduleName}.${clazzName}Entity;
        import com.ctw.domain.common.PageResult;
import ${basepackage}.domain.${moduleName}.${clazzName};
import ${basepackage}.domain.${moduleName}.${clazzName}Query;
import ${basepackage}.domain.${moduleName}.${clazzName}VoConvert;
 import com.ctw.service.${moduleName}.I${clazzName}Service;
import com.ctw.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ${clazzName}ServiceImpl implements I${clazzName}Service {

    @Autowired
    private I${clazzName}Dao i${clazzName}Dao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(${clazzName} ${clazzNameLower}) {
        if (null == ${clazzNameLower}) {
            return 0;
        }
      /*  if(StringUtils.isNotBlank(${clazzNameLower}.getId().toString())){
            ${clazzNameLower}.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        EntityUtils.setCreateInfo(${clazzNameLower});
        ${clazzName}Entity ${clazzNameLower}Entity = ${clazzName}VoConvert.toEntity(${clazzNameLower});
        return i${clazzName}Dao.create(${clazzNameLower}Entity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(${clazzName} ${clazzNameLower}) {
        if(StringUtils.isEmpty(${clazzNameLower}.get${table.pkColumn.columnName}().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(${clazzNameLower});
        ${clazzName}Entity ${clazzNameLower}Entity = ${clazzName}VoConvert.toEntity(${clazzNameLower});
        return i${clazzName}Dao.update(${clazzNameLower}Entity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return i${clazzName}Dao.deleteById(id);
    }
    @Override
    public int batchDelete(String[] ids) {
        return i${clazzName}Dao.batchDelete(ids);
    }

    @Override
    public ${clazzName} getById(Integer id) {
        return ${clazzName}VoConvert.toVo(i${clazzName}Dao.getById(id));
    }

    @Override
    public List<${clazzName}> findList(${clazzName}Query query) {
        return i${clazzName}Dao.findList(query);
    }

    @Override
    public PageResult<${clazzName}> findPage(${clazzName}Query query) {
        return i${clazzName}Dao.findPage(query);
    }
}