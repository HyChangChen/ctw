package com.ctw.domain.common;

import java.io.Serializable;

/**
 * ctw com.ctw.domain.common
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/02 19: 01
 * @Version 1.0
 * @explain：............ 操作数据返回信息
 */
public class Result implements Serializable {
    private static final long serialVersionUID = -2516726606727416571L;
    private boolean isSuccess;
    private String msg;
    private Object data;

    public Result(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Result(boolean isSuccess, String msg) {
        this.isSuccess = isSuccess;
        this.msg = msg;
    }

    public Result(boolean isSuccess, Object data) {
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public Result(boolean isSuccess, String msg, Object data) {
        this.isSuccess = isSuccess;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
