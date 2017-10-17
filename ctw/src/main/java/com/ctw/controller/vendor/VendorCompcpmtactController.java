package com.ctw.controller.vendor;

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

import com.ctw.domain.vendor.VendorCompcpmtact;
import com.ctw.domain.vendor.VendorCompcpmtactQuery;
import com.ctw.service.vendor.IVendorCompcpmtactService;


import java.util.List;

@Controller
@RequestMapping(value = "/vendorCompcpmtact")
public class VendorCompcpmtactController {
    private static final Logger logger = LoggerFactory.getLogger(VendorCompcpmtactController.class);

    @Autowired
    private IVendorCompcpmtactService iVendorCompcpmtactService;

    //@SystemControllerLog(description = "/list")
   // @Log(message = "查看列表")
    @RequiresPermissions("vendorCompcpmtact:view")
    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return "/vendor/vendorCompcpmtactList";
    }

    //@SystemControllerLog(description = "/save")
    //@Log(message="创建。{0}")
    @RequiresPermissions("vendorCompcpmtact:save")
    @RequestMapping("/save")
    @ResponseBody
    public Result create(VendorCompcpmtact vendorCompcpmtact) {
        try {


            if (StringUtils.isBlank(vendorCompcpmtact.getId())) {
                iVendorCompcpmtactService.create(vendorCompcpmtact);
            } else {
                iVendorCompcpmtactService.update(vendorCompcpmtact);
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
   // @Log(message = "删除了编号{id}")
    @RequiresPermissions("vendorCompcpmtact:delete")
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result deleteById(@PathVariable("id") String id) {
        try {
            return new Result(true, "删除成功", iVendorCompcpmtactService.deleteById(Integer.parseInt(id)));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("删除失败", e);
            return new Result(false, "删除失败");
        }
    }

  //  @SystemControllerLog(description = "/batchDelete")
   // @Log(message = "批量删除了编号。{id}")
    @RequiresPermissions("vendorCompcpmtact:batchDelete")
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Result batchDelete(String[] ids) {
        try {
            return new Result(true, "删除成功", iVendorCompcpmtactService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("删除失败", e);
            return new Result(false, "删除失败");
        }
    }

   // @SystemControllerLog(description = "/list")
   // @Log(message = "查看{id}的详情")
    @RequiresPermissions("vendorCompcpmtact:view")
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public VendorCompcpmtact getById(@PathVariable String id) {
        return iVendorCompcpmtactService.getById(Integer.parseInt(id));
    }

    //@SystemControllerLog(description = "/list")
  //  @Log(message = "查看列表")
    @RequiresPermissions("vendorCompcpmtact:view")
    @RequestMapping("/findList")
    @ResponseBody
    public List<VendorCompcpmtact> findList(VendorCompcpmtactQuery query) {
        return iVendorCompcpmtactService.findList(query);
    }

    //@SystemControllerLog(description = "/list")
  //  @Log(message = "查看列表")
    @RequiresPermissions("vendorCompcpmtact:view")
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<VendorCompcpmtact> findPage(VendorCompcpmtactQuery query) {
        return iVendorCompcpmtactService.findPage(query);
    }

    @RequestMapping(value = "/aj_getFtlView", method = RequestMethod.POST)
    public String getFtlView() {
        return "/vendor/vendorCompcpmtactList";
    }


}