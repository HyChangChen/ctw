<#assign bodyTitle="资源管理">
<#assign htmlTitle="资源管理">
<#assign smallTitle="系统资源管理">
<#include "macro/_global_macro.ftl">
<#include "template/template.ftl">
<@template>
<div class="easyui-layout layout-wrapper" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <table id="resourceView" <#--title="Folder Browser"--> style="height:100%" class="easyui-treegrid"></table>
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
    </form>
</div>
<div id="dlg" class="easyui-dialog" data-options="closed:true,border:false,modal:true"
     style="width:720px;height:500px;">
    <form role="form"  id="form">
        <input type="hidden" name="id">
        <input type="hidden" name="createBy">
        <input type="hidden" name="createTime">

        <div class="row">
            <div class="col-xs-6">
                <div class="form-group required">
                    <label class="Validform_label">资源名称</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="name" datatype="*">
                </div>

                <div class="form-group required">
                    <label class="Validform_label">上级节点</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="parentId" id="parentId" style="height: 29px; width: 80%">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">类型</label><span class="Validform_checktip"></span>
                   <#-- <input type="text" class="form-control" name="type">-->
                    <select name="type" id="type" class=" form-control">
                        <option value="menu" checked>菜单</option>
                        <option value="btn">按钮</option>
                    </select>
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
            <div class="col-xs-6">
                <div class="form-group required">
                    <label class="Validform_label">访问路径</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="url" datatype="*" value="/">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">权限</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="permission" datatype="*">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">排序</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="priority" datatype="*" value="99">
                </div>
                <div class="form-group">
                    <label class="Validform_label">创建时间</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="ts"  id="ts" readonly>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="form-group required">
                    <label class="Validform_label">备注</label><span class="Validform_checktip"></span>
                    <textarea type="text" class="form-control" rows="3" name="description" datatype="*"></textarea>
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
    <div id="findRole" iconcls="icon-remove"> 查看角色/资源</div>
    <!--具体的菜单事件请自行添加，跟toolbar的方法是基本一样的-->
</div>
<script type="text/javascript" src="${request.contextPath}/static/plugins/easyui/plugins/treegrid-dnd.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/site/js/resource/resourceList.js"></script>


</@template>