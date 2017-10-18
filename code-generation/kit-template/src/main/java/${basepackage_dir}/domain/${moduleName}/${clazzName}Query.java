<#assign clazzNameLower = clazzName?uncap_first>
package ${basepackage}.domain.${moduleName};

import com.ctw.domain.common.PageQuery;

public class ${clazzName}Query extends PageQuery {
    private static final long serialVersionUID = -3573093602719090148L;

    <#list table.columns as column>
    private ${column.javaType} ${column.columnNameLower};//${column.columnAlias}
    </#list>

    /**
     * 自定义属性
     */
    <#list table.columns as column>
    <#if column.isDateTimeColumn>
    private String ${column.columnNameLower}Range;//${column.columnAlias}范围
    </#if>
    </#list>

    <#list table.columns as column>
    public ${column.javaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }

    public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }

    </#list>
    <#list table.columns as column>
    <#if column.isDateTimeColumn>
    public String get${column.columnName}Range() {
        return this.${column.columnNameLower}Range;
    }

    public void set${column.columnName}Range(String ${column.columnNameLower}Range) {
        this.${column.columnNameLower}Range = ${column.columnNameLower}Range;
    }

    </#if>
    </#list>
}