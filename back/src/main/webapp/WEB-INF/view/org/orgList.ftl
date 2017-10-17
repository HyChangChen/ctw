<#assign bodyTitle="组织管理">
<#assign htmlTitle="组织管理">
<#assign smallTitle="系统组织管理">
<#include "macro/_global_macro.ftl">
<#include "template/template.ftl">
<@template>
<div id="cc" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west',title:'组织架构' , split:true" style="width:240px;">
        <input value="${checkOrgId}" type="hidden" id="checkOrgId"/>
        <ul id="orgTree">
        </ul>
    </div>
    <div data-options="region:'center'">
        <div id="dg"></div>
      <#--  <div id="cc4" class="easyui-layout" data-options="fit:true">
     &lt;#&ndash;       <div data-options="region:'north',title:'搜索框' , split:true" style="width:280px;">
                <form class="form-inline" role="form">
                    <div class="form-group">
                        <label for="daterange">角色名称：</label><span class="Validform_checktip"></span>
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
            </div>&ndash;&gt;
          &lt;#&ndash;  <div data-options="region:'center'" style="width:280px;">

            </div>&ndash;&gt;
        </div>-->
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
        <button type="button" class="btn btn-primary btn-sm" id="btnEditRole"><i class="fa fa-fw fa-pencil"></i>&nbsp;操作角色
        </button>
    </form>
</div>
<!-- 新增编辑模块-->
<div id="dlg" class="easyui-dialog" data-options="closed:true,border:false,modal:true"
     style="width:430px;height: 380px;">
    <form role="form" id="form">
        <input type="hidden" name="id">
        <div class="row">
            <div class="col-lg-12">
                <div class="form-group required">
                    <label class="Validform_label">上级组织</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-group"></i>
                        </span>
                        <input id="partentName" name="partentName" style="height: 29px; width: 210px">
                        <input id="partentId" name="partentId"  type="hidden"/>
                    </div>
                </div>
                <div class="form-group required">

                    <label class="Validform_label">组织名称</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-group"></i>
                        </span>
                        <input type="text" class="form-control" name="orgName"   datatype="/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,15}$/">
                    </div>
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
               <#-- <div class="form-group ">
                    <label class="Validform_label">样式</label>&lt;#&ndash;<span class="Validform_checktip"></span>&ndash;&gt;
                    <input type="text" class="form-control" name="icon">
                </div>-->
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div align="right">
                    <button type="button" class="btn btn-sm" id="btnCancel"><i
                            class="fa fa-fw fa-close"></i>&nbsp;取消
                    </button>
                    <button type="submit" class="btn btn-info btn-sm" id="btnSave"><i class="fa fa-fw fa-save"></i>&nbsp;保存
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
<!--右键菜单--->
<div id="menu" class="easyui-menu" style="width: 50px; display: none;">
    <!--放置一个隐藏的菜单Div-->
    <div id="Add" iconcls="glyphicon-plus">新&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;增</div>
    <div id="Del" iconcls="glyphicon-remove-circle">删&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除</div>
    <div id="Update" iconcls="glyphicon-edit">修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;改</div>
    <div class="menu-sep"></div>
    <div id="findRole" iconcls="icon-remove"> 查看角色/资源</div>
    <!--具体的菜单事件请自行添加，跟toolbar的方法是基本一样的-->
</div>

<!--组织角色-->
<div id="dlgRoleOrg" class="easyui-dialog" data-options="closed:true,border:false,modal:true"
     style="width:78%;height: 68%;">
    <div id="cc2" class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',title:'组织角色分配' , split:true" style="width:70%;">
            <div id="cc4" class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',title:'已分配角色' , split:true" style="height:50%;">
                    <div id="haveRole"></div>
                </div>
                <div data-options="region:'center',title:'未分配角色' , split:true" style="width:50%;">
                    <div id="wrongRole"></div>
                </div>
            </div>
        </div>
        <div data-options="region:'east',title:'资源列表',split:true , split:true" style="width:280px;">
            <ul id="resourceTree"></ul>
        </div>
    </div>
</div>

<input id="orgId" type="hidden"/>
<input id="roleId" type="hidden"/>
<script type="text/javascript" src="${request.contextPath}/static/site/js/org/orgList.js"></script>
</@template>

