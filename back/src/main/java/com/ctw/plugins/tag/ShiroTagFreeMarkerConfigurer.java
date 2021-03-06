package com.ctw.plugins.tag;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/27.23:42
 * @Vistion：1.0
 * @Remark：  自定义一个ShiroTagFreeMarkerConfigurer继承Spring本身提供的FreeMarkerConfigurer,
 * 目的是在FreeMarker的Configuration中添加shiro的配置
 */
public class ShiroTagFreeMarkerConfigurer  extends FreeMarkerConfigurer {
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
}
