package com.ctw.controller.roleuser;


import com.ctw.domain.common.PageResult;
import com.ctw.domain.common.Result;
import com.ctw.exception.BusinessException;
import com.mysql.fabric.xmlrpc.base.Array;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctw.domain.roleuser.RoleUser;
import com.ctw.domain.roleuser.RoleUserQuery;
import com.ctw.service.roleuser.IRoleUserService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/roleUser")
public class RoleUserController {

    @Autowired
    private IRoleUserService iRoleUserService;

    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return "roleuser/roleUserList";
    }

    @RequestMapping("/create")
    @ResponseBody
    public Result create(RoleUser roleUser) {
        try {
            return new Result(true, "创建成功", iRoleUserService.create(roleUser));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "创建失败");
        }
    }


    /**
     * 批量新增
     *
     * @param roleUser
     * @return
     */
    @RequestMapping("/batchCreate")
    @ResponseBody
    public Result batchCreate(String[] uIds, RoleUser roleUser) {
        try {
            int num = 0;
            if (null != uIds) {
                List<String> uIdsTemp = Arrays.asList(uIds);
                for (String uId : uIdsTemp) {
                    roleUser.setUid(Integer.parseInt(uId));
                    num += iRoleUserService.create(roleUser);
                }
            }
            return new Result(num != 0, num == 0 ? "授权失败！" : "共计" + num + "授权成功！", null);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "创建失败");
        }
    }


    @RequestMapping("/update")
    @ResponseBody
    public Result update(RoleUser roleUser) {
        try {
            return new Result(true, "更新成功", iRoleUserService.update(roleUser));
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
            return new Result(true, "删除成功", iRoleUserService.deleteById(Integer.parseInt(id)));
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
            return new Result(true, "删除成功", iRoleUserService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/getById/{id}")
    @ResponseBody
    public RoleUser getById(@PathVariable String id) {
        return iRoleUserService.getById(Integer.parseInt(id));
    }

    @RequestMapping("/findList")
    @ResponseBody
    public List<RoleUser> findList(RoleUserQuery query) {
        return iRoleUserService.findList(query);
    }

    @RequestMapping("/findListByRoleId")
    @ResponseBody
    public PageResult<RoleUser> findListByRoleId(RoleUserQuery query) {
        return iRoleUserService.findListByRoleId(query);
    }

    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<RoleUser> findPage(RoleUserQuery query) {
        return iRoleUserService.findPage(query);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteByUIdRId/{uId}/{rId}", method = RequestMethod.POST)
    public boolean deleteByUIdRId(@PathVariable("uId") String uId, @PathVariable("rId") String rId) {
        RoleUser roleUser = new RoleUser();
        roleUser.setRoleId(Integer.parseInt(rId));
        roleUser.setUid(Integer.parseInt(uId));
        iRoleUserService.deleteByUIdRId(roleUser);
        return true;
    }


}