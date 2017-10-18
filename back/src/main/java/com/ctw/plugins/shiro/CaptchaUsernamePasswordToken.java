package com.ctw.plugins.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * ctw com.ctw.plugins.shiro
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/17 11: 20
 * @Version 1.0
 * @explain：............ yanzhengma
 */
public class  CaptchaUsernamePasswordToken extends UsernamePasswordToken {
    /**
     * 描述
     */
    private static final long serialVersionUID = -3178260335127476542L;

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public CaptchaUsernamePasswordToken() {
        super();
    }

    public CaptchaUsernamePasswordToken(String username, String password,
                                        boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }
}
