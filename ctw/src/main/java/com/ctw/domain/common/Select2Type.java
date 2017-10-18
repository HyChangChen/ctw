package com.ctw.domain.common;

/**
 * ctw com.ctw.domain.common
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/19 18: 06
 * @Version 1.0
 * @explain：............
 */
public class Select2Type extends  PageQuery{
    private Long id;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
