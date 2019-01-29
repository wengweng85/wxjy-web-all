<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>日志信息查看</title>
    <rc:csshead/>
</head>
<body class="gray-bg">
    <rc:hidden property="logid_input" value="${logid }"/>
    <div class="wrapper wrapper-content ">
         <table class="table table-bordered table-striped xedittable " id="content">
            <tr>
                 <td  width="100"><strong>日志编号</strong></td><td>${slog.logid}</td>
            </tr>
            <tr>
                 <td><strong>访问地址</strong></td><td id="url">${slog.url}</td>
            </tr>
            <tr>
                 <td><strong>日志时间</strong></td><td id="logtime_string">${slog.logtime_string}</td>
            </tr>
            <tr>
                 <td><strong>日志标题</strong></td><td id="message">${slog.message}</td>
            </tr>
            <tr>
                 <td><strong>异常明细</strong></td><td id="stackmsg">${slog.stackmsg}</td>
            </tr>
            <tr>
                 <td><strong>代理信息</strong></td><td id="usergent">${slog.usergent}</td>
            </tr>
            
            <tr>
                 <td><strong>用户编号</strong></td><td id="userid">${slog.userid}</td>
            </tr>
            <tr>
                 <td><strong>异常类型</strong></td><td id="exceptiontype">${slog.exceptiontype}</td>
            </tr>
        </table>
        </script>
        <!--  
        <div class="form-group" style="text-align: right;">
            <a class="btn btn-danger " onclick="select_closeframe()"><i class="fa fa-remove"></i>&nbsp;关闭</a>
        </div>
        -->
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
    $(function() {
    	var url=gateway_base_url+"/api-base/q_errorlog";
    	rc.api_post_query(url,{logid:$('#logid_input').val()},$('#content'));
    })
     //关闭
    function select_closeframe(){
       var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
       parent.layer.close(index); //再执行关闭   
    }
    </script>
</body>
</html>