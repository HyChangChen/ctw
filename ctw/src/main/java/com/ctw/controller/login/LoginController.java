package com.ctw.controller.login;

import com.ctw.domain.common.Result;
import com.ctw.plugins.aop.Log;
import com.ctw.plugins.aop.SystemControllerLog;
import com.ctw.plugins.log.LogMessageObject;
import com.ctw.utils.LogUitls;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/27.23:50
 * @Vistion：1.0
 * @Remark： 登录系统操作controller
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    private static final String LOGIN_PAGE = "login";
    private static final String LOGIN_DIALOG = "management/index/loginDialog";


    @RequestMapping(method = RequestMethod.GET)
    public String login(HttpServletRequest req, Model model) {
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
           // LogUitls.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{exceptionClassName}));
        }
        model.addAttribute("error", error);
        return LOGIN_PAGE;
    }

    @RequestMapping(method = {RequestMethod.GET}, params = "ajax=true")
    @ResponseBody
    public Result loginDialog2AJAX() {
        return loginDialog();
    }

    //@Log(message = "{0}登录超时。")
    @RequestMapping(method = {RequestMethod.GET}, headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    public Result loginDialog() {
        System.out.println("登录超时。。。。。。。");
        return null;
    }

    @RequestMapping(value = "/timeout", method = {RequestMethod.GET})
    public String timeout() {
        return LOGIN_DIALOG;
    }
}
