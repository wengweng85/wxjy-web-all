<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<%@ page import="com.insigma.common.listener.AppConfig" %>
<%
       String gateway_base_url = AppConfig.getProperties("gateway_base_url");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>demo测试编辑页面</title>
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <rc:hidden property="id" value="${id }"/>
        <form action="<%=gateway_base_url%>/api-base/demo/ac01/put" >
        <div id="input_content">
	        <!-- 人员选择基本信息开始 -->
	        <div class="ibox ">
	            <div class="ibox-title">
	                <h5>基本信息</h5>
	            </div>
	            <div class="ibox-content">
		            <div class="form-horizontal"  >
				        <div class="form-group">
				           <rc:hidden property="aac001"  />
			               <rc:textedit  property="aac003"  readonly="true" required="true" label="姓名" validate="{required:true,chinese:true,maxlength:10,messages:{required:'姓名不能为空'}}"  />
			               <rc:textedit  property="aac002"   readonly="true" required="true" datamask="999999999999999999" label="身份证"  validate="{required:true,idcard:true,messages:{required:'身份证不能为空'}}"/>
			               <rc:select property="aac004" required="true"  label="性别"   codetype="AAC004"  validate="{required:true,messages:{required:'性别2不能为空'}}"/>
			           </div>
				       <div class="form-group">
				           <rc:select property="aac005"   required="true"  label="民族"   codetype="AAC005"  validate="{required:true,messages:{required:'民族不能为空'}}"/>
				           <rc:select property="aac011" required="true"  label="学历"   codetype="AAC011"  validate="{required:true,messages:{required:'学历不能为空'}}"/>
				           <rc:date property="aac006"   label="出生日期"  validate="{required:true,messages:{required:'出生日期不能为空'}}"/>
				         </div>
				       <div class="form-group">
				             <rc:select property="aac033"  required="true"  label="健康状况"   codetype="AAC033" validate="{required:true,messages:{required:'健康状况不能为空'}}"/>
				             <rc:select property="aac017"  required="true"  label="婚姻状况"   codetype="AAC017" validate="{required:true,messages:{required:'婚姻状况不能为空'}}"/>
				             <rc:select property="aac024"  required="true"  label="政治面貌"   codetype="AAC024" validate="{required:true,messages:{required:'政治面貌不能为空'}}"/>
				       </div>
				        <div class="form-group">
				           <rc:textedit property="aae006"  required="true" label="联系电话"  validate="{required:true,phone:true,messages:{required:'联系电话不能为空'}}"/>
				           <rc:textedit property="aac067"   required="true"  label="移动电话"  validate="{required:true,mobile:true,messages:{required:'移动电话不能为空'}}"/>
				           <rc:textedit property="aae015"  required="true"    label="电子邮件"  validate="{required:true,email:true,messages:{required:'电子邮件不能为空'}}"/>
				       </div>
			       </div>
		       </div>
	        </div>
	        <!-- 人员基本信息结束 -->
	        <!-- 人员附加信息结束-->
	        <div class="form-group" style="text-align: right;">
	              <a class="btn btn-primary " onclick="demo_save_data()"><i class="fa fa-save"></i>&nbsp;保存</a>
	              <a class="btn btn-danger " onclick="select_closeframe()"><i class="fa fa-remove"></i>&nbsp;关闭</a>
	         </div>
         </div>
        </form>
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
    $(function() {
    	var url=gateway_base_url+"/api-base/demo/ac01/detail";
    	rc.api_post_query(url,{id:$('#id').val()},$('#input_content'));
    	//验证 ajax
    	rc.api_submit($("form"),demo_callback);
    })
    
    //保存页面配置信息
	function demo_save_data(){
	     $('form').submit();
	}
    
     //关闭
    function select_closeframe(){
    	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    	parent.layer.close(index); //再执行关闭   
    }
  
    function demo_callback(response){
    	if(response.success){
           	alert(response.message);
           	parent.demo_query();
           	select_closeframe();
    	}
    	else{
    		alert(response.message);
    	}
    }
    
    </script>
</body>
</html>