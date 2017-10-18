<#assign clazzNameLower = clazzName?uncap_first>
package ${basepackage}.domain.${moduleName};

import java.util.ArrayList;
import java.util.List;



/**
 * ${clazzName}Entity<->${clazzName}转换工具类
 *
 */
public class ${clazzName}VoConvert {

    public static ${clazzName} toVo(${clazzName}Entity entity) {
        if (entity == null) {
            return null;
        }
        ${clazzName} vo = new ${clazzName}();
    <#list table.columns as column>
        vo.set${column.columnName}(entity.get${column.columnName}());
    </#list>
        return vo;
    }

    public static ${clazzName}Entity toEntity(${clazzName} vo) {
        if (vo == null) {
            return null;
        }
        ${clazzName}Entity entity = new ${clazzName}Entity();
    <#list table.columns as column>
        entity.set${column.columnName}(vo.get${column.columnName}());
    </#list>
        return entity;
    }

    public static List<${clazzName}> toVoList(List<${clazzName}Entity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<${clazzName}> voList = new ArrayList<${clazzName}>();
        for (${clazzName}Entity entity : entityList) {
            voList.add(toVo(entity));
        }
        return voList;
    }

    public static List<${clazzName}Entity> toEntityList(List<${clazzName}> voList) {
        if (voList == null) {
            return null;
        }
        List<${clazzName}Entity> entityList = new ArrayList<${clazzName}Entity>();
        for (${clazzName} vo : voList) {
            entityList.add(toEntity(vo));
        }
        return entityList;
    }
}