<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
 <%
        String staticPath =  com.insigma.common.listener.AppConfig.getProperties("website_static_resource_url");
    %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360浏览器优先以webkit内核解析-->
     <title>文件上传</title>
     
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
            padding:25px 15px;
        }
    </style>
</head>
<body >
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <form class="form-horizontal" role="form">
                <input type="hidden" id="excel_batch_excel_type" value="${sExcelBatch.excel_batch_excel_type}">
                <input type="hidden" id="excel_batch_assistid" value="${sExcelBatch.excel_batch_assistid}">
                <input type="hidden" id="mincolumns" value="${sExcelBatch.mincolumns}">
                <input type="hidden" id="upload_callback" value="${sExcelBatch.upload_callback}">
                <div class="form-group">
                    <div class="col-xs-6">
                        <div id="picker" style="height: 45px;">选择文件</div>
                        <p class="mt-10">(文件不超过100M)</p>
                    </div>
                    <!--用来存放文件信息-->
                    <div class="col-xs-6">
                        <div id="thelist" class="uploader-list"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <button type="button" class="btn btn-success btn-block" id="ctlBtn">开始上传</button>
                    </div>
                    <div class="col-xs-6">
                        <button type="button" class="btn btn-default btn-block" id="cancelBtn">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
    <script src="<%=staticPath%>/resource/hplus/js/jquery.min.js"></script>
    <script src="<%=staticPath%>/resource/hplus/js/bootstrap.min.js"></script>
    <script src="<%=staticPath%>/resource/hplus/js/plugins/layer/layer.min.js"></script>
    <script type="text/javascript">
        var BASE_URL = 'js/plugins/webuploader';
        var contextPath='<c:url value="/"/>';
        var upload_callback=function(excel_batch_number){
        	parent.${sExcelBatch.upload_callback}()
        }
    </script>
    <script src="<%=staticPath%>/resource/hplus/js/plugins/webuploader/webuploader.min.js"></script>
    <script src="<%=staticPath%>/resource/hplus/js/json2.js"></script>
    <script src="<%=staticPath%>/resource/hplus/js/rc.webuploader.excelfile.js"></script>

<script>
    $(function () {
        $('#cancelBtn').on('click', function () {
            parent.layer.closeAll();
        });
    });
</script>
</body>
</html>
