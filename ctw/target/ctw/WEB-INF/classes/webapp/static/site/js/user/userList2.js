/**
 * Created by HAiAng on 2016/4/8.
 */
(function (window, $, undefined) {

    function User() {
        this._init();
    }

    User.prototype = {
        constructor: User,
        _init: function () {
            var that = this;
            that._cacheParam();//缓存变量
            that._initData();//初始化需要的数据
            that._initMain();//初始化功能模块
            that._bindEven();//绑定事件
        },
        _cacheParam: function () {
            var that = this;
            that.dlgOptions = new Array("close", "open");
            /*DataGrid*/
            that.$btnAdd = $('#btnAdd');
            that.$btnEdit = $('#btnEdit');
            that.$Add = $('#Add');
            that.$Del = $('#Del');
            that.$Update = $('#Update');
            that.$btnHistory = $('#btnHistory');
            that.$btnDelete = $('#btnDelete');
            that.$btnQuery = $('#btnQuery');
            that.$dg = $('#dgUserList');
            that.$dt = $('#dt');

            that.$haveRole = $("#haveRole");
            that.$wrongRole = $("#wrongRole");

            /*Dialog*/
            that.$dlg = $('#dlg');
            that.$form = $('#form');
            that.$btnCancel = $('#btnCancel');
            that.$btnSave = $('#btnSave');
            that.$dlgRoleOrg = $("#dlgRoleOrg");
            that.$btnEditole = $("#btnEditRole");

            /*Other*/
            that.$daterange = $('#daterange');
            that.$birth = $('#birth');
            that.$checkOrgId = $("#checkOrgId").val();
            that.$editRole = $("#editRole");
            that.$findRole = $("#findRole");
            that.$loginName = $("#loginName");
            /**zTree**/
            that.$orgTree = $("#orgTree");
            that.$rTree = $("#resourceTree");

            /*combox */
            that.$orgNameBox = $("#orgName");
            that.$postNameBox = $("#postName");
            that.$btnRefresh = $("#btnRefresh");

        },
        _initData: function () {
            var that = this;
            Init._initData(that);
        },
        _initMain: function () {
            var that = this;
            that._grid();
            Init._init_org_tree($('#orgId'), that.$orgNameBox, that.$orgTree, that.$dg);
            Init._init_form(that);
            Init._init_select_Date(that.$daterange);
            Init._init_start_end_date(that.$birth);
        },
        _grid: function () {
            var that = this;
            that.$dg.datagrid({
                toolbar: '#tb',
                idField: 'id',
                remoteFilter: true,
                fit: true,
                url: context + "/user/findList",
                // fitColumns: true,
                striped: true,
                singleSelect: false,
                // nowrap: false,
                pagination: true,
                rownumbers: true,
                filterDelay: 600,
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', hidden: true},
                    {field: 'loginName', title: '登录名', width: 80},
                    {field: 'fullName', title: '真实姓名', width: 80},
                    {field: 'mobile', title: '手机号码', width: 80},
                    {field: 'tel', title: '电话号码', width: 80},
                    {field: 'email', title: '邮箱', width: 80},
                    {
                        field: 'genter', title: '性别', width: 60, sortable: true, formatter: function (value) {
                        return value ? that.MapGender[value] : null;
                    }
                    },
                    {field: 'postName', title: '所属岗位', width: 80},
                    {field: 'orgName', title: '所属组织', width: 80},
                    {
                        field: 'status', title: '状态', width: 80, sortable: true, formatter: function (value) {
                        return value ? that.MapStatus[value] : null;
                    }
                    },
                    {
                        field: 'isValid', title: '是否有效', width: 80, sortable: true, formatter: function (value) {
                        return value ? that.MapIsValid[value] : null;
                    }
                    }
                ]],
                onBeforeLoad: function (param) {
                    var rules = param.filterRules ? JSON.parse(param.filterRules) : [];
                    for (var i in rules) {
                        var rule = rules[i];
                        param[rule.field] = rule.value;
                    }
                    return param;
                },
                queryParams: {
                    orgId: that.$checkOrgId
                },
                /* onClickRow:that._dataGridClick,*/
                onRowContextMenu: function (e, rowIndex, rowData) {
                    //三个参数：e ? rowIndex就是当前点击时所在行的索引，rowData当前行的数据
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
                field: 'genter',
                type: 'combobox',
                options: {
                    data: that.ListGender,
                    panelHeight: 'auto',
                    onChange: function (value) {
                        Jutil.doFilter(that.$dg, $(this).attr('name'), value);
                    }
                }
            }, {
                field: 'status',
                type: 'combobox',
                options: {
                    data: that.ListSatus,
                    panelHeight: 'auto',
                    onChange: function (value) {
                        Jutil.doFilter(that.$dg, $(this).attr('name'), value);
                    }
                }
            }, {
                field: 'isValid',
                type: 'combobox',
                options: {
                    data: that.ListIsValid,
                    panelHeight: 'auto',
                    onChange: function (value) {
                        Jutil.doFilter(that.$dg, $(this).attr('name'), value);
                    }
                }
            }, {
                field: 'birth',
                type: 'datebox',
                options: {
                    editable: false,
                    onChange: function (value) {
                        Jutil.doFilter(that.$dg, $(this).attr('name'), value);
                    }
                }
            }]);
        },
        _haveRoleGrid: function () {
            var that = this;
            that.$haveRole.datagrid({
                fit: true,
                striped: true,
                singleSelect: true,
                pagination: true,
                rownumbers: true,
                idField: 'id',
                filterDelay: 500,
                columns: [[
                    {field: 'id', hidden: true},
                    {field: 'roleName', title: '角色编码', width: '20%'},
                    {field: 'description', title: '角色名称', width: '20%'},
                    {
                        field: 'fromSource', title: '关系', width: '20%',
                        formatter: function (value, rec) {
                            var text = "";
                            var source = rec.fromSource;
                            if (source == 'my') {
                                text = "直属角色"
                            }
                            if (source == 'post') {
                                text = "继承岗位角色"
                            }
                            if (source == 'org') {
                                text = "继承组织角色"
                            }
                            return text;
                        }
                    },
                    {
                        field: 'opt', title: '操作', width: '30%', align: 'center',
                        formatter: function (value, rec) {
                            var source = rec.fromSource;
                            var delId = rec.id;
                            var btn;
                            if (source != 'my') {
                                btn = '<button type="button"  disabled="disabled" class="btn btn-danger btn-sm" id="delRole">' +
                                    '<i class="fa fa-fw fa-trash-o"></i>&nbsp;删除角色</button>'
                            } else {
                                btn = '<button type="button"    onclick="deleteUsrRole(' + delId + ');"  class="btn btn-danger btn-sm" id="delRole">' +
                                    '<i class="fa fa-fw fa-trash-o"></i>&nbsp;删除角色</button>'
                            }
                            return btn;
                        }
                    }
                    /*{field: 'fullName', title: '操作', width: '48%'}*/
                ]],
                onSelect: function (index, row) {
                    that.$wrongRole.datagrid('clearSelections');
                },
                onDblClickRow: function (index, row) {
                    that.$wrongRole.datagrid('clearSelections');
                    that.$rTree.tree({
                        url: context + '/resource/findResourceTree',
                        queryParams: {
                            roleId: row.id
                        },
                        checkbox: true,
                        onLoadSuccess: function () {
                            $(this).find('span.tree-checkbox').unbind().click(function () {
                                return false;
                            });
                        }
                    });
                }
            });

        },
        _wrongRoleGrid: function () {
            var that = this;
            that.$wrongRole.datagrid({
                fit: true,
                striped: true,
                singleSelect: true,
                pagination: true,
                rownumbers: true,
                idField: 'id',
                filterDelay: 500,
                columns: [[
                    {field: 'id', hidden: true},
                    {field: 'roleName', title: '角色编码', width: '30%'},
                    {field: 'description', title: '角色名称', width: '30%'},
                    {
                        field: 'opt', title: '操作', width: '30%', align: 'center',
                        formatter: function (value, rec) {
                            var delId = rec.id;
                            var btn = '<button type="button"   onclick="addUsrRole(' + delId + ');"    class="btn btn-primary btn-sm" id="addRole"><i class="fa fa-fw fa-plus"></i>&nbsp;添加角色</button>'
                            return btn;
                        }
                    }
                ]],
                onSelect: function (index, row) {
                    that.$haveRole.datagrid('clearSelections');
                }, onDblClickRow: function (index, row) {
                    that.$wrongRole.datagrid('clearSelections');
                    that.$rTree.tree({
                        url: context + '/resource/findResourceTree',
                        queryParams: {
                            roleId: row.id
                        },
                        checkbox: true,
                        onLoadSuccess: function () {
                            $(this).find('span.tree-checkbox').unbind().click(function () {
                                return false;
                            });
                        }
                    });
                }
            });

        },
        /*       _form: function () {
                   var that = this;
                   that.validform = that.$form.Validform({
                       tiptype: Jutil.tiptype,
                       ajaxPost: true,
                       callback: function (result) {
                           if (result.success) {
                               swal({
                                   title: "温馨提示",
                                   text: result.msg,
                                   timer: 2000,
                                   type: "success",
                                   showConfirmButton: false
                               });
                               that.$dlg.dialog('close');
                               that.$dg.datagrid('reload');

                           } else {
                               swal(result.msg, '', "error");
                           }
                       }
                   });
               },
               _orgTree: function () {
                   var that = this;
                   that.$orgTree.tree({
                       url: context + '/org/findOrgTree',
                       checkbox: false,
                       onClick: function (node) {
                           that.$dg.datagrid('clearSelections');
                           that.$dg.datagrid('load', {
                               orgId: node.id
                           });
                           $("#orgId").val(node.id);
                           that.$orgNameBox.val(node.text);
                       }
                   });
               },*/
        _bindEven: function () {
            var that = this;
            that.$btnAdd.on('click', function () {
                Optionsutil._option_not_function();
            });
            that.$btnRefresh.on('click', function () {
                var rows = that.$dg.datagrid('getSelections');
                if (rows.length == 1) {
                    var row = rows[0];
                    $.ajax({
                        url: context + "/user/restPwd",
                        type: "POST",
                        data: {id: row.id},
                        success: function (result) {
                            console.log(result);
                            if (result.success) {
                                swal(result.msg, '', "success")
                                //swal(result.msg, '', "success")
                                swal({
                                    title: "温馨提示",
                                    text: result.msg,
                                    /* timer: 2000,*/
                                    type: "success",
                                    showConfirmButton: true
                                });
                                /*  that.$dg.datagrid('reload');
                                 that.$dlg.dialog('close');*/
                            } else {
                                swal(result.msg, '', "error");
                            }
                        }

                    }, 'json');
                } else {
                    swal('请选择单个帐号进行密码重置！.....！', '', "error");
                }
            });
            that.$btnEditole.on('click', function () {
                that.editRole();
            });
            that.$btnHistory.on('click', function () {
                Optionsutil._option_not_function();
            });
            that.$Add.on('click', function () {
                that.$form.Validform();
                Init._init_reset_form(that);
                Optionsutil._option_btn_add(that, context + "/user/create", '新增用户');
            });
            that.$Del.on('click', function () {
                that.delete();
            });
            that.$btnDelete.on('click', function () {
                that.delete();
            });
            that.$btnEdit.on('click', function () {
                that.edit();
            });
            that.$Update.on('click', function () {
                that.edit();
            });
            that.$btnQuery.on('click', function () {
                Option._option_not_function();
            });
            that.$btnCancel.on('click', function () {
                that.$dlg.dialog('close');
            });
            that.$findRole.on('click', function () {
                that.editRole();
            });
            that.$editRole.on('click', function () {
                that.editRole();
            });


        },
        edit: function () {
            var that = this;
            var rows = that.$dg.datagrid('getSelections');
            if (rows.length == 1) {
                var row = rows[0];
                that.resetForm();
                that.$form.form('load', row);
                that._postOrgChu();
                that.$form.attr("action", context + "/user/update");
                that.$dlg.dialog('setTitle', '编辑用户信息').dialog('center').dialog('open');
            } else {
                swal('请选择一行数据！', '', "error");
            }
        },
        delete: function () {
            var that = this, rows = that.$dg.datagrid('getSelections');
            if (rows.length > 0) {
                swal({
                    title: '删除后不可恢复！',
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#DD6B55',
                    closeOnConfirm: false
                }, function (isOk) {
                    if (isOk) {
                        var rows = that.$dg.datagrid('getSelections'), ids = [], url = "";
                        for (var i in rows) {
                            var row = rows[i];
                            ids.push(row.id);
                        }
                        $.ajax({
                            url: context + '/user/batchDelete',
                            data: {ids: ids},
                            dataType: 'json',
                            traditional: true,//参数为数组,traditional为true阻止深度序列化,这样才可以传数组
                            success: function (result) {
                                if (result.success) {
                                    swal({
                                        title: "温馨提示",
                                        text: result.msg,
                                        timer: 2000,
                                        type: "success",
                                        showConfirmButton: false
                                    });
                                    that.$dg.datagrid('unselectAll').datagrid('reload');
                                } else {
                                    swal(result.msg, '', "error");
                                }
                            }
                        });
                    }
                });
            } else {
                swal('请选择删除的数据！', '', 'error');
            }
        },
        editRole: function () {
            var that = this, rows = that.$dg.datagrid('getSelections');
            if (rows.length == 1) {
                that._haveRoleGrid();
                that._wrongRoleGrid();
                var row = rows[0];
                //开始加载数据
                //加载拥有的角色
                that.$haveRole.datagrid('clearSelections');
                that.$haveRole.datagrid('options').url = context + "/role/findHaveListPage";
                that.$haveRole.datagrid('load', {
                    userId: row.id,
                    orgId: row.orgId,
                    postId: row.postId
                });
                //加载未拥有的角色
                that.$wrongRole.datagrid('clearSelections');
                that.$wrongRole.datagrid('options').url = context + "/role/findHaveListPage";
                that.$wrongRole.datagrid('load', {
                    userId: row.id,
                    orgId: row.orgId,
                    postId: row.postId,
                    findType: 'not'
                });
                //为删除用户与角色储备变量值
                $("#userId").val(row.id);
                //加载拥有的所有资源
                that.$dlgRoleOrg.dialog('setTitle', '查看用户的角色与资源').dialog('center').dialog('open');
            } else {
                swal('请选择一行数据！', '', "error");
            }
        },
        _postOrgChu: function () {
            var that = this;
            that.$orgNameBox.combotree({
                url: context + '/org/findOrgTree',
                required: true,
                queryParams: {
                    orgId: $("#orgId").val()
                },
                onClick: function (node) {
                    $("#orgId").val(node.id);
                    that.$postNameBox.combobox({
                        url: context + '/post/findList',
                        queryParams: {orgId: node.id},
                        valueField: 'id',
                        textField: 'postName',
                        onSelect: function (rec) {
                            $("#postId").val(rec.id);
                        }
                    });
                }
            });
            that.$postNameBox.combobox({
                url: context + '/post/findList',
                queryParams: {orgId: $("#orgId").val()},
                valueField: 'id',
                textField: 'postName',
                onSelect: function (rec) {
                    $("#postId").val(rec.id);
                }
            });
        }
    };
    deleteUsrRole = function (rec) {
        var uId = $("#userId").val();
        $.ajax({
            url: context + "/roleUser/deleteByUIdRId/" + uId + "/" + rec,
            type: "POST",
            beforeSend: function () {
            },
            success: function (data) {
                $("#haveRole").datagrid('reload');
                $("#wrongRole").datagrid('reload');
            }
        }, 'json');
    };
    addUsrRole = function (rec) {
        var uId = $("#userId").val();
        $.ajax({
            url: context + "/roleUser/create",
            type: "POST",
            data: {uid: uId, roleId: rec},
            beforeSend: function () {
            },
            success: function (data) {
                $("#haveRole").datagrid('reload');
                $("#wrongRole").datagrid('reload');
            }
        }, 'json');
    }
    new User();
})(this, jQuery);

