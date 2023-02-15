/**
 * Created by 13 on 2017/2/22.
 */
// Tags Input
$('#tags').tagsInput({
    width: '100%',
    height: '35px',
    defaultText: '请输入文章标签'
});


$('#recommend').toggles({
    on: true,
    text: {
        on: '开启',
        off: '关闭'
    }
});

$('#reprint').toggles({
    on: true,
    text: {
        on: '开启',
        off: '关闭'
    }
});

$('#appreciation').toggles({
    on: true,
    text: {
        on: '开启',
        off: '关闭'
    }
});

$('#comment').toggles({
    on: true,
    text: {
        on: '开启',
        off: '关闭'
    }
});



$(".select2").select2({
    width: '100%'
});

var tale = new $.tale();

/**
 * 保存文章
 * @param status
 */
function subArticle(status) {
    var title = $('#articleForm input[name=title]').val();
    var summary = $('#articleForm input[name=summary]').val();
    var picture_url = $('#add_picture_url').val();
    var content = $('#text').val();
    if (title == '') {
        tale.alertWarn('请输入文章标题');
        return;
    }
    if (summary == '') {
        tale.alertWarn('请输入文章摘要');
        return;
    }
    if (picture_url == '') {
        tale.alertWarn('请输入封面地址');
        return;
    }
    if (content == '') {
        tale.alertWarn('请输入文章内容');
        return;
    }
    $('#content-editor').val(content);
    $("#articleForm #status").val(status);
    $("#articleForm #property").val($('#property1').val());
    $("#articleForm #classification").val($('#multiple-sel').val());
    $("#articleForm #type").val($('#multiple-type').val());
    var params = $("#articleForm").serialize();
    console.log(params);
    if($('#articleForm #id').val() != ''){
        tale.ajax({
            type:"PUT",
            url:"/admin/blog/modify",
            data:params,
            success: function (result) {
                if (result && result.code == 20021) {
                    tale.alertOk({
                        text:'文章保存成功',
                        then: function () {
                            setTimeout(function () {
                                window.location.href = 'http://localhost/admin/blog';
                            }, 500);
                        }
                    });
                } else {
                    tale.alertError(result.message || '保存文章失败');
                }
            }
        });
    }else {
        tale.ajax({
            type:"POST",
            url:"/admin/blog/publish",
            data:params,
            success: function (result) {
                if (result && result.code == 20021) {
                    tale.alertOk({
                        text:'文章保存成功',
                        then: function () {
                            setTimeout(function () {
                                window.location.href = 'https://www.xiaohuis.top/admin/blog';
                            }, 500);
                        }
                    });
                } else {
                    tale.alertError(result.message || '保存文章失败');
                }
            }
        });
    }
}

$(document).ready(function(){
    //获取后台数据的值
    var key=$("#property").val();
    //根据值让option选中
    $("#property1 option[value='"+key+"']").attr("selected","selected");
})


$('#recommend').on('toggle', function (e, active) {
    console.log("88888")
    console.log(active)
    if (active) {
        $('#is_recommend').val(1);
    }else {
        $('#is_recommend').val(0);
    }
});

$('#reprint').on('toggle', function (e, active) {
    console.log("88888")
    console.log(active)
    if (active) {
        $('#is_reprint').val(1);
    }else {
        $('#is_reprint').val(0);
    }
});

$('#appreciation').on('toggle', function (e, active) {
    console.log("88888")
    console.log(active)
    if (active) {
        $('#is_appreciation').val(1);
    }else {
        $('#is_appreciation').val(0);
    }
});

$('#comment').on('toggle', function (e, active) {
    console.log("88888")
    console.log(active)
    if (active) {
        $('#is_comment').val(1);
    }else {
        $('#is_comment').val(0);
    }
});


// function is_recommend(obj) {
//     var this_ = $(obj);
//     var on = this_.find('.toggle-on.active').length;
//     var off = this_.find('.toggle-off.active').length;
//     tale.alertError(on + "+" + off);
//     console.log(on + "+" + off)
//     if (on == 1) {
//         tale.alertError("on");
//         $('#is_recommend').val(1);
//     }
//     if (off == 1) {
//         tale.alertError("off");
//         $('#is_recommend').val(0);
//     }
// }


$('div.allow-false').toggles({
    off: true,
    text: {
        on: '开启',
        off: '关闭'
    }
});

$('#multiple-type').change(function () {
    var postType = $('#multiple-type').val();
    var tags = $('#tags');
    var categories = $('#multiple-sel');
    if(postType == 'post'){
        $('#tags_tagsinput').show(500);
        $('#s2id_multiple-sel').show(500);
        $('#comment-div').attr("style","display:block;");
    }else {
        $('#tags_tagsinput').hide(500);
        $('#s2id_multiple-sel').hide(500);
        $('#comment-div').attr("style","display:none;");

    }
});

$(function () {
    var postType = $('#multiple-type').val();
    var tags = $('#tags');
    var categories = $('#multiple-sel');
    if(postType == 'post'){
        $('#tags_tagsinput').show();
        $('#s2id_multiple-sel').show();
        $('#comment-div').attr("style","display:block;");
    }else {
        $('#tags_tagsinput').hide();
        $('#s2id_multiple-sel').hide();
        $('#comment-div').attr("style","display:none;");

    }
});
