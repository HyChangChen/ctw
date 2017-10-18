package com.ctw.service;

import com.ctw.domain.common.VEasyUiTree;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * ctw com.ctw.service
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/01 02: 42
 * @Version 1.0
 * @explain：............ 返回 easyUi Tree
 */
public abstract class EasyUiTree<T> {
    /**
     * 打造树
     *
     * @param list
     * @return
     */
    public List<VEasyUiTree> ObjectToTree(List<VEasyUiTree> list) {
        List<VEasyUiTree> nodeList = new ArrayList<VEasyUiTree>();
        if (null != list && list.size() > 0) {
            for (VEasyUiTree node1 : list) {
                boolean mark = false;
                for (VEasyUiTree node2 : list) {
                    if (node1.getPid() != null && node1.getPid().equals(node2.getId())) {
                        mark = true;
                        if (node2.getChildren() == null)
                            node2.setChildren(new ArrayList<VEasyUiTree>());
                            node2.getChildren().add(node1);
                        break;
                    }
                }
                if (!mark) {
                    nodeList.add(node1);
                }
            }
        }

        return nodeList;
    }

}
