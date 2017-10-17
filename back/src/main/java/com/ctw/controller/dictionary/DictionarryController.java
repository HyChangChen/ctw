package com.ctw.controller.dictionary;

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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang3.StringUtils;

import com.ctw.domain.dictionary.Dictionarry;
import com.ctw.domain.dictionary.DictionarryQuery;
import com.ctw.service.dictionary.IDictionarryService;


import java.util.List;

@Controller
@RequestMapping(value = "/dictionarry")
public class DictionarryController {
    private static final Logger logger = LoggerFactory.getLogger(DictionarryController.class);

    @Autowired
    private IDictionarryService iDictionarryService;

    //@SystemControllerLog(description = "/list")
    //@Log(message = "查看列表")
    @RequiresPermissions("dictionarry:view")
    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return "/dictionary/DictionarryList";
    }

    //@SystemControllerLog(description = "/save")
    //@Log(message="创建。{0}")
    @RequiresPermissions("dictionarry:save")
    @RequestMapping("/save")
    @ResponseBody
    public Result create(Dictionarry dictionarry) {
        try {
            if (StringUtils.isBlank(dictionarry.getId().toString())) {
                iDictionarryService.create(dictionarry);
            } else {
                iDictionarryService.update(dictionarry);
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
    //@Log(message="删除了编号{id}")
    @RequiresPermissions("dictionarry:delete")
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result deleteById(@PathVariable("id") String id) {
        try {
            int num = 0;
            return new Result(true, "删除成功", iDictionarryService.deleteById(Integer.parseInt(id)));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("删除失败", e);
            return new Result(false, "删除失败");
        }
    }

    //@SystemControllerLog(description = "/batchDelete")
    //@Log(message="批量删除了编号。{id}")
    @RequiresPermissions("dictionarry:batchDelete")
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Result batchDelete(String[] ids) {
        try {
            int num = 0;
            return new Result(true, "删除成功", iDictionarryService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("删除失败", e);
            return new Result(false, "删除失败");
        }
    }

    //@SystemControllerLog(description = "/list")
    //@Log(message="查看{id}的详情")
    @RequiresPermissions("dictionarry:view")
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Dictionarry getById(@PathVariable String id) {
        return iDictionarryService.getById(Integer.parseInt(id));
    }

    //@SystemControllerLog(description = "/list")
    //@Log(message="查看列表")
    @RequiresPermissions("dictionarry:view")
    @RequestMapping("/findList")
    @ResponseBody
    public List<Dictionarry> findList(DictionarryQuery query) {
        // System.out.println(query.toString());
        return iDictionarryService.findList(query);
    }

    //@SystemControllerLog(description = "/list")
    //@Log(message="查看列表")
    @RequiresPermissions("dictionarry:view")
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<Dictionarry> findPage(DictionarryQuery query) {
        return iDictionarryService.findPage(query);
    }


}