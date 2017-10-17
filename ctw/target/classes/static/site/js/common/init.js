/**
 * Created by Ocean on 2016/4/12.
 */
/**
 * 工具js文件。
 *  公用的数据初始化，比如 岗位树，部门树
 */
(function () {

    /**
     * @constructor
     */
    function init() {
    }

    init.prototype = {
        document: window.document,

        _init_start_end_date: function (that) {
            that.daterangepicker({
                startDate: new Date(),
                maxDate: new Date(),
                singleDatePicker: true,
                showDropdowns: true
            })
        },
        _init_select_Date: function (that) {
            var _that = that;
            _that.daterangepicker({
                ranges: {
                    '今天': [moment(), moment()],
                    '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                    '过去7天': [moment().subtract(6, 'days'), moment()],
                    '过去30天': [moment().subtract(29, 'days'), moment()],
                    '本月': [moment().startOf('month'), moment().endOf('month')],
                    '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                }
            })
        },
        //初始化下拉框选项
        _initData: function (that) {
            var _that = that;
            _that.ListGender = [{value: '', text: '全部'}, {value: '1', text: '男'}, {value: '2', text: '女'}, {
                value: '99',
                text: '未知'
            }];
            _that.MapGender = {1: '男', 2: '女'};
            /*状态 1.正常，0 锁定 2 过期 3 危险]*/
            _that.ListSatus = [{value: '', text: '全部'}, {value: '1', text: '正常'}, {value: '0', text: '锁定'},
                {value: '2', text: '过期'}, {value: '3', text: '危险'}];
            _that.MapStatus = {1: '正常', 2: '过期', 3: '危险', 4: '<font class="fa fa-key" color="red"></font>'};
            /**是否有效  1生效 2失效 **/
            _that.ListIsValid = [{value: '', text: '全部'}, {value: '1', text: '有效'}, {value: '2', text: '失效'}];
            _that.MapIsValid = {2: '失效', 1: '有效'};

        },

        _init_form: function (_that) {
           // var that=_that;
            _that.validform= _that.$form.Validform({
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
                        _that.$dlg.dialog(_that.dlgOptions[1]);
                        _that.$dlg.datagrid('reload');

                    } else {
                        swal(result.msg, '', "error");
                    }
                }
            });
        },
        //重置表单验证信息
        _init_reset_form: function (that) {
            //调用插件的清空表单方法；
            that.validform.resetForm();
            that.$form.find('.Validform_checktip').text('');
        },
        //初始化组织树结构
        _init_org_tree: function (that) {
            var url = context + '/org/findOrgTree';
            that.$orgTree.tree({
                url: url,
                checkbox: false,
                onClick: function (node) {
                    that.$dlg.datagrid('clearSelections');
                    that.$dlg.datagrid('load', {
                        orgId: node.id
                    });
                    that.$orgId.val(node.id);
                    that.$orgNameBox.val(node.text);
                }
            });
        },
        //填写按钮中初始化组织架构（岗位相关）
        _init_post_org_data: function (that) {
           // orgId, postId, orgNameBox, postName
            var org_url = context + '/org/findOrgTree';
            var post_url = context + '/post/findList';
            that.$orgNameBox.combotree({
                url: org_url,
                required: true,
                queryParams: {
                    orgId: that.$orgId.val()
                },
                onClick: function (node) {
                    that.$orgId.val(node.id);
                    that.$postNameBox.combobox({
                        url: post_url,
                        queryParams: {orgId: node.id},
                        valueField: 'id',
                        //textField: 'postName',
                        textField: that.staticVal[0],
                        onSelect: function (rec) {
                            that.$postId.val(rec.id);
                        }
                    });
                }
            });
            that.$postNameBox.combobox({
                url: post_url,
                queryParams: {orgId: that.$orgId.val()},
                valueField: 'id',
                //textField: 'postName',
                textField: that.staticVal[0],//postName,
                onSelect: function (rec) {
                    that.$postId.val(rec.id);
                }
            });
        }

    };


    window.Init = new init();

})();

