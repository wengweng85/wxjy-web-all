<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360浏览器优先以webkit内核解析-->
    <title>文件上传</title>
    <%
        String staticPath =  com.insigma.common.listener.AppConfig.getProperties("website_static_resource_url");
    %>

    <link href="<%=staticPath%>/resource/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=staticPath%>/resource/hplus/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=staticPath%>/resource/hplus/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=staticPath%>/resource/hplus/css/animate.min.css" rel="stylesheet">
    <link href="<%=staticPath%>/resource/hplus/css/plugins/webuploader/webuploader.css" rel="stylesheet">
    <link href="<%=staticPath%>/resource/hplus/css/demo/webuploader-demo.min.css" rel="stylesheet" >
    <style>
        *, *:before, *:after {
            box-sizing: border-box;
            word-wrap:break-word;
        }

        body {
            padding: 15px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <form class="form-horizontal" role="form">
                <input type="hidden" id="file_bus_id" value="${filerecord.file_bus_id}">
                <input type="hidden" id="url" value="${url}">
                <input type="hidden" id="upload_callback" value="${filerecord.upload_callback}">
                <div class="form-group">
                    <!--用来存放文件信息-->

                    <div class="col-xs-6">
                        <div id="filePicker" style="height: 45px;">选择图片</div>
                        <p class="mt-10">(大小不超过10M)</p>
                    </div>
                    <div class="col-xs-6">
                        <div id="filelist" class="uploader-list"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12">
                        <input type="hidden"  value="身份证及就创业证照片"  class="form-control" id="desc" placeholder="请输入图片描述">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <button type="button" class="btn btn-success btn-block" id="uploadBtn">开始上传</button>
                    </div>
                    <div class="col-xs-6">
                        <button type="button" class="btn btn-default btn-block" id="cancelBtn">取消</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-xs-6">
            <!--用来存放item-->
            <div id="image-list" class="uploader-list"></div>
        </div>
    </div>
</div>
<script src="<%=staticPath%>/resource/hplus/js/jquery.min.js"></script>
<script src="<%=staticPath%>/resource/hplus/js/bootstrap.min.js"></script>
<script src="<%=staticPath%>/resource/hplus/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript">
    var BASE_URL = 'js/plugins/webuploader';
    var contextPath='<c:url value="/"/>';
    var upload_callback=function(){
        parent.${filerecord.upload_callback}();
        parent.layer.msg("上传成功");
        parent.layer.close(index);
    }
</script>
<script src="<%=staticPath%>/resource/hplus/js/plugins/webuploader/webuploader.min.js"></script>

</body>
<script>
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    $(function () {
        var $list = $('#filelist');   //这几个初始化全局的百度文档上没说明，好蛋疼。
        var $btn = $('#uploadBtn');   //开始上传
        var state = 'pending';
        var thumbnailWidth = 100;   //缩略图高度和宽度 （单位是像素），当宽高度是0~1的时候，是按照百分比计算，具体可以看api文档
        var thumbnailHeight = 100;

        var uploader = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: false,

            // swf文件路径
            swf: '<%=staticPath%>/resource/js/webupload/Uploader.swf',

            // 文件接收服务端。
            server: contextPath + $('#url').val(),

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: {
                id: $('#filePicker'), // id
                multiple: false  // false  单选
            },

            fileNumLimit: 1, // 上传数量限制

            fileSingleSizeLimit: 100 * 1024 * 1024, //限制上传单个文件大小

            // 只允许选择图片文件。
            accept: {
            	title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            },
            method: 'POST'
        });

        // 当有文件添加进来的时候
        uploader.on('fileQueued', function (file) {  // webuploader事件.当选择文件后，文件被加载到文件队列中，触发该事件。等效于 uploader.onFileueued = function(file){...} ，类似js的事件定义。
            $list.append( '<div id="' + file.id + '" class="item">' +
                '<h4 class="info">' + file.name + '</h4>' +
                '<p class="state">等待上传...</p>' +
                '</div>' );
        });

        // 文件上传过程中创建进度条实时显示。
        uploader.on('uploadProgress', function (file, percentage) {
            var $li = $( '#'+file.id ),
                $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                    '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                    '</div>' +
                    '</div>').appendTo( $li ).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css( 'width', percentage * 100 + '%' );
        });

        uploader.on('uploadAccept', function (file, response) {
            layer.msg(response.message);
            return response.success === true;
        });

        // 完成上传完了，成功或者失败，先删除进度条。
        uploader.on('uploadComplete', function (file) {
            $('#' + file.id).find('.progress').remove();
        });

        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader.on('uploadSuccess', function (file,response) {
        	parent.$('#filePath').val(response.obj);
            $('#' + file.id).addClass('upload-state-done');
            //执行回调
            upload_callback();
        });

        // 文件上传失败，显示上传出错。
        uploader.on('uploadError', function (file) {
            $( '#'+file.id ).find('p.state').text('上传出错');
        });

        uploader.on( 'all', function( type ) {
            if ( type === 'startUpload' ) {
                state = 'uploading';
            } else if ( type === 'stopUpload' ) {
                state = 'paused';
            } else if ( type === 'uploadFinished' ) {
                state = 'done';
            }

            if ( state === 'uploading' ) {
                $btn.text('暂停上传');
            } else {
                $btn.text('开始上传');
            }
        });

        $btn.on('click', function () {
            if (!uploader.getFiles().length) {
                return;
            }
            if($('#desc').val()==''){
                layer.msg("请输入文件描述");
                return;
            }
            // 添加其它的参数
            uploader.option('formData', {
                file_bus_id : $('#file_bus_id').val(),
                desc: $('#desc').val()
            });
            uploader.upload();
        });

        $('#cancelBtn').on('click', function () {
            parent.layer.closeAll();
        });

    });


</script>
</html>
