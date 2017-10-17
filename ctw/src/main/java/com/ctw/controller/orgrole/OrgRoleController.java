package com.ctw.controller.orgrole;


import com.ctw.domain.common.PageResult;
import com.ctw.domain.common.Result;
import com.ctw.domain.org.Org;
import com.ctw.domain.org.OrgQuery;
import com.ctw.exception.BusinessException;
import com.ctw.service.org.IOrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctw.domain.orgrole.OrgRole;
import com.ctw.domain.orgrole.OrgRoleQuery;
import com.ctw.service.orgrole.IOrgRoleService;


import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/orgRole")
public class OrgRoleController {

    @Autowired
    private IOrgRoleService iOrgRoleService;
    @Autowired
    private IOrgService iOrgService;

    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return "orgrole/orgRoleList";
    }

    @RequestMapping("/create")
    @ResponseBody
    public Result create(OrgRole orgRole) {
        try {
            int num = 0;
            //查询是否存在子节点
            OrgQuery orgQuery = new OrgQuery();
            orgQuery.setPartentId(orgRole.getOrgId());
            List<Org> orgList = iOrgService.findList(orgQuery);
            if (orgList.size() > 0) {
                for (Org org : orgList) {
                    orgRole.setOrgId(org.getId());
                    num+= iOrgRoleService.create(orgRole);
                }

            }
            return new Result(true, "创建成功", null);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "创建失败");
        }
    }

    /**
     * 组织授权
     *
     * @param ids     组织id集合
     * @param orgRole
     * @return
     */
    @RequestMapping("/batchCreate")
    @ResponseBody
    public Result batchCreate(String[] ids, OrgRole orgRole) {
        try {
            int num = 0;
            List<String> idsTemp = Arrays.asList(ids);
            for (String orgId : idsTemp) {
                Integer orgIdTemp = Integer.parseInt(orgId);
                //判断组织ID是否已经存在关联
                OrgRoleQuery orgRoleQuery = new OrgRoleQuery();
                orgRoleQuery.setOrgId(orgIdTemp);
                orgRoleQuery.setRoleId(orgRole.getRoleId());
                List<OrgRole> list = iOrgRoleService.findList(orgRoleQuery);
                orgRole.setOrgId(orgIdTemp);
                int count = list == null ? 0 : list.size();
                if (count <= 0)
                    num += iOrgRoleService.create(orgRole);
            }
            return new Result(true, "共计：" + num + "条组织授权成功！", num);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "创建失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(OrgRole orgRole) {
        try {
            return new Result(true, "更新成功", iOrgRoleService.update(orgRole));
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
            return new Result(true, "删除成功", iOrgRoleService.deleteById(Integer.parseInt(id)));
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
            return new Result(true, "删除成功", iOrgRoleService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 根据组织ID 角色ID 删除组织权限关系
     * @param ids
     * @return
     */
    @RequestMapping("/batchDeleteByRoleId")
    @ResponseBody
    public Result batchDeleteByRoleId(String[] ids,OrgRole orgRole) {
        try {
            int num=0;
            if(ids.length>0){
                List<String> orgIdList=Arrays.asList(ids);
                for(String orgIdTemp:orgIdList){
                    orgRole.setOrgId(Integer.parseInt(orgIdTemp));
                    iOrgRoleService.deleteByOrgIdRId(orgRole);
                }
            }
            return new Result(true, "删除成功",num );
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/getById/{id}")
    @ResponseBody
    public OrgRole getById(@PathVariable String id) {
        return iOrgRoleService.getById(Integer.parseInt(id));
    }

    @RequestMapping("/findList")
    @ResponseBody
    public List<OrgRole> findList(OrgRoleQuery query) {
        return iOrgRoleService.findList(query);
    }

    @RequestMapping("/findListByRoleId")
    @ResponseBody
    public List<OrgRole> findListByRoleId(OrgRoleQuery query) {
        return iOrgRoleService.findListByRoleId(query);
    }

    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<OrgRole> findPage(OrgRoleQuery query) {
        return iOrgRoleService.findPage(query);
    }


    @ResponseBody
    @RequestMapping(value = "/deleteByOrgIdRId/{orgId}/{rId}", method = RequestMethod.POST)
    public Result deleteByOrgIdRId(@PathVariable("orgId") String orgId, @PathVariable("rId") String rId) {
        OrgRole orgRole = new OrgRole();
        orgRole.setOrgId(Integer.parseInt(orgId));
        orgRole.setRoleId(Integer.parseInt(rId));
        try {
            return new Result(true, "删除成功", iOrgRoleService.deleteByOrgIdRId(orgRole));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }


}