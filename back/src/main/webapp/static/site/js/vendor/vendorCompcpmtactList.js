/**
 * Created by HaiAng on 2016/5/20.
 */
(function (window, $, undefined) {
    function VendorCompcpmtact() {
        this._init();
    }

    VendorCompcpmtact.prototype = {
        constructor: VendorCompcpmtact,
        _init: function () {
            var that = this;
            that._cacheParamC();
            that._initDataC();
            that._initMainC();
            that._bindEvenC();
        },
        _cacheParamC: function () {
            var that = this;
            /*DataGrid*/
            that.$btnAdd = $('#btnAdd');
            that.$btnEdit = $('#btnEdit');
            that.$btnDelete = $('#btnDelete');
            that.$dgPerson = $('#dgPerson');
            that.$dt = $('#dt');

            /*Dialog*/
            that.$dlgPerson = $('#dlgPerson');
            that.$formPerson = $('#formPerson');
            that.$btnSave = $('#btnSave');

            /*Other*/
            /**menu**/
            that.$Add = $('#Add');
            that.$Del = $('#Del');
            that.$Update = $('#Update');
        },
        _initDataC: function () {
            var that = this;

        },
        _initMainC: function () {
            var that = this;
            that._formC();
            that._gridC();
        },
        _bindEvenC: function () {
            var that = this;
            that.$btnAdd.on('click', function () {
                that.add();
            });
            that.$Add.on('click', function () {
                that.add();
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
        _formC: function () {
            var that = this;
            that.Validform = that.$formPerson.Validform({
                tiptype: Jutil.tiptype,
                ajaxPost: true,
                callback: function (result) {
                    if(result.success){
                        swal(result.msg, '', "success")
                        that.$dgPerson.datagrid('unselectAll').datagrid('reload');
                        that.$dlgPerson.dialog('close');
                    }else{
                        swal(result.msg, '', "error");
                    }
                }
            });
        },
        _gridC: function () {
            var that = this;
            that.$dgPerson.datagrid({
                url: context+'/vendorCompcpmtact/findPage',
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
                    { field:'compcontactnum', title:'联系人编号', width:100},
                    { field:'vendorId', title:'所属供应商', width:100},
                    { field:'compcontactid', title:'公司联系人标识', width:100},
                    { field:'company', title:'公司', width:100},
                    { field:'contact', title:'联系人', width:100},
                    { field:'position', title:'职位', width:100},
                    { field:'voicePhone', title:'办公电话', width:100},
                    { field:'faxPhone', title:'传真', width:100},
                    { field:'email', title:'邮箱', width:100},
                    { field:'homePhone', title:'家庭电话', width:100},
                    { field:'cellPhone', title:'移动电话', width:100},
                    { field:'vendorNum', title:'Vendor_num', width:100},
                    { field:'description', title:'备注', width:100},
                    { field:'status', title:'状态', width:100},
                    { field:'orgid', title:'ORGID', width:100},
                    { field:'siteid', title:'SITEID', width:100}
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

            that.$dgPerson.datagrid('enableFilter', []);
        },
        resetFormC: function () {
            var that = this;
            that.Validform.resetForm();
            that.$form.find('.Validform_checktip').text('');
        },
            add: function () {
                var that = this;
                that.$formPerson.Validform();
                that.resetFormC();
              //特殊字段异步验证，比如用户重名等等
                that.$dlgPerson.dialog('setTitle', '新增联系人').dialog('center').dialog('open');
            },
        edit: function () {
            var that = this, rows = that.$dgPerson.datagrid('getSelections')
            if (rows.length == 1) {
                var row = rows[0];
                that.resetForm();
                that.$form.form('load', row);
                that.$dlgPerson.dialog('setTitle', '编辑联系人').dialog('center').dialog('open');
            } else {
                swal('请选择一条数据！', '', "error");
            }
        },
        delete: function () {
            var that = this, rows = that.$dgPerson.datagrid('getSelections'), len = rows.length;
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
                    var rows = that.$dgPerson.datagrid('getSelections'), ids = [];
                    for (var i in rows) {
                        var row = rows[i];
                        ids.push(row.id);
                    }
                    $.ajax({
                        url: context + '/vendorCompcpmtact/batchDelete',
                        data: {ids: ids},
                        dataType: 'json',
                        traditional: true,//参数为数组,traditional为true阻止深度序列化,这样才可以传数组
                        success: function (result) {
                            if(result.success){
                                swal(result.msg, '', "success")
                                that.$dgPerson.datagrid('unselectAll').datagrid('reload');
                            }else{
                                swal(result.msg, '', "error");
                            }
                        }
                    });
                }
            });
        }

    };

    new VendorCompcpmtact();


})(this, jQuery);





