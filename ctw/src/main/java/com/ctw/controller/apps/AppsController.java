package com.ctw.controller.apps;

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

import com.ctw.domain.apps.Apps;
import com.ctw.domain.apps.AppsQuery;
import com.ctw.service.apps.IAppsService;


import java.util.List;

@Controller
@RequestMapping(value = "/apps")
public class AppsController {
    private static final Logger logger = LoggerFactory.getLogger(AppsController.class);

    @Autowired
    private IAppsService iAppsService;

    /*@SystemControllerLog(description = "/list")
    //@Log(message="查看列表")*/
    @RequiresPermissions("apps:view")
    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return "/apps/apps";
    }

    //@SystemControllerLog(description = "/save")
    //@Log(message = "创建。{0}")
    @RequiresPermissions("apps:save")
    @RequestMapping("/save")
    @ResponseBody
    public Result create(Apps apps) {
        try {
            if (StringUtils.isBlank(apps.getAppsid())) {
                iAppsService.create(apps);
            } else {
                iAppsService.update(apps);
            }
            LogUitls.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{"创建成功"}));
            return new Result(true, "保存成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("保存失败", e);
            return new Result(false, "保存失败");
        }
    }

    /*@SystemControllerLog(description = "/delete")
    //@Log(message="删除了编号{id}")*/
    @RequiresPermissions("apps:delete")
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result deleteById(@PathVariable("id") String id) {
        try {
            return new Result(true, "删除成功", iAppsService.deleteById(Integer.parseInt(id)));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("删除失败", e);
            return new Result(false, "删除失败");
        }
    }

    //@SystemControllerLog(description = "/batchDelete")
//@Log(message="批量删除了编号。{id}")
//@RequiresPermissions("apps:batchDelete")
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Result batchDelete(String[] ids) {
        try {
            return new Result(true, "删除成功", iAppsService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("删除失败", e);
            return new Result(false, "删除失败");
        }
    }

  /*  //@SystemControllerLog(description = "/list")
    //@Log(message = "查看{id}的详情")*/
    @RequiresPermissions("apps:view")
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Apps getById(@PathVariable String id) {
        return iAppsService.getById(Integer.parseInt(id));
    }

   /* //@SystemControllerLog(description = "/list")
    //@Log(message = "查看列表")*/
    @RequiresPermissions("apps:view")
    @RequestMapping("/findList")
    @ResponseBody
    public List<Apps> findList(AppsQuery query) {
        return iAppsService.findList(query);
    }

  /*  //@SystemControllerLog(description = "/list")
    //@Log(message = "查看列表")*/
    @RequiresPermissions("apps:view")
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<Apps> findPage(AppsQuery query) {
        return iAppsService.findPage(query);
    }


}