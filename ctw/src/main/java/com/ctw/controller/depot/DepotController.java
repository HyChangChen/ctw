package com.ctw.controller.depot;


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

import com.ctw.domain.depot.Depot;
import com.ctw.domain.depot.DepotQuery;
import com.ctw.service.depot.IDepotService;


import java.util.List;

@Controller
@RequestMapping(value = "/depot")
public class DepotController {
    private static final Logger logger = LoggerFactory.getLogger(DepotController.class);

    @Autowired
    private IDepotService iDepotService;

    //@SystemControllerLog(description = "/list")
    //@Log(message = "查看列表仓库信息列表")
    @RequiresPermissions("depot:view")
    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return "/depot/depotList";
    }

    //@SystemControllerLog(description = "/save")
    //@Log(message = "创建仓库信息{0}")
    @RequiresPermissions("depot:save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result create(Depot depot) {
        try {
            if (StringUtils.isBlank(depot.getId() == null ? null : depot.getId().toString())) {
                iDepotService.create(depot);
            } else {
                iDepotService.update(depot);
            }
            LogUitls.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{depot.getDepotName()}));
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
    @RequiresPermissions("depot:delete")
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result deleteById(@PathVariable("id") String id) {
        try {
            return new Result(true, "删除成功", iDepotService.deleteById(Integer.parseInt(id)));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("删除失败", e);
            return new Result(false, "删除失败");
        }
    }

    //@SystemControllerLog(description = "/batchDelete")
    //@Log(message = "批量删除了编号。{id}")
    @RequiresPermissions("depot:batchDelete")
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Result batchDelete(String[] ids) {
        try {
            return new Result(true, "删除成功", iDepotService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("删除失败", e);
            return new Result(false, "删除失败");
        }
    }

    //@SystemControllerLog(description = "/list")
    //@Log(message = "查看{id}的详情")
    @RequiresPermissions("depot:view")
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Depot getById(@PathVariable String id) {
        return iDepotService.getById(Integer.parseInt(id));
    }

    //@SystemControllerLog(description = "/list")
    //@Log(message = "查看列表仓库信息列表")
    @RequiresPermissions("depot:view")
    @RequestMapping("/findList")
    @ResponseBody
    public List<Depot> findList(DepotQuery query) {
        return iDepotService.findList(query);
    }

    //@SystemControllerLog(description = "/list")
    //@Log(message = "查看列表仓库信息列表")
    @RequiresPermissions("depot:view")
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<Depot> findPage(DepotQuery query) {
        return iDepotService.findPage(query);
    }


}