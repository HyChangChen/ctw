<#assign bodyTitle="Apps">
<#assign htmlTitle="Apps">
<#assign smallTitle="Apps">
<#include "macro/_global_macro.ftl">
<#include "template/template.ftl">
<@template>
<div class="easyui-layout layout-wrapper" data-options="fit:true">
    <div data-options="region:'north',border:false" style="height:50px">
    </div>
    <div data-options="region:'center',border:false">
        <div id="dg"></div>
    </div>
</div>

        <div id="tb" style="padding: 5px">
            <form class="form-inline" role="form">
                <button type="button" class="btn btn-primary btn-sm" id="btnAdd"><i class="fa fa-fw fa-plus"></i>&nbsp;新增 </button>
                <button type="button" class="btn btn-success btn-sm" id="btnEdit"><i class="fa fa-fw fa-pencil"></i>&nbsp;编辑 </button>
                <button type="button" class="btn btn-danger btn-sm" id="btnDelete"><i class="fa fa-fw fa-trash-o"></i>&nbsp;删除 </button>
            </form>
        </div>


        <div id="dlg" class="easyui-dialog" data-options="closed:true,border:false,modal:true" style="width:720px;height:500px;">
            <form role="form"  action="/apps/save" id="form">
                <input type="hidden" name="appsid">
                <input type="hidden" name="createBy">
                <input type="hidden" name="createTime">

                <div class="row">
                    <div class="col-xs-4">
                        <div class="form-group required">
                            <label class="Validform_label">custapptype</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="custapptype" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">groupname</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="groupname" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">keyattribute</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="keyattribute" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">module</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="module" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">readd</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="readd" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">viewport</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="viewport" datatype="*">
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="form-group required">
                            <label class="Validform_label">app</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="app" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">deletee</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="deletee" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">insertt</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="insertt" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">objectname</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="objectname" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">orderby</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="orderby" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">restrictions</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="restrictions" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">datasrc</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="datasrc" datatype="*">
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="form-group required">
                            <label class="Validform_label">apptype</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="apptype" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">description</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="description" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">ismobile</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="ismobile" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">maxappsid</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="maxappsid" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">originalapp</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="originalapp" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">save</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="save" datatype="*">
                        </div>
                    </div>
                </div>
                <div align="center">
                    <button type="button" class="btn btn-sm" onclick="javascript:$('#dlg').dialog('close');"> <i class="fa fa-fw fa-close"></i>&nbsp;取消 </button>
                    <button type="submit" class="btn btn-info btn-sm" id="btnSave"><i class="fa fa-fw fa-save"></i>&nbsp;保存 </button>
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

    <script type="text/javascript" src="${request.contextPath}/static/site/js/apps/apps.js"></script>

</@template>

