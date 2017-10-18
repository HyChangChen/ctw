package com.ctw.controller.user;


import com.ctw.domain.common.PageResult;
import com.ctw.domain.common.Result;
import com.ctw.domain.common.Select2Type;
import com.ctw.domain.common.ValideForm;
import com.ctw.domain.org.OrgQuery;
import com.ctw.exception.BusinessException;
import com.ctw.plugins.aop.Log;
import com.ctw.plugins.aop.SystemControllerLog;
import com.ctw.service.org.IOrgService;
import com.ctw.utils.Functions;
import com.ctw.utils.PasswordHelper;
import com.ctw.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctw.domain.user.User;
import com.ctw.domain.user.UserQuery;
import com.ctw.service.user.IUserService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrgService iOrgService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("user:view")
    public String mainPage() {
        return "user/userList";
    }

    /****
     * 新增用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/create")
    @RequiresPermissions("user:create")
    @ResponseBody
    //@Log(message = "创建{0}用户")
    //@SystemControllerLog(description = "创建用户！")
    public Result create(User user) {
        try {
            return new Result(true, "创建成功", iUserService.create(user));

        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "创建失败");
        }
    }

    @RequiresPermissions("user:edit")
    @RequestMapping("/update")
    @ResponseBody
    public Result update(User user) {
        user.setPassWord(null);
        try {
            return new Result(true, "更新成功", iUserService.update(user));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "更新失败");
        }
    }


    @RequestMapping("/delete/{id}")
    @RequiresPermissions("user:delete")
    @ResponseBody
    public Result deleteById(@PathVariable("id") String id) {
        try {
            return new Result(true, "删除成功", iUserService.deleteById(Integer.parseInt(id)));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    @RequiresPermissions("user:beatchDelete")
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Result batchDelete(String[] ids) {
        try {
            return new Result(true, "删除成功", iUserService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/getById/{id}")
    @ResponseBody
    public User getById(@PathVariable String id) {
        return iUserService.getById(Integer.parseInt(id));
    }

    @RequiresPermissions("user:view")
    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    @ResponseBody
    public List<User> findList(UserQuery query) throws Exception {
        //作为管理用户，应该将所有的用户都查询出来，因为默认了查询有效用户，故需要将isValid 设置为空
        User user = Functions.getUserInfo();
       // query.setIsValid(null);
        if (null == query.getOrgId()) {
            query.setOrgId(user.getOrgId());
        }

        return iUserService.findList(query);
    }

    @RequiresPermissions("user:view")
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<User> findPage(UserQuery query) {
        return iUserService.findPage(query);
    }

    /***
     * 查找未被角色直关联的用户
     *
     * @param query
     * @return
     */

    @RequestMapping("/findUserNotLinkRolePage")
    @ResponseBody
    public PageResult<User> findUserNotLinkRolePage(UserQuery query) {
        return iUserService.findUserNotLinkRolePage(query);
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    @ResponseBody
    public Object findUser(UserQuery query, String param) throws Exception {
        query.setLoginName(param);
        int i = iUserService.findList(query).size();
        ValideForm valideForm = new ValideForm();
        if (i > 0) {
            valideForm.setStatus("n");
            valideForm.setInfo("抱歉！此用户名已存在！");
        } else {
            valideForm.setStatus("y");
            valideForm.setInfo("恭喜！此用户名可以使用！");
        }
        return valideForm;
    }

    /**
     * 用户自行修改密码
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/updatePassWord", method = RequestMethod.POST)
    @ResponseBody
    public Result updatePassWord(User user) {
        boolean Bak = false;
        String msg = "";
        try {
            int i = iUserService.updatePassWord(user);
            if (i > 0) {
                Bak = true;
                msg = "密码修改成功";
            } else {
                msg = "密码修改失败";
            }
            return new Result(Bak, msg, i);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "更新失败");
        }
    }

    /****
     * 密码重置
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/restPwd", method = RequestMethod.POST)
    @ResponseBody
    public Result restPwd(User user) {
        user = iUserService.getById(user.getId());
        String newPwd = StringUtils.getStringRandom(8);
        user.setPassWord(newPwd);
        try {
            return new Result(true, "密码重置成功：请记住密码：" + newPwd, iUserService.update(user));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "更新失败");
        }
    }

    /***
     * 通过组织id 列表查找组织下是否存在用户
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/getCountUserByOrg", method = RequestMethod.POST)
    @ResponseBody
    public Result getCountUserByOrg(String[] ids) {
        try {
            List<String> idsTemp = Arrays.asList(ids);
            List<String> bakIds = new ArrayList<String>();
            Integer num = 0;
            String msg = "";
            boolean bak = true;
            for (String id : idsTemp) {
                int numT = iUserService.getCountUserByOrg(Integer.parseInt(id));
                OrgQuery orgQuery = new OrgQuery();
                orgQuery.setIsValid(null);
                orgQuery.setPartentId(Integer.parseInt(id));
                int numB = iOrgService.findList(orgQuery).size();
                if (numT > 0 || numB > 1) {
                    bakIds.add(id);
                    num++;
                }
            }
            if (num > 0) {
                bak = false;
                msg = "组织下存在用户或者组织下存在其他组织";
            }
            return new Result(bak, "温馨提示", msg);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "获取数据异常");
        }
    }

    /**
     * 搜索用户 下拉
     *
     * @return
     */
    @RequestMapping(value = "/findAllUserList", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<Select2Type> findAllUserList(UserQuery userQuery) {
        return iUserService.findAllUserList(userQuery);
    }


}