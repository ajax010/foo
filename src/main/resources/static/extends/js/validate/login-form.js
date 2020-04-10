var FormValidation = function () {

    var addValidation = function() {
        let $inputForm = $('#inputForm');
        $inputForm.validate({
            rules: {
                username:   "required",
                password:   "required"
            },
            messages:{
                username:   "请输入用户名！",
                password:   "请输入密码！"
            }
        })
    };

    return {
    	init:function(){
    		addValidation();
    	}
    }
}();