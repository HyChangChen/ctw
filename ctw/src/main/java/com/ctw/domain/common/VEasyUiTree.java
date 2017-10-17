package com.ctw.domain.common;

import java.util.List;

/**
 * ctw com.ctw.domain.common
 *
 * @author: HaiAng
 * @CreateDate: 2016/05/30 13: 12
 * @Version 1.0
 * @explain： 使用easyUi Tree 返回其需要的json 数据格式
 */
public class VEasyUiTree {
    private int id;
    private String text;
    private String state;//节点状态，'open' 或 'closed'
    private boolean checked;
    private Object attributes;// 该节点的自定义属性
    private List<VEasyUiTree> children;
    private String iconCls;
    private Integer pid;
    private int isValid;
    private boolean isLeaf=false;

    public boolean isLeaf() {
        return isLeaf;
    }

    public boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }


    public boolean isChecked() {
        return checked;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public List<VEasyUiTree> getChildren() {
        return children;
    }

    public void setChildren(List<VEasyUiTree> children) {
        this.children = children;
    }
}
