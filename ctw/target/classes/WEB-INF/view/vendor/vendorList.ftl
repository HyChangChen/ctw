<#assign bodyTitle="供应商管理">
<#assign htmlTitle="供应商管理">
<#assign smallTitle="供应商管理">
<#include "macro/_global_macro.ftl">
<#include "template/template.ftl">
<@template>
<div class="easyui-layout layout-wrapper" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <div id="vendorTable" class="easyui-tabs" style="width:100%;height:100%;">
            <div title="供应商列表">
                <div id="dg"></div>
            </div>
        </div>

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
     style="width:720px;height:650px;">
    <form role="form" action="/vendor/save" id="form">
        <input type="hidden" name="id">
        <input type="hidden" name="createBy">
        <input type="hidden" name="createTime">

        <div class="row">
            <div class="col-xs-4">
                <div class="form-group required">
                    <label class="Validform_label">名称</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="vendorName" datatype="*">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">状态</label><span class="Validform_checktip"></span>
                    <div class="input-group">
                     <span class="input-group-addon">
                            <i class="fa fa-key"></i>
                        </span>
                        <select name="statuss" id="statuss" class=" form-control">
                            <option value="1" checked>有效</option>
                            <option value="2">无效</option>
                        </select>
                    </div>
                </div>
                <div class="form-group required">
                    <label class="Validform_label">银行账户</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="bankaccount" datatype="n13-15">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">运输方式</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="shipvia" datatype="*">
                </div>

                <div class="form-group required">
                    <label class="Validform_label">开户行</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="openAccount" datatype="*">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">邮箱</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="mail" datatype="*">
                </div>
            </div>
            <div class="col-xs-4">
                <div class="form-group required">
                    <label class="Validform_label">编号</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="vendorNum" datatype="*">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">公司法人</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="contact" datatype="*">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">创建人</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" readonly value=" <#if Session["user"].fullName??>
   ${Session["user"].fullName}
                         <#elseif Session["user"].chinaName??>
                            ${Session["user"].chinaName}
                            <#else >
                            ${Session["user"].loginName}
                        </#if>" datatype="*">
                    <input type="hidden" class="form-control" name="changeby"  value=" <#if Session["user"].id??>
                               ${Session["user"].id}

                        </#if>" datatype="*">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">传真</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="fax" datatype="*">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">税率</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="taxrate" datatype="*">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">税注册号码</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="regIstration" datatype="*">
                </div>

            </div>
            <div class="col-xs-4">
                <div class="form-group">
                    <label class="Validform_label">公司类型</label><span class="Validform_checktip"></span>
                    <select name="type" id="type" class=" form-control">
                        <option value="" checked>---请选择类型--</option>
                    </select>
                    <input class="easyui-combobox"
                           name="type"
                           id="type"
                           data-options="
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto' ">

                </div>
                <div class="form-group required">
                    <label class="Validform_label">法人电话号码</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="phone" datatype="*">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">注册资金</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="remitContact" datatype="*">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">货币类型</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="currencyCode" datatype="*">
                </div>
                <div class="form-group required">
                    <label class="Validform_label">创建时间</label><span class="Validform_checktip"></span>
                    <input type="text" class="form-control" name="datetime" id="datetime" readonly>
                </div>

            </div>
        </div>
        <div class="row">
            <div class="col-xs-6">
                <div class="form-group required">
                    <label class="Validform_label">公司地址</label><span class="Validform_checktip"></span>
                    <textarea type="text" class="form-control" name="address" datatype="*"></textarea>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="form-group required">
                    <label class="Validform_label">经营范围</label><span class="Validform_checktip"></span>
                    <textarea type="text" class="form-control" name="scope" datatype="*"></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-6">
                <div class="form-group required">
                    <label class="Validform_label">支付条款</label><span class="Validform_checktip"></span>
                    <textarea type="text" class="form-control" name="paymentTerms" datatype="*"></textarea>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="form-group required">
                    <label class="Validform_label">收款人地址</label><span class="Validform_checktip"></span>
                    <textarea type="text" class="form-control" name="remitaDdress" datatype="*"></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="form-group required">
                    <label class="Validform_label">备注</label><span class="Validform_checktip"></span>
                    <textarea type="text" class="form-control" name="note" datatype="*"></textarea>
                </div>
            </div>
        </div>

        <div align="center">
            <button type="button" class="btn btn-sm" onclick="javascript:$('#dlg').dialog('close');"><i
                    class="fa fa-fw fa-close"></i>&nbsp;取消
            </button>
            <button type="submit" class="btn btn-info btn-sm" id="btnSave"><i class="fa fa-fw fa-save"></i>&nbsp;保存
            </button>
        </div>
    </form>
</div>
<div id="menu" class="easyui-menu" style="width: 50px; display: none;">
    <!--放置一个隐藏的菜单Div-->
    <div id="seeMore" iconcls="glyphicon-plus">查看详情</div>
    <div class="menu-sep"></div>
    <div id="seeHisoryPrice" iconcls="glyphicon-plus">查看历史报价</div>
    <div id="seeHistoryRemove" iconcls="glyphicon-plus">查看历史退货</div>
    <div id="seeHistoryBuy" iconcls="glyphicon-plus">查看历史采购单</div>
    <div class="menu-sep"></div>
    <div id="seeLinkPeoper" iconcls="glyphicon-plus">查看联系人</div>
<#-- <div class="menu-sep"></div>
 <div id="findRole" iconcls="icon-remove"> 查看角色/资源</div>-->
    <!--具体的菜单事件请自行添加，跟toolbar的方法是基本一样的-->
</div>
<script type="text/javascript" src="${request.contextPath}/static/site/js/vendor/vendorList.js"></script>

</@template>

