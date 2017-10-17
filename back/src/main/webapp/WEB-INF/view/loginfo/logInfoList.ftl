<#assign bodyTitle="日志管理">
<#assign htmlTitle="日志管理">
<#assign smallTitle="系统日志管理">
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
        <button type="button" class="btn btn-danger btn-sm" id="btnDelete"><i class="fa fa-fw fa-trash-o"></i>&nbsp;删除
        </button>
    </form>
</div>



<script type="text/javascript" src="${request.contextPath}/static/site/js/loginfo/logInfoList.js"></script>
 </@template>

