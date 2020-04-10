let $ = jQuery;
$(function(){
    $('.fa-fighter-jet').hide();
    let menuId = $('#left-panel').attr('menuId');
    $('#' + menuId).find('.fa-fighter-jet').show();
    $('#' + menuId).parent().addClass('show');
    $('#' + menuId).parent().parent().addClass('show');
    $('#' + menuId).parent().parent().find('a:first').attr('aria-expanded', true);

    $('#_page_module button').click(function(){
        let val = $(this).val();
        let pageNumber = $('#pageNumber').text();
        let totalPages = $('#totalPages').text();
        let pageSize = $('#pageSize').val();

        $('#listForm').find('input[name="page"]').remove();
        $('#listForm').find('input[name="size"]').remove();
        $('#listForm').append( '<input name="page" type="hidden"/>');
        $('#listForm').append('<input name="size" value="' + pageSize + '" type="hidden"/>');
        switch(val){
            case 'home_page':
                val = 1;
                break;
            case 'previous_page':
                val = pageNumber > 1 ? parseInt(pageNumber) - 1 : 1;
                break;
            case 'next_page':
                val = pageNumber >= totalPages ? totalPages : parseInt(pageNumber) + 1;
                break;
            case 'end':
                val = totalPages;
                break;
            default:

        }

        $('input[name="page"]').val(val);
        $('#listForm').submit();
    })
});

// 重置搜索条件
function form_rest(obj){
    let _that = $(obj);
    _that.parents('form').find('input').each(function(){
        $(this).val('');
    })
};
