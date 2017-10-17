/**
 * Created by HaiAng on 2016/5/20.
 */
(function (window, $, undefined) {
    function Vendor() {
        this._init();
    }

    Vendor.prototype = {
        constructor: Vendor,
        _init: function () {
            var that = this;
            that._cacheParam();
            that._initData();
            that._initMain();
            that._bindEven();
        },
        _cacheParam: function () {
            var that = this;
            /*DataGrid*/
            that.$btnAdd = $('#btnAdd');
            that.$btnEdit = $('#btnEdit');
            that.$btnDelete = $('#btnDelete');
            that.$dg = $('#dg');
            that.$dt = $('#dt');

            /*Dialog*/
            that.$dlg = $('#dlg');
            that.$form = $('#form');
            that.$btnSave = $('#btnSave');

            /*Other*/
            /**menu**/
            that.$seeMore = $('#seeMore');
            that.$seeHisoryPrice=$("#seeHisoryPrice");
            that.$seeHistoryRemove=$("#seeHistoryRemove");
            that.$seeHistoryBuy=$("#seeHistoryBuy");
            that.$seeLinkPeoper=$("#seeLinkPeoper");
            
            that.$Del = $('#Del');
            that.$Update = $('#Update');
            /**Tal**/
            that.$vendorTable=$("#vendorTable");
           
        },
        _initData: function () {
            var that = this;
            //公司类型
            that.ListType = Jutil.getDictListByType('company');
            that.MapType = Jutil.getDictMapByData(that.ListType);
            that.ListIsValidType = Jutil.getDictListByType('isValid');
            that.MapIsValidType = Jutil.getDictMapByData(that.ListIsValidType);

        },
        _initMain: function () {
            var that = this;
            that._form();
            that._grid();
        },
        _bindEven: function () {
            var that = this;
            that.$btnAdd.on('click', function () {
                that.add();
            });
            that.$seeMore.on('click', function () {
              that.addTab('查看供应商详情','vendor/list');
            });
            that.$seeHisoryPrice.on('click', function () {
                that.addTab('查看历史报价','vendor/list');
            });
            that.$seeHistoryRemove.on('click', function () {
                that.addTab('查看历史退货','vendor');
            });
            that.$seeHistoryBuy.on('click', function () {
                that.addTab('查看历史采购单','vendor');
            });
            that.$seeLinkPeoper.on('click', function () {
                that.addTab('查看联系人','vendorCompcpmtact/aj_getFtlView');
            });

            that.$btnEdit.on('click', function () {
                that.edit();
            });
            that.$Update.on('click', function () {
                that.edit();
            });

            that.$btnDelete.on('click', function () {
                that.delete();
            });
            that.$Del.on('click', function () {
                that.delete();
            });
            
        },
        _form: function () {
            var that = this;
            that.Validform = that.$form.Validform({
                tiptype: Jutil.tiptype,
                ajaxPost: true,
                callback: function (result) {
                    if(result.success){
                        swal(result.msg, '', "success")
                        that.$dg.datagrid('unselectAll').datagrid('reload');
                        that.$dlg.dialog('close');
                    }else{
                        swal(result.msg, '', "error");
                    }
                }
            });
        },
        _addDlgTools:function () {
            var that=this;
            that.$dlg.window({
                collapsible:false,
                minimizable:false,
                maximizable:false,
                tools:[{
                    iconCls:'icon-ok',
                    handler:function(){
                        alert('add');
                    }
                },{
                    iconCls:'icon-undo',
                    handler:function(){
                        that.resetForm();
                    }
                }]
            });
        },
        _grid: function () {
            var that = this;
            that.$dg.datagrid({
                url: context+'/vendor/findPage',
                toolbar: '#tb',
                idField: 'id',
                //sortName:'createTime,id',默认排序字段
                //sortOrder:'desc,asc',排序字段对应的order
                filterDelay: 600,
                fit: true,
                striped: true,
                singleSelect: false,
                pagination: true,
                rownumbers: true,
                remoteFilter: true,
                onBeforeLoad: function (param) {
                    var rules = param.filterRules ? JSON.parse(param.filterRules) : [];
                    for (var i in rules) {
                        var rule = rules[i];
                        param[rule.field]=rule.value;
                    }
                    return param;
                },
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', hidden: true},
                    { field:'vendorNum', title:'编号', width:100},
                    { field:'vendorName', title:'公司名称', width:100},
                 /*  { field:'type', title:'公司类型', width:100},*/
                    {
                        field: 'type', title: '公司类型', width: 100, sortable: true, formatter: function (value) {
                        return that.MapType[value] ? that.MapType[value]  : null;
                    }
                    },
                    { field:'contact', title:'法人代表', width:100},
                    { field:'phone', title:'法人电话号码', width:100},
                    { field:'address', title:'公司地址', width:100},
                /*    { field:'paymentTerms', title:'支付条款', width:100},
                    { field:'remitContact', title:'注册资金', width:100},*/
                    { field:'statuss', title:'状态', width:100,sortable: true, formatter: function (value) {
                        return that.MapIsValidType[value] ? that.MapIsValidType[value]  : null;}},
                    /*{ field:'changeby', title:'创建人', width:100},*/
                /*    {field: 'datetime', title:'创建时间', width:120, sortable: true, formatter: function (value) {
                            return value ? moment(value).format('YYYY-MM-DD HH:mm:ss') : null;
                    }},*/
                    { field:'bankaccount', title:'银行账户', width:100},
                    { field:'fax', title:'传真', width:100},
                   /* { field:'currencyCode', title:'货币类型', width:100},*/
                    { field:'shipvia', title:'运输方式', width:100},
                 /*   { field:'taxrate', title:'税率', width:100},
                    { field:'note', title:'备注', width:100},
                    { field:'remitaDdress', title:'收款人地址', width:100},
                    { field:'regIstration', title:'税注册号码', width:100},
                    { field:'groupid', title:'集团', width:100},*/
                    { field:'openAccount', title:'开户行', width:100}
                /*    { field:'mail', title:'邮箱', width:100},*/
                   /* { field:'scope', title:'经营范围', width:100},
                    { field:'orgid', title:'ID', width:100},
                    { field:'siteid', title:'ID', width:100}*/
                ]],
                    onRowContextMenu: function (e, rowIndex, rowData) {
                        //三个参数：e里面的内容很多，真心不明白，rowIndex就是当前点击时所在行的索引，rowData当前行的数据
                        e.preventDefault(); //阻止浏览器捕获右键事件
                        $(this).datagrid("clearSelections"); //取消所有选中项
                        $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
                        $('#menu').menu('show', {
                        //显示右键菜单
                        left: e.pageX,//在鼠标点击处显示菜单
                        top: e.pageY
                    });
                    }
            });

            that.$dg.datagrid('enableFilter', [{
                field: 'datetime',
                type: 'datebox',
                options: {
                    onChange: function (value) {
                        Jutil.doFilter(that.$dg, 'datetimeBox', value);
                    }
                }
             }, {
                field: 'type',
                type: 'combobox',
                options: {
                    data: that.ListType,
                    panelHeight: 'auto',
                    onChange: function (value) {
                        Jutil.doFilter(that.$dg, $(this).attr('name'), value);
                    }
                }
            }, {
                field: 'statuss',
                type: 'combobox',
                options: {
                    data: that.ListIsValidType,
                    panelHeight: 'auto',
                    onChange: function (value) {
                        Jutil.doFilter(that.$dg, $(this).attr('name'), value);
                    }
                }
            }]);
        },
        resetForm: function () {
            var that = this;
            that.Validform.resetForm();
            that.$form.find('.Validform_checktip').text('');
        },
        add: function () {
                var that = this;
                that. _addDlgTools();
                that.$form.Validform();
                that.resetForm();
             //   that._postOrgChu();
               // that.$form.attr("action", context + "/vendor/save");
              //特殊字段异步验证，比如用户重名等等
               // that.$loginName.attr("ajaxurl", context + "/user/findUser");
            //初始化默认值
            $("#datetime").val(Jutil.formatDateTime(new Date()));
          /*  var $CompType=$("#type");
            for(var i =0;i<that.ListIsValidType.length;i++){
                $CompType.append("<option value=''></option>>");
            }*/
            
                that.$dlg.dialog('setTitle', '新增').dialog('center').dialog('open');
            },
        edit: function () {
            var that = this, rows = that.$dg.datagrid('getSelections');
            if (rows.length == 1&&rows[0].id!=null) {
                var row = rows[0];
                that.resetForm();
                that.$form.form('load', row);
                that.$dlg.dialog('setTitle', '编辑').dialog('center').dialog('open');
            } else {
                swal('请选择一条数据！', '', "error");
            }
        },
        delete: function () {
            var that = this, rows = that.$dg.datagrid('getSelections'), len = rows.length;
            if (len == 0) {
                swal('请选择删除的数据！', '', 'error');
                return;
            }
            swal({
                title: '选择'+len+'条数据，确定删除？',
                text: '删除后不可恢复！',
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#DD6B55',
                closeOnConfirm: false
            }, function (isOk) {
                if (isOk) {
                    var rows = that.$dg.datagrid('getSelections'), ids = [];
                    for (var i in rows) {
                        var row = rows[i];
                        ids.push(row.id);
                    }
                    $.ajax({
                        url: context + '/vendor/batchDelete',
                        data: {ids: ids},
                        dataType: 'json',
                        traditional: true,//参数为数组,traditional为true阻止深度序列化,这样才可以传数组
                        success: function (result) {
                            if(result.success){
                                swal(result.msg, '', "success");
                                that.$dg.datagrid('unselectAll').datagrid('reload');
                            }else{
                                swal(result.msg, '', "error");
                            }
                        }
                    });
                }
            });
        },
        addTab:function (title,url) {
            var that = this, rows = that.$dg.datagrid('getSelections');
            if (rows.length == 1&&rows[0].id!=null) {
                if (that.$vendorTable.tabs('exists', title)){
                    that.$vendorTable.tabs('select', title);
                } else {
                    var content="";
                    $.ajax({
                        url: context +"/"+ url,
                        type: "POST",
                       // data:{vendorId:rows[0].id},
                        success: function (result) {
                            content=result;
                            console.log(result);
                            that.$vendorTable.tabs('add',{
                                title:title,
                                content:content,
                                closable:true
                            });
                        }
                    });

                }
            }else{
                swal({
                    title: "温馨提示",
                    text:"请选择单条数据",
                    timer: 2000,
                    type: "error",
                    showConfirmButton: false
                });
            }

        }


    };

    new Vendor();


})(this, jQuery);





