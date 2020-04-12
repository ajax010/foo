var FormValidation = function () {

    var addValidation = function() {
        let $inputForm = $('#inputForm');
        $inputForm.validate({
            rules: {
                name:   "required",
                remark:   "required"
            },
            messages:{
                name:   "角色名不能为空！",
                remark:   "描述不能为空"
            },
            submitHandler:function(form){
                let permissionIds = '';
                $('._p_item:checked').each(function(){
                    permissionIds += $(this).val() + ',';
                });

                if(permissionIds != ''){
                    permissionIds = permissionIds.substring(0, permissionIds.length - 1);
                    $('#permissionIds').val(permissionIds);
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