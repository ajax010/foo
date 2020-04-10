$.message = function(flag, msg){
    if(flag == '' || msg == null || msg == ''){
        return;
    }

    jQuery(".sufee-alert").remove();
    let html = '';
    if(flag == 'SUCCESS' || flag == 'success'){
        html += '<div class="sufee-alert alert with-close alert-success alert-dismissible fade show" style="width: 30%; margin:1% 33%; position: absolute; display: none;">';
        html += '<span class="badge badge-pill badge-success">SUCCESS</span>';
    }else{
        html += '<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show" style="width: 30%; margin:1% 33%; position: absolute; display: none;">';
        html += '<span class="badge badge-pill badge-danger">ERROR</span>';
    }
    html += msg;
    html += '<button type="button" class="close" data-dismiss="alert" aria-label="Close">';
    html += '<span aria-hidden="true">&times;</span></button></div>';
    jQuery('body').prepend(html);
    jQuery(".sufee-alert").slideDown(1500);
    if(jQuery(".sufee-alert:visible").length > 0){
        setTimeout(function(){jQuery(".sufee-alert").slideUp(3000);}, 1500);
    }
}
