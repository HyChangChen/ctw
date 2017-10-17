<#macro template>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${htmlTitle}</title>
    <#include "include/_head_link.ftl">
</head>
<body class="hold-transition skin-blue sidebar-mini wrapper">
<!-- Main Header -->
    <#include "include/_sidebar_top.ftl">
<!-- Left side column. contains the logo and sidebar -->
    <#include "include/_sidebar_left.ftl">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">

    <div class="easyui-layout layout-wrapper" data-options="fit:true">
        <div data-options="region:'north',border:false" style="height:50px">
            <section class="content-header">
                <h1>
                    <span id="body-title">${bodyTitle?trim?html}</span>
                    <small><span id="small-title">${smallTitle?trim?html}</span></small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
                    <li class="active">Here</li>
                </ol>
            </section>
        </div>
        <div data-options="region:'center',border:false" style="visibility:hidden" id="content-body">
            <#nested>
        </div>
    </div>
</div>

<!-- Control Sidebar -->
    <#include "include/_sidebar_right.ftl">

<!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
<div class="control-sidebar-bg"></div>
<div id="pwd" class="easyui-dialog" data-options="closed:true,border:false,modal:true"
     style="width:430px;height: 280px;">
    <form role="form" id="form" action="${request.contextPath}/user/updatePassWord">
        <input   type="hidden"  name="id" value="  ${Session["user"].id}"/>
        <div class="row">
            <div class="col-lg-12">
                <div class="form-group required">
                    <label class="Validform_label">原始密码</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-key"></i>
                        </span>
                        <input id="oldPwd" class="form-control"  type="password" name="oldPwd"  datatype="*6-16" >
                    </div>
                </div>
                <div class="form-group required">

                    <label class="Validform_label">新密码</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-keyboard-o"></i>
                        </span>
                        <input type="password" class="form-control" name="newPwd"  datatype="*6-16">
                    </div>
                </div>
                <div class="form-group required">
                    <label class="Validform_label">确认密码</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-keyboard-o"></i>
                        </span>
                        <input type="password" value=""  class="form-control"  name="newPwd1" class="inputxt" datatype="*" recheck="newPwd" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！" />
                    </div>
                </div>
            </div>
        </div>
        <div align="right">
            <button type="button" class="btn btn-sm" id="btnCancel"><i
                    class="fa fa-fw fa-close"></i>&nbsp;取消
            </button>
            <button type="submit" class="btn btn-info btn-sm" id="btnSave"><i
                    class="fa fa-fw fa-save"></i>&nbsp;保存
            </button>
        </div>
    </form>
</div>
<script>
    $(document).ready(function () {
        $('#content-body').css('visibility', 'visible');
        $("#updatePwd").on("click",function () {
           $("#pwd").dialog('setTitle', '修改密码').dialog('center').dialog('open');
        });
        $("#form").Validform({
            tiptype:Jutil.tiptype,
            ajaxPost: true,
            callback: function (result) {
                if (result.success) {
                    //swal(result.msg, '', "success")
                    swal({
                        title: "温馨提示",
                        text: result.msg,
                        timer: 2000,
                        type: "success",
                        showConfirmButton: false
                    });
                    $("#pwd").dialog('close');
                } else {
                    swal(result.msg, '', "error");
                }
            }
        });
      $("#btnCancel").on('click', function () {
            $("#pwd").dialog('close');
        });

    });
</script>
<script type="text/javascript" src="${context}/static/site/js/common/options_util.js"></script>
</body>
</html>
</#macro>