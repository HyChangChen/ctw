package com.ctw.controller.post;


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

import com.ctw.domain.post.Post;
import com.ctw.domain.post.PostQuery;
import com.ctw.service.post.IPostService;


import java.util.List;

@Controller
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private IPostService iPostService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("post:view")
    public String mainPage() {
        return "post/postList";
    }

    @RequestMapping("/create")
    @ResponseBody
    public Result create(Post post) {
        try {
            return new Result(true, "创建成功", iPostService.create(post));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "创建失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Post post) {
        try {
            return new Result(true, "更新成功", iPostService.update(post));
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
            return new Result(true, "删除成功", iPostService.deleteById(Integer.parseInt(id)));
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
            return new Result(true, "删除成功", iPostService.batchDelete(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Post getById(@PathVariable String id) {
        return iPostService.getById(Integer.parseInt(id));
    }

    @RequestMapping("/findList")
    @ResponseBody
    public List<Post> findList(PostQuery query) {
        return iPostService.findList(query);
    }

    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult<Post> findPage(PostQuery query) {
        query.setIsValid(null);
        return iPostService.findPage(query);
    }

    /***
     * 角色ID关联岗位职责时候搜索未关联的岗位
     *
     * @param query
     * @return
     */
    @RequestMapping("/seachNotLinkPost")
    @ResponseBody
    public PageResult<Post> seachNotLinkPost(PostQuery query) {
        return iPostService.seachNotLinkPost(query);
    }


}