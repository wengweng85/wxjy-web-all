<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>代码选择框</title>
    <rc:csshead/>
</head>
<body >
    <div class="wrapper">
         <div id="tree-div"   class="ztree" style="overflow: auto; width:100%; height: 450px;border-bottom:1px solid #ccc ;border-left:1px solid #ccc ;margin-bottom:2px;"></div>
	          <rc:hidden property="callback_fun_name" value="${callback_fun_name}"/>
			  <rc:hidden property="codetype" value="${codetype}"/>
			  <rc:hidden property="code"/>
         <div>
	         <div class="col-sm-2 col-xs-2">
		            <span class="btn btn-xs  btn-outline btn-primary" id="value">${select_val_name }</span>
	         </div>
	         <div class="col-sm-10 col-xs-10" align="right">
	              <button disabled="disabled" id="btn_confirm" class="btn btn-xs btn-primary" onclick="select_confirmframe()"><i class="fa fa-check"></i>&nbsp;确定</button>
	              <button class="btn btn-xs  btn-danger"  onclick="select_closeframe()"><i class="fa fa-remove"></i>&nbsp;取消</button>
	         </div>
         </div>
    </div>
    <rc:jsfooter/> 
    
    <script type="text/javascript">
     var code_type_setting ={
     	  view: {
                showLine: true
     	  },	
     	  check: {
     		    enable: false
     	  },
     	  data: {
	 			simpleData: {
	 				enable: true,
	 				pIdKey: "pid"
	 			}
 		  },
     	  callback: {
     	        onClick:onClick
     	  },
     	  async: {
	     		 enable: true,
	     		 url: "<c:url value='/sys/codetype/treedata'/>/${codetype}",
	     		 autoParam:["id"]
     	  }
     };
     
     //点击事件
 	function onClick(event, treeId, treeNode, clickFlag) {
	 		$('#code').val(treeNode.id);
	 	  	$('#value').html(treeNode.code_describe);
	        $('#btn_confirm').removeAttr("disabled");
 	}
     
     
     function select_confirmframe(){
     	var ff=$('#callback_fun_name').val();
     	if(callback_fun_name){
     		var code=$('#code').val();
         	var value=$('#value').text();
         	if(code){
         		parent.${callback_fun_name}(code,value);
         		select_closeframe();
         	}else{
         		layer.alert('请先选择一条记录');
         	}
     	}else{
     		layer.alert('页面设计缺少相关回调函数,请联系开发人员');
     	}
     }
     //关闭
     function select_closeframe(){
     	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
     	parent.layer.close(index); //再执行关闭   
     }
     
      $(function() {
    	   if(!$('#codetype').val()){
    	       layer.alert('页面设计缺少相关代码类型参数,请联系开发人员');
    	   }else{
    	       $.fn.zTree.init($("#tree-div"), code_type_setting);
    	   }
     })
    
    </script>   
</body>
</html>