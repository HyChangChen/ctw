package com.ctw.controller.role;


import com.ctw.domain.common.PageResult;
import com.ctw.domain.common.Result;
import com.ctw.exception.BusinessException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctw.domain.role.Role;
import com.ctw.domain.role.RoleQuery;
import com.ctw.service.role.IRoleService;


import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("role:view")
    public String mainPage() {
        return "role/roleList";
    }

    @RequestMapping("/create")
    @ResponseBody
    public Result create(Role role) {
        try {
            return new Result(true, "创建成功", iRoleService.create(role));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "创建失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Role role) {
        try {
            return new Result(true, "更新成功", iRoleService.update(role));
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
            return new Result(true, "删除成功", iRoleService.deleteById(Integer.parseInt(id)));
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
            return new Result(true, "删除成功", iRoleService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Role getById(@PathVariable String id) {
        return iRoleService.getById(Integer.parseInt(id));
    }

    @RequestMapping("/findList")
    @ResponseBody
    public List<Role> findList(RoleQuery query) {
        return iRoleService.findList(query);
    }

    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<Role> findPage(RoleQuery query) {
       // query.setIsValid(null);
        return iRoleService.findPage(query);
    }

    @RequestMapping(value = "/findHaveListPage", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<Role> findHaveListPage(RoleQuery query) throws Exception {
        return iRoleService.findHaveListPage(query);
    }

    @RequestMapping(value = "/getCountByRole", method = RequestMethod.POST)
    @ResponseBody
    public Result getCountByRole(String[] ids) throws Exception {
        if (ids.length < 0) {
            return new Result(false, "请选择数据", null);
        }
        boolean bak=false;
        int num;
        String msg="";
        try {
            num = iRoleService.getCountByRole(ids);
            if(num>0){
                msg="您选择的角色已被其他资源使用！请进入相应界面进行角色关系解除";
            }else{
                bak=true;
            }
            return new Result(bak, msg, null);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

}