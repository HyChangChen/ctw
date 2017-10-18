package com.ctw.utils;

import com.ctw.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

/**
 * ctw com.ctw.utils
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/02 19: 38
 * @Version 1.0
 * @explain：............
 */

public class EntityUtils {
    private static final Logger logger = LoggerFactory.getLogger(EntityUtils.class);

    public static void setCreateInfo(Object entity) {
        setOperateInfo(entity, "setCreateBy", "setCreateTime", "getCreateBy");
    }

    public static void setUpdateInfo(Object entity) {
        setOperateInfo(entity, "setUpdateBy", "setUpdateTime", "getUpdateBy");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void setOperateInfo(Object entity, String setFieldBy, String setFieldTime, String getFieldBy) {
        if (entity == null) {
            return;
        }

        Class clazz = entity.getClass();
        try {
            Method setTime = clazz.getDeclaredMethod(setFieldTime, Date.class);
            setTime.invoke(entity, Calendar.getInstance().getTime());

            Method getBy = clazz.getDeclaredMethod(getFieldBy);
            Object name = getBy.invoke(entity);
            if (name != null && name != "") {
                return;
            }

            User u = Functions.getUserInfo();
            if (u != null) {
                Method czr = clazz.getDeclaredMethod(setFieldBy, String.class);
                czr.invoke(entity, u.getLoginName());
            }
        } catch (Exception e) {
            // ignore the exception
        }


    }


}
