<#assign bodyTitle="岗位管理">
<#assign htmlTitle="岗位管理">
<#assign smallTitle="系统岗位管理">
<#include "macro/_global_macro.ftl">
<#include "template/template.ftl">
<@template>
<div class="easyui-layout layout-wrapper" data-options="fit:true">
    <div data-options="region:'west',border:false" style="width:20% ;">
        <ul id="orgTree">
        </ul>
    </div>
    <div data-options="region:'center',border:false">
        <div id="dg"></div>
    </div>
</div>
<!---工具框--->
<div id="tb" style="padding: 5px">
    <form class="form-inline" role="form">
        <button type="button" class="btn btn-primary btn-sm" id="btnAdd"><i class="fa fa-fw fa-plus"></i>&nbsp;新增岗位
        </button>
        <button type="button" class="btn btn-success btn-sm" id="btnEdit"><i class="fa fa-fw fa-pencil"></i>&nbsp;编辑岗位
        </button>
        <button type="button" class="btn btn-danger btn-sm" id="btnDelete"><i class="fa fa-fw fa-trash-o"></i>&nbsp;删除岗位
        </button>
        <button type="button" class="btn btn-primary btn-sm" id="btnEditRole"><i class="fa fa-fw fa-pencil"></i>&nbsp;操作角色
        </button>
    </form>
</div>
<!--操作角色-->
<div id="dlgRolePost" class="easyui-dialog" data-options="closed:true,border:false,modal:true"
     style="width:50%;height: 68%;">
    <div id="cc2" class="easyui-layout" data-options="fit:true">
        <div id="cc4" class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',title:'已分配角色' , split:true" style="height:50%;">
                <div id="haveRole"></div>
            </div>
            <div data-options="region:'center',title:'未分配角色' , split:true" style="width:50%;">
                <div id="wrongRole"></div>
            </div>
        </div>
    </div>
</div>
<!---新增编辑-->
<div id="dlg" class="easyui-dialog" data-options="closed:true,border:false,modal:true"
     style="width:50%;height:50%;">
    <form role="form" id="form">
        <input type="hidden" name="id">
        <div class="row">
            <div class="col-lg-12">
                <div class="form-group required">
                    <label class="Validform_label">组织名称</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                             <i class="fa fa-sitemap"></i>
                        </span>
                        <input type="text" class="form-control" name="postName"
                               datatype="/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,15}$/">
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-6">
                <div class="form-group required">
                    <label class="Validform_label">所属组织</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-sitemap"></i>
                        </span>
                        <input id="orgId" name="orgId" class="form-control" style="height: 29px; width: 80%">
                    </div>
                </div>
            </div>
            <div class="col-xs-6">
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
                <div class=" form-group">
                    <label class="Validform_label">岗位说明</label><span class="Validform_checktip"></span>
                    <textarea class="form-control" rows="3" name="description"></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div align="right">
                    <button type="button" class="btn btn-sm" id="btnCancel"
                            onclick="javascript:$('#dlg').dialog('close');"><i
                            class="fa fa-fw fa-close"></i>&nbsp;取消
                    </button>
                    <button type="submit" class="btn btn-info btn-sm" id="btnSave">
                        <i class="fa fa-fw fa-save"></i>&nbsp;保存
                    </button>
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
<script type="text/javascript" src="${request.contextPath}/static/site/js/post/postList.js"></script>

</@template>

