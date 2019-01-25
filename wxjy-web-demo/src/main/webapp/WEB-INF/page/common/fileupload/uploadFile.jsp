<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<%@ page isELIgnored="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%
       String path = request.getContextPath();
%>
<html>
<head>
  <title>文件上传</title>
  <!--图片上传-->
  <rc:csshead/>
  <link rel="stylesheet" href="<%=path%>/resource/js/webuploader/webuploader.css">
  <script src="<%=path%>/resource/js/webuploader/webuploader.js"></script>
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
    <div class="col-xs-6">
      <form class="form-horizontal" role="form">
        <input type="hidden" id="fileStyle" name="fileStyle" value="${fileStyle}"/>
        <input type="hidden" id="businessType" name="businessType" value="${businessType}"/>
        <input type="hidden" id="fileRandomFlag" name="fileRandomFlag" value="${fileRandomFlag}"/>
        <div class="form-group">
          <div class="col-xs-12">
            <div id="filePicker">选择图片</div>
            <p class="mt-10">(大小不能超过1M，支持格式为:jpg、png等)</p>
          </div>
        </div>
        <div class="form-group">
          <div class="col-xs-12">
           <!-- <input type="text" class="form-control" id="desc" placeholder="请输入图片描述">-->
            <input type="hidden" class="form-control" id="desc" placeholder="请输入图片描述">
          </div>
        </div>
        <div class="form-group">
          <div class="col-xs-6">
            <button type="button" class="btn btn-success btn-block" id="uploadBtn">上传</button>
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
<rc:jsfooter/>
</body>
<script>
$(function () {
    var fileStyle = $('#fileStyle').val();
    var businessType = $('#businessType').val();
    var fileRandomFlag = $('#fileRandomFlag').val();
    var $list = $('#image-list');   //这几个初始化全局的百度文档上没说明，好蛋疼。
    var $btn = $('#uploadBtn');   //开始上传
    var thumbnailWidth = 100;   //缩略图高度和宽度 （单位是像素），当宽高度是0~1的时候，是按照百分比计算，具体可以看api文档
    var thumbnailHeight = 100;

    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: false,

        // swf文件路径
        swf: '<%=path%>/resource/js/webupload/Uploader.swf',

        // 文件接收服务端。
        server: '<%=path%>/common/uploadFile/uploadImage/'+fileStyle+"/"+businessType+'/'+fileRandomFlag,

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {
            id: $('#filePicker'), // id
            multiple: true  // false  单选
        },

        //fileNumLimit: 10, // 上传数量限制
       // threads:'5',
        fileSizeLimit:3 * 1024 * 1024,//限制所有文件大小
        fileSingleSizeLimit: 3 * 1024 * 1024, //限制上传单个文件大小

        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'jpg,jpeg,png',
            mimeTypes: 'image/*'
        },
        method: 'POST'
    });

    // 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {  // webuploader事件.当选择文件后，文件被加载到文件队列中，触发该事件。等效于 uploader.onFileueued = function(file){...} ，类似js的事件定义。
        var $li = $(
            '<div id="' + file.id + '" class="file-item thumbnail">' +
            '<img>' +
            '<div class="info">' + file.name + '</div>' +
            '</div>'
            ),
            $img = $li.find('img');


        // $list为容器jQuery实例
        $list.append($li);

        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb(file, function (error, src) {   //webuploader方法
            if (error) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr('src', src);
        }, thumbnailWidth, thumbnailHeight);
    });

    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped">\n' +
                '    <div class="progress-bar progress-bar-success" role="progressbar">\n' +
                '    </div>\n' +
                '</div>')
                .appendTo($li)
                .find('.progress-bar');
        }

        $percent.css('width', percentage * 100 + '%');
    });

    uploader.on('uploadAccept', function (file, response) {
        layer.msg(response.message);
        return response.success;
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').remove();
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on('uploadSuccess', function (file) {
        $('#' + file.id).addClass('upload-state-done');
        console.log('上传成功');

        // 上传成功后，关闭弹窗
        parent.layer.closeAll();
        // 更新父页面的列表
        parent.query(fileStyle,businessType,fileRandomFlag);
    });

    // 文件上传失败，显示上传出错。
    uploader.on('uploadError', function (file) {
        console.log(file);
        var $li = $('#' + file.id), $error = $li.find('div.error');

        // 避免重复创建
        if (!$error.length) {
            $error = $('<div class="text-red"></div>').appendTo($li);
        }

        $error.text('上传失败');
        console.log('上传失败');
    });

    /**
     * 验证文件格式以及文件大小
     */
    uploader.on("error", function (type) {
        console.log(type);
        if (type === 'Q_TYPE_DENIED') {
            layer.msg("请上传JPG、JPEG、PNG格式的文件");
        } else {
            layer.msg('上传出错！错误代码' + type);
        }

    });

    $btn.on('click', function () {
        if (!uploader.getFiles().length) {
            return;
        }
        // 添加其它的参数
        uploader.option('formData', {
            desc: $('#desc').val()
        });
        uploader.upload();
    });

    $('#cancelBtn').on('click', function () {
        //弹窗关闭刷新父页面
        parent.layer.closeAll();
    });

});
</script>
</html>
