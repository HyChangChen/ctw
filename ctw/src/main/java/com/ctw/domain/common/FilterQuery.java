package com.ctw.domain.common;

import java.util.ArrayList;
import java.util.List;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.1:04
 * @Vistion：1.0
 * @Remark： 请输入本类的作用
 */
public class FilterQuery  extends PageQuery {

    private static List<FilterRule> filterRules = new ArrayList<FilterRule>();

    public List<FilterRule> getFilterRules() {
        return filterRules;
    }

    public void setFilterRules(List<FilterRule> filterRules) {
        this.filterRules = filterRules;
    }
}