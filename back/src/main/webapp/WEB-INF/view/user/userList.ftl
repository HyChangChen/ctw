<#assign bodyTitle="用户管理">
<#assign htmlTitle="用户管理">
<#assign smallTitle="系统用户管理">
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
        <div id="cc4" class="easyui-layout" data-options="fit:true">
          <#--  <div data-options="region:'north',title:'搜索框' , split:true" style="width:280px;">
                <form class="form-inline" role="form">
                    <div class="form-group">
                        <label for="daterange">注册时间：</label><span class="Validform_checktip"></span>
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
            </div>-->
            <div data-options="region:'center'" style="width:280px;">
                <div id="dgUserList"></div>
            </div>
        </div>
    </div>
</div>

<div id="menu" class="easyui-menu" style="width: 50px; display: none;">
    <!--放置一个隐藏的菜单Div-->
    <div id="Add" iconcls="glyphicon-plus">新&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;增</div>
    <div id="Del" iconcls="glyphicon-remove-circle">删&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除</div>
    <div id="Update" iconcls="glyphicon-edit">修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;改</div>
    <div class="menu-sep"></div>
    <div id="findRole" iconcls="icon-remove"> 查看角色/资源</div>
<#--<div  id="findResouce" data-options="iconCls:'icon-save'" >查看拥有的资源</div>-->
    <!--具体的菜单事件请自行添加，跟toolbar的方法是基本一样的-->
</div>
<div id="tb" style="padding: 5px">
    <form class="form-inline" role="form">
        <button type="button" class="btn btn-primary btn-sm" id="btnAdd"><i class="fa fa-fw fa-plus"></i>&nbsp;添加用户
        </button>

        <button type="button" class="btn btn-success btn-sm" id="btnEdit"><i class="fa fa-fw fa-pencil"></i>&nbsp;编辑用户
        </button>

        <@shiro.hasPermission name="user:delete">
            <button type="button" class="btn btn-danger btn-sm" id="btnDelete"><i class="fa fa-fw fa-trash-o"></i>&nbsp;删除用户
            </button>
        </@shiro.hasPermission>
        <button type="button" class="btn btn-info btn-sm" id="btnRefresh"><i class="fa fa-fw fa-refresh"></i>&nbsp;重置密码
        </button>
        <button type="button" class="btn btn-sm" id="btnHistory"><i class="fa fa-fw fa-history"></i>&nbsp;更新状态
        </button>
        <button type="button" class="btn btn-primary btn-sm" id="btnEditRole"><i class="fa fa-fw fa-pencil"></i>&nbsp;操作角色
        </button>
    </form>
</div>
<#--用户拥有的角色和资源-->
<div id="dlgRoleOrg" class="easyui-dialog" data-options="closed:true,border:false,modal:true"
     style="width:78%;height: 68%;">
    <div id="cc2" class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',title:'单独分配用户角色' , split:true" style="width:70%;">
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
<#--新增修改用户模块-->
<div id="dlg" class="easyui-dialog" data-options="closed:true,border:false,modal:true"
     style="width:830px;height: $(document.body).height()*.98;">
    <form role="form" id="form">
        <input id="id" name="id" type="hidden"/>
        <div class="row">
            <div class="col-lg-4">
                <div class="form-group">
                    <label class="Validform_label">登录名称</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="fa fa-envelope"></i>
                        </span>
                        <input type="text" class="form-control" tip="请在这里输入您的登录名称。"
                               datatype="s6-18" name="loginName" id="loginName">
                    </div>
                </div>
                <div class="form-group">
                    <label class="Validform_label">所属组织</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-group"></i>
                        </span>
                        <input id="orgName" name="orgName" style="height: 29px; width: 210px">
                        <input id="orgId" name="orgId" type="hidden">
                    </div>
                </div>
                <div class="form-group">
                    <label class="Validform_label">注册日期</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </span>
                        <input type="text" class="form-control" name="regTime" readonly
                               id="regTime">

                    </div>

                </div>

                <div class="form-group">
                    <label class="Validform_label">电话号码</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-phone"></i>
                        </span>
                        <input type="text" class="form-control" name="tel" id="tel">
                    </div>

                </div>
                <div class="form-group">
                    <label class="Validform_label">性别</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-child"></i>
                        </span>
                        <select name="genter" id="genter" class=" form-control">
                            <option value="1" checked>男</option>
                            <option value="2">女</option>
                        </select>
                    </div>
                </div>


            </div>
            <div class="col-lg-4">
                <div class="form-group">
                    <label class="Validform_label">密码</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class=" fa fa-lock"></i>
                        </span>
                        <input class="form-control" value="123456" readonly
                               type="passWord" name="passWord" id="passWord"/>
                        <span class="input-group-addon">
                            <i class=" fa fa-lock"> 重置</i>
                        </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="Validform_label">岗位</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-group"></i>
                        </span>
                        <input id="postName" name="postName" style="height: 29px; width: 210px" class="form-control">
                        <input id="postId" name="postId" type="hidden" <#--type="hidden"-->>
                    </div>
                </div>
                <div class="form-group">
                    <label class="Validform_label">手机号码</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-mobile"></i>
                        </span>
                        <input type="text" class="form-control" name="mobile" datatype="m" id="mobile">
                    </div>
                </div>
                <div class="form-group">
                    <label class="Validform_label">邮箱</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="fa fa-envelope"></i>
                        </span>
                        <input type="text" class="form-control" name="email" datatype="e" ignore="ignore">
                    </div>
                </div>
                <div class="form-group">
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
            <div class="col-lg-4">
                <div class="form-group">
                    <label class="Validform_label">真实姓名</label><span class="Validform_checktip"></span>
                    <div class="input-group ">
                     <span class="input-group-addon">
                            <i class="fa fa-user"></i>
                        </span>
                        <input type="text" class="form-control" name="fullName"
                               datatype="/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/">

                    </div>
                </div>
                <div class="form-group">
                    <label class="Validform_label">拼音名</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-user-md"></i>
                        </span>
                        <input type="text" class="form-control"
                               name="chinaName"
                               id="chinaName">
                    </div>
                </div>
                <div class="form-group">
                    <label class="Validform_label">QQ</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-qq"></i>
                        </span>
                        <input type="text" class="validate[custom[qq]] form-control" name="qq"
                               id="qq">
                    </div>
                </div>
                <div class="form-group">
                    <label class="Validform_label">年龄</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-heart"></i>
                        </span>
                        <input type="text" class="form-control" name="age" id="age"
                               datatype="/^\d{1,3}$/"
                               ignore="ignore">
                    </div>

                </div>
                <div class="form-group">
                    <label class="Validform_label">状态</label><span class="Validform_checktip"></span>
                    <select name="status" id="status"
                            class="form-control">
                        <option value="1" checked>正常</option>
                        <option value="3">锁定</option>
                    </select>
                </div>

            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="form-group">
                    <label class="Validform_label">详细地址</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-home"></i>
                        </span>
                        <input type="text" class="form-control" name="address">
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
<input id="userId" type="hidden"/>
<input id="roleId" type="hidden"/>

<#--自定义-->
<script type="text/javascript" src="${context}/static/site/js/user/userList.js"></script>
</@template>


