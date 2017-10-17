/**
 * Created by HaiAng on 2016/5/20.
 */
(function (window, $, undefined) {
    function Depot() {
        this._init();
    }

    Depot.prototype = {
        constructor: Depot,
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
            that.$btnCopy = $('#btnCopy');
            that.$btnDelete = $('#btnDelete');
            that.$dg = $('#dg');
            that.$dt = $('#dt');

            /*Dialog*/
            that.$dlg = $('#dlg');
            that.$form = $('#form');
            that.$btnSave = $('#btnSave');

            /*Other*/
            /**select2**/
            that.$depotManager=$("#depotManager");

        },
        _initData: function () {
            var that = this;

        },
        _initMain: function () {
            var that = this;
            $('[name="isDefault"]').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue'
            });
            that._form();
            that._grid();

        },
        _bindEven: function () {
            var that = this;
            that.$btnAdd.on('click', function () {
                that.add();
            });
            that.$btnEdit.on('click', function () {
                that.edit();
            }); that.$btnCopy.on('click', function () {
                that.copy();
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
                url: context + '/depot/findPage',
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
                    {field: 'depotNo', title: '仓库编号', width: 100},
                    {field: 'depotName', title: '仓库名称', width: 100},
                    {field: 'depotManager', title: '创库管理者', width: 100},
                    {field: 'address', title: '仓库地址', width: 100},
                    {field: 'isValid', title: '状态[1,有效，0.无效]', width: 100},
                    {field: 'isDefault', title: '是否默认仓库', width: 100}
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

            that.$dg.datagrid('enableFilter', []);
        },
        resetForm: function () {
            var that = this;
            that.Validform.resetForm();
            that.$form.find('.Validform_checktip').text('');
        },
        add: function () {
            var that = this;
            that._form();
            that.$form.Validform();
            that.resetForm();
            //初始化负责人
            that._initmager();
            //特殊字段异步验证，比如用户重名等等
            // that.$loginName.attr("ajaxurl", context + "/user/findUser");
            that.$dlg.dialog('setTitle', '新增仓库信息').dialog('center').dialog('open');
        },
        edit: function () {
            var that = this, rows = that.$dg.datagrid('getSelections')
            if (rows.length == 1) {
                var row = rows[0];
                that.resetForm();
                that.$form.form('load', row);
                that.$dlg.dialog('setTitle', '编辑').dialog('center').dialog('open');
            } else {
                swal('请选择一条数据！', '', "error");
            }
        },
        copy: function () {
            var that = this, rows = that.$dg.datagrid('getSelections')
            if (rows.length == 1) {
                var row = rows[0];
                that.resetForm();
                that.$form.form('load', row);
                $("#id").val("");
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
                        url: context + '/depot/batchDelete',
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
        _initmager:function(){
            var that=this;
            that.$depotManager.select2({
                multiple: false,
                closeOnSelect: true,
                minimumInputLength: 2,
                ajax: {
                    url: context + '/user/findAllUserList',
                    dataType: 'json',
                    cache: true,
                    data: function (params) {
                        return {
                            inputVal: params.term, // search term
                            page: params.page
                        };
                    },
                    processResults: function (data, page) {
                        var rows = data.rows;
                        console.log(rows);
                        for (var i in rows) {
                            var row = rows[i];
                            row.id = row.id;
                            row.text = row.text;
                        }
                        return {
                            results: rows,
                            pagination: {
                                more: (page * 10) < data.total
                            }
                        };
                    }
                }
            });
        }

    };

    new Depot();
})(this, jQuery);





