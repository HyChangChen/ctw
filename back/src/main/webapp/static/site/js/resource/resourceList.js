/**
 * Created by yanxiaojun on 2016/5/20.
 */
(function (window, $, undefined) {
    function resource() {
        this._init();
    }

    resource.prototype = {
        constructor: resource,
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
            that.$dgResourceView = $('#resourceView');
            that.$dt = $('#dt');

            /*Dialog*/
            that.$dlg = $('#dlg');
            that.$form = $('#form');
            that.$btnSave = $('#btnSave');

            /*Other*/
            that.$partentIdBox=$("#parentId");

        },
        _initData: function () {
            var that = this;
            that.ListIsValid = [{value: '', text: '全部'}, {value: '1', text: '有效'}, {value: '2', text: '失效'}];
            that.MapIsValid = {2: '失效', 1: '有效'};

        },
        _initMain: function () {
            var that = this;
            that._treeGrid();
            that._form();
            /* that._grid();*/
        },
        _bindEven: function () {
            var that = this;
            var rows = that.$dgResourceView.datagrid('getSelections');
            that.$btnAdd.on('click', function () {
                that._resetForm();
                that._postResourceinit();
                $("#ts").val(Jutil.formatDateTime(new Date()));
                var resourceId="",resourceVal="";
                if (rows.length == 1) {
                    resourceId=rows[0].id;
                    resourceVal=rows[0].name;
                }else{
                    resourceId=1;
                    resourceVal="系统资源";
                }
                that.$partentIdBox.combotree('setValue', {
                    id: resourceId,
                    text:resourceVal
                });
                that.$form.attr("action", context + "/resource/create");
                that.$dlg.dialog('setTitle', '新增系统资源').dialog('center').dialog('open');
            });
            that.$btnEdit.on('click', function () {
                that._resetForm();
                if (rows.length == 1) {
                    var row = rows[0];
                    that._postResourceinit();
                    that.$form.form('load', row);
                    $("#ts").val(Jutil.formatDateTime(row.ts));
                    that.$form.attr("action", context + "/resource/update");
                    that.$dlg.dialog('setTitle', '编辑系统资源').dialog('center').dialog('open');
                } else {
                    swal('请选择一条数据！', '', "error");
                }
            });
            that.$btnDelete.on('click', function () {
                that.delete();
            });
        },
        _form: function () {
            var that = this;
            that.Validform = that.$form.Validform({
                tiptype: Jutil.tiptype,
                ajaxPost: true,
                callback: function (result) {
                   debugger;
                    if (result.success) {
                        that._showMsg();
                        swal({
                            title: "温馨提示",
                            text: result.msg,
                            timer: 2000,
                            type: "success",
                            showConfirmButton: false
                        });
                    } else {
                        that._showMsg();
                        swal(result.msg, '', "error");
                    }
                }
            });
        },
        _resetForm: function () {
            var that = this;
            that.Validform.resetForm();
            that.$form.find('.Validform_checktip').text('');
        },
        _treeGrid: function () {
            var that = this;
            that.$dgResourceView.treegrid({
                url: context + '/resource/findAllPage',
                idField: 'id',
                treeField: 'name',
                pagination: true,
                toolbar: '#tb',
                filterDelay: 600,
                fit: true,
                striped: true,
                singleSelect: false,
                rownumbers: true,
                dragSelection: true,
                remoteFilter: true,
                frozenColumns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'name', title: '菜单名称', width: 200, editor: "text"}
                ]],
                columns: [[
                    {field: 'id', hidden: true},
                    {
                        field: 'url', title: '访问路径', width: 200, editor: "text"
                    },
                    {field: 'type', title: '类型 ', width: 100},
                    {field: 'priority', title: '排序', width: 100},
                    {field: 'parentId', hidden: true},
                    {
                        field: 'isValid', title: '是否有效',aign: 'center',width: 80, sortable: true,
                        formatter: function (value) {
                            return value ? that.MapIsValid[value] : null;
                        }
                    },
                    {field: 'partentsIds',  hidden: true},
                    {field: 'leaf',  hidden: true},
                    {field: 'createNameId', hidden: true},
                    {
                        field: 'ts', title: '创建时间', width: 120, sortable: true,
                        formatter: function (value) {
                            return value ? moment(value).format('YYYY-MM-DD HH:mm:ss') : null;
                        }
                    },
                    {field: 'description', title: '备注', width: 100},
                    {field: 'icon',  hidden: true},
                    {field: 'permission', title: '权限', width: 100}
                ]],
                onDragOver :function (targetRow, sourceRow) {
                    //console.log("targetRow:"+targetRow.id);
                },
                onDrop: function (targetRow, sourceRow, point) {
                    $.ajax({
                        url: context + "/resource/update",
                        data: {id: sourceRow.id, parentId: targetRow.id},
                        dataType: 'JSON',
                        type: "POST",
                        traditional: true,//参数为数组,traditional为true阻止深度序列化,这样才可以传数组
                        success: function (result) {
                            Jutil.showTips('success', result.msg);
                            swal(result.msg, '', "success")
                            that.$dgResourceView.treegrid('unselectAll').treegrid('reload');
                        }
                    });
                },
                onLoadSuccess: function (row) {
                    $(this).treegrid('enableDnd', row ? row.id : null);
                }
            });
        },
        _postResourceinit: function () {
            var that = this;
            that.$partentIdBox.combotree({
                url: context + '/resource/findResourceTree',
                required: false
            });
        },
        delete: function () {
            var that = this,     rows = that.$dgResourceView.datagrid('getSelections'), len = rows.length;
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
                    var rows = that.$dg.datagrid('getSelections'), ids = [];
                    for (var i in rows) {
                        var row = rows[i];
                        ids.push(row.id);
                    }
                    $.ajax({
                        url: context + '/resource/batchDelete',
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
        },
        _showMsg:function () {
            var that=this;
            that.$dlg.dialog('close');
            that.$dgResourceView.treegrid('unselectAll').treegrid('reload');
        }
    };

    new resource();


})(this, jQuery);





