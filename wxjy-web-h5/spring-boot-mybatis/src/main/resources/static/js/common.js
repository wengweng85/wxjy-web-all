/*
 * Created by Dabay on 2016/6/23
 */



document.addEventListener("change",function(evt){
		var formId = $("#formId").val()
	    var target=evt.target;
	    var sizeType='0';
	    var fileNum;
	    var fileId = target.id;
	    var f;
	    switch (fileId){
	    case 'xz_file1':
	    	f=$("#xz_file1").val();
	    	fileNum ="1";
		    if(f==null||f==""){  
		    	$.alert("错误提示:上传文件不能为空,请重新选择文件!");
		        return false;  
		      }else{  
		       var extname = f.substring(f.lastIndexOf(".")+1,f.length);  
		       extname = extname.toLowerCase();//处理了大小写  
		       if(extname!= "jpeg"&&extname!= "jpg"&&extname!= "gif"&&extname!= "png"){  
		    	 $.alert("错误提示:格式不正确,支持的图片格式为：JPEG、GIF、PNG!");  
		         return false;  
		        }  
		      }
		     var file = document.getElementById(fileId).files;    
		     var size = file[0].size;  
		     if(size>10485760){
		    	 $.alert("<span style='color:Red'>错误提示:所选择的图片太大，图片大小最多支持10M!</span>");  
		         return false; 
		     }
		     if(size>2097152){  
		    	 sizeType='1';
		      }
		     $.ajaxFileUpload({  
		         url : ctxPath+'/zhejiang/m/fileUpload.json?appId='+formId+'&sizeType='+sizeType+"&fileNum="+fileNum,  
		         secureuri : false, //一般设置为false  
		         fileElementId : fileId, //文件上传空间的id属性  <input type="file" id="file" name="file" />  
		         type : 'post',  
		         dataType : 'json', //返回值类型 一般设置为json  
		         success : function(data) //服务器成功响应处理函数  
		         {  	  
		        	 var filename = data[0].demoData;
		        	 if(filename=='00'){
		        		 $.alert("错误提示:文件上传失败!");
		        		 return;
		        	 }
		        	 $("#btn1").css("display","block");       	 
		        	 $("#delete1").val(filename);
		             $("#xz_file1").css("display","none");
		             $("#a_file1").css("background", "none");	   
		             $("#a_file1").css("background", "url("+ctxPath+"/jsp/zhejiang/myimages/"+formId+"/"+filename+")");
		             $("#a_file1").css("background-size", "50px 50px");
		         }  
		     });
	    	break;
	    case 'xz_file2':
	    	f=$("#xz_file2").val();
	    	fileNum ="2";
	    	if(f==null||f==""){  
		    	$.alert("错误提示:上传文件不能为空,请重新选择文件!");
		        return false;  
		      }else{  
		       var extname = f.substring(f.lastIndexOf(".")+1,f.length);  
		       extname = extname.toLowerCase();//处理了大小写  
		       if(extname!= "jpeg"&&extname!= "jpg"&&extname!= "gif"&&extname!= "png"){  
		    	 $.alert("错误提示:格式不正确,支持的图片格式为：JPEG、GIF、PNG!");  
		         return false;  
		        }  
		      }
		     var file = document.getElementById(fileId).files;    
		     var size = file[0].size;  
		     if(size>10485760){
		    	 $.alert("<span style='color:Red'>错误提示:所选择的图片太大，图片大小最多支持10M!</span>");  
		         return false; 
		     }
		     if(size>2097152){  
		    	 sizeType='1';
		      }
		     $.ajaxFileUpload({  
		         url : ctxPath+'/zhejiang/m/fileUpload.json?appId='+formId+'&sizeType='+sizeType+"&fileNum="+fileNum,  
		         secureuri : false, //一般设置为false  
		         fileElementId : fileId, //文件上传空间的id属性  <input type="file" id="file" name="file" />  
		         type : 'post',  
		         dataType : 'json', //返回值类型 一般设置为json  
		         success : function(data) //服务器成功响应处理函数  
		         {  
		        	 var filename = data[0].demoData;
		        	 if(filename=='00'){
		        		 $.alert("错误提示:文件上传失败!");
		        		 return;
		        	 }
		        	 $("#btn2").css("display","block");
		        	 $("#delete2").val(filename);
		             $("#xz_file2").css("display","none");
		             $("#a_file2").css("background", "none");
		             $("#a_file2").css("background", "url("+ctxPath+"/jsp/zhejiang/myimages/"+formId+"/"+filename+")");
		             $("#a_file2").css("background-size", "50px 50px");
		         }  
		     });
	    	break;
	    case 'xz_file3':
	    	f=$("#xz_file3").val();
	    	fileNum ="3";
	    	if(f==null||f==""){  
		    	$.alert("错误提示:上传文件不能为空,请重新选择文件!");
	        	 if(filename==''){
	        		 $.alert("错误提示:文件上传失败!");
	        	 }
		        return false;  
		      }else{  
		       var extname = f.substring(f.lastIndexOf(".")+1,f.length);  
		       extname = extname.toLowerCase();//处理了大小写  
		       if(extname!= "jpeg"&&extname!= "jpg"&&extname!= "gif"&&extname!= "png"){  
		    	 $.alert("错误提示:格式不正确,支持的图片格式为：JPEG、GIF、PNG!");  
		         return false;  
		        }  
		      }
		     var file = document.getElementById(fileId).files;    
		     var size = file[0].size;  
		     if(size>10485760){
		    	 $.alert("<span style='color:Red'>错误提示:所选择的图片太大，图片大小最多支持10M!</span>");  
		         return false; 
		     }
		     if(size>2097152){  
		    	 sizeType='1'; 
		      }
		     $.ajaxFileUpload({  
		         url : ctxPath+'/zhejiang/m/fileUpload.json?appId='+formId+'&sizeType='+sizeType+"&fileNum="+fileNum,  
		         secureuri : false, //一般设置为false  
		         fileElementId : fileId, //文件上传空间的id属性  <input type="file" id="file" name="file" />  
		         type : 'post',  
		         dataType : 'json', //返回值类型 一般设置为json  
		         success : function(data) //服务器成功响应处理函数  
		         {  
		        	 var filename = data[0].demoData;
		        	 if(filename=='00'){
		        		 $.alert("错误提示:文件上传失败!");
		        		 return;
		        	 }
		        	 $("#btn3").css("display","block");
		        	 $("#delete3").val(filename);
		             $("#xz_file3").css("display","none");
		             $("#a_file3").css("background", "none");
		             $("#a_file3").css("background", "url("+ctxPath+"/jsp/zhejiang/myimages/"+formId+"/"+filename+")");
		             $("#a_file3").css("background-size", "50px 50px");	              
		         }  
		     });
	    	break;
	    case 'xz_file4':
	    	f=$("#xz_file4").val();
	    	fileNum ="4";
	    	if(f==null||f==""){  
		    	$.alert("错误提示:上传文件不能为空,请重新选择文件!");
		        return false;  
		      }else{  
		       var extname = f.substring(f.lastIndexOf(".")+1,f.length);  
		       extname = extname.toLowerCase();//处理了大小写  
		       if(extname!= "jpeg"&&extname!= "jpg"&&extname!= "gif"&&extname!= "png"){  
		    	 $.alert("错误提示:格式不正确,支持的图片格式为：JPEG、GIF、PNG!");  
		         return false;  
		        }  
		      }
		     var file = document.getElementById(fileId).files;    
		     var size = file[0].size;  
		     if(size>10485760){
		    	 $.alert("<span style='color:Red'>错误提示:所选择的图片太大，图片大小最多支持10M!</span>");  
		         return false; 
		     }
		     if(size>2097152){  
		    	 sizeType='1'; 
		      }
		     $.ajaxFileUpload({  
		         url : ctxPath+'/zhejiang/m/fileUpload.json?appId='+formId+'&sizeType='+sizeType+"&fileNum="+fileNum,  
		         secureuri : false, //一般设置为false  
		         fileElementId : fileId, //文件上传空间的id属性  <input type="file" id="file" name="file" />  
		         type : 'post',  
		         dataType : 'json', //返回值类型 一般设置为json  
		         success : function(data) //服务器成功响应处理函数  
		         {  
		        	 var filename = data[0].demoData;
		        	 if(filename=='00'){
		        		 $.alert("错误提示:文件上传失败!");
		        		 return;
		        	 }
		        	 $("#btn4").css("display","block");
		        	 $("#delete4").val(filename);
		             $("#xz_file4").css("display","none");
		             $("#a_file4").css("background", "none");
		             $("#a_file4").css("background", "url("+ctxPath+"/jsp/zhejiang/myimages/"+formId+"/"+filename+")");
		             $("#a_file4").css("background-size", "50px 50px");	              
		         }  
		     });
	    	break;
	    case 'xz_file5':
	    	f=$("#xz_file5").val();
	    	fileNum ="5";
	    	if(f==null||f==""){  
		    	$.alert("错误提示:上传文件不能为空,请重新选择文件!");
		        return false;  
		      }else{  
		       var extname = f.substring(f.lastIndexOf(".")+1,f.length);  
		       extname = extname.toLowerCase();//处理了大小写  
		       if(extname!= "jpeg"&&extname!= "jpg"&&extname!= "gif"&&extname!= "png"){  
		    	 $.alert("错误提示:格式不正确,支持的图片格式为：JPEG、GIF、PNG!");  
		         return false;  
		        }  
		      }
		     var file = document.getElementById(fileId).files;    
		     var size = file[0].size;  
		     if(size>10485760){
		    	 $.alert("<span style='color:Red'>错误提示:所选择的图片太大，图片大小最多支持10M!</span>");  
		         return false; 
		     }
		     if(size>2097152){  
		    	 sizeType='1'; 
		      }
		     $.ajaxFileUpload({  
		         url : ctxPath+'/zhejiang/m/fileUpload.json?appId='+formId+'&sizeType='+sizeType+"&fileNum="+fileNum,  
		         secureuri : false, //一般设置为false  
		         fileElementId : fileId, //文件上传空间的id属性  <input type="file" id="file" name="file" />  
		         type : 'post',  
		         dataType : 'json', //返回值类型 一般设置为json  
		         success : function(data) //服务器成功响应处理函数  
		         {  
		        	 var filename = data[0].demoData;
		        	 if(filename=='00'){
		        		 $.alert("错误提示:文件上传失败!");
		        		 return;
		        	 }
		        	 $("#btn5").css("display","block");
		        	 $("#delete5").val(filename);
		             $("#xz_file5").css("display","none");
		             $("#a_file5").css("background", "none");
		             $("#a_file5").css("background", "url("+ctxPath+"/jsp/zhejiang/myimages/"+formId+"/"+filename+")");
		             $("#a_file5").css("background-size", "50px 50px");	              
		         }  
		     });
	    	break;
	   }
});
 
/*button*/
 function mychange()
 {
	 document.getElementById("btn_submit,btn_submit2,btn_submit3").style.background="#2284dd";
 }
	
/*popup*/
document.addEventListener("click",function(evt){
	var target = evt.target;
	switch(target.id){
		case "layer1":
			var html = $('#alertdiv').html();
			layer.closeAll()
			var page1 = layer.open({
				type: 1,
				style: 'width:100%',
				shadeClose: false,
				content: html
			});
			break;
		case "layer2":
			var html = $('#alertdiv2').html();
			layer.closeAll()
			var page2 = layer.open({
				type: 1,
				style: 'width:100%',
				shadeClose: false,
				content: html
			});
			break;
			case "layer3":
			var html = $('#alertdiv3').html();
			layer.closeAll()
			var page3 = layer.open({
				type: 1,
				style: 'width:100%',
				shadeClose: false,
				content: html
			});
			break;
	}
},false);


/*menu*/
function add(e){
        e.parent().find("li").removeClass("on");
        e.addClass("on");
        var index= e.index();
        $(".rank-box").hide();
        $(".rank ul:nth-child("+(index+1)+")").show();
    }
	
/**/
$(function () {

        $(".show").bind("click", function () {

            if ($(this).next("#Direction").is(".Ejiantou_blue_down")) {

                $(this).next("#Direction").removeClass("Ejiantou_blue_down").addClass("Ejiantou_blue_up");

                $(this).html("收起");

                $(this).parent().prev("#Expansion").removeClass("Ehidden_2");

            } else {

                $(this).next("#Direction").removeClass("Ejiantou_blue_up").addClass("Ejiantou_blue_down");

                $(this).html("展开");

                $(this).parent().prev("#Expansion").addClass("Ehidden_2");

            }

        });

    });  

//发送手机验证码
function sendMsg(val){
	var phoneNumVal = $("#phone_num").val();
	if(!validPhoneNum(phoneNumVal)){
		$.alert("请输入有效的手机号码");
		return"";
	}
	var data = {};
	data.phone_num =$("#phone_num").val();
	$.ajax({
		url:ctxPath+'/zhejiang/m/sendMsg.json',
		type:'post',
		data: data,
		dataType:'html',
		ifModified:true,
		cache:false,
		success: function(data){
			data = $.parseJSON(data);
			var code = data.resultCode;
			if(code ==10200){
				$.alert("验证码已发送到您"+phoneNumVal+"的手机上,请注意查收！");
				settime(val);
			}
			else
			{
				$.alert("验证码发送失败,每个手机号码每天最多可接收8条验证码");
			}
		}
	});
}

//页面倒计时器
var countdown=60; 
function settime(val) { 
	if (countdown == 0) { 
		$("#sendValidId").attr("onclick", "sendMsg(this)");  
		val.text="重发验证码"; 
		countdown = 60;
		return;
	} else { 
		$("#sendValidId").attr("onclick", "null");
		val.text="(" + countdown + ")秒后重发"; 
		countdown--; 
	} 
	setTimeout(function() { 
		settime(val) 
	},2056) 
} 


function validPhoneNum(phoneVal){
	var myreg = /^(((1[0-9]{1}))+\d{9})$/; 
	if(!myreg.test(phoneVal)) 
	{ 
	    return false; 
	} 
	return true;
}
	//手机号码格式校验
	$("#phone_num").change(function(){
		var reg = /(^\d{11,15}$)/;
		if (reg.test($("#phone_num").val())==false){
			$.alert("您输入的手机号码格式不合法，请重新输入");
			$("#phone_num").val("");
		}
	});
	
	$(function () {

        $(".shows").bind("click", function () {


            if ($(this).next("#Direction").is(".Ejiantou_blue_down")) {

                $(this).next("#Direction").removeClass("Ejiantou_blue_down").addClass("Ejiantou_blue_up");

                $(this).html("收起");

                $(this).parent().prev("#Expansion").removeClass("Ehidden_2");

            } else {

                $(this).next("#Direction").removeClass("Ejiantou_blue_up").addClass("Ejiantou_blue_down");

                $(this).html("展开");

                $(this).parent().prev("#Expansion").addClass("Ehidden_2");

            }

        });

    }); 
	
	//进入选择地域或者选择问题类型页面并将页面数据保存session
	function toSession(sessionType){
		var receiver =$("#receiver").val();
		var advice=$('input:radio[name="advice"]:checked').val();	
		var areaCode = $("#areaCode").val();//地域code
		var areaId = $("#areaId").val();//地域Id
		var areaName = $("#areaName").text();//地域名字		
		var deptName = $("#deptName").text();//部门名称
		var matterAreaName = $("#matterAreaName").text();//问题所属区域
		
		var sessionTypeVal = sessionType;
		var anonymous='0'
		if($('#nimingbox').is(':checked')){
			anonymous='1';
		}else{
			anonymous='0';
		}
		var name=$("#myname").val();
		var phone=$("#phone_num").val();
		var title=$("#title").val();
		var content=$("#content").val();
		var matterAreaId=$("#countyId").val();
		var is_public=$('input:radio[name="isPublic"]:checked').val();		
		var unit_id=$("#unit_id").val();
		var phoneMsg=$("#code").val();
		var form_id = $("#formId").val();
		var file1 = $("#delete1").val();
		var file2 = $("#delete2").val();
		var file3 = $("#delete3").val();
		var file4 = $("#delete4").val();
		var file5 = $("#delete5").val();
		var data = {};
		data.receiver = receiver;
		data.advice = advice;
		data.areaName= areaName;
		data.areaCode= areaCode;
		data.areaId= areaId;
		data.deptName=deptName;
		data.matterAreaName= matterAreaName;
		data.matterAreaId=matterAreaId;
		data.anonymous =anonymous;
		data.name =name;
		data.phone=phone;
		data.title=title;
		data.content=content;
		data.unit_id=unit_id;
		data.isPublic=is_public;
		data.phoneMsg=phoneMsg;
		data.form_id=form_id;
		data.file1 = file1;
		data.file2 = file2;
		data.file3 = file3;
		data.file4 = file4;
		data.file5 = file5;
		if((sessionTypeVal ==2)&&(!areaCode)){
			$.alert("请先选择区域");
			return;
		}
		$.ajax({
			url:ctxPath+'/zhejiang/m/submitToSession.json',
			type:'post',
			data: data,
			dataType:'html',
			ifModified:true,
			cache:false,
			success: function(data){
				if(sessionTypeVal ==1){
					window.location.href=ctxPath+'/zhejiang/m/toAreaSelect';
				}else if(sessionTypeVal ==2){
					window.location.href=ctxPath+'/zhejiang/m/toDeptSelect?area_code='+areaCode+'&area_name='+areaName;
				}else if(sessionTypeVal ==3){
					window.location.href=ctxPath+'/zhejiang/m/toMatAreaSelect';
				}else if(sessionTypeVal ==4){
					window.location.href=ctxPath+'/zhejiang/m/getmatter?matter_level=&matter_id=';
				}
				
			}
		});
	}
	
	//点击产生匿名效果的操作
	$("#nimingbox").change(function(){
		if($('#nimingbox').is(':checked')){
			$("#myname").css("display","none");
			$("#niming").css("display","block");
		}
		else{
			$("#myname").css("display","block");
			$("#niming").css("display","none");
		}
	});
	
	//验证内容字数
	$("#content").change(function(){
		var content=$("#content").val();
		var length = content.length;
		if(!content){
			$.alert("请输入内容")
			return;
		}
		if((length>1000)||(length<10)){
			$.alert("诉求内容字数未达到指定长度");
			return;
		}
	});
	
	function deleteFile1(){
		var data = {};
		data.filename = $("#delete1").val();
		data.appId=$("#formId").val();
		data.fileid = '1';
		$.ajax({
			url:ctxPath+'/zhejiang/m/fileDelete.json',
			type:'post',
			data: data,
			dataType:'html',
			ifModified:true,
			cache:false,
			success: function(data){
				$("#xz_file1").css("display","block");
				$("#btn1").css("display","none");
				$("#delete1").val("");
				$("#a_file1").css("background","none");
				$("#a_file1").css("background", "url("+ctxPath+"/jsp/zhejiang/images/add_photo.png)");
				$("#a_file1").css("background-size", "50px 50px");
			}
		});
	}
	
	function deleteFile2(){
		var data = {};
		data.fileid = '2';
		data.appId=$("#formId").val();
		data.filename = $("#delete2").val();
		$.ajax({
			url:ctxPath+'/zhejiang/m/fileDelete.json',
			type:'post',
			data: data,
			dataType:'html',
			ifModified:true,
			cache:false,
			success: function(data){
				$("#xz_file2").css("display","block");
				$("#btn2").css("display","none");
				$("#delete2").val("");
				$("#a_file2").find("img").remove();
				$("#a_file2").css("background", "url("+ctxPath+"/jsp/zhejiang/images/add_photo.png)");
				$("#a_file2").css("background-size", "50px 50px");
			}
		});
	}
	function deleteFile3(){
		var data = {};
		data.fileid = '3';
		data.appId=$("#formId").val();
		data.filename = $("#delete3").val();
		$.ajax({
			url:ctxPath+'/zhejiang/m/fileDelete.json',
			type:'post',
			data: data,
			dataType:'html',
			ifModified:true,
			cache:false,
			success: function(data){
				$("#xz_file3").css("display","block");
				$("#btn3").css("display","none");
				$("#delete3").val("");
				$("#a_file3").css("background", "url("+ctxPath+"/jsp/zhejiang/images/add_photo.png)");
				$("#a_file3").css("background-size", "50px 50px");
			}
		});
	}
	function deleteFile4(){
		var data = {};
		data.fileid = '4';
		data.appId=$("#formId").val();
		data.filename = $("#delete4").val();
		$.ajax({
			url:ctxPath+'/zhejiang/m/fileDelete.json',
			type:'post',
			data: data,
			dataType:'html',
			ifModified:true,
			cache:false,
			success: function(data){
				$("#xz_file4").css("display","block");
				$("#btn4").css("display","none");
				$("#delete4").val("");
				$("#a_file4").find("img").remove();
				$("#a_file4").css("background", "url("+ctxPath+"/jsp/zhejiang/images/add_photo.png)");
				$("#a_file4").css("background-size", "50px 50px");
			}
		});
	}
	function deleteFile5(){
		var data = {};
		data.fileid = '5';
		data.appId=$("#formId").val();
		data.filename = $("#delete5").val();
		$.ajax({
			url:ctxPath+'/zhejiang/m/fileDelete.json',
			type:'post',
			data: data,
			dataType:'html',
			ifModified:true,
			cache:false,
			success: function(data){
				$("#xz_file5").css("display","block");
				$("#btn5").css("display","none");
				$("#delete5").val("");
				$("#a_file5").css("background", "url("+ctxPath+"/jsp/zhejiang/images/add_photo.png)");
				$("#a_file5").css("background-size", "50px 50px");
			}
		});
	}
	
	//跳转到我要写信的详情页面
	
	function towyxx(receiver,unit_id){
		var m_receiver = jiami(receiver);
		var m_unit_id = jiami(unit_id);
		receiver = escape(escape(m_receiver));
		unit_id=escape(m_unit_id);
		window.location.href=ctxPath+'/zhejiang/m/mailToLeader?receiver='+receiver+'&unit_id='+unit_id;
	}
	
	//生成指定长度的随机字符串
	function generatePassword(length){  
    length = length || 32;  
    var source = "abcdefghzklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
    var s = "";  
    for(var i = 0;i < length; i++)  {  
        s += source.charAt(Math.ceil(Math.random()*1000)%source.length);  
    }
    return s;  
	}
	
	//加密算法
	function jiami(key){
		var sss = "";
		var arr = key.split("");
		for(var i=0;i<arr.length;i++){
			var cha = arr[i];
			var prif = generatePassword(4);
			var res = prif+ cha;
			sss = sss+res;
		}
		return sss;

	};
	
	//解密算法
	function jiemi(source){
		var n = source.length/5; 
		var res ="";
		for(var i=4;i<source.length;i=i+5){
			res = res + source.substring(i,i+1);
		}
		return res;
	}
	
	//模拟发送post请求
	function post(URL, PARAMS) {        
	    var temp = document.createElement("form");        
	    temp.action = URL;        
	    temp.method = "post";        
	    temp.style.display = "none";        
	    for (var x in PARAMS) {        
	        var opt = document.createElement("textarea");        
	        opt.name = x;        
	        opt.value = PARAMS[x];        
	        // alert(opt.name)        
	        temp.appendChild(opt);        
	    }        
	    document.body.appendChild(temp);        
	    temp.submit();        
	    return temp;        
	}   
	
	function deptToSubmit(dept_id,dept_name){
		var URL = ctxPath+'/zhejiang/m/deptToSubmit.json';
		var PARAMS = {'dept_name':dept_name,'dept_id':dept_id};
		post(URL,PARAMS);
	}
	
	function bigMatToSubmit(matterName,unitId){
		var URL = ctxPath+'/zhejiang/m/bigMatToSubmit.json';
		var PARAMS = {'matterName':matterName,'unitId':unitId};
		post(URL,PARAMS);
	}
	
	function getmatter(matter_level,matter_id){
		var URL = ctxPath+'/zhejiang/m/getmatter.json';
		var PARAMS = {'matter_level':matter_level,'matter_id':matter_id};
		post(URL,PARAMS);
	}
	
	function matToSubmit(matter_name,matter_code){
		var URL = ctxPath+'/zhejiang/m/matToSubmit.json';
		var PARAMS = {'matter_name':matter_name,'matter_code':matter_code};
		post(URL,PARAMS);
	}
	
/*	  wx.config({
		  debug: true,
	      appId: appId,
	      timestamp:timestamp,
	      nonceStr:noncestr,
	      signature:signature,
	      jsApiList: [
	        'hideOptionMenu'
	      ]
	  });
	  
		wx.ready(function(){
			wx.hideOptionMenu();
		});*/
		
	function toindex(){
		window.location.href=ctxPath+'/zhejiang/m/index.json';
	}
	