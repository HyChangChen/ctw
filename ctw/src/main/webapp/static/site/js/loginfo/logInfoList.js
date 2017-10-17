/**
 * Created by HaiAng on 2016/5/20.
 */
(function (window, $, undefined) {
    function LogInfo() {
        this._init();
    }

    LogInfo.prototype = {
        constructor: LogInfo,
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


        },
        _initData: function () {
            var that = this;

        },
        _initMain: function () {
            var that = this;

            that._grid();
        },
        _bindEven: function () {
            var that = this;


            that.$btnDelete.on('click', function () {
                that.delete();
            });
            
        },

        _grid: function () {
            var that = this;
            that.$dg.datagrid({
                url: context+'/logInfo/findPage',
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
                    { field:'functionName', title:'functionName', width:100},
                    { field:'params', title:'params', width:100},
                    { field:'contimes', title:'contimes', width:100},
                    { field:'ipAddress', title:'ipAddress', width:100},
                    { field:'macAddress', title:'macAddress', width:100},
                    { field:'message', title:'message', width:100},
                    { field:'username', title:'username', width:100},
                    { field:'logLevel', title:'logLevel', width:100},
                    {field: 'createTime', title:'createTime', width:120, sortable: true, formatter: function (value) {
                            return value ? moment(value).format('YYYY-MM-DD HH:mm:ss') : null;
                    }}
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
                field: 'createTime',
                type: 'datebox',
                options: {
                    onChange: function (value) {
                        Jutil.doFilter(that.$dg, 'createTimeBox', value);
                    }
                }
             }]);
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
                        url: context + '/logInfo/batchDelete',
                        data: {ids: ids},
                        dataType: 'json',
                        traditional: true,//参数为数组,traditional为true阻止深度序列化,这样才可以传数组
                        success: function (result) {
                            if(result.success){
                                swal(result.msg, '', "success")
                                that.$dg.datagrid('unselectAll').datagrid('reload');
                            }else{
                                swal(result.msg, '', "error");
                            }
                        }
                    });
                }
            });
        }

    };

    new LogInfo();


})(this, jQuery);





