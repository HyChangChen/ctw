/**
 * Created by HaiAng on 2016/5/20.
 */
(function (window, $, undefined) {
    function Post() {
        var that = this;
        that.pid = "", pn = "";
        that._init();

    }

    Post.prototype = {
        constructor: Post,
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

            /*Tree**/
            that.$orgTree = $("#orgTree");
            that.$orgNameBox = $("#orgId");
            /***Edit Role***/
            that.$dlgRolePost = $("#dlgRolePost");
            that.$editRole=$("#btnEditRole");
            that.$haveRole = $("#haveRole");
            that.$wrongRole = $("#wrongRole");
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
            that._grid();
            that._orgTree();
        },
        _bindEven: function () {
            var that = this;
            that.$btnAdd.on('click', function () {
                that.resetForm();
                that._postOrgChu();
                that.$orgNameBox.combotree('setValue', {
                    id: that.pid,
                    text: that.pn
                });
                that.$form.attr("action", context + "/post/create");
                that.$dlg.dialog('setTitle', '添加岗位').dialog('center').dialog('open');
            });
            that.$btnEdit.on('click', function () {
                var rows = that.$dg.datagrid('getSelections');
                if (rows.length == 1) {
                    var row = rows[0];
                    that.resetForm();
                    that._postOrgChu();
                    
                    that.$orgNameBox.combotree('setValue', {
                        id: row.orgId,
                        text:row.orgName
                    });
                    that.$form.form('load', row);
                    that.$form.attr("action", context + "/post/update");
                    that.$dlg.dialog('setTitle', '编辑岗位').dialog('center').dialog('open');
                } else {
                    swal('请选择一条数据！', '', "error");
                }
            });
            that.$btnDelete.on('click', function () {
                that.delete();
            });

            that.$editRole.on('click', function () {
                that.editRole();
            });
        },
        _form: function () {
            var that = this;
            //debugger;
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
                url: context + '/post/findPage',
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
                    {field: 'orgId', hidden: true},
                    {field: 'postName', title: '岗位名称', align: 'center', width: '20%'},
                    {field: 'description', title: '岗位说明', align: 'center', width: '20%'},
                    {field: 'orgName', title: '所属组织', align: 'center', width: '20%'},
                    {
                        field: 'creatDate', title: '创建时间', align: 'center', width: 120, sortable: true,
                        formatter: function (value) {
                            return value ? moment(value).format('YYYY-MM-DD') : null;
                        }
                    },
                    {
                        field: 'isValid', title: '是否有效',aign: 'center',width: 80, sortable: true,
                        formatter: function (value) {
                            return value ? that.MapIsValid[value] : null;
                        }
                    }

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
                field: 'creatDate',
                type: 'dateRange',
                options: {
                    onChange: function (value) {
                        Jutil.doFilter(that.$dg, 'creatDate', value);
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
            }]);
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
                    that.pid = node.id;
                    that.pn = node.text;
                }
            });
        },
        _postOrgChu: function () {
            var that = this;
            that.$orgNameBox.combotree({
                url: context + '/org/findOrgTree',
                required: false

            });
        },
        resetForm: function () {
            var that = this;
            that.Validform.resetForm();
            that.$form.find('.Validform_checktip').text('');
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
                            btn = '<button type="button"    onclick="deletePostRole(' + delId + ');"  class="btn btn-danger btn-sm" id="delRole">' +
                                '<i class="fa fa-fw fa-trash-o"></i>&nbsp;删除角色</button>'
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
                            var btn = '<button type="button"   onclick="addPostRole(' + delId + ');"    class="btn btn-primary btn-sm" id="addRole"><i class="fa fa-fw fa-plus"></i>&nbsp;添加角色</button>'
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

        },
        delete: function () {
            var that = this, rows = that.$dg.datagrid('getSelections'), len = rows.length;
            if (len == 0) {
                swal('请选择删除的数据！', '', 'error');
                return;
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
                    var  ids = [];
                    for (var i in rows) {
                        var row = rows[i];
                        ids.push(row.id);
                    }
              $.ajax({
                        url: context + '/post/batchDelete',
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
                    orgId: row.orgId,
                      postId: row.id
                });
                //加载未拥有的角色
                that.$wrongRole.datagrid('clearSelections');
                that.$wrongRole.datagrid('options').url = context + "/role/findHaveListPage";
                that.$wrongRole.datagrid('load', {
                    orgId: row.orgId,
                    postId: row.id,
                    findType: 'not'
                });
                //为删除岗位与角色储备变量
                $("#orgId").val(row.id);
                //加载拥有的所有资源
                that.$dlgRolePost.dialog('setTitle', '查看岗位的角色与资源').dialog('center').dialog('open');
            } else {
                swal('请选择一行数据！', '', "error");
            }
        },
    };
    deletePostRole = function (rec) {
        var  rows = $('#dg').datagrid('getSelections');
        $.ajax({
            url: context + "/postRole/deleteByOrgIdRId/" + rows[0].id + "/" + rec,
            type: "POST",
            beforeSend: function () {
            },
            success: function (data) {
                Jutil.showTips('success','授权取消成功');
                $("#haveRole").datagrid('reload');
                $("#wrongRole").datagrid('reload');
            }
        }, 'json');
    }
    addPostRole = function (rec) {
      var  rows = $('#dg').datagrid('getSelections');
        $.ajax({
            url: context + "/postRole/create",
            type: "POST",
            data:{psotId:rows[0].id,roleId:rec},
            beforeSend: function () {
            },
            success: function (data) {
                Jutil.showTips('success','授权成功');
                $("#haveRole").datagrid('reload');
                $("#wrongRole").datagrid('reload');
            }
        }, 'json');
    }
    new Post();
})(this, jQuery);





