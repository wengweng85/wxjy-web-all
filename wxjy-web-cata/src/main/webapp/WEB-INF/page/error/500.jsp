<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>500异常  Internal Server Error</title>
    <link href="<%=path%>/webjars/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/webjars/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path%>/webjars/css/style.min.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="middle-box text-center animated fadeInDown">
        <h1>500</h1>
        <h3 class="font-bold">服务器内部错误</h3>
        <div class="error-desc">
                           服务器好像出错了...
            <br/>您可以返回主页看看
            <br/><a href="<%=path%>/" class="btn btn-primary m-t">主页</a>
        </div>
    </div>
    <script src="<%=path%>/webjars/js/jQuery/jquery-2.0.0.min.js"></script>
    <script src="<%=path%>/webjars/js/bootstrap.min.js"></script>
</body>
</html>

