<#assign bodyTitle="仓库管理-->仓库信息">
<#assign htmlTitle="仓库信息">
<#assign smallTitle="-->仓库信息">
<#include "macro/_global_macro.ftl">
<#include "template/template.ftl">
<@template>
<#--<div class="easyui-layout layout-wrapper" data-options="fit:true">
    <div data-options="region:'north',border:false" style="height:50px">
    </div>
    <div data-options="region:'center',border:false">
        <div id="dg"></div>
    </div>
</div>-->

<div id="tb" style="padding: 5px">
    <form class="form-inline" role="form">
        <button type="button" class="btn btn-primary btn-sm" id="btnAdd"><i class="fa fa-fw fa-plus"></i>&nbsp;新增
        </button>
        <button type="button" class="btn btn-success btn-sm" id="btnEdit"><i class="fa fa-fw fa-pencil"></i>&nbsp;编辑
        </button>
        <button type="button" class="btn btn-success btn-sm" id="btnCopy"><i class="fa fa-copy"></i>&nbsp;复制
        </button>
        <button type="button" class="btn btn-danger btn-sm" id="btnDelete"><i class="fa fa-fw fa-trash-o"></i>&nbsp;删除
        </button>
    </form>
</div>


<div id="dlg" class="easyui-dialog" data-options="closed:true,border:false,modal:true"
     style="width:50%;height:30%;">
    <form role="form" action="${request.contextPath}/depot/save" id="form">
        <input type="hidden" name="id" id="id">
        <input type="hidden" name="createBy">
        <input type="hidden" name="createTime">
        <div class="row">
            <div class="col-lg-12">
                <div class="form-group required">
                    <label class="Validform_label">仓库名称</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="depotName" datatype="*">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <div class="row">
                    <div class="form-group required">
                        <label class="Validform_label">是否默认仓库</label><span class="Validform_checktip"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group required">
                        是： <input type="radio" value="1" name="isDefault">
                        否：<input type="radio" value="0" name="isDefault" checked>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group required">
                    <label class="Validform_label">负责人</label><span class="Validform_checktip"></span>
                    <select id="depotManager" name="depotManager" class="form-control select2-search--hide"
                            placeholder="负责人..." style="width:100%;">
                        <option value="" selected="selected">--请选择仓库负责人--</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group required">
                    <label class="Validform_label">仓库编号</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="depotNo" datatype="*">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="form-group required">
                    <label class="Validform_label">仓库地址</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="address" datatype="*">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div align="right">
                    <button type="button" class="btn btn-sm" onclick="javascript:$('#dlg').dialog('close');"><i
                            class="fa fa-fw fa-close"></i>&nbsp;取消
                    </button>
                    <button type="submit" class="btn btn-info btn-sm" id="btnSave"><i class="fa fa-fw fa-save"></i>&nbsp;保存
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
    <!--具体的菜单事件请自行添加，跟toolbar的方法是基本一样的-->
</div>
<link rel="stylesheet" href="${request.contextPath}/static/plugins/select2/select2.min.css">
<script type="text/javascript" src="${request.contextPath}/static/plugins/select2/select2.full.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/plugins/select2/zh-CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/site/js/depot/depotList.js"></script>

</@template>

