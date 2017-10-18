/**
 * Created by Administrator on 2016/4/10.
 */

/**
 * 定义swal默认属性
 */
swal.setDefaults({
    allowOutsideClick: true,
//            animation: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消'
});
/**
 * datebox添加清空按钮
 */
var dateboxButtons = $.extend([], $.fn.datebox.defaults.buttons);
dateboxButtons.splice(1, 0, {
    text: '清空',
    handler: function (target) {
        $(target).datebox('clear').datebox('hidePanel');
    }
});
$.fn.datebox.defaults.buttons = dateboxButtons;

/**
 * 设置easyui datebox默认不可编辑
 */
$.fn.datebox.defaults.editable = false;

/**
 * easyui datagrid 日期范围过滤
 */
$.extend($.fn.datagrid.defaults.filters, {
    dateRange: {
        init: function (container, options) {
            var input = $('<input>').appendTo(container);
            input.combo($.extend({
                panelWidth: 330,
                panelHeight: 210,
                editable: false,
            }, options, {
                onShowPanel: function () {
                    var dd = input.combo('getText').split(':');
                    var d1 = $.fn.datebox.defaults.parser(dd[0]);
                    var d2 = $.fn.datebox.defaults.parser(dd[1]);
                    var p = input.combo('panel');
                    p.find('.c1').calendar('moveTo', d1);
                    p.find('.c2').calendar('moveTo', d2);
                }
            }));

            var p = input.combo('panel');
            $('<div class="clearfix"><div class="c1" style="width:50%;float:left"></div><div class="c2" style="width:50%;float:right"></div></div>').appendTo(p);
            var c1 = p.find('.c1').calendar();
            var c2 = p.find('.c2').calendar();
            var footer = $('<div></div>').appendTo(p);
            var formatter = function (v) {
                return $.fn.datebox.defaults.formatter(v);
            };
            var setValue = function (v1, v2) {
                var v = v1 + '~' + v2;
                input.combo('setValue', v).combo('setText', v);
                input.combo('hidePanel');
            };

            var thisMonthBtn = $('<button type="button" style="margin: 2px;" class="btn btn-info btn-xs">本月</button>').appendTo(footer);
            thisMonthBtn.bind('click', function () {
                var v1 = formatter(new Date(new Date().setDate(1)));
                var v2 = formatter(new Date());
                setValue(v1, v2);
            });

            var monthBtn = $('<button type="button" style="margin: 2px;" class="btn btn-info btn-xs">过去30天</button>').appendTo(footer);
            monthBtn.bind('click', function () {
                var now = new Date(), newDate = new Date(now.setDate(now.getDate() - 30));
                var v1 = formatter(newDate);
                var v2 = formatter(new Date());
                setValue(v1, v2);
            });

            var weekBtn = $('<button type="button" style="margin: 2px;" class="btn btn-info btn-xs">过去7天</button>').appendTo(footer);
            weekBtn.bind('click', function () {
                var now = new Date(), newDate = new Date(now.setDate(now.getDate() - 7));
                var v1 = formatter(newDate);
                var v2 = formatter(new Date());
                setValue(v1, v2);
            });

            var yesterdaybtn = $('<button type="button" style="margin: 2px;" class="btn btn-info btn-xs">昨天</button>').appendTo(footer);
            yesterdaybtn.bind('click', function () {
                var now = new Date(), yesterday = new Date(now.setDate(now.getDate() - 1));
                var v1 = formatter(yesterday);
                var v2 = formatter(yesterday);
                setValue(v1, v2);
            });

            var todayBtn = $('<button type="button" style="margin: 2px;" class="btn btn-info btn-xs">今天</button>').appendTo(footer);
            todayBtn.bind('click', function () {
                var now = new Date();
                var v1 = formatter(now);
                var v2 = formatter(now);
                setValue(v1, v2);
            });

            var clearBtn = $('<button type="button" style="margin: 2px;" class="btn btn-primary btn-xs">清空</button>').appendTo(footer);
            clearBtn.bind('click', function () {
                input.combo('clear').combo('hidePanel');
            });

            var okBtn = $('<button type="button" style="margin: 2px;" class="btn btn-primary btn-xs">确定</button>').appendTo(footer);
            okBtn.bind('click', function () {
                var v1 = formatter(c1.calendar('options').current);
                var v2 = formatter(c2.calendar('options').current);
                setValue(v1, v2);
            });
            return input;
        },
        destroy: function (target) {
            $(target).combo('destroy');
        },
        getValue: function (target) {
            var p = $(target).combo('panel');
            var v1 = $.fn.datebox.defaults.formatter(p.find('.c1').calendar('options').current);
            var v2 = $.fn.datebox.defaults.formatter(p.find('.c2').calendar('options').current);
            return v1 + ':' + v2;
        },
        setValue: function (target, value) {
            var dd = value.split(':');
            var d1 = $.fn.datebox.defaults.parser(dd[0]);
            var d2 = $.fn.datebox.defaults.parser(dd[1]);
            var p = $(target).combo('panel');
            p.find('.c1').calendar('moveTo', d1);
            p.find('.c2').calendar('moveTo', d2);
            $(target).combo('setValue', value).combo('setText', value);
        },
        resize: function (target, width) {
            $(target).combo('resize', width);
        }

    }
});

//日期选择
window.datePick = function(elem)
{
    $(elem).datetimepicker({
        autoclose: true,
        todayHighlight: true,
        language: 'zh-CN',
        pickTime: false
    })
}