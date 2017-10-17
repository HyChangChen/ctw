package com.ctw.domain.common;

/**
 * ctw com.ctw.domain.common
 *
 * @author: HaiAng
 * @CreateDate: 2016/05/31 15: 54
 * @Version 1.0
 * @explain：............ 表单验证返回对象 组成json
 */
public class ValideForm {
    private String status;
    private  String info;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
