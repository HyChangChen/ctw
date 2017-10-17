<#include "/macro.include"/>
<#assign classNameLower = clazzName?uncap_first>
package ${basepackage}.domain.${moduleName};

/**
 * ${tableName}
 */
public class ${clazzName}Entity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

    <#list table.columns as column>
	private ${column.javaType} ${column.columnNameLower};//${column.columnAlias} db_column: ${column.sqlName}
    </#list>

	<#list table.columns as column>
	public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}

	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}

</#list>
}
