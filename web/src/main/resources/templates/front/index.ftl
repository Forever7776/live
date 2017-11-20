<#include "../inc/inc.ftl"/>
<@html>
<div style="padding: 100px 100px 10px;">
    <form action="${root}/upload/pic" class="bs-example bs-example-form" method="get" role="form" enctype="multipart/form-data">
        <div class="row">
            <div class="col-lg-6">
                <div class="input-group">
                    <input type="file" name="file" class="form-control" onchange="upload()">
                    <span class="input-group-btn">
                        <button class="btn btn-red-lg" type="button">上传${request.contextPath}</button>
                    </span>
                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
            <br/><br/>
            <div class="col-lg-6">
                <button class="btn btn-default" type="submit">确定</button>
            </div><!-- /.col-
        </div><!-- /.row -->
    </form>
</div>

<@script_>
<script>
    function upload() {
        $.ajaxFileUpload({
            url : '/upload/pic',// 需要链接到服务器地址
            fileElementId : 'pictureFile',// 文件选择框的id属性
            dataType : 'text/html',// 服务器返回的格式，可以是json
            success : function(data) {
                //data = /{[\"\:\w\d\,\/\\/.]+}/.exec(data)[0];
                var json = jQuery.parseJSON(data);
                console.log(json)
            }
        });
    }
</script>
</@script_>
</@html>