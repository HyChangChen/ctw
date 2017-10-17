<#assign clazzNameLower = clazzName?uncap_first>
package ${basepackage}.controller.${moduleName};
        import com.ctw.plugins.aop.Log;
        import com.ctw.plugins.aop.SystemControllerLog;
        import org.apache.shiro.authz.annotation.RequiresPermissions;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import com.ctw.plugins.log.LogMessageObject;
        import com.ctw.utils.LogUitls;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.common.Result;
import com.ctw.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
        import org.apache.commons.lang3.StringUtils;

import ${basepackage}.domain.${moduleName}.${clazzName};
import ${basepackage}.domain.${moduleName}.${clazzName}Query;
import ${basepackage}.service.${moduleName}.I${clazzName}Service;


import java.util.List;

@Controller
@RequestMapping(value = "/${clazzNameLower}")
public class ${clazzName}Controller {
    private static final Logger logger = LoggerFactory.getLogger(${clazzName}Controller.class);

    @Autowired
    private I${clazzName}Service i${clazzName}Service;
@SystemControllerLog(description = "/list")
@Log(message="查看列表")
@RequiresPermissions("${clazzNameLower}:view")
    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return "/${moduleName}/${pageName}";
    }
    @SystemControllerLog(description = "/save")
    @Log(message="创建。{0}")
    @RequiresPermissions("${clazzNameLower}:save")
    @RequestMapping("/save")
    @ResponseBody
    public Result create(${clazzName} ${clazzNameLower}) {
        try {


            if(StringUtils.isBlank(${clazzNameLower}.get<#list table.compositeIdColumns as column>${column.columnName}</#list>())){
                i${clazzName}Service.create(${clazzNameLower});
            }else{
                i${clazzName}Service.update(${clazzNameLower});
            }
        LogUitls.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{"创建成功"}));
            return new Result(true, "保存成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("保存失败",e);
            return new Result(false, "保存失败");
        }
    }

@SystemControllerLog(description = "/delete")
@Log(message="删除了编号{id}")
@RequiresPermissions("${clazzNameLower}:delete")
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result deleteById(@PathVariable("id") String id) {
        try {
            return new Result(true, "删除成功",i${clazzName}Service.deleteById(Integer.parseInt(id)));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("删除失败",e);
            return new Result(false, "删除失败");
        }
    }
@SystemControllerLog(description = "/batchDelete")
@Log(message="批量删除了编号。{id}")
@RequiresPermissions("${clazzNameLower}:batchDelete")
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Result batchDelete(String[] ids) {
        try {
            return new Result(true, "删除成功",i${clazzName}Service.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("删除失败",e);
            return new Result(false, "删除失败");
        }
    }
@SystemControllerLog(description = "/list")
@Log(message="查看{id}的详情")
@RequiresPermissions("${clazzNameLower}:view")
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public ${clazzName} getById(@PathVariable String id) {
        return i${clazzName}Service.getById(Integer.parseInt(id));
    }
@SystemControllerLog(description = "/list")
@Log(message="查看列表")
@RequiresPermissions("${clazzNameLower}:view")
    @RequestMapping("/findList")
    @ResponseBody
    public List<${clazzName}> findList(${clazzName}Query query) {
        return i${clazzName}Service.findList(query);
    }
@SystemControllerLog(description = "/list")
@Log(message="查看列表")
@RequiresPermissions("${clazzNameLower}:view")
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<${clazzName}> findPage(${clazzName}Query query) {
        return i${clazzName}Service.findPage(query);
    }


}