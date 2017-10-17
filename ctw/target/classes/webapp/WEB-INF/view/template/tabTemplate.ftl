<#macro tabs>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${htmlTitle}</title>
    <#include "include/_head_link.ftl">
</head>
<body>
    <div class="easyui-layout layout-wrapper" data-options="fit:true">
        <div data-options="region:'center',border:false" style="visibility:hidden" id="content-body">
            <#nested>
        </div>
    </div>
<!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
<div class="control-sidebar-bg"></div>

</div>
<script>
    $(document).ready(function () {
        $('#content-body').css('visibility', 'visible');
        $('#content-body').css('width', '100%');
        $('#content-body').css('height', '100%');
    });
</script>
</body>
</html>
</#macro>