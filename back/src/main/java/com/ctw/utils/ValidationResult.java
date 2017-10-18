package com.ctw.utils;

import java.io.Serializable;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.1:01
 * @Vistion：1.0
 * @Remark：  hibernate validator校验结果
 */
public class ValidationResult implements Serializable {

    private static final long serialVersionUID = -5037363691132886607L;

    private boolean hasErrors; // 校验结果是否有错
    private String errormsg; // 校验错误信息

    /**
     * @return the hasErrors
     */
    public boolean hasErrors() {
        return hasErrors;
    }

    /**
     * @param hasErrors
     *            the hasErrors to set
     */
    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    /**
     * @return the errormsg
     */
    public String getErrormsg() {
        return errormsg;
    }

    /**
     * @param errormsg
     *            the errormsg to set
     */
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

}
