/**
 * Created by HaiAng on 2016/5/20.
 */
(function (window, $, undefined) {
    function role() {
        this._init();
    }

    role.prototype = {
        constructor: role,
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
            that.$daterange = $('#daterange');
            that.$birth = $('#birth');
            that.$btnEditole = $("#btnEditRole");
            that.$dlgRoleOrg = $("#dlgRoleOrg");
            that.$userRole = $("#userRole");
            that.$postRole = $("#postRole");
            that.$orgRole = $("#orgRole");
            that.$resourceTree = $("#resourceTree");
            /**操作角色btn**/
            that.$btnAddPost = $("#btnAddPost");
            that.$btnAddOrg = $("#btnAddOrg");
            that.$btnAddUser = $("#btnAddUser");
            that.$dlgAddOrg = $("#dlgAddOrg");
            that.$dlgAddPost = $("#dlgAddPost");
            that.$dlgAddUser = $("#dlgAddUser");
            that.$btnDellListOrg = $("#btnDellListOrg");
            that.$btnDellListPost = $("#btnDellListPost");
            that.$btnDellListUser = $("#btnDellListUser");
            that.$btnQueryNotLinkUser = $("#btnQueryNotLinkUser");
            that.$btnQueryNotLinkPost = $("#btnQueryNotLinkPost");
            that.$btnSaveUserRole = $("#btnSaveUserRole");
            that.$btnSavePostRole = $("#btnSavePostRole");
            /***关联角色*/
            that.$linkUser = $("#linkUser");
            that.$nolinkUser = $("#noLinkUser");
            that.$linkPost = $("#linkPost");
            that.$notlinkPost = $("#notLinkPost");
            that.$linkPost = $("#linkPost");

            that.$orgTree = $("#orgTree");
            that.$orgRoleTree = $("#orgRoleTree");

        },
        _initData: function () {
            var that = this;
            /**是否有效  1生效 2失效 **/
            that.ListIsValid = [{value: '', text: '全部'}, {value: '1', text: '有效'}, {value: '2', text: '失效'}];
            that.MapIsValid = {2: '失效', 1: '有效'};
            that.$daterange.daterangepicker({
                ranges: {
                    '今天': [moment(), moment()],
                    '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                    '过去7天': [moment().subtract(6, 'days'), moment()],
                    '过去30天': [moment().subtract(29, 'days'), moment()],
                    '本月': [moment().startOf('month'), moment().endOf('month')],
                    '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                }
            });
            that.$birth.daterangepicker({
                startDate: new Date(),
                maxDate: new Date(),
                singleDatePicker: true,
                showDropdowns: true
            });

        },
        _initMain: function () {
            var that = this;
            that._form();
            that._grid();
        },
        _bindEven: function () {
            var that = this;
            that.$btnAdd.on('click', function () {
                that.resetForm();
                var d = new Date();
                $("#createDate").val(d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate());
                that.$form.attr("action", context + "/role/create");
                that.$dlg.dialog('setTitle', '新增角色').dialog('center').dialog('open');
            });
            that.$btnEdit.on('click', function () {
                var rows = that.$dg.datagrid('getSelections');
                if (rows.length == 1) {
                    var row = rows[0];
                    that.resetForm();
                    that.$form.form('load', row);
                    that.$form.attr("action", context + "/role/update");
                    $("#createDate").val(moment(new Date(row.createDate)).format('YYYY-MM-DD'));
                    that.$dlg.dialog('setTitle', '编辑角色').dialog('center').dialog('open');
                } else {
                    swal('请选择一条数据！', '', "error");
                }
            });
            that.$btnDelete.on('click', function () {
                that.delete();
            });
            that.$btnEditole.on('click', function () {
                that.editRole();
            });
            that.$btnAddOrg.on('click', function () {
                that._orgRoleTree();
                that.$dlgAddOrg.dialog('setTitle', '关联组织').dialog('center').dialog('open');
            });
            that.$btnAddPost.on('click', function () {
                that._linkPostTreeGrid();
            });
            that.$btnAddUser.on('click', function () {
                //初始化已关联用户
                that._linkUserGrid();
                var rows = that.$dg.datagrid('getSelections');
                that.$linkUser.datagrid('clearSelections');
                that.$linkUser.datagrid('options').url = context + "/roleUser/findListByRoleId";
                that.$linkUser.datagrid('load', {
                    roleId: rows[0].id
                });
                //初始化未关联用户
                that._nolinkUserGrid();
                that.$nolinkUser.datagrid('clearSelections');
                that.$nolinkUser.datagrid('options').url = context + "/user/findUserNotLinkRolePage";
                that.$nolinkUser.datagrid('load', {
                    roleId: rows[0].id
                });

                that.$dlgAddUser.dialog('setTitle', '关联用户').dialog('center').dialog('open');
            });
            that.$btnDellListOrg.on('click', function () {
                deleteOrgRole();
            });
            that.$btnDellListPost.on('click', function () {
                deletePostRole();
            });
            that.$btnDellListUser.on('click', function () {
                deleteUserRole();
            });
            that.$btnQueryNotLinkUser.on('click', function () {
                that.findNotLinkUser();
            });
            that.$btnQueryNotLinkPost.on('click', function () {
                that.findNotLinkPost();
            });
            that.$btnSaveUserRole.on('click', function () {
                var selectUserRows = that.$nolinkUser.datagrid('getSelections');
                var roleRows = that.$dg.datagrid('getSelections');
                var len = selectUserRows.length, ids = [];
                if (len == 0) {
                    swal('请选择要关联的用户！', '', 'error');
                    return;
                }
                for (var i in selectUserRows) {
                    var row = selectUserRows[i];
                    ids.push(row.id);
                }
                $.ajax({
                    url: context + "/roleUser/batchCreate",
                    data: {uIds: ids, roleId: roleRows[0].id},
                    dataType: 'json',
                    type: "POST",
                    traditional: true,//参数为数组,traditional为true阻止深度序列化,这样才可以传数组
                    success: function (result) {
                        swal(result.msg, '', "success")
                        that.$linkUser.datagrid('unselectAll').datagrid('reload');
                        that.$nolinkUser.datagrid('unselectAll').datagrid('reload');
                        that.$userRole.datagrid('unselectAll').datagrid('reload');
                    }
                });

            });
            that.$btnSavePostRole.on('click', function () {
                var selectUserRows = that.$notlinkPost.datagrid('getSelections');
                var roleRows = that.$dg.datagrid('getSelections');
                var len = selectUserRows.length, ids = [];
                if (len == 0) {
                    swal('请选择要关联的岗位！', '', 'error');
                    return;
                }
                for (var i in selectUserRows) {
                    var row = selectUserRows[i];
                    ids.push(row.id);
                }
                $.ajax({
                    url: context + "/postRole/batchCreate",
                    data: {postIds: ids, roleId: roleRows[0].id},
                    dataType: 'json',
                    type: "POST",
                    traditional: true,//参数为数组,traditional为true阻止深度序列化,这样才可以传数组
                    success: function (result) {
                        Jutil.showTips('success', result.data);
                        swal(result.msg, '', "success")
                        that.$linkPost.datagrid('unselectAll').datagrid('reload');
                        that.$notlinkPost.datagrid('unselectAll').datagrid('reload');
                        that.$postRole.datagrid('unselectAll').datagrid('reload');
                    }
                });

            });
        },
        _form: function () {
            var that = this;
            that.Validform = that.$form.Validform({
                tiptype: Jutil.tiptype,
                ajaxPost: true,
                callback: function (result) {
                    if (result.success) {
                        swal(result.msg, '', "success")
                        that.$dg.datagrid('unselectAll').datagrid('reload');
                        that.$dlg.dialog('close');
                    } else {
                        swal(result.msg, '', "error");
                    }
                }
            });
        },
        _grid: function () {
            var that = this;
            that.$dg.datagrid({
                url: context + '/role/findPage',
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
                        param[rule.field] = rule.value;
                    }
                    return param;
                },
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', hidden: true},
                    {field: 'roleName', title: '角色名称', width: 100},
                    {field: 'description', title: '备注', width: 100},
                    {
                        field: 'isValid', title: '是否有效', width: 80, sortable: true,
                        formatter: function (value) {
                            return value ? that.MapIsValid[value] : null;
                        }
                    },
                    {
                        field: 'createDate', title: '创建时间', width: 120, sortable: true,
                        formatter: function (value) {
                            return value ? moment(value).format('YYYY-MM-DD') : null;
                        }
                    },
                    {field: 'createNameId', title: '创建人', width: 100}
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
            that.$dg.datagrid('enableFilter', [
                {
                    field: 'createDate',
                    type: 'datebox',
                    options: {
                        onChange: function (value) {
                            Jutil.doFilter(that.$dg, 'createDate', value);
                        }
                    }
                },
                {
                    field: 'isValid',
                    type: 'combobox',
                    options: {
                        data: that.ListIsValid,
                        panelHeight: 'auto',
                        onChange: function (value) {
                            Jutil.doFilter(that.$dg, $(this).attr('name'), value);
                        }
                    }
                }
            ]);
        },
        _orgTree: function () {
            var that = this;
            that.$orgTree.tree({
                url: context + '/org/findOrgTree',
                checkbox: false,
                /* dnd: false,
                 animate: true,*/
                lines: true,
                onClick: function (node) {
                    that.$notlinkPost.datagrid('clearSelections');
                    that.$notlinkPost.datagrid('load',
                        {
                            orgId: node.id
                        });

                },
                formatter: function (node) {
                    var isValid = node.isValid;
                    if (isValid != 1) {
                        return '<span style="text-decoration:line-through; color: firebrick">' + node.text + '</span>';
                    } else {
                        return node.text;
                    }
                }

            });
        },
        _orgRoleTree: function () {
            var that = this;
            var roleRows = that.$dg.datagrid('getSelections');
            that.$orgRoleTree.tree({
                url: context + '/org/findOrgTreeByRoleId',
                checkbox: true,
                lines: true,
                queryParams: {
                    roleId: roleRows[0].id
                },
                onCheck: function (node, checked) {
                    var ids = '';
                    if (!that.$orgRoleTree.tree('isLeaf', node.target)) {//判断是否是叶子节点
                        var children = that.$orgRoleTree.tree('getChildren', node.target);
                        for (var i = 0; i < children.length; i++) {
                            ids += children[i].id + ',';
                        }
                        ids += node.id;
                    } else {
                        ids = node.id;
                    }
                    var url;
                    if (checked) {
                        url = context + "/orgRole/batchCreate";
                    } else {
                        url = context + "/orgRole/batchDeleteByRoleId"
                    }
                    $.ajax({
                        url: url,
                        type: "POST",
                        data: {roleId: roleRows[0].id, ids: ids},
                        traditional: true,//参数为数组,traditional为true阻止深度序列化,这样才可以传数组
                        success: function (result) {
                            if (result.success) {
                                Jutil.showTips('success', result.msg);
                                that.$orgRole.datagrid('unselectAll').datagrid('reload');
                            } else {
                                Jutil.showTips('error', '授权失败');
                            }
                        }
                    }, 'json');
                },
                formatter: function (node) {
                    var isValid = node.isValid;
                    if (isValid != 1) {
                        return '<span style="text-decoration:line-through; color: firebrick">' + node.text + '</span>';
                    } else {
                        return node.text;
                    }
                }
            });
        },
        _postRole: function () {
            var that = this;
            that.$postRole.datagrid({
                fit: true,
                striped: true,
                pagination: true,
                rownumbers: true,
                toolbar: '#tbPost',
                singleSelect: false,
                idField: 'id',
                filterDelay: 500,
                remoteFilter: true,
                columns: [[
                    {field: 'id', hidden: true},
                    {field: 'ck', checkbox: true},
                    {field: 'postName', title: '岗位名称', width: '60%'},
                    {
                        field: 'opt', title: '操作', width: '61px', align: 'center',
                        formatter: function (value, rec) {
                            var source = rec.fromSource;
                            var delId = rec.id;
                            var btn = '<button type="button"    onclick="deletePostRole(' + delId + ');"  class="btn btn-danger btn-sm" id="delRole">' +
                                '<i class="fa fa-fw fa-trash-o"></i>&nbsp;删除</button>'
                            return btn;
                        }
                    }

                ]]
            });

        },
        _orgRole: function () {
            var that = this;
            that.$orgRole.datagrid({
                fit: true,
                striped: true,
                singleSelect: false,
                pagination: true,
                rownumbers: true,
                toolbar: '#tbOrg',
                idField: 'id',
                filterDelay: 500,
                remoteFilter: true,
                columns: [[
                    {field: 'id', hidden: true},
                    {field: 'ck', checkbox: true},
                    {field: 'orgName', title: '组织名称', width: '60%'},
                    {
                        field: 'opt', title: '操作', width: '61px', align: 'center',
                        formatter: function (value, rec) {
                            var delId = rec.id;
                            var btn = '<button type="button"    onclick="deleteOrgRole(' + delId + ');"  class="btn btn-danger btn-sm" id="delRole">' +
                                '<i class="fa fa-fw fa-trash-o"></i>&nbsp;删除</button>'
                            return btn;
                        }
                    }

                ]]
            });

        },
        _userRole: function () {
            var that = this;
            that.$userRole.datagrid({
                fit: true,
                striped: true,
                singleSelect: false,
                pagination: true,
                rownumbers: true,
                idField: 'id',
                toolbar: '#tbUser',
                filterDelay: 500,
                columns: [[
                    {field: 'id', hidden: true},
                    {field: 'ck', checkbox: true},
                    {field: 'userName', title: '用户名称', width: '50%'},
                    {
                        field: 'opt', title: '操作', width: '30%', align: 'center',
                        formatter: function (value, rec) {
                            var delId = rec.id;
                            var btn = '<button type="button"    onclick="deleteUserRole(' + delId + ');"  class="btn btn-danger btn-sm" id="delRole">' +
                                '<i class="fa fa-fw fa-trash-o"></i>&nbsp;删除</button>'
                            return btn;
                        }
                    }

                ]]
            });

        },
        _linkUserGrid: function () {
            var that = this;
            that.$linkUser.datagrid({
                fit: true,
                striped: true,
                singleSelect: false,
                pagination: true,
                rownumbers: true,
                idField: 'id',
                // toolbar: '#tbUser',
                filterDelay: 500,
                columns: [[
                    {field: 'id', hidden: true},
                    {field: 'userName', title: '用户名称', width: '80%'}
                ]]
            });

        },
        _nolinkUserGrid: function () {
            var that = this;
            that.$nolinkUser.datagrid({
                fit: true,
                striped: true,
                singleSelect: false,
                pagination: true,
                rownumbers: true,
                idField: 'id',
                toolbar: '#tbAddUser',
                filterDelay: 500,
                columns: [[
                    {field: 'id', hidden: true},
                    {field: 'ck', checkbox: true},
                    {field: 'orgName', title: '所属组织', width: '40%'},
                    {field: 'fullName', title: '用户名称', width: '40%'}
                ]]
            });

        },
        _notlinkPost: function () {
            var that = this;
            that.$notlinkPost.datagrid({
                fit: true,
                striped: true,
                singleSelect: false,
                pagination: true,
                rownumbers: true,
                idField: 'id',
                toolbar: '#tbAddPost',
                filterDelay: 500,
                columns: [[
                    {field: 'id', hidden: true},
                    {field: 'ck', checkbox: true},
                    {field: 'orgName', title: '所属组织', width: '40%'},
                    {field: 'postName', title: '岗位名称', width: '40%'}
                ]]
            });
        },
        _linkPost: function () {
            var that = this;
            that.$linkPost.datagrid({
                fit: true,
                striped: true,
                // url:
                singleSelect: false,
                pagination: true,
                rownumbers: true,
                idField: 'id',
                queryParams: {
                    orgId: that.$checkOrgId
                },
                //toolbar: '#tbAddPost',
                filterDelay: 500,
                columns: [[
                    {field: 'id', hidden: true},
                    {field: 'postName', title: '岗位名称'}
                ]]
            });
        },
        _linkPostTreeGrid: function () {
            var that = this;
            var rows = that.$dg.datagrid('getSelections');
            var row = rows[0];
            that._orgTree();
            that._linkPost();
            that.$linkPost.datagrid('clearSelections');
            that.$linkPost.datagrid('options').url = context + '/postRole/findListByRoleId';
            that.$linkPost.datagrid('load', {
                roleId: row.id
            });
            that._notlinkPost();
            that.$notlinkPost.datagrid('clearSelections');
            that.$notlinkPost.datagrid('options').url = context + '/post/seachNotLinkPost';
            that.$notlinkPost.datagrid('load', {
                roleId: row.id
            });
            that.$dlgAddPost.dialog('setTitle', '关联岗位').dialog('center').dialog('open');


        },
        findNotLinkUser: function () {
            var that = this;
            var rows = that.$dg.datagrid('getSelections');
            that.$nolinkUser.datagrid('clearSelections');
            that.$nolinkUser.datagrid('options').url = context + "/user/findUserNotLinkRolePage";
            that.$nolinkUser.datagrid('load', {
                roleId: rows[0].id,
                inputVal: $("#inputVal").val()
            });
        },
        findNotLinkPost: function () {
            var that = this;
            var rows = that.$dg.datagrid('getSelections');
            that.$notlinkPost.datagrid('clearSelections');
            that.$notlinkPost.datagrid('options').url = context + '/post/seachNotLinkPost',
                that.$notlinkPost.datagrid('load', {
                    //roleId: rows[0].id,
                    postName: $("#inputValPost").val()
                });
        },
        resetForm: function () {
            var that = this;
            that.Validform.resetForm();
            that.$form.find('.Validform_checktip').text('');
        },
        delete: function () {
            var that = this, rows = that.$dg.datagrid('getSelections'), len = rows.length, ids = [], delRow = 0;
            if (len == 0) {
                swal('请选择删除的数据！', '', 'error');
                return;
            }
            for (var i in rows) {
                var row = rows[i];
                ids.push(row.id);
            }
            $.ajax({
                url: context + "/role/getCountByRole",
                data: {ids: ids},
                dataType: 'json',
                type: "POST",
                traditional: true,//参数为数组,traditional为true阻止深度序列化,这样才可以传数组
                success: function (result) {
                    if (result.success) {
                        swal({
                            title: '选择' + len + '条数据，确定删除？',
                            text: '删除后不可恢复！',
                            type: 'warning',
                            showCancelButton: true,
                            confirmButtonColor: '#DD6B55',
                            closeOnConfirm: false
                        }, function (isOk) {
                            if (isOk) {
                                $.ajax({
                                    url: context + '/role/batchDelete',
                                    data: {ids: ids},
                                    dataType: 'json',
                                    traditional: true,//参数为数组,traditional为true阻止深度序列化,这样才可以传数组
                                    success: function (result) {
                                        if (result.success) {
                                            swal(result.msg, '', "success")
                                            that.$dg.datagrid('unselectAll').datagrid('reload');
                                        } else {
                                            swal(result.msg, '', "error");
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        swal(result.msg, result.data, "error");
                    }
                }
            });
        },
        editRole: function () {
            var that = this, rows = that.$dg.datagrid('getSelections');
            if (rows.length == 1) {
                var row = rows[0];
                //初始化树
                that.$resourceTree.tree({
                    url: context + '/resource/findResourceTree',
                    queryParams: {
                        roleId: row.id,
                        type:null
                    },
                    checkbox: true,
                    onBeforeCheck: function (node, checked) {
                    },
                    formatter: function (node) {
                        var isValid = node.isValid;
                        if (isValid != 1) {
                            return '<span style="text-decoration:line-through; color: firebrick">' + node.text + '</span>';
                        } else {
                            return node.text;
                        }
                    },
                    onCheck: function (node, checked) {
                        var ids = '';
                        if (!that.$resourceTree.tree('isLeaf', node.target)) {//判断是否是叶子节点
                            var children = that.$resourceTree.tree('getChildren', node.target);
                            for (var i = 0; i < children.length; i++) {
                                ids += children[i].id + ',';
                            }
                            ids += node.id;
                        } else {
                            ids = node.id;
                        }
                        var url;
                        if (checked) {
                            url = context + "/roleResource/create";
                        } else {
                            url = context + "/roleResource/batchDelete"
                        }
                        $.ajax({
                            url: url,
                            type: "POST",
                            data: {roleId: row.id, ids: ids},
                            traditional: true,//参数为数组,traditional为true阻止深度序列化,这样才可以传数组
                            success: function (result) {
                                if (result.success) {
                                    Jutil.showTips('success', result.msg);
                                } else {
                                    Jutil.showTips('error', '授权失败');
                                }
                            }
                        }, 'json');
                    }
                });
                //初始化拥有的岗位
                that._postRole();
                that.$postRole.datagrid('clearSelections');
                that.$postRole.datagrid('options').url = context + "/postRole/findListByRoleId";
                that.$postRole.datagrid('load', {
                    roleId: row.id
                });
                //初始化拥有的组织
                that._orgRole();
                that.$orgRole.datagrid('clearSelections');
                that.$orgRole.datagrid('options').url = context + "/orgRole/findListByRoleId";
                that.$orgRole.datagrid('load', {
                    roleId: row.id
                });
                //初始化用户
                that._userRole();
                that.$userRole.datagrid('clearSelections');
                that.$userRole.datagrid('options').url = context + "/roleUser/findListByRoleId";
                that.$userRole.datagrid('load', {
                    roleId: row.id
                });


                that.$dlgRoleOrg.dialog('setTitle', '查看与角色' + row.roleName + '关联的资源').dialog('center').dialog('open');
            } else {
                swal('请选择一行数据！', '', "error");
            }
        }
    };

    //      删除关联本角色的组织
    deleteOrgRole = function (rec) {
        var rows = $("#orgRole").datagrid('getSelections'), len = rows.length, ids = [], delRow = 0;
        if (rec == null && len == 0) {
            swal('请选择删除的数据！', '', 'error');
            return;
        }
        if (rows.length > 0) {
            for (var i in rows) {
                var row = rows[i];
                ids.push(row.id);
            }
        } else {
            ids.push(rec);
        }
        swal({
            title: '选择' + len + '条数据，确定删除？',
            text: '删除后不可恢复！',
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#DD6B55',
            closeOnConfirm: false
        }, function (isOk) {
            if (isOk) {
                $.ajax({
                    url: context + "/orgRole/batchDelete",
                    type: "POST",
                    data: {ids: ids},
                    traditional: true,
                    beforeSend: function () {
                    },
                    success: function (data) {
                        $("#orgRole").datagrid('unselectAll').datagrid('reload');
                        Jutil.showTips('success', "删除关系成功！");
                        swal({
                            title: "温馨提示",
                            text: data.msg,
                            timer: 2000,
                            type: "success",
                            showConfirmButton: false
                        });
                    }
                }, 'json');
            }
        });

    };
    //删除关联本角色的用户
    deleteUserRole = function (rec) {
        var rows = $("#userRole").datagrid('getSelections'), len = rows.length, ids = [];
        if (rec == null && len == 0) {
            swal('请选择删除的数据！', '', 'error');
            return;
        }
        if (rows.length > 0) {
            for (var i in rows) {
                var row = rows[i];
                ids.push(row.id);
            }
        } else {
            ids.push(rec);
        }
        swal({
            title: '选择' + len == 0 ? 1 : len + '条数据，确定删除？',
            text: '删除后不可恢复！',
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#DD6B55',
            closeOnConfirm: false
        }, function (isOk) {
            if (isOk) {
                $.ajax({
                    url: context + "/roleUser/batchDelete",
                    type: "POST",
                    data: {ids: ids},
                    traditional: true,
                    beforeSend: function () {
                    },
                    success: function (data) {
                        $("#userRole").datagrid('unselectAll').datagrid('reload');
                        Jutil.showTips('success', "删除关系成功！");
                        swal({
                            title: "温馨提示",
                            text: data.msg,
                            timer: 2000,
                            type: "success",
                            showConfirmButton: false
                        });
                    }
                }, 'json');
            }
        });

    };
    //删除关联本角色的岗位
    deletePostRole = function (rec) {
        var rows = $("#postRole").datagrid('getSelections'), len = rows.length, ids = [];
        if (rec == null && len == 0) {
            swal('请选择删除的数据！', '', 'error');
            return;
        }
        if (rows.length > 0) {
            for (var i in rows) {
                var row = rows[i];
                ids.push(row.id);
            }
        } else {
            ids.push(rec);
        }
        swal({
            title: '选择' + len + '条数据，确定删除？',
            text: '删除后不可恢复！',
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#DD6B55',
            closeOnConfirm: false
        }, function (isOk) {
            if (isOk) {
                $.ajax({
                    url: context + "/postRole/batchDelete",
                    type: "POST",
                    data: {ids: ids},
                    traditional: true,
                    beforeSend: function () {
                    },
                    success: function (data) {
                        $("#postRole").datagrid('unselectAll').datagrid('reload');
                        Jutil.showTips('success', "删除关系成功！");
                        swal({
                            title: "温馨提示",
                            text: data.msg,
                            timer: 2000,
                            type: "success",
                            showConfirmButton: false
                        });
                    }
                }, 'json');
            }
        });

    };


    new role();
})(this, jQuery);