/**
 * Created by Ocean on 2016/4/12.
 */
/**
 * 工具js文件。
 * 公用的操作方法存放在这
 * 增、删、改、关闭窗口
 */
(function () {

    /**
     * @constructor
     */
    function options_util() {
    }

    options_util.prototype = {
        document: window.document,
        _option_not_function: function () {
            swal('该功能正在开发中，敬请期待.....！', '', "error");
        },

        _option_btn_add: function  (that,ajax_path,dig_title) {
            var _that=that;
            _that.$form.attr("action", ajax_path);
            _that.$dlg.dialog('setTitle', dig_title).dialog('center').dialog(that.dlgOptions[1]);
         }
    };
    window.Optionsutil = new options_util();
})();

