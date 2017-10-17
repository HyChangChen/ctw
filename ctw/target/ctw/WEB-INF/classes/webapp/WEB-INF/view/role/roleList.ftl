<#assign bodyTitle="角色管理">
<#assign htmlTitle="角色管理">
<#assign smallTitle="系统角色管理">
<#include "macro/_global_macro.ftl">
<#include "template/template.ftl">
<@template>
<div class="easyui-layout layout-wrapper" data-options="fit:true">
<#--    <div data-options="region:'north',border:false,title:'搜索'　,split:true" style="height:90px">
        <form class="form-inline" role="form">
            <div class="form-group">
                <label for="daterange">注册时间：</label><span class="Validform_checktip"></span>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-calendar"></i>
                    </span>
                    <input type="text" class="form-control" id="daterange" style="width: 200px">
                </div>
            </div>
            <button type="button" class="btn btn-info btn-sm" id="btnQuery"><i class="fa fa-fw fa-search"></i>&nbsp;查询
            </button>
        </form>
    </div>-->
    <div data-options="region:'center',border:false">
        <div id="dg"></div>
    </div>
</div>
<div id="tb" style="padding: 5px">
    <form class="form-inline" role="form">
        <button type="button" class="btn btn-primary btn-sm" id="btnAdd"><i class="fa fa-fw fa-plus"></i>&nbsp;新增
        </button>
        <button type="button" class="btn btn-success btn-sm" id="btnEdit"><i class="fa fa-fw fa-pencil"></i>&nbsp;编辑
        </button>
        <button type="button" class="btn btn-danger btn-sm" id="btnDelete"><i class="fa fa-fw fa-trash-o"></i>&nbsp;删除
        </button>
        <button type="button" class="btn btn-sm" id="btnHistory"><i class="fa fa-fw fa-history"></i>&nbsp;更新状态
        </button>
        <button type="button" class="btn btn-primary btn-sm" id="btnEditRole"><i class="fa fa-fw fa-pencil"></i>&nbsp;操作资源
        </button>
    </form>
</div>
<div id="tbUser" style="padding: 1px">
    <form class="form-inline" role="form">
        <button type="button" class="btn btn-primary btn-sm" id="btnAddUser"><i class="fa fa-fw fa-plus"></i>&nbsp;关联用户
        </button>
        <button type="button" class="btn btn-danger btn-sm" id="btnDellListUser"><i class="fa fa-fw fa-trash-o"></i>&nbsp;批量删除用户关系
        </button>
    </form>
</div>
<div id="tbAddUser" style="padding: 1px">
    <form class="form-inline" role="form">
        <form class="form-inline" role="form">
            <div class="form-group">
                <label for="daterange">用户名称：</label><span class="Validform_checktip"></span>
                <div class="input-group">
                    <input type="text" class="form-control" id="inputVal" style="width: 200px">
                </div>
            </div>
            <button type="button" class="btn btn-info btn-sm" id="btnQueryNotLinkUser"><i
                    class="fa fa-fw fa-search"></i>&nbsp;查询
            </button>
            <button type="button" class="btn btn-primary btn-sm" id="btnSaveUserRole"><i class="fa fa-fw fa-plus"></i>&nbsp;确定关联
            </button>
        </form>
    </form>
</div>
<div id="tbAddPost" style="padding: 1px">
    <form class="form-inline" role="form">
        <form class="form-inline" role="form">
            <div class="form-group">
                <label for="daterange">岗位名称：</label><span class="Validform_checktip"></span>
                <div class="input-group">
                    <input type="text" class="form-control" id="inputValPost">
                     <span class="input-group-btn">
                     <#--    <i class="btn btn-default" onclick="alert(1)" type="button">
                     x
                  </i>
               </span>-->
                </div>
            </div>
            <button type="button" class="btn btn-sm" id="btnHistory"><i class="fa fa-fw fa-history"></i>&nbsp;清空
            </button>
            <button type="button" class="btn btn-info btn-sm" id="btnQueryNotLinkPost"><i
                    class="fa fa-fw fa-search"></i>&nbsp;查询
            </button>
            <button type="button" class="btn btn-primary btn-sm" id="btnSavePostRole"><i class="fa fa-fw fa-plus"></i>&nbsp;确定关联
            </button>
        </form>
    </form>
</div>
<div id="tbOrg" style="padding: 1px">
    <form class="form-inline" role="form">
        <button type="button" class="btn btn-primary btn-sm" id="btnAddOrg"><i class="fa fa-fw fa-plus"></i>&nbsp;关联组织
        </button>
        <button type="button" class="btn btn-danger btn-sm" id="btnDellListOrg"><i class="fa fa-fw fa-trash-o"></i>&nbsp;批量删除组织关系
        </button>
    </form>
</div>
<div id="tbPost" style="padding: 1px">
    <form class="form-inline" role="form">
        <button type="button" class="btn btn-primary btn-sm" id="btnAddPost"><i class="fa fa-fw fa-plus"></i>&nbsp;关联岗位
        </button>
        <button type="button" class="btn btn-danger btn-sm" id="btnDellListPost"><i class="fa fa-fw fa-trash-o"></i>&nbsp;批量删除岗位关系
        </button>
    </form>
</div>

<div id="dlgAddOrg" class="easyui-dialog"
     data-options="closed:true,border:false,modal:true,maximizable:true,collapsible:true"
     style="width:38%;height: 68%;">
    <div id="cc8" class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',collapsible:false, split:true" style="width:50%;">
            <ul id="orgRoleTree">
            </ul>
        </div>

    </div>
</div>
<div id="dlgAddUser" class="easyui-dialog"
     data-options="closed:true,border:false,modal:true,maximizable:true,collapsible:true"
     style="width:65%;height: 68%;">
    <div id="cc7" class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',collapsible:false,title:'已关联用户', split:true" style="width:50%;">
            <div id="linkUser"></div>
        </div>
        <div data-options="region:'west',collapsible:false,title:'未关联用户', split:true" style="width:50%;">
            <div id="noLinkUser"></div>
        </div>
    </div>
</div>
<div id="dlgAddPost" class="easyui-dialog"
     data-options="closed:true,border:false,modal:true,maximizable:true,collapsible:true"
     style="width:58%;height: 68%;">
    <div id="cc6" class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',collapsible:false,title:'已关联岗位', split:true" style="width:50%;">
            <div id="linkPost"></div>
        </div>
        <div data-options="region:'west',collapsible:false,title:'未关联岗位', split:true" style="width:70%;">
            <div id="cc10" class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',collapsible:false,title:'', split:true" style="height:50%;">
                    <ul id="orgTree">
                    </ul>
                </div>
                <div data-options="region:'center',collapsible:false,title:'', split:true" style="width:50%;">
                    <div id="notLinkPost"></div>
                </div>
            </div>

        </div>
    </div>
</div>
<div id="dlgRoleOrg" class="easyui-dialog" data-options="closed:true,border:false,modal:true,maximizable:true"
     style="width:98%;height: 68%;">
    <div id="cc2" class="easyui-layout" data-options="fit:true">
        <div data-options="region:'east', split:true" style="width:85%;">
            <div id="cc4" class="easyui-layout" data-options="fit:true">
                <div data-options="region:'center',title:'关联的组织' , split:true" style="width:33%;">
                    <div id="orgRole"></div>
                </div>
                <div data-options="region:'west',title:'关联的岗位' , split:true" style="width:33%;">
                    <div id="postRole"></div>
                </div>
                <div data-options="region:'east',title:'关联的人员' , split:true" style="width:33%;">
                    <div id="userRole"></div>
                </div>
            </div>
        </div>
        <div data-options="region:'center',title:'关联的资源列表',split:true , split:true" style="width:15%;">
            <ul id="resourceTree"></ul>
        </div>
    </div>
</div>
<div id="dlg" class="easyui-dialog" data-options="closed:true,border:false,modal:true"
     style="width:30%;height:38%;">
    <form role="form" id="form">
        <input type="hidden" name="id">
        <input type="hidden" name="createBy">
        <input type="hidden" name="createTime">

        <div class="row">
            <div class="col-lg-12">
                <div class="form-group required">
                    <label class="Validform_label">角色名称</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="roleName" datatype="s3-18">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">备注</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="description" datatype="*">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">创建时间</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" id="createDate" name="createDate" readonly>
                </div>
                <div class="form-group required">
                    <label class="Validform_label">是否有效</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-key"></i>
                        </span>
                        <select name="isValid" id="isValid" class=" form-control">
                            <option value="1" checked>有效</option>
                            <option value="2">无效</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div align="right">
                    <div align="right">
                        <button type="button" class="btn btn-sm" onclick="javascript:$('#dlg').dialog('close');"><i
                                class="fa fa-fw fa-close"></i>&nbsp;取消
                        </button>
                        <button type="submit" class="btn btn-info btn-sm" id="btnSave"><i class="fa fa-fw fa-save"></i>&nbsp;保存
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<div id="menu" class="easyui-menu" style="width: 50px; display: none;">
    <!--放置一个隐藏的菜单Div-->
    <div id="Add" iconcls="glyphicon-plus">新&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;增</div>
    <div id="Del" iconcls="glyphicon-remove-circle">删&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除</div>
    <div id="Update" iconcls="glyphicon-edit">修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;改</div>
    <div class="menu-sep"></div>
    <div id="findRole" iconcls="icon-remove"> 查看角色/资源</div>
    <!--具体的菜单事件请自行添加，跟toolbar的方法是基本一样的-->
</div>
<script type="text/javascript" src="${request.contextPath}/static/site/js/role/roleList.js"></script>

</@template>