<%@ page import="com.insigma.common.listener.AppConfig" %>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<%
       String gateway_base_url = AppConfig.getProperties("gateway_base_url");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <title>系统日志之异常日志查询</title>
    <!-- css引入 -->
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
         <!-- 查询条件开始 -->
         <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>查询条件</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
	            <form class="form-horizontal" id="query_form" >
			       <div class="form-group">
			           <div class="col-sm-12" align="right">
			              <rc:textedit property="exceptiontype"   cols="1,3"  label="异常类型" value="你好"/>
		                  <a class="btn btn-info" onclick="param_query()"><i class="fa fa-search"></i>&nbsp;查询</a>
		                  <a class="btn btn-info" onclick="rc.clean($('query_form'))"><i class="fa fa-refresh"></i>&nbsp;重置</a>
		               </div>
			       </div>
		       </form>
	       </div>
        </div>
        <!-- 查询条件结束 -->
        <!-- 查询结果开始 -->    
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>查询结果列表</h5>
                <div class="ibox-tools">
                </div>
            </div>
            <!-- toolbar -->
            <div class="ibox-content">
			    <table id="logtable" data-url="<%=gateway_base_url %>/api-base/errorlogs" 
			          data-click-to-select="false"
                      data-show-export="true"
                      data-pagination="true"
                      data-uniqueId="1"
                      data-page-size="15" 
                      >
			    <thead>
				    <tr>
				        <th data-formatter="_indexFormatter">序号</th>
				        <th width="150" data-field="logtime_string" >发生时间</th>
				        <th data-field="url" data-formatter="text_next_row_indexFormatter">访问地址</th>
				        <th data-field="message">错误信息</th>
	                    <th data-field="userid">用户编号</th>
	                    <th data-field="usergent">代理信息</th>
	                    <th data-field="exceptiontype">异常类型</th> 
				    </tr>
			    </thead>
			    </table>
            </div>
        </div>
       <!-- 查询结果结束 -->
    </div>
    <!-- javascript引入 -->
    <rc:jsfooter/>
    <script type="text/javascript">
    var $table=$('#logtable');
    var demo_options={
    	formid:'query_form'
    }
    //初始化
    $(function(){
    	$('.collapse-link').click();
    	$('#logtable').inittable_v2(demo_options);
    });
   
  //用户表格监听,双击 
    $('#logtable').on('dbl-click-row.bs.table', function (e, row, $element) {
    	view_by_id(row.logid)
    });
  
    function _indexFormatter(value, row, index) {
        return index+1;
    }
    //查询
    function param_query(){
    	$('#logtable').refreshtable();
    	$('.collapse-link').click();
    }
    function text_next_row_indexFormatter(value, row, index){
    	if(value!='undefined'){
    		return "<span style='word-wrap:break-word;word-break:break-all;'>"+value+"</span>"
    	}
    }
    
    //查看
    function view_by_id(id){
    	layer.open({
      		  type: 2,
      		  title: '应用错误日志查看',
      		  shadeClose: true,
      		  maxmin:true,
      		  area: ['70%', '90%'],
      		  content: "<c:url value='/log/getErrorLogById'/>/"+id //iframe的url
   		});
    }
    </script>
</body>
</html>