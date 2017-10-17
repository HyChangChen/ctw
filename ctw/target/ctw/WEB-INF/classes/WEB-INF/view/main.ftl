<#assign bodyTitle="首页">
<#assign htmlTitle="主办公区域">
<#assign smallTitle="首页">
<#include "macro/_global_macro.ftl">
<#include "template/template.ftl">
<@template>
<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
        <div id="tt" class="easyui-tabs" style="width:100%;height:100%;">
            <div title="进货" style="padding:50px;display:none;">
                <button type="button" class="btn btn-info btn-lg">进货按钮</button>
            </div>
            <div title="出库"  style="overflow:auto;padding:1px;display:none;">
                <button type="button" class="btn btn-info btn-lg">出货按钮</button>
            </div>
            <div title="订单"  style="padding:1px;display:none;">
                <button type="button" class="btn btn-info btn-lg">订单按钮</button>
            </div>
        </div>
    </div>
</div>


</@template>