<#include "/macro.include"/>
<#assign classNameLower = clazzName?uncap_first>
package ${basepackage}.domain.${moduleName};

public class ${clazzName} implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

    <#list table.columns as column>
	private ${column.javaType} ${column.columnNameLower};//${column.columnAlias}
    </#list>

	/**
	 * 自定义属性
	 */
	private String token;

	<#list table.columns as column>
	public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}

	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}

	</#list>
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}