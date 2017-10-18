package com.ctw.controller.loginfo;

import com.ctw.plugins.aop.Log;
import com.ctw.plugins.aop.SystemControllerLog;
import com.ctw.plugins.log.LogMessageObject;
import com.ctw.utils.LogUitls;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ctw.domain.loginfo.LogInfo;
import com.ctw.domain.loginfo.LogInfoQuery;
import com.ctw.service.loginfo.ILogInfoService;


import java.util.List;

@Controller
@RequestMapping(value = "/logInfo")
public class LogInfoController {
    private static final Logger logger = LoggerFactory.getLogger(LogInfoController.class);

    @Autowired
    private ILogInfoService iLogInfoService;

    //@SystemControllerLog(description = "/list")
    //@Log(message = "查看列表")
    @RequiresPermissions("logInfo:view")
    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return "/loginfo/logInfoList";
    }

    //@SystemControllerLog(description = "/save")
    //@Log(message = "创建。{0}")
    @RequiresPermissions("logInfo:save")
    @RequestMapping("/save")
    @ResponseBody
    public Result create(LogInfo logInfo) {
        try {


            if (StringUtils.isBlank(logInfo.getId().toString())) {
                iLogInfoService.create(logInfo);
            } else {
                iLogInfoService.update(logInfo);
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

    //@SystemControllerLog(description = "/delete")
    //@Log(message = "删除了编号{id}")
    @RequiresPermissions("logInfo:delete")
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result deleteById(@PathVariable("id") String id) {
        try {
            return new Result(true, "删除成功", iLogInfoService.deleteById(Integer.parseInt(id)));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("删除失败", e);
            return new Result(false, "删除失败");
        }
    }

    //@SystemControllerLog(description = "/batchDelete")
    //@Log(message = "批量删除了编号。{id}")
    @RequiresPermissions("logInfo:batchDelete")
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Result batchDelete(String[] ids) {
        try {
            return new Result(true, "删除成功", iLogInfoService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("删除失败", e);
            return new Result(false, "删除失败");
        }
    }

    //@SystemControllerLog(description = "/list")
    //@Log(message = "查看{id}的详情")
    @RequiresPermissions("logInfo:view")
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public LogInfo getById(@PathVariable String id) {
        return iLogInfoService.getById(Integer.parseInt(id));
    }

    //@SystemControllerLog(description = "/list")
    //@Log(message = "查看列表")
    @RequiresPermissions("logInfo:view")
    @RequestMapping("/findList")
    @ResponseBody
    public List<LogInfo> findList(LogInfoQuery query) {
        return iLogInfoService.findList(query);
    }

    //@SystemControllerLog(description = "/list")
    //@Log(message = "查看列表")
    @RequiresPermissions("logInfo:view")
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<LogInfo> findPage(LogInfoQuery query) {
        return iLogInfoService.findPage(query);
    }


}