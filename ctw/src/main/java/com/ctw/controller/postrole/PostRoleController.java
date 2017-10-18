package com.ctw.controller.postrole;


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

import com.ctw.domain.postrole.PostRole;
import com.ctw.domain.postrole.PostRoleQuery;
import com.ctw.service.postrole.IPostRoleService;


import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/postRole")
public class PostRoleController {

    @Autowired
    private IPostRoleService iPostRoleService;

    /* @RequiresPermissions("resource:view")*/
    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return "postrole/postRoleList";
    }

    @RequestMapping("/create")
    @ResponseBody
    public Result create(PostRole postRole) {
        try {
            return new Result(true, "创建成功", iPostRoleService.create(postRole));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "创建失败");
        }
    }

    /**
     * @param postRole
     * @return
     */
    @RequestMapping("/batchCreate")
    @ResponseBody
    public Result batchCreate(String[] postIds, PostRole postRole) {
        try {
            int num = 0;
            if (postIds.length > 0) {
                List<String> postIdsTemp = Arrays.asList(postIds);
                for (String pId : postIdsTemp) {
                    Integer inPid = Integer.parseInt(pId);
                    PostRoleQuery postRoleQuery = new PostRoleQuery();
                    postRoleQuery.setRoleId(postRole.getRoleId());
                    postRoleQuery.setPsotId(inPid);
                    List<PostRole> list = iPostRoleService.findList(postRoleQuery);
                    if (list.size() <= 0) {
                        postRole.setPsotId(inPid);
                        num += iPostRoleService.create(postRole);
                    }
                }
            }
            return new Result(num != 0, "岗位授权成功", "共计：" + num + "岗位授权成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "创建失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(PostRole postRole) {
        try {
            return new Result(true, "更新成功", iPostRoleService.update(postRole));
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
            return new Result(true, "删除成功", iPostRoleService.deleteById(Integer.parseInt(id)));
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
            return new Result(true, "删除成功", iPostRoleService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
    @ResponseBody
    @RequestMapping(value = "/deleteByOrgIdRId/{postId}/{roleId}", method = RequestMethod.POST)
    public Result deleteByOrgIdRId(@PathVariable("postId") Integer postId, @PathVariable("roleId") Integer roleId) {
        PostRole postRole=new PostRole();
        postRole.setPsotId(postId);
        postRole.setRoleId(roleId);

        try {
            return new Result(true, "删除成功", iPostRoleService.deleteByPostIdRId(postRole));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public PostRole getById(@PathVariable String id) {
        return iPostRoleService.getById(Integer.parseInt(id));
    }

    @RequestMapping("/findList")
    @ResponseBody
    public List<PostRole> findList(PostRoleQuery query) {
        return iPostRoleService.findList(query);
    }

    @RequestMapping("/findListByRoleId")
    @ResponseBody
    public List<PostRole> findListByRoleId(PostRoleQuery query) {
        return iPostRoleService.findListByRoleId(query);
    }

    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<PostRole> findPage(PostRoleQuery query) {
        return iPostRoleService.findPage(query);
    }


}