/**
 * Created by yanxiaojun on 2016/5/20.
 */
(function (window, $, undefined) {
    function ${clazzName}() {
        this._init();
    }

    ${clazzName}.prototype = {
        constructor: ${clazzName},
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
            that.$dg = $('#dg');
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

        },
        _grid: function () {
            var that = this;
            that.$dg.datagrid({
                fit: true,
                url: context+"/biz/${moduleName}/${clazzName}/findPage",
                striped: true,
                singleSelect: false,
                // nowrap: false,
                pagination: true,
                rownumbers: true,
                // toolbar: '#tb',
                <#list table.pkColumns as column>
                <#if !table.compositeId>
                idField: '${column.columnNameLower}',
                </#if>
                </#list>
                filterDelay: 600,
                remoteFilter: true,
                onBeforeLoad: function (param) {
                    console.log(param);
                    var rules = param.filterRules ? JSON.parse(param.filterRules) : [];
                    for (var i in rules) {
                        var rule = rules[i];
                        param[rule.field]=rule.value;
                    }
                    return param;
                },
                columns: [[
        <#list table.columns as column>
            <#if column.pk>
            {
                field:'${column.columnNameLower}',
                hidden:true
            }<#if column_has_next>,</#if>
            <#else>
            {
                field:'${column.columnNameLower}',
                title:'${column.columnAlias}',
                width:100
            }<#if column_has_next>,</#if>
            </#if>
            </#list>
            ]],

            });
            that.$dg.datagrid('enableFilter', []);
        }
    };

    new ${clazzName}();


})(this, jQuery);





