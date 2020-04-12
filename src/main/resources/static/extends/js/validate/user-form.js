var FormValidation = function () {

    var addValidation = function() {
        let $inputForm = $('#inputForm');
        $inputForm.validate({
            rules: {
                username:   {
                    required : true,
                    remote : {
                        type: 'post',
                        url : '/user/username_is_exists',
                        data: {
                            id : function(){
                                return $('#id').val();
                            },
                            username : function(){
                                return $('#username').val();
                            }
                        },
                        dataFilter: function(data, type){
                            if(data == 'true') {
                                return true;
                            }else{
                                return false;
                            }
                        }
                    }
                },
                nickname:   "required"
            },
            messages:{
                username:   {
                    required : '用户账号不能为空！',
                    remote: "账号已存在！"
                },
                nickname:   "用户昵称不能为空！"
            },
            submitHandler:function(form){
                let roleIds = '';
                $('input[name="roleId"]:checked').each(function(){
                    roleIds += $(this).val() + ',';
                });

                if(roleIds != ''){
                    roleIds = roleIds.substring(0, roleIds.length - 1);
                    $('#roleIds').val(roleIds);
                }

                form.submit();
            }
        })
    };

    return {
    	init:function(){
    		addValidation();
    	}
    }
}();