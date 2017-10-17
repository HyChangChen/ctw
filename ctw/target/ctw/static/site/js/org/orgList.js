/**
 * Created by yanxiaojun on 2016/5/20.
 */
(function (window, $, undefined) {
    function Org() {
        var that = this;
        that.pid = "", pn = "";
        this._init();
    }

    Org.prototype = {
        constructor: Org,
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
            that.$btnCancel = $('#btnCancel');
            /*Other*/
            that.$orgTree = $("#orgTree");
            that.$daterange = $('#daterange');
            that.$birth = $('#birth');
            that.$partentNameBox = $("#partentName");
            that.$editRole = $("#btnEditRole");
            that.$haveRole = $("#haveRole");
            that.$wrongRole = $("#wrongRole");
            that.$dlgRoleOrg = $("#dlgRoleOrg");
            that.$resourceTree=$("#resourceTree");
            


        },
        _initData: function () {
            var that = this;
            /**是否有效  1生效 2失效 **/
            that.ListIsValid = [{value: '', text: '全部'}, {value: '1', text: '有效'}, {value: '2', text: '失效'}];
            that.MapIsValid = {2: '失效', 1: '有效'};

        },
        _initMain: function () {
            var that = this;
            that._form();
            that._orgTree();
            that._grid();
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
        _bindEven: function () {
            var that = this;
            that.$btnAdd.on('click', function () {
                that.addOrg();
            });
            that.$btnEdit.on('click', function () {
           that.editOrg();
            });
            that.$btnDelete.on('click', function () {
                that.delOrg();
            });
            that.$btnCancel.on('click', function () {
                that.$dlg.dialog('close');
            });
            that.$editRole.on('click', function () {
                that.editRole();
            });
        },
        _form: function () {
            var that = this;
            that.Validform = that.$form.Validform({
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
                        that.$dg.datagrid('reload');
                        that.$dlg.dialog('close');

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
                dnd: true,
                animate: true,
                lines: true,
                onClick: function (node) {
                    that.pid = node.id;
                    that.pn = node.text;
                    that.$dg.datagrid('clearSelections');
                    that.$dg.datagrid('load',
                        {
                            partentId: node.id
                        });
                },
                formatter: function (node) {
                    var isValid = node.isValid;
                    if (isValid != 1) {
                        return '<span style="text-decoration:line-through; color: firebrick">' + node.text + '</span>';
                    } else {
                        return node.text;
                    }
                },
                onDrop: function (targetNode, source, point) {
                    /**目标ID*/
                    var targetId = $(this).tree('getNode', targetNode).id;
                    $.ajax({
                        url: context + '/org/update',
                        data: {
                            id: source.id,
                            partentId: targetId
                        },
                        type: 'post',
                        dataType: 'json',
                        traditional: true,//参数为数组,traditional为true阻止深度序列化,这样才可以传数组
                        success: function (result) {
                            if (result.success) {
                                Jutil.showTips('success', '变更成功');
                                that.$dg.datagrid('clearSelections');
                                that.$dg.datagrid('load',
                                    {
                                        partentId: targetId
                                    });
                            } else {
                                swal(result.msg, '', "error");
                            }
                        }
                    });
                }

            });
        },
        _grid: function () {
            var that = this;
            that.$dg.datagrid({
                fit: true,
                url: context + '/org/findByPid',
                // fitColumns: true,
                striped: true,
                singleSelect: false,
                // nowrap: false,
                pagination: true,
                rownumbers: true,
                toolbar: '#tb',
                //  data: that.GridData,
                idField: 'id',
                filterDelay: 500,
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', hidden: true},
                    {field: 'orgName', title: '组织名称', width: 100},
                    {field: 'partentId', title: '备注', hidden:true},
                    {
                        field: 'isValid', title: '是否有效', width: 80, sortable: true, formatter: function (value) {
                        return value ? that.MapIsValid[value] : null;
                    }
                    },
                    {
                        field: 'ts', title: '创建时间', align: 'center', width: 120, sortable: true,
                        formatter: function (value) {
                            return value ? moment(value).format('YYYY-MM-DD') : null;
                        }
                    },
                    {field: 'patrentName', title: '上级组织', width: 300}
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
                }, onClickRow: function (index, row) {
                    that.pid = row.id;
                    that.pn = row.orgName
                },
                /* onClickRow:that._dataGridClick,*/
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
            that.$dg.datagrid('enableFilter',
                [{
                    field: 'ts',
                    type: 'dateRange',
                    options: {
                        onChange: function (value) {
                            Jutil.doFilter(that.$dg, 'ts', value);
                        }
                    }
                },
                    {
                field: 'genter',
                type: 'combobox',
                options: {
                    panelHeight: 'auto',
                    editable: false,
                    data: that.ListGender,
                    onChange: function (value) {
                        Jutil.doFilter(that.$dg, $(this).attr('name'), value);
                    }
                }
            }, {
                field: 'status',
                type: 'combobox',
                options: {
                    panelHeight: 'auto',
                    editable: false,
                    data: that.ListSatus,
                    onChange: function (value) {
                        if (value == '') {
                            that.$dg.datagrid('removeFilterRule', 'status');
                        } else {
                            that.$dg.datagrid('addFilterRule', {
                                field: 'status',
                                op: 'equal',
                                value: value
                            });
                        }
                        that.$dg.datagrid('doFilter');
                    }
                }
            }, {
                field: 'isValid',
                type: 'combobox',
                options: {
                    panelHeight: 'auto',
                    editable: false,
                    data: that.ListIsValid,
                    onChange: function (value) {
                        Jutil.doFilter(that.$dg, $(this).attr('name'), value);
                    }
                }
            }, {
                field: 'birth',
                type: 'datebox',
                options: {
                    editable: false
                }
            }]);
        },
        resetForm: function () {
            var that = this;
            that.Validform.resetForm();
            that.$form.find('.Validform_checktip').text('');
        },
        _postOrgChu: function () {
            var that = this;
            that.$partentNameBox.combotree({
                url: context + '/org/findOrgTree',
                required: false,
                queryParams: {
                    orgId: that.partentId
                },
                onClick: function (node) {
                    $("#partentId").val(node.id);
                    $("#orgName").val(node.text);
                }
            });
        },
        delOrg: function () {
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
                url: context + "/user/getCountUserByOrg",
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
                                    url: context + '/org/batchDelete',
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
                        that.$dg.datagrid('unselectAll').datagrid('reload');
                    } else {
                        swal(result.msg, result.data, "error");
                    }
                }
            });
        },
        addOrg: function () {
            var that = this;
            var rows = that.$dg.datagrid('getSelections');
            if (rows.length <= 1) {
                that.resetForm();
                that._postOrgChu();
                $("#partentId").val(that.pid);
                that.$partentNameBox.combotree('setValue', {
                    id: that.pid,
                    text: that.pn
                });
                that.$form.attr("action", context + "/org/create");
                that.$dlg.dialog('setTitle', '添加组织').dialog('center').dialog('open');
            } else {
                swal('请选择一条数据作为上级组织！', '', "error");
            }
        },
        editOrg:function () {
            var that=this;
            var rows = that.$dg.datagrid('getSelections');
            if (rows.length == 1) {
                var row = rows[0];
                that.resetForm();
                that._postOrgChu();
                that.$form.form('load', row);
                that.$partentNameBox.combotree('setValue', {
                    id: row.partentId,
                    text: row.patrentName
                });
                that.$dlg.dialog('setTitle', '编辑组织信息').dialog('center').dialog('open');
            } else {
                swal('请选择一条数据！', '', "error");
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
                  //  userId: row.id,
                    orgId: row.id
                   // postId: row.postId
                });
                //加载未拥有的角色
                that.$wrongRole.datagrid('clearSelections');
                that.$wrongRole.datagrid('options').url = context + "/role/findHaveListPage";
                that.$wrongRole.datagrid('load', {
                    orgId: row.id,
                    findType: 'not'
                });
                //为删除组织与角色储备变量值
                $("#orgId").val(row.id);
                //加载拥有的所有资源
                that.$dlgRoleOrg.dialog('setTitle', '查看组织的角色与资源').dialog('center').dialog('open');
            } else {
                swal('请选择一行数据！', '', "error");
            }
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
                    {field: 'roleName', title: '角色编码', width: '33%'},
                    {field: 'description', title: '角色名称', width: '33%'},
                    {
                        field: 'opt', title: '操作', width: '30%', align: 'center',
                        formatter: function (value, rec) {
                            var delId = rec.id;
                            var btn;
                            btn = '<button type="button"    onclick="deleteOrgRole(' + delId + ');"  class="btn btn-danger btn-sm" id="delRole">' +
                                '<i class="fa fa-fw fa-trash-o"></i>&nbsp;删除角色</button>'
                            return btn;
                        }
                    }
                ]],
               /* onSelect: function (index, row) {
                    that.$wrongRole.datagrid('clearSelections');
                },*/
                onDblClickRow: function (index, row) {
                    that.$wrongRole.datagrid('clearSelections');
                    that.$resourceTree.tree({
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
                            var btn = '<button type="button"   onclick="addOrgRole(' + delId + ');"    class="btn btn-primary btn-sm" id="addRole"><i class="fa fa-fw fa-plus"></i>&nbsp;添加角色</button>'
                            return btn;
                        }
                    }
                ]],
               onDblClickRow: function (index, row) {
                    that.$wrongRole.datagrid('clearSelections');
                    that.$resourceTree.tree({
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

        }
    };
    deleteOrgRole = function (rec) {
        var orgId = $("#orgId").val();
        $.ajax({
            url: context + "/orgRole/deleteByOrgIdRId/" + orgId + "/" + rec,
            type: "POST",
            beforeSend: function () {
            },
            success: function (data) {
                $("#haveRole").datagrid('reload');
                $("#wrongRole").datagrid('reload');
            }
        }, 'json');
    }
    addOrgRole = function (rec) {
        var orgId = $("#orgId").val();
        $.ajax({
            url: context + "/orgRole/create",
            type: "POST",
            data:{orgId:orgId,roleId:rec},
            beforeSend: function () {
            },
            success: function (data) {
                $("#haveRole").datagrid('reload');
                $("#wrongRole").datagrid('reload');
            }
        }, 'json');
    }
    new Org();
})(this, jQuery);