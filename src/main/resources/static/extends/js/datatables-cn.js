var oTable;

$(document).ready(function(){
    oTable = $('#dataTable').DataTable( {
        //定义语言，不写也可以，就会变英文。
        "oLanguage": { //国际化配置
            "sProcessing" : "正在获取数据，可能网速不够快，请稍候...",
            "sLengthMenu" : "显示 _MENU_ 条/页",
            "sZeroRecords" : "没有您要搜索的内容",
            "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
            "sInfoEmpty" : "记录数为0",
            "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
            "sInfoPostFix" : "",
            "sSortClasses": false,
            "sSearch" : "搜索",
            "sUrl" : "",
            "oPaginate": {
                "sPrevious" : "上一页",
                "sNext" : "下一页"
            }
        }
    } );
});