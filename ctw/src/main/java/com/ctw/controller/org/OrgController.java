package com.ctw.controller.org;


import com.ctw.domain.common.PageResult;
import com.ctw.domain.common.Result;
import com.ctw.domain.common.VEasyUiTree;
import com.ctw.domain.user.User;
import com.ctw.exception.BusinessException;
import com.ctw.utils.Functions;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctw.domain.org.Org;
import com.ctw.domain.org.OrgQuery;
import com.ctw.service.org.IOrgService;


import java.util.List;

@Controller
@RequestMapping(value = "/org")
public class OrgController {

    @Autowired
    private IOrgService iOrgService;
    @RequiresPermissions("org:view")
    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return "org/orgList";
    }

    @RequestMapping("/create")
    @ResponseBody
    public Result create(Org org) {
        try {
            return new Result(true, "创建成功", iOrgService.create(org));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "创建失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Org org) {
        try {
            return new Result(true, "更新成功", iOrgService.update(org));
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
            return new Result(true, "删除成功", iOrgService.deleteById(Integer.parseInt(id)));
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
            return new Result(true, "删除成功", iOrgService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Org getById(@PathVariable String id) {
        return iOrgService.getById(Integer.parseInt(id));
    }

    @RequestMapping("/findList")
    @ResponseBody
    public List<Org> findList(OrgQuery query) {
        query.setIsValid(null);
        return iOrgService.findList(query);
    }

    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<Org> findPage(OrgQuery query) {
        query.setIsValid(null);
        return iOrgService.findPage(query);
    }

    /**
     * 主要是根据partentId findPage脚本存在差役
     *
     * @param query
     * @return
     */

    @ResponseBody
    @RequestMapping("/findByPid")
    public PageResult<Org> findByPid(OrgQuery query) throws Exception {
        if (null == query.getPartentId()) {
            query.setPartentId(1);
        }
        return iOrgService.findByPartentId(query);
    }

    /**
     * 查询组织树结构
     *
     * @param query
     * @return
     * @throws Exception
     */
    @RequestMapping("/findOrgTree")
    @ResponseBody
    public List<VEasyUiTree> findOrgTree(OrgQuery query) throws Exception {
        List<VEasyUiTree> vEasyUiTreeList;
        User user = Functions.getUserInfo();
        //拥有该权限的用户 可以查看所有的组织，用户等信息
        query.setIsValid(null);
        if (null == query.getPartentId() || 0 == query.getPartentId()) {
            query.setPartentId(1);
        }
        vEasyUiTreeList = iOrgService.findOrgTree(query);
        return vEasyUiTreeList;
    }
    /**
     * 查询组织树结构
     *
     * @param query
     * @return
     * @throws Exception
     */
    @RequestMapping("/findOrgTreeByRoleId")
    @ResponseBody
    public List<VEasyUiTree> findOrgTreeByRoleId(OrgQuery query) throws Exception {
        int num=0;
        List<VEasyUiTree> vEasyUiTreeList;
        //拥有该权限的用户 可以查看所有的组织，用户等信息
        query.setIsValid(null);
        if (null == query.getPartentId() || 0 == query.getPartentId()) {
            query.setPartentId(1);
        }
        vEasyUiTreeList = iOrgService.findOrgTreeByRoleId(query);
        return vEasyUiTreeList;
    }
}