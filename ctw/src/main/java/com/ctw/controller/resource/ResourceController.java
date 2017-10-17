package com.ctw.controller.resource;


import com.ctw.domain.common.PageResult;
import com.ctw.domain.common.Result;
import com.ctw.domain.common.VEasyUiTree;
import com.ctw.domain.resource.ResourceQuery;
import com.ctw.exception.BusinessException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctw.domain.resource.ResourceVo;
import com.ctw.service.resource.IResourceService;


import java.util.List;

@Controller
@RequestMapping(value = "/resource")
public class ResourceController {

    @Autowired
    private IResourceService iResourceService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("resource:view")
    public String mainPage() {
        return "resource/resourceList";
    }

    @RequestMapping("/create")
    @ResponseBody
    public Result create(ResourceVo resourceVo) {
        try {
            return new Result(true, "创建成功",  iResourceService.create(resourceVo));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "创建失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(ResourceVo resourceVo) {
        try {
            return new Result(true, "更新成功", iResourceService.update(resourceVo));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "更新失败");
        }
    }


    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result deleteById(@PathVariable("id") String id) {
        try {
            return new Result(true, "删除成功",iResourceService.deleteById(Integer.parseInt(id)));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Result batchDelete(String[] ids) {
        try {
            return new Result(true, "删除成功",iResourceService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/getById/{id}")
    @ResponseBody
    public ResourceVo getById(@PathVariable String id) {
        return iResourceService.getById(Integer.parseInt(id));
    }

    @RequestMapping("/findList")
    @ResponseBody
    public List<ResourceVo> findList(ResourceQuery query) {
        return iResourceService.findList(query);
    }

    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<ResourceVo> findPage(ResourceQuery query) {
        return iResourceService.findPage(query);
    }
  @RequestMapping("/findAllPage")
    @ResponseBody
    public PageResult<ResourceVo> findAllPage(ResourceQuery query) {
        return iResourceService.findAllPage(query);
    }

    /**
     * 根据条件查询资源数字
     *
     * @param query
     * @return
     * @throws Exception
     */
    @RequestMapping("/findResourceTree")
    @ResponseBody
    public List<VEasyUiTree> findResourceTree(ResourceQuery query) throws Exception {
        return iResourceService.findResourceTree(query);
    }


}