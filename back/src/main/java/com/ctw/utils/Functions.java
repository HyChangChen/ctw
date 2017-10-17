package com.ctw.utils;


import com.ctw.domain.user.User;
import com.ctw.type.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.io.UnsupportedEncodingException;

/**
 * Spitals
 *
 * @Author: HaiAng
 * @Time： 2016/5/13.0:22
 * @Vistion：1.0
 * @Remark： 帮助类
 */
public class Functions {


    /**
     * 获取用户信息
     *
     * @return
     */
    public static User getUserInfo() {
        Subject currentUser = SecurityUtils.getSubject();
        User user = (User) currentUser.getSession().getAttribute(Constants.CURRENT_USER);
        return user;
    }

    /**
     * 处理url传中文乱码
     *
     * @param str
     * @return
     */
    public static String encodeStr(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }





}
