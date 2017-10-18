<#assign clazzNameLower = clazzName?uncap_first>
package ${basepackage}.dao.${moduleName}.impl;
        import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
        import com.ctw.dao.AbstractBaseDao;
        import com.ctw.dao.${moduleName}.I${clazzName}Dao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;
        import com.ctw.domain.common.PageResult;
import ${basepackage}.domain.${moduleName}.${clazzName}Entity;

import ${basepackage}.domain.${moduleName}.${clazzName};
import ${basepackage}.domain.${moduleName}.${clazzName}Query;
import ${basepackage}.domain.${moduleName}.${clazzName}VoConvert;

import java.util.List;

@Repository("i${clazzName}Dao")
public class ${clazzName}DaoImpl extends AbstractBaseDao<Integer, ${clazzName}Entity>implements I${clazzName}Dao{
private static final String NAMESPACE="${basepackage}.dao.${moduleName}.I${clazzName}Dao";

@Override
protected String getNamespace(){
        return NAMESPACE;
        }

private void rewriteSortColumns(${clazzName}Query query){

        }

@Override
public List<${clazzName}>findList(${clazzName}Query query){
        rewriteSortColumns(query);
        return selectList("findList",query);
        }

@Override
public PageResult<${clazzName}>findPage(${clazzName}Query query){
        rewriteSortColumns(query);
        return pageQuery("findList",query);
        }
@Override
public int create(${clazzName}Entity entity) {
        return super.create(entity);
        }
@Override
public int deleteById(Integer id) {
        return super.deleteById(id);
        }
@Override
public int update(${clazzName}Entity entity) {
        return super.update(entity);
        }
@Override
public ${clazzName}Entity getById(Integer id) {
        return super.getById(id);
        }
@Override
public int batchDelete(String[] ids) {
        int i = 0;
        List<String> idsTemp = Arrays.asList(ids);
            if (idsTemp.size() > 0) {
                for (String id : idsTemp) {
                    i += super.deleteById(Integer.parseInt(id));
                }
            }
        return i;
        }

        }