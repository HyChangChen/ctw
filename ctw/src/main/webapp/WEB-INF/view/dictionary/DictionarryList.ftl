<#assign bodyTitle="数据字典管理">
<#assign htmlTitle="数据字典管理">
<#assign smallTitle="数据字典管理">
<#include "macro/_global_macro.ftl">
<#include "template/template.ftl">
<@template>
<div class="easyui-layout layout-wrapper" data-options="fit:true">
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
            <form role="form"  action="/dictionary/save" id="form">
                <input type="hidden" name="id">
                <input type="hidden" name="createBy">
                <input type="hidden" name="createTime">

                <div class="row">
                    <div class="col-xs-4">
                        <div class="form-group required">
                            <label class="Validform_label">说明</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="description" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">创建人</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="changeby" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">orgid</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="orgid" datatype="*">
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="form-group required">
                            <label class="Validform_label">备注</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="remark" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">状态</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="active" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">currencyid</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="currencyid" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">siteid</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="siteid" datatype="*">
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="form-group required">
                            <label class="Validform_label">编码</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="currencycode" datatype="*">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">创建时间</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="changedate">
                        </div>
                        <div class="form-group required">
                            <label class="Validform_label">类型</label><span class="Validform_checktip"></span>
                            <input type="text" class="form-control" name="type" datatype="*">
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

    <script type="text/javascript" src="${request.contextPath}/static/site/js/dictionary/DictionarryList.js"></script>
</@template>


