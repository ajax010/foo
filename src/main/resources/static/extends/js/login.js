$(function(){
    $('#inputForm').submit(function(){
        let username = $('#username').val();
        let password = $('#password').val();
        if(username != '' && password != ''){
            $.get('/doLogin', {username:username, password:password}, function(data){
                let json = $.parseJSON(data);
                if(json.code == '000'){
                    location.href = json.homePath;
                }else{
                    $.message('error', json.msg);
                }
            })
        }
        return false;
    })
})