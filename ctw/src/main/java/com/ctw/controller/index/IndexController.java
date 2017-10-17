package com.ctw.controller.index;

import com.ctw.domain.user.User;
import com.ctw.plugins.aop.CurrentUser;
import com.ctw.plugins.aop.Log;
import com.ctw.plugins.aop.SystemControllerLog;
import com.ctw.plugins.log.LogMessageObject;
import com.ctw.utils.Functions;
import com.ctw.utils.LogUitls;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.8:55
 * @Vistion：1.0
 * @Remark： 登录成功以后
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    //@SystemControllerLog(description = "用户{0}成功登录系统，开始使用系统")
    public String index(@CurrentUser User loginUser) {
        return "redirect:/main";
    }
    @RequestMapping("/main")
   /* @RequiresPermissions("admin:*")*/
    //@Log(message="{0}登录了系统")
    public String indexMain(@CurrentUser User loginUser,Model model) {
       // Subject currentUser = SecurityUtils.getSubject();
        User user = Functions.getUserInfo();
        model.addAttribute("menu",user.getHtmlMenu());
        model.addAttribute("loginUser",user);
        LogUitls.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{user.getLoginName()}));
        return "main";
    }
    @RequestMapping("/test")
    public String test(@CurrentUser User loginUser) {
        return "test";
    }
}
