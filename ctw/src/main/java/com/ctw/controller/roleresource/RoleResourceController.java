package com.ctw.controller.roleresource;


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

import com.ctw.domain.roleresource.RoleResource;
import com.ctw.domain.roleresource.RoleResourceQuery;
import com.ctw.service.roleresource.IRoleResourceService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/roleResource")
public class RoleResourceController {

    @Autowired
    private IRoleResourceService iRoleResourceService;

    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return "roleresource/roleResourceList";
    }

    @RequestMapping("/create")
    @ResponseBody
    public Result create(String[] ids, RoleResource roleResource) {
        try {
            int num = 0;
            List<String> idsTemp = Arrays.asList(ids);
            for (String resourceId : idsTemp) {
                roleResource.setResourceId(Integer.parseInt(resourceId));
                RoleResourceQuery roleResourceQuery = new RoleResourceQuery();
                roleResourceQuery.setRoleId(roleResource.getRoleId());
                roleResourceQuery.setResourceId(Integer.parseInt(resourceId));
                //判断是否已经存在
                List<RoleResource> list = iRoleResourceService.findList(roleResourceQuery);
                int count = list == null ? 0 : list.size();
                if (count <= 0)
                    num += iRoleResourceService.create(roleResource);
            }
            return new Result(true, "共计：" + num + "条资源菜单授权成功！", num);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "创建失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(RoleResource roleResource) {
        try {
            return new Result(true, "更新成功", iRoleResourceService.update(roleResource));
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
            return new Result(true, "删除成功", iRoleResourceService.deleteById(Integer.parseInt(id)));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Result batchDelete(String[] ids, Integer roleId) {
        try {
            int num = 0;
            num = iRoleResourceService.batchDelete(ids, roleId);
            return new Result(true, "共计" + num + "条授权取消成功", num);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "条授权取消失败");
        }
    }

    @RequestMapping("/getById/{id}")
    @ResponseBody
    public RoleResource getById(@PathVariable String id) {
        return iRoleResourceService.getById(Integer.parseInt(id));
    }

    @RequestMapping("/findList")
    @ResponseBody
    public List<RoleResource> findList(RoleResourceQuery query) {
        return iRoleResourceService.findList(query);
    }

    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<RoleResource> findPage(RoleResourceQuery query) {
        return iRoleResourceService.findPage(query);
    }


}