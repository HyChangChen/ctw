package com.ctw.domain.common;

/**
 * Spitals
 *
 * @Author: HaiAng
 * @Time： 2016/5/21.20:36
 * @Vistion：1.0
 * @Remark： 树
 */
public class ZtreeDomain {

    private Integer id;
    private Integer pId;
    private String name;
    private String icon;
    private String title;
    private boolean open;
    private  String font;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }


}
