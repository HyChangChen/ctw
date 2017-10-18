package com.ctw.service.resource.impl;


import com.ctw.domain.common.VEasyUiTree;
import com.ctw.domain.resource.ResourceEntity;
import com.ctw.domain.resource.ResourceQuery;
import com.ctw.domain.resource.ResourceVoConvert;
import com.ctw.domain.roleresource.RoleResource;
import com.ctw.domain.roleresource.RoleResourceQuery;
import com.ctw.domain.user.User;
import com.ctw.service.EasyUiTree;
import com.ctw.service.resource.IResourceService;
import com.ctw.service.roleresource.IRoleResourceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ctw.dao.resource.IResourceDao;
import com.ctw.domain.common.PageResult;
import com.ctw.domain.resource.ResourceVo;
import com.ctw.utils.EntityUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class ResourceServiceImpl extends EasyUiTree implements IResourceService {

    private ResourceVo arrMenuCss;
    private ResourceVo arrCacheMenu;
    private String htmlMenuCss;
    private String htmlMenuCssTemp;
    private List<ResourceVo> menus;
    private List<ResourceVo> items2;
    private HttpServletRequest hRequest;
    private Integer topParentId;
    @Autowired
    private IResourceDao iResourceDao;
    @Autowired
    private IRoleResourceService iRoleResourceService;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int create(ResourceVo resourceVo) {
        if (null == resourceVo) {
            return 0;
        }
      /*  if (StringUtils.isNotBlank(resourceVo.getId().toString())) {
            resourceVo.setId(Integer.parseInt(UUID.randomUUID().toString().replaceAll("-", "")));
        }*/
        EntityUtils.setCreateInfo(resourceVo);
        ResourceEntity resourceEntity = ResourceVoConvert.toEntity(resourceVo);
        return iResourceDao.create(resourceEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int update(ResourceVo resourceVo) {
        if (StringUtils.isEmpty(resourceVo.getId().toString())) {
            return 0;
        }
        EntityUtils.setUpdateInfo(resourceVo);
        ResourceEntity resourceEntity = ResourceVoConvert.toEntity(resourceVo);
        return iResourceDao.update(resourceEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int deleteById(Integer id) {
        return iResourceDao.deleteById(id);
    }

    @Override
    public int batchDelete(String[] ids) {
        return iResourceDao.batchDelete(ids);
    }

    @Override
    public ResourceVo getById(Integer id) {
        return ResourceVoConvert.toVo(iResourceDao.getById(id));
    }

    @Override
    public List<ResourceVo> findList(ResourceQuery query) {
        return iResourceDao.findList(query);
    }

    @Override
    public PageResult<ResourceVo> findPage(ResourceQuery query) {
            return iResourceDao.findPage(query);
    }

    @Override
    public PageResult<ResourceVo> findAllPage(ResourceQuery query) {
        query.setType(null);//便于按钮也查询出来
        query.setIsValid(null);
        query.setRows(50);
        PageResult<ResourceVo> pageResult = iResourceDao.findAllPage(query);
        List<ResourceVo> voList = pageResult.getRows();
        if (voList.size() > 0) {
            pageResult.setRows(null);
            pageResult.setRows( bulidResourceVo(voList));
        }
        return pageResult;
    }

    //进行递归组装树
    protected List<ResourceVo> bulidResourceVo( List<ResourceVo> voList ) {
        List<ResourceVo> nodeList = new ArrayList<ResourceVo>();
        if (null != voList && voList.size() > 0) {
            for (ResourceVo node1 : voList) {
                boolean mark = false;
                for (ResourceVo node2 : voList) {
                    if (node1.getParentId() != null && node1.getParentId().equals(node2.getId())) {
                        mark = true;
                        if (node2.getChildren() == null)
                            node2.setChildren(new ArrayList<ResourceVo>());
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

    /***
     * 根据角色查找资源信息
     *
     * @param query
     * @return
     */
    @Override
    public List<VEasyUiTree> findResourceTree(ResourceQuery query) {
        List<Integer> resourceIds = new ArrayList<Integer>();
        RoleResourceQuery roleResourceQuery = new RoleResourceQuery();
        roleResourceQuery.setRoleId(query.getRoleId());
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        //查找拥有的资源id 集合
        List<RoleResource> roleResourceList = iRoleResourceService.findList(roleResourceQuery);
        if (roleResourceList.size() > 0 && null != roleResourceList) {
            for (RoleResource rr : roleResourceList) {
                resourceIds.add(rr.getResourceId());
                map.put(rr.getResourceId(), true);
            }
        }
        // query.setResourceIds(resourceIds);
        List<VEasyUiTree> vEasyUiTreeList;
        vEasyUiTreeList = buildResourceToVE(iResourceDao.findList(query), map);
        vEasyUiTreeList = super.ObjectToTree(vEasyUiTreeList);
        return vEasyUiTreeList;
    }

    @Override
    public String findResourceHtml(User user, ServletRequest request) {
        arrMenuCss = new ResourceVo();
        arrCacheMenu = new ResourceVo();
        htmlMenuCss = "";
        hRequest = (HttpServletRequest) request;
        menus = new ArrayList<ResourceVo>();
        List<ResourceVo> allResources = iResourceDao.findList(new ResourceQuery());
        for (ResourceVo sr : allResources) {
            //不存在权限的问题，则添加给到菜单 公用模块
            if (sr.getPermission() != null && !sr.getPermission().equals("")) {
                   /*权限检查*/
                if (!hasPermission(user.getPermission(), sr)) {
                    continue;
                }
            }
            menus.add(sr);
        }
        // setTopParentId(1);
        buildResourceVoMenuCss(0);
        return htmlMenuCss;
    }

    /**
     * 类型转换
     */
    public List<VEasyUiTree> buildResourceToVE(List<ResourceVo> list, Map<Integer, Boolean> map) {
        List<VEasyUiTree> veTemp = new ArrayList<VEasyUiTree>();
        if (list.size() > 0 && null != list) {
            for (ResourceVo vu : list) {
                VEasyUiTree vEasyUiTree = new VEasyUiTree();
                if (map != null && map.size() > 0) {
                    for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
                        if ((entry.getKey() == vu.getId()) && !vu.getLeaf().equals("1")) {
                            vEasyUiTree.setChecked(entry.getValue());
                        }

                    }
                }
                if (vu.getLeaf().equals("0")) {
                    vEasyUiTree.setIsLeaf(true);
                }
                vEasyUiTree.setId(vu.getId());
                vEasyUiTree.setPid(vu.getParentId());
                vEasyUiTree.setText(vu.getName());
                vEasyUiTree.setIconCls(vu.getIcon());
                vEasyUiTree.setIsValid(vu.getIsValid());
                veTemp.add(vEasyUiTree);
            }
        }
        return veTemp;
    }


    /**
     * 将查询到的菜单进行转换为html
     *
     * @param pid
     * @return
     */
    public List<ResourceVo> buildResourceVoMenuCss(Integer pid) {
        //boolean fadd = false;
        String checkBgStyle = "style=\"background-color: rgb(189, 202, 214);\"";
        String fcs = "", fcsTemp = "", mActive = "";
        Integer itemPid, itemId, ccId, ccHasChild;
        String itemUrl, itemFullUrl, itemLabel, icon, ccicon, ccUrl = "", ccFullUrl, ccLabel;
        String servletPath = hRequest.getServletPath();
        String contextPath = hRequest.getContextPath();
        //根据Url 获得d当前点击的菜单的详细信息 排除空url即/
        String[] checkPartenIds = new String[]{};
        int array[] = new int[0];
        if (!"/".equals(hRequest.getServletPath()) && !("").equals(hRequest.getServletPath()) && null != hRequest.getServletPath()) {
            ResourceQuery rq = new ResourceQuery();
            rq.setUrl(hRequest.getServletPath());
            List<ResourceVo> clickVoList = iResourceDao.findList(rq);
            ResourceVo clickVo = null;
            if (clickVoList.size() > 0) {
                clickVo = clickVoList.get(0);
                if (clickVo.getPartentsIds() != null && !"".equals(clickVo.getPartentsIds())) {
                    checkPartenIds = clickVo.getPartentsIds().substring(0, clickVo.getPartentsIds().length() - 1).split("/");
                    array = new int[checkPartenIds.length];
                    for (int i = 0; i < checkPartenIds.length; i++) {
                        array[i] = Integer.parseInt(checkPartenIds[i]);
                    }
                }

            }
        }

        //获得当前pid下所有的ID
        List<ResourceVo> items = getSubitems(pid);
        int i = items.size();
        for (ResourceVo item : items) {
            itemPid = item.getParentId();
            itemId = item.getId();
            itemUrl = item.getUrl();
            itemFullUrl = contextPath + itemUrl;
            itemLabel = item.getName();
            icon = item.getIcon();
            //迭代查找子节点
            List<ResourceVo> child = buildResourceVoMenuCss(itemId);
            if (child != null && child.size() > 0) {

                ///检查本节点是否需要进行渲染
                if (Arrays.binarySearch(array, itemId) > 0) {
                    mActive = "active";
                }
                fcsTemp = "<li class=\"treeview " + mActive + "\"><a href='javascript:;'><i class=\"" + icon + "\"></i>"
                        + " <span>" + itemLabel + "</span><span class=\"label label-primary pull-right\">" + child.size() + "</span>  </a><ul class=\"treeview-menu\">";
                fcs = fcsTemp;
                //构造子节点HTML
                for (ResourceVo cc : child) {
                    ccId = cc.getId();
                    ccUrl = cc.getUrl();
                    ccFullUrl = contextPath + ccUrl;
                    ccLabel = cc.getName();
                    ccHasChild = cc.getHasChild();
                    ccicon = cc.getIcon();
                    //一级节点之前的所有节点只暂存于数组arrMenu，如果该子节点还存在下一级的节点，
                    //则逐级用数组arrMenu中的HTML标签替代相应的子节点
                    if (arrMenuCss.getId() != null && arrMenuCss.getId().equals(ccId)) {
                        fcs = fcs + arrMenuCss.getHtmlMenu();
                    } //如果该子节点不存在下一级的节点，直接给予完整的标签
                    if (ccHasChild.intValue() == 0) {
                        //  <li<#if menu_index == "1_1"> class="active"</#if>><a href="<#if menu_index == "1_1">#<#else>${context}/test/demo1</#if>"><i class="fa fa-files-o"></i> demo1</a></li>

                        String temp = "<li ><a href='" + ccFullUrl + "'>  <i class=\"" + ccicon + "\"></i>&nbsp;" + ccLabel + "</a></li>";
                        if (Arrays.binarySearch(array, ccId) > 0) {
                            //  mActive = "active";
                            temp = "<li  class=\"active\"><a href='" + ccFullUrl + "'>  <i class=\"" + ccicon + "\"></i>&nbsp;" + ccLabel + "</a></li>";
                        }
                        fcs = fcs + temp;
                    }
                }
                //子节点ul结束标签
                fcs = fcs + "</ul>";
                fcs = fcs + "</li>";
                //如果有兄弟节点，则需要先进行拼接，以免原来数组中的数据被新数据替换
                if (!(arrMenuCss.getParentId() == null) && arrMenuCss.getParentId() != 1 && child.size() >= 1
                        && !(arrMenuCss.getHtmlMenu() == null)) {
                    //同级兄弟通过比较arrMenuCss中的父节点是否一样，如果一样先拼接，再存储于数组arrMenuCss中
                    if (itemPid.equals(arrMenuCss.getParentId())) {
                        fcs = arrMenuCss.getHtmlMenu() + fcs;
                    } //同级兄弟通过比较arrCacheMenu中的父节点是否一样，如果一样先拼接，再存储于数组arrMenuCss中
                    else if (itemPid.equals(arrCacheMenu.getParentId())) {
                        fcs = arrCacheMenu.getHtmlMenu() + fcs;
                    } //不同级兄弟先存储于缓存数组arrCacheMenu中
                    else {
                        arrCacheMenu.setId(arrMenuCss.getId());
                        arrCacheMenu.setParentId(arrMenuCss.getParentId());
                        arrCacheMenu.setHtmlMenu(arrMenuCss.getHtmlMenu());
                    }
                }
                //一级节点之前的所有节点只暂存于数组arrMenu
                arrMenuCss.setId(itemId);
                arrMenuCss.setParentId(itemPid);
                arrMenuCss.setHtmlMenu(fcs);

                /*因为迭代顺序是从孙、儿、父这样倒序确认的，所以一级节点之前的所有节点只暂存于数组arrMenu中，
                不进行HTML标签拼接，在一级节点才进行最终的拼接*/

                if (itemPid.intValue() == 1) {
                    htmlMenuCss = htmlMenuCss + fcs;
                }
            } else {
                //如果不存在子节点的情况下
                if (itemPid.intValue() == 1) {
                    fcsTemp = "<li class=\"treeview " + mActive + "><a href='javascript:;'><i class=\"" + icon + "\"></i>"
                            + " <span>" + itemLabel + "</span><span class=\"label label-primary pull-right\">" + child.size() + "</span>  </a><ul class=\"treeview-menu\">";


                    htmlMenuCss = htmlMenuCss + "<li >" +
                            "<a href ='" + itemFullUrl + "' ><i class=\"" + icon + "\" ></i>&nbsp;"
                            + itemLabel + "</span></a ></li > ";
                }

            }
        }

        return items;
    }

    public List<ResourceVo> getSubitems(Integer parentId) {
        List<ResourceVo> items = this.getMenus();
        List<ResourceVo> items2 = new ArrayList<ResourceVo>();
        for (ResourceVo item : items) {
            Object itemPid = item.getParentId();
            if (((Integer) itemPid).intValue() == parentId) {
                items2.add(item);
            }
        }
        return items2;
    }


    /**
     * 将所有的菜单权限与用户的权限进行比较
     *
     * @param permissions
     * @param sr
     * @return
     */
    private boolean hasPermission(Set<String> permissions, ResourceVo sr) {
        String itemPermission = sr.getPermission();
        if (org.springframework.util.StringUtils.isEmpty(itemPermission)) {
            return true;
        }
        for (String permission : permissions) {
            if (permission != null && !permission.equals("")) {
                WildcardPermission p1 = new WildcardPermission(permission);
                WildcardPermission p2 = new WildcardPermission(itemPermission);
                if (p1.implies(p2) || p2.implies(p1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /***********************
     * get======set
     *****************************/


    public ResourceVo getArrMenuCss() {
        return arrMenuCss;
    }

    public void setArrMenuCss(ResourceVo arrMenuCss) {
        this.arrMenuCss = arrMenuCss;
    }

    public ResourceVo getArrCacheMenu() {
        return arrCacheMenu;
    }

    public void setArrCacheMenu(ResourceVo arrCacheMenu) {
        this.arrCacheMenu = arrCacheMenu;
    }

    public String getHtmlMenuCss() {
        return htmlMenuCss;
    }

    public void setHtmlMenuCss(String htmlMenuCss) {
        this.htmlMenuCss = htmlMenuCss;
    }

    public List<ResourceVo> getMenus() {
        return menus;
    }

    public void setMenus(List<ResourceVo> menus) {
        this.menus = menus;
    }

    public List<ResourceVo> getItems2() {
        return items2;
    }

    public void setItems2(List<ResourceVo> items2) {
        this.items2 = items2;
    }

    public HttpServletRequest gethRequest() {
        return hRequest;
    }

    public void sethRequest(HttpServletRequest hRequest) {
        this.hRequest = hRequest;
    }

    public Integer getTopParentId() {
        return topParentId;
    }

    public void setTopParentId(Integer topParentId) {
        this.topParentId = topParentId;
    }

    public String getHtmlMenuCssTemp() {
        return htmlMenuCssTemp;
    }

    public void setHtmlMenuCssTemp(String htmlMenuCssTemp) {
        this.htmlMenuCssTemp = htmlMenuCssTemp;
    }
}