/**
 * rc.all-2.0.js
 *
 * 用于网站的主要功能js
 * @author wengsh
 * 
 * version 2.0
 * 修改记录
 * v2.0 在公共服务框架中使用此框架,此框架目前为支持基于springmvc+spring+mybatis的管理系统框架,框架ui为hplus
 *      hplus是基于bootstrap的自定义ui框架.从维护角度来说，这个框架有一个大的问题，就是没有人做支持维护。现阶段的安排是觉得这
 *      个框架ui自成一体。比较漂亮，从开发难度上来说可能会有一些。但这个框架有一些参考好的样式，非常漂亮。
 *      此版本升级点为在框架中增加相关权限类管理功能，包括权限管理、角色管理、授权管理、参考管理、代码管理 
 *      
 * 2018-1-11 修改xxx    
 * 增加网页版本直接调用接口公共js,在网页端保存token
 * 更新ajax校验头 如果发现token为空或异常.做重写向处理
 * 
 * 
 *
 */
var contextPath;// 工程路径
$(function() {
	if($.metadata){
		//验证框架使用此属性
		$.metadata.setType("attr", "validate");
	}

	if(layer){
		window.alert=layer.alert;
	}
	if (!contextPath) {
		if($('#contextPath').length>0){
			contextPath=$('#contextPath').val();
			/*rc.debug('#contextPath'+contextPath);*/
		}else{
			var pathName = document.location.pathname;
			var index = pathName.substr(1).indexOf("/");
			contextPath = pathName.substr(0,index+1);
		}
		/*rc.debug('contextPath'+contextPath);*/
	}
	
    //ajax如果session超时,强制跳转到登录页面	
	$.ajaxSetup( {
		cache: false ,
		//设置ajax请求结束后的执行动作
		complete:
		function(XMLHttpRequest, textStatus) {
			// 通过XMLHttpRequest取得响应头，sessionstatus
			var sessionstatus = XMLHttpRequest.getResponseHeader("statuscode");
			// 登录超时或访问了未授权的信息
			if (sessionstatus == "session_expired"||sessionstatus == "unauthorized"||sessionstatus == "token_error") {
				console.debug("ajaxsetup sessionstatus "+sessionstatus);
				var win = window;
				while (win != win.top){
					win = win.top;
				}
				win.location.href= contextPath+XMLHttpRequest.getResponseHeader("redirecturl")+"?redirect_url="+window.location.href;
			}
		}
	});
	
	/**
	 * 防止退格
	 */
	$(document).bind('keydown', function(event) {
	});

	//浏览器尺寸变化响应事件时，重新设置遮盖层
	//window.onresize=rc.updateBody;
	
	//collapse监听
	$(".collapse-link")
		.click(function() {
			var o = $(this).closest("div.ibox"), 
			e = $(this).find("i"), 
			i = o.find("div.ibox-content");
			i.slideToggle(200), 
			e.toggleClass("fa-chevron-up").toggleClass("fa-chevron-down"), 
			o.toggleClass("").toggleClass("border-bottom"), 
			setTimeout(function() {
				o.resize(), o.find("[id^=map-]").resize()
			}, 50)
		})
});

$.fn.serializeObject = function()    
{
    var obj=new Object();  
    $.each(this.serializeArray(),function(index,param){  
        if( param.value){
        	if(!(param.name in obj) ){  
                obj[param.name]=param.value;  
	        }else{
	        	obj[param.name]+=","+param.value
	        }  
        }
    });
    return obj;  
};


$.fn.serializeObject_v2 = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


var rc = {
	 /**
	  * 通过iframe找开下载页面
	  */
     download:function(url){
		var elemIF = document.createElement("iframe");     
	    elemIF.src = url;     
	    elemIF.style.display = "none";     
	    document.body.appendChild(elemIF); 
     },
    /**
	 * rsa加密
	 * @param {} value
	 * @return {}
	 */
	rsaencode:function(url,form){
		RSAUtils.setMaxDigits(200);  
		var key = new RSAUtils.getKeyPair($('#publicKeyExponent').val(), "",$('#publicKeyModulus').val());  
		var rasvalue = RSAUtils.encryptedString(key,orgPwd.split("").reverse().join(""));  
		return rasvalue;
	},

	/**
	 * 生成唯一的uuid
	 * @param {} len
	 * @param {} radix
	 * @return {}
	 */
	uuid:function (len, radix) {
		  var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
		  var uuid = [], i;
		  radix = radix || chars.length;
		 
		  if (len) {
		   // Compact form
		   for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
		  } else {
		   // rfc4122, version 4 form
		   var r;
		 
		   // rfc4122 requires these characters
		   uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
		   uuid[14] = '4';
		 
		   // Fill in random data. At i==19 set the high bits of clock sequence as
		   // per rfc4122, sec. 4.1.5
		   for (i = 0; i < 36; i++) {
		    if (!uuid[i]) {
		     r = 0 | Math.random()*16;
		     uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
		    }
		   }
		  }
		 
		  return uuid.join('');
	 },
	 guid:function() {
		  var s = [];
		  var hexDigits = "0123456789abcdef";
		  for (var i = 0; i < 36; i++) {
		    s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		  }
		  s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
		  s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
		  s[8] = s[13] = s[18] = s[23] = "-";
		 
		  var uuid = s.join("");
		  return uuid;
	},	
	/**得到当前事件对应的元素*/
	getEventTarget:function(event){
		return $(event.currentTarget||event.srcElement);
	},
	/**
	 * 参数
	 */
	submitparam:{
		tips: [1, '#3595CC'],
        time: 4000
	},
	/**
	 * ajax 状态码
	 */
	status:{
		timeout:'请求超时',
		parsererror:'解析出错-系统发生异常请联系管理员',
		error:'请求出错-系统发生异常请联系管理员',
		notmodified:'未修改'
	},
	/**
	 * 使用ajax方式提交form
	 * @param _form
	 * @param successFun
	 * @param failureFun
	 */
	validAndAjaxSubmit:function(_form,callback,param,istokenreload){
		param= $.extend(rc.submitparam,param);
		_form.validate({
            onblur: true,
            onfocusin: function (element) {
            },
            onfocusout: function (element) {
                // 日期不实时验证
                //if(!$(element).parent().hasClass('form_date')) {
                    $(element).valid();
                //}
            },
            onkeyup: function (element, event) {
            },
			submitHandler : function(form) {
				rc.ajaxsubmit($(form),callback,param.ismask,istokenreload);
			},
			/*errorPlacement: function(error, element) {
				//layer.tips(error.html(),element,param);
				console.debug(element);
				$(element).focus();
				//layer.tips(errorList[0].message,$(errorList[0].element),param);
				layer.msg(error.html());
			}*/
			showErrors:function(errorMap,errorList) {
				console.log(errorList);
				if(errorList.length>0){
					//$(errorList[0].element).focus();
					//layer.tips(errorList[0].message,$(errorList[0].element),param);
					layer.msg(errorList[0].message);
				}
				//this.defaultShowErrors();
			}
		})
	},

	/**
	 * 使用传统方式提交form
	 * @param _form
	 * @param successFun
	 * @param failureFun
	 */
	validAndNormalSubmit:function(_form,param){
		param= $.extend(rc.submitparam,param);
		_form.validate({
			onblur: function (element) {
            },
            onfocusin: function (element) {
            },
            onfocusout: function (element) {
                //$(element).valid();
            },
            onkeyup: function (element, event) {
            },
			submitHandler : function(form) {
				form.submit();
			},
			/*errorPlacement: function(error, element) {
				layer.tips(error.html(),element,param);
			},*/
			showErrors:function(errorMap,errorList) {
				if(errorList.length>0){
					//$(errorList[0].element).focus();
					//layer.tips(errorList[0].message,$(errorList[0].element),param);
					layer.msg(errorList[0].message);
				}
				//this.defaultShowErrors();
			}
		})
	},
	/**
	 * form提交
	 * @param _form
	 * @param successFun
	 * @param failureFun
	 */
	ajaxsubmit : function(_form, callback,ismask,istokenreload) {
		var param=_form.serialize();
		if($('#CSRFToken').length>0) {
			param.CSRFToken=$('#CSRFToken').val()
		}
		if(_form.attr('action')){
		    rc.ajax(_form.attr('action'),param,callback,'post',null,true,true,istokenreload);	
		}else{
			layer.alert('请给要提交的表单提供正确的保存路径');
		}
	},
	ajaxdelete:function(url,callback){
		rc.ajax(url,null,callback);
	},
	/**
	 * 调用成功重新生成Token ajax
	 * @param url
	 * @param param
	 * @param callback
	 * @param method
	 */
	ajaxTokenReload:function(url,param,callback,method){
		rc.ajax(url,param,callback,method,null,true,true,true);
	},
	/**
	 * ajax
	 * @param {} url
	 * @param {} param
	 * @param {} callback
	 * @param {} method
	 * @param {} maskdom_selector
	 * @param {} ismask
	 * @param {} async
	 * @param {} istokenreload
	 */
	ajax:function(url,param,callback,method,maskdom_selector,ismask,async,istokenreload){
		rc.$ajax(url,param,callback,{
			method:method,
			ismask:ismask,
			async:async,
			istokenreload:istokenreload
		})
	},

	/**
	 * ajax
	 * @param {} url
	 * @param {} param
	 * @param {} callback
	 * @param {} method
	 * @param {} maskdom_selector
	 * @param {} ismask
	 * @param {} async
	 * @param {} istokenreload
	 */
	$ajax:function(url,param,callback,setting_in,error_callback){
		console.debug(url);
		//console.debug(param);
		var default_setting={
			method:'post',
			ismask:false,
			_async:true,
			_istokenreload:false,
			contentType:'application/x-www-form-urlencoded'
		}
		//param,callback,method,maskdom_selector,ismask,async,istokenreload
		//setting {}
		var setting=$.extend(default_setting,setting_in);
		
		var layer_ajax_index_;

		var options={};
		options.headers={
			'RequestVerificationToken':$("#CSRFToken").val(),
			'Authorization':'Bearer '+localStorage.getItem('token')
		};

		$.extend(options.headers,rc.signature())

		options.type=setting.method;
		options.cache = false;
		options.url=url;
		options.contentType=setting.contentType;
		options.async=setting._async;//ajax异步或同步请求（遮罩效果需要异步）
		options.data=param;
		options.beforeSend=function(xhr) {
			//rc.ajax_beforeSend(_ismask,maskdom_selector);
			 layer_ajax_index_ = layer.load(1);
		},
		options.complete=function(xhr, textStatus){
			// 通过XMLHttpRequest取得响应头，sessionstatus
			// 登录超时或访问了未授权的信息
			var sessionstatus = xhr.getResponseHeader("statuscode");
			if (sessionstatus == "session_expired"||sessionstatus == "unauthorized"||sessionstatus == "token_error") {
				console.log("complete sessionstatus "+sessionstatus);
				var win = window;
				while (win != win.top){
					win = win.top;
				}
				win.location.href= contextPath+xhr.getResponseHeader("redirecturl")+"?redirect_url="+window.location.href;
			}
        	layer.close(layer_ajax_index_);
        },
		options.success=function(response,textStatus){
			try{
				console.debug(response);
				if(response.syscode&&response.syscode==200){
					if(response.success==true){
						//自定义成功回调函数
						if(callback){
							callback(response);
						}else{
							layer.msg(response.message);
						}
					}else{
						if(error_callback){
							error_callback(response);
						}else{
							layer.msg(response.message);
						}
					}
				}else{
					if(callback){
						callback(response);
					}else{
						layer.msg(response.message);
					}
				}
			}catch (e) {
				console.debug(e);
				rc.ajax_error(null, '解析返回的文本出错！');
				return;
			}
		},
		options.error=function(xhr, textStatus, errorThrown) {
			var errormsg= rc.status[textStatus]||textStatus;
			rc.ajax_error(xhr,errormsg);
		};
		options.timeout=10*1000,//超时10秒
		options.global=false;
		options.dataType = 'json';
		ajax=$.ajax(options);
	},
	/**
	 * 重新生成token
	 * @param {} response
	 */
	reloadToken:function(response,istokenreload){
		//success
		/* if((response.success&&istokenreload)||!response.success){
			if($('#CSRFToken').length>0){
				$.get(contextPath+"/token",function(data){
				     $("#CSRFToken").val(data);
				});
		    }
		} */
	},

	/**
	 * 打开一个查询页面
	 * @param url
	 * @param formid
	 */
	openQueryPage: function (url,formid) {
		if(formid){
			var index = url.indexOf('?');
			if(index != -1) {
				//url 中已含有其他参数
				url = url + '&'+$('#'+formid).serialize()
			} else {
				//url 中没有其他参数
				url = url + '?'+$('#'+formid).serialize()
			}
		}
		var encodeurl=encodeURI(encodeURI(url));
		window.open(encodeurl);
	},

	remove_token(){
		  localStorage.clear();
	},
	/**
	 * @param _form
	 * @param callback
	 * @param param
	 */
	api_submit:function(_form,callback){
		_form.validate({
            onblur: true,
            onfocusin: function (element) {
            },
            onfocusout: function (element) {
                // 日期不实时验证
                if(!$(element).parent().hasClass('form_date')) {
//                    $(element).valid();
                }
            },
            onkeyup: function (element, event) {
            },
			submitHandler : function(form) {
				rc.api_post_submit($(form),callback);
			},
			/* errorPlacement: function(error, element) {
				//layer.tips(error.html(),element,param);
				$(element).focus();
				//layer.tips(errorList[0].message,$(errorList[0].element),param);
				layer.msg(error.html());
			}, */
			showErrors:function(errorMap,errorList) {
				if(errorList.length>0){
					//$(errorList[0].element).focus();
					//layer.tips(errorList[0].message,$(errorList[0].element),param);
					layer.msg(errorList[0].message);
				}
				//this.defaultShowErrors();
			}
		})
	},

	/**
	 * api_post_submit
	 */
	api_post_submit:function(_form, callback){
		var param=JSON.stringify(_form.serializeObject());
		if(_form.attr('action')){
		    rc.$ajax(_form.attr('action') ,param,callback,{
				contentType:"application/json"
			});	
		}else{
			layer.alert('请给要提交的表单提供正确的保存路径');
		}
	},
	/**
	 * api_post_update
	 */
	api_get:function(url,param,callback,error_callback){
		rc.api_post(url,param,callback);
	},

	/**
	 * api_post
	 */
	api_post:function(url,param,callback,error_callback){
		rc.$ajax(url,JSON.stringify(param),callback,{
			contentType:"application/json"
		});	
	},

	/**
	 * api_post_query
	 */
	api_post_query:function(url,param,dom_selector,model,target,formid){
		if(formid){
			param=JSON.stringify($.extends(param,$('#'+formid).serializeObject()));
			console.debug(param);
		}else{
			param=JSON.stringify(param);
			console.debug(param);
		}
		rc.$ajax(url,param,function(response){
			if(model){
				var jsondata=response.obj;
				var modeldata=Handlebars.compile(model.html());
				var views = modeldata(jsondata);
				if(target){
					target.html(views);
				}
			}else{
				rc.evaluation(response.obj,dom_selector);
			}
		},{
			contentType:'application/json'
		})
	},
	/**
	 * 以ajax方式调用查询方法并赋值
	 * @param url 
	 * @param model
	 * @param target
	 * @param formid
	 */
	ajaxQuery:function(url,dom_selector,model,target,formid){
		var param={};
		if(formid){
			param=$('#'+formid).serializeObject()
		}
		rc.ajax(url,param,function(response){
			if(model){
				var jsondata=response.obj;
				var modeldata=Handlebars.compile(model.html());
				var views = modeldata(jsondata);
				if(target){
					target.html(views);
				}
			}else{
				rc.evaluation(response.obj,dom_selector);
			}
		})
	},
	/**
	 * 获取对象属性及对应值
	 * @param {} obj
	 * @return {}
	 */
	getEnumPropertyNames: function (obj) {
        var props = [];
        for (prop_key in obj) {
            var prop=new Object();
            prop.key=prop_key;
            prop.value=obj[prop_key];
            props.push(prop);
        }
        return props;
    },
  
    /**
     * 设置下拉值
     */
    set_select_value:function (selector,res){
    	selector.selectpicker('val',res);
    },
    
    /**
     * 清空下拉值
     */
    clean_select_value:function(selector){
    	selector.selectpicker('val','');
    },	/**
	 * 查询成功,赋值
	 *
	 * @param {} response
	 */
	evaluation : function(obj,dom_selector) {
		if(obj){
			var inputs = $(":input").not('.ignore_evaluation');
			if(dom_selector){
				inputs=dom_selector.find(":input").not('.ignore_evaluation');
			}
			if(inputs.length>0){
			   inputs.each(function(i, dom) {
					var type = dom.type;
					var name = dom.name;
					if (name) {
						var res=obj[name];
						//if (res) {
						if (type == 'text'||type=='hidden'||type=='number'||type=='textarea') {
							$(dom).val(res);
						} else if (type == 'checkbox') {
							if(res){
								var checkboxvalues = res.split(',');
								for (var i = 0; i < checkboxvalues.length; i++) {
									if ($(dom).val() == checkboxvalues[i]) {
										$(dom).attr('checked', true);
									}
									//针对switch 增加动态修改功能
									try{
										if($(dom).hasClass("js-switch")){
										   $(dom).bootstrapSwitch('state',checkboxvalues[i]=='1'?true:false);
										}
									}catch(e){

				                    }
								}
							}
						} else if (type == 'radio') {
							if ($(dom).val() == res) {
								$(dom).attr('checked', true);
							}
						} else if (type == 'select-one') {
							$(dom).selectpicker('val',res);
							//$(dom).val(res).trigger('change');
						} else if (type == 'select-multiple' ) {
							try{
								$(dom).selectpicker('val',res.split(','));
							} catch(error) {
								$(dom).selectpicker('val',res);
							}
							//$(dom).val(res.split(',')).trigger('change');
						} 
					}
				});
			}
			else{
				var propertys = rc.getEnumPropertyNames(obj);
				for(var i=0;i<propertys.length;i++){
					var key = propertys[i].key;
					var value = propertys[i].value;
					if($('#'+key).length>0){
						$('#'+key).html(value);
					}
				}
		    }
           
		}
	},
	
	/**
	 * 清空页面值
	 *
	 * @param {} response
	 */
	clean : function(dom_selector) {
		var inputs = $("form :input");
		if(dom_selector){
			inputs=dom_selector.find(":input");
		}
		inputs.each(function(i, dom) {
			var type = dom.type;
			var name = dom.name;
			if (name) {
				if (type == 'text'||type=='hidden'||type=='number'||type=='textarea') {
					$(dom).val('');
				} else if (type == 'checkbox'||type == 'radio') {
				    $(dom).attr('checked', false);
				} else if (type == 'select-one' || type == 'select-multiple') {
					$(dom).selectpicker('val','');
					//$(dom).val('').trigger('change');
				} 
			}
		});
		if(dom_selector>0){
			dom_selector.find("table tr:gt(0)").remove();
		}
	},
	/**
	 * checkbox 事件绑定
	 * @param wrapper_id_name
	 * @param all_name
	 * @param name
	 */
	checkboxbind:function(wrapper_id_name,all_name,name){
		$("#"+wrapper_id_name+" :checkbox[name='"+all_name+"']").live('click',
			function(){
				if($(this).prop("checked")){
					$("#"+wrapper_id_name+" :checkbox[name='"+name+"']").prop("checked",true);
				}else{
					$("#"+wrapper_id_name+" :checkbox[name='"+name+"']").attr("checked",false);
				}
			}
		)
	},
	/**
	 * 得到选中列的值,并以逗号分割
	 * @param wrapper_id_name
	 * @param name
	 * @returns {string}
	 */
	getCheckedValues:function(wrapper_id_name,name){
		var selected_values="";
		$("#"+wrapper_id_name+" :checkbox[name='"+name+"']:checked").each(function(i,dom){
			selected_values+=$(this).val()+","
		})
		return selected_values;
	},
	/**
	 * ajax调用前
	 */
	ajax_beforeSend: function (_ismask,maskdom_selector) {
		//rc.mask(_ismask,maskdom_selector);
	},
	/**
	 * ajax调用成功
	 */
	ajax_success:function(response){
		//rc.hideMask(response);
	},
	/**
	 * ajax调用发生异常
	 * @param xhr
	 * @param textStatus
	 * @param _ismask 是否有遮盖效果
	 */
	ajax_error:function(xhr, errormsg,maskdom_selector){
		rc.errorMask(xhr, errormsg,maskdom_selector);
	},
	/**
	 * 遮盖
	 * @param _ismask 是否有遮盖效果
	 */
	mask:function(_ismask,maskdom_selector){
		if(_ismask){
			if($('#maskdom').length==0){
				var md=$("<div id='maskdom' style='z-index: 10001;position: absolute;background-color: #fff;filter: Alpha(Opacity = 10);opacity: 0.1;cursor:wait;'></div>");
				//高度居中
				var top=rc.getScreenHeight()-35/2;
				//固定高度fixed
				//var mkimg=$("<div id='maskimg' style='position: fixed;top:"+top+"px;padding: 5px;width: 200px;height: 35px;line-height: 35px; text-align: center;z-index: 10002;background: #fbfbfb;-moz-border-radius: 2px;-webkit-border-radius: 2px;border-radius:5px;font: normal 11px;cursor: wait;'><img src='"+contextPath+"/resource/images/loading.gif' style='vertical-align:middle;'>&nbsp;正在加载中,请稍后...</div>");
				var mkimg=$("<span id='maskimg' class='loading_2' style='top:"+top+"px;cursor:wait;'></span>");
				/*if(maskdom_selector){
					md.prependTo($(maskdom_selector));
					mkimg.prependTo($(maskdom_selector));
					rc.updateDom(maskdom_selector);
				}else{*/
					md.prependTo($("html"));
					mkimg.prependTo($("html"));
					rc.updateBody();
				/*}*/
			}else{
				$('#maskdom').show();
				//$('#maskimg').html("<img src='"+contextPath+"/resource/images/loading.gif' style='vertical-align:middle;'>&nbsp;正在加载中,请稍后...").show();
				$('#maskimg').show();
			}
		}
	},
	/**
	 * 遮盖
	 * @param xhr
	 * @param errormsg
	 */
	errorMask:function(xhr, errormsg,maskdom_selector){
		//if($('#maskdom').length>0) {
			//$('#maskimg').html("<span style='color:red'>" + errormsg + "</span>");
			//rc.hideMask();//关闭提示框
		//}
		//layer.msg(errormsg);
		alert(errormsg);
	},
	/**
	 * 延迟关闭mask
	 */
	hideMask:function(){
		if($('#maskdom').length>0){
			$('#maskdom').remove();//4秒之后隐藏
			$('#maskimg').remove();//4秒之后隐藏
		}
	},
	/**
	 * 得到页面宽
	 * @returns {number}
	 */
	getBodyWidth:function() {
		return document.body.clientWidth;
	},
	/**
	 * 得到页面宽
	 * @returns {number}
	 */
	getDOMWidth:function(maskdom_selector) {
		return $(maskdom_selector).width();
	},
	/**
	 * 得到页面高
	 * @returns {number}
	 */
	getBodyHeight:function() {
		return document.body.clientHeight;
	},
	getDomHeight:function(maskdom_selector) {
		return $(maskdom_selector).height();
	},
	/**
	 * 得到当前屏幕高度
	 * @returns {number}
	 */
	getScreenHeight:function() {
		/*
		网页可见区域宽：document.body.clientWidth
		网页可见区域高：document.body.clientHeight
		网页可见区域宽：document.body.offsetWidth(包括边线的宽)
		网页可见区域高：document.body.offsetHeight(包括边线的宽)
		网页正文全文宽：document.body.scrollWidth
		网页正文全文高：document.body.scrollHeight
		网页被卷去的高：document.body.scrollTop(IE7无效)
		网页被卷去的左：document.body.scrollLeft(IE7无效)
		网页被卷去的高：document.documentElement.scrollTop(IE7有效)
		网页被卷去的左：document.documentElement.scrollLeft(IE7有效)
		网页正文部分上：window.screenTop
		网页正文部分左：window.screenLeft
		屏幕分辨率的高：window.screen.height
		屏幕分辨率的宽：window.screen.width
		屏幕可用工作区高度：window.screen.availHeight
		屏幕可用工作区宽度：window.screen.availWidth*/
		/*rc.debug("document.body.clientWidth"+document.body.clientWidth);
		rc.debug("document.body.clientHeight"+document.body.clientHeight);
		rc.debug("document.documentElement.clientHeight"+document.documentElement.clientHeight);
		rc.debug("document.documentElement.clientWidth"+document.documentElement.clientWidth);
		rc.debug("document.body.offsetWidth(包括边线的宽)"+document.body.offsetWidth);
		rc.debug("document.body.offsetHeight(包括边线的宽)"+document.body.offsetHeight);
		rc.debug("document.body.scrollWidth"+document.body.scrollWidth);
		rc.debug("document.body.scrollHeight"+document.body.scrollHeight);
		rc.debug("document.documentElement.scrollTop(IE7有效)"+document.documentElement.scrollTop);
		rc.debug("document.documentElement.scrollLeft(IE7有效)"+document.documentElement.scrollLeft);
		rc.debug("document.body.scrollTop(IE7无效)"+document.body.scrollTop);
		rc.debug("document.body.scrollLeft(IE7无效)"+document.body.scrollLeft);
		rc.debug("window.screenTop"+window.screenTop);
		rc.debug("window.screenLeft"+window.screenLeft);
		rc.debug("window.screen.height"+window.screen.height);
		rc.debug("window.screen.width"+window.screen.width);
		rc.debug("window.screen.availHeight"+window.screen.availHeight);
		rc.debug("window.screen.availWidth"+window.screen.availWidth);*/
		var middletop=document.documentElement.clientHeight/2;
		/*rc.debug(middletop);*/
		return middletop;
	},
	/**
	 * 创建遮盖层
	 */
	updateBody:function(){
		if($('#maskdom').length>0) {
			$('#maskdom').width(rc.getBodyWidth());
			$('#maskdom').height(rc.getBodyHeight());
			$('#maskimg').css({'left': (rc.getBodyWidth() - $('#maskimg').width()) / 2});
			//$('#maskimg').css({'top':  rc.getScreenHeight() - $('#maskimg').height() / 2});
		}
	},
	/**
	 *加载中
	 * @param maskdom_selector
	 */
	updateDom:function(maskdom_selector){
		if($('#maskdom').length>0) {
			$('#maskdom').width(rc.getDOMWidth(maskdom_selector));
			$('#maskdom').height(rc.getDomHeight(maskdom_selector));
			$('#maskimg').css({'left': (rc.getDOMWidth(maskdom_selector) - $('#maskimg').width()) / 2});
			$('#maskimg').css({'top': (rc.getDomHeight(maskdom_selector) + $('#maskimg').height()) / 4});
		}
	},
	/**
	 * 清空
	 * 
	 * @param {} response
	 */
	reset : function() {
		var inputs = $(":input");
		inputs.each(function(i, dom) {
			var type = dom.type;
			var name = dom.name;
			if (name) {
			   $(dom).val('')
			}
		});
	},

	/**
	 * 更新所有form及Href增加token
	 */
	appendToken:function (){
		rc.updateForms();
		//rc.updateTags();
	},

	updateForms:function () {
		// 得到页面中所有的 form 元素
		var forms = document.getElementsByTagName('form');
		for(i=0; i<forms.length; i++) {
			var url = forms[i].action;
			// 如果这个 form 的 action 值为空，则不附加 csrftoken
			if(url == null || url == "" ) continue;

			// 动态生成 input 元素，加入到 form 之后
			var e = document.createElement("input");
			e.name = "CSRFToken";
			e.value = $("#CSRFToken").val();
			e.type="hidden";
			forms[i].appendChild(e);
		}
	},
	updateTags:function () {
		var all = document.getElementsByTagName('a');
		var len = all.length;
		// 遍历所有 a 元素
		for(var i=0; i<len; i++) {
			var e = all[i];
			rc.updateTag(e, 'href', $("#CSRFToken").val());
		}
	},

	updateTag:function(element, attr, token) {
		var location = element.getAttribute(attr);
		if(location != null && location != ' ' ) {
			var fragmentIndex = location.indexOf('#');
			var fragment = null;
			if(fragmentIndex != -1){
				//url 中含有只相当页的锚标记
				fragment = location.substring(fragmentIndex);
				location = location.substring(0,fragmentIndex);
			}
			var jsindex=location.indexOf('javascript');
			//如果超连接为不是javascript事件
			if(jsindex==-1){
				var index = location.indexOf('?');
				if(index != -1) {
					//url 中已含有其他参数
					location = location + '&CSRFToken=' + token;
				} else {
					//url 中没有其他参数
					location = location + '?CSRFToken=' + token;
				}
			}
			if(fragment != null){
				location += fragment;
			}
			element.setAttribute(attr, location);
		}
	},
    /**
	 * 全选及全不选控制
	 */
    selectAll:function(name,ischecked){
       $(":checkbox[name='"+name+"']").prop("checked",ischecked)
    },

    /**
     * flv,f4v格式视频文件查看
     * @param {} filepath
     */
    videoplayer:function(player,filepath){
        flowplayer(player, 
        	{
        	   src:contextPath + '/resource/js/flowplayer/flowplayer-3.2.16.swf',
        	   wmode: "opaque" //这个很重要，仅在ie下会出现问题，设置后flash就不会遮住其他div或者下拉菜单
        	}, 
        	{
				clip : {
				    /**是否自动播放*/
					autoPlay : false,
					autoBuffer:false,
					/**视频截取长度0代表不截取*/
					duration : 0
				},
				plugins : {
				    /**控制面板*/
					controls : {
						url : contextPath+ '/resource/js/flowplayer/flowplayer.controls-3.2.15.swf',
						playlist : false,
						height : 30,
						progressColor:'#FD4B42',
						bufferColor: '#FF8040', 
						volumeColor:'#FD4B42', 
						/**提示*/
						tooltips : {
							buttons : true,
							fullscreen : '全屏',
							play : '播放',
							stop : '停止',
							pause : '暂停',
							fullscreenExit : '退出全屏',
							next : '下一个',
							previous : '上一个',
							mute : '静音',
							unmute : '关闭静音'
						}
					}
				},
				play : {
					label : '播放',
					replayLabel : '重新播放'
				}
	     });
   },
   /**
    * 自动加载
    * @param {} diaplay_element_selector
    */
   autocomplte:function(diaplay_element_selector,url,options) {
	    var setting={
	    	minChars:1,//在触发前用户至少需要输入的字符数
	    	width:448, //指定下拉框的宽度
	    	max:12, //下拉显示项目的个数
	    	cacheLength:0,//缓存的长度
	    	scroll:false,//当结果集大于默认高度时是否使用卷轴显示
	    	scrollHeight:400,//自动完成提示的卷轴高度用像素大小表示 Default: 180 
	    	multipleSeparator:',',//如果是多选时,用来分开各个选择的字符
	    	multiple:false,//是否允许输入多个值即多次使
	    	selectFirst:false,//如果设置成true,在用户键入tab或return键时autoComplete下拉列表的第一个值将被自动选择
	    	delay:0, //击键后激活的延迟时间(单位毫秒)
	    	extraParams:{}, //提供更多的参数.
	    	mustMatch:false //必须匹配,否则清空
	    }
	    $.extend(setting,options)
	    diaplay_element_selector.autocomplete(url,{
			minChars : setting.minChars,
			width : setting.width,
			max:setting.max,
			cacheLength:setting.cacheLength,
			mustMatch : setting.mustMatch,
			scroll:setting.scroll,
			scrollHeight:setting.scrollHeight,
			multipleSeparator:setting.multipleSeparator,
			multiple:setting.multiple,
			selectFirst:setting.selectFirst,
			delay:setting.delay,
			extraParams:setting.extraParams,
			//加入对返回的json对象进行解析函数，函数返回一个数组
			dataType: 'html',
			parse: function(data) {
				var rows = [];
				try{
					var response = eval('(' + data + ')');
					for(var i=0; i<response.obj.length; i++){
						var name=response.obj[i].name;
						var cou=response.obj[i].cou;
						rows[rows.length] = {
							data:{key:name,cou:cou},
							value:name,
							result:name
						};
					}
				}catch(e){

				}
				return rows;
			},
			formatItem: function(row, i, n) {
				return "<table width='"+setting.width+"px'><tr><td align='left'>" + row.key + "</td>" +
					"<td align='right'><font style='color: #3013FF; font-family: 黑体;'>约" + row.cou + "个结果</font>&nbsp;&nbsp;</td></tr></table>";
			}
		});
   },
   setTab:function(name,cursel,n){
		for (i = 1; i <= n; i++) {
			var menu = $("#"+name + i);
			var con = $("#con_" + name + "_" + i);
			if(i==cursel){
				menu.addClass("hover");
				con.css({
					"display":"block"
				})
			}else{
				menu.removeClass("hover");
				con.css({
					"display":"none"
				})
			}
		}
	},
	returnToUrl:function() {
		window.history.back();
		return false;
	},
	gotoP:function(pages){
		var n=$('#gtop').val();if(n){if(isNaN(n)){alert('请输入正确的页码！');}
		else{if(n>pages||n<=0){alert('此页码不存在！')}else{rc.page(n-1);}}}else{alert('请输入正确的页码！');}
	},
	page:function(n,queryFormId,pageForm){
		$('#p').val(n);
		if(queryFormId!=""){
			var action=$('#'+queryFormId).attr("action");
			var param='&p='+$('#p').val();
			$("#"+queryFormId).attr("action",action+param);
			$("#"+queryFormId).submit();
		}else{
			$('#'+pageForm+'').submit();
		}
	},
	//得到卷掉的高度
	getScrollTop:function () {
		return $(document.documentElement).scrollTop() + $(document.body).scrollTop();
	},
	//设置卷掉高度
	setScrollTop:function(value){
		$(document.documentElement).scrollTop(value);
		$(document.body).scrollTop(value);
	},
	//调试
	debug:function(info){
		if(!document.all){
			console.debug(info)
		}
	},
	//等比例缩放
	setImg:function(img,img_w,img_h,w,h){
		var r=1;
		var width=img_w;
		var height=img_h;
		if(img_w>w){//如果图片宽度超出容器宽度--要撑破了
			var wr=w/img_w;
			height = img_h*wr; //高度等比缩放
			width  = w;
		}
		if(height>h){
			var hr=h/height;
			width = img_w*hr; //高度等比缩放
			height=h;
		}
		img.css({"width":width,"height":height});//设置缩放后的宽度和高度
		return width/img_w;
	},
    /**
	 * url签名 随机 字符串+当前时间+签名
	 */
/* 	 signature:function(url){
		var key="123456789F"
		var nonce=Math.random()*(999999-100000)+100000;
		var timestamp=(new Date().getTime()+300000);
		var a=[];
		a.push(key,nonce,timestamp);
		//排序并合并
		var sign_str=a.sort().join('');
		var signature=hex_sha1(sign_str);
		var result="nonce="+nonce+"&timestamp="+timestamp+"&signature="+signature;
		var index = url.indexOf('?');
		if(index != -1) {
			//url 中已含有其他参数
			url = url + '&' + result;
		} else {
			//url 中没有其他参数
			location = url+"?"+result;
		}
		console.log(url);
		return url;
	},  */

	 /**
	 * 签名 随机 字符串+当前时间+签名
	 */
	signature:function(){
		var key="123456789F"
		var nonce=Math.random()*(999999-100000)+100000;
		var timestamp=(new Date().getTime()+60000);
		var a=[];
		a.push(key,nonce,timestamp);
		//排序并合并
		var sign_str=a.sort().join('');
		console.debug(sign_str);
		var signature=hex_sha1(sign_str);
		var result={
			nonce:nonce,
			timestamp:timestamp,
			signature:signature
		};
		return result;
	},

	/**
	 * ajax文件上传
	 * @param button
	 * @param filetype文件类型(1图片 2视频 3附件)
	 * @param type
	 * @param url 文件上传链接
	 * @param crop 是否裁剪
	 * @param callback
	 */
	upload:function(button,filetype,type,business_id,callback){
		var interval;
		var fileTypeExts = "";
		if(filetype=='01'){
			fileTypeExts ='jpg|jpeg|JPG|JPEG|gif|GIF|png|GNG';
		}else if(filetype=='03'){
			fileTypeExts ='jpg|jpeg|JPG|JPEG|gif|GIF|png|GNG|doc|DOC|docx|DOCX|pdf|PDF|xls|XLS|xlsx|XLSX|rar|RAR|zip|ZIP';
		}else{
			button.val('文件类型不正确!');
			alert('文件类型不正确!');
			return false;
		}
		//var fileTypeExts =(type=='01'?'jpg|jpeg|JPG|JPEG|gif|GIF|png|GNG':(type=='02'?'flv|FLV':'doc|DOC|docx|DOCX'));
		
		var url=contextPath+'/upload?business_id='+business_id+'&type='+type+'&filetype='+filetype;
		new AjaxUpload(button, {
		action: url,
		name: 'myfile',
		onSubmit: function (file, ext) {
			var r= new RegExp("^("+fileTypeExts+")$");
			if (!(ext &&r.test(ext))) {
				button.val('格式不正确,请选择'+fileTypeExts+'格式的文件!');
				alert('格式不正确,请选择'+fileTypeExts+'格式的文件!');
				return false;
			}
			// change button text, when user selects file
			button.val('上传中');

			// If you want to allow uploading only 1 file at time,
			// you can disable upload button
			this.disable();

			// Uploding -> Uploading. -> Uploading...
			interval = window.setInterval(function () {
				var text = button.val();
				if (text.length < 10) {
					button.val(text + '.');
				} else {
					button.val('上传中');
				}
			}, 200);
		},
		onComplete: function (file, response) {
			window.clearInterval(interval);
			// enable upload button
			this.enable();
			var k = response.success;
			if(response.success){
				button.val("上传成功");
				alert('上传成功');
				if(callback){
					callback(response);
				}
			}else{
				button.val(response.message);
			}
		  }
		});
	},
	/**页面端发出的数据作两次encodeURI,防止中文乱码*/
	encodeURITwice:function(value){
		return  encodeURI(encodeURI(value));
	},
	/**
	 * codetype值变化后多级联动
	 * @param {} currentvalue 当前选中框值
	 * @param {} next_code_type 下一级数据对应联动代码类型
	 * @param {} next_selector 全部对应下级选择数组
	 */
	code_value_select_data_change:function(currentvalue,next_code_type,next_selector){
		var url=contextPath+'/sys/codetype/queryByCodeTypeAndParent';
		rc.ajax(url,{par_code_value:currentvalue,code_type:next_code_type},function(response){
			//将当前节点后的所有子选择框清空
			$.each(next_selector,function(index, value, array){
				 $(value).removeAttr("disabled");
			     $(value).empty();
			     $(value).selectpicker('refresh');
			})
			//数据加模型生成新的option数组
			var modeldata=Handlebars.compile($('#tpl_option').html());
			var views = modeldata(response);
		    $(next_selector[0]).append(views);
		    $(next_selector[0]).selectpicker('refresh');
		})
	},
	
	
	//找开文件列表上传页面
    open_file_list_upload_page:function(file_bus_id,url){
   		if(file_bus_id&&url){
    		index=layer.open({
	   		  type: 2,
	   		  title: '文件上传列表',
	   		  shadeClose: true,
	   		  shade: 0.8,
	   		  area: ['30%', '40%'],
	   		  content: contextPath+"/common/fileload/tofilelist?file_bus_id="+file_bus_id+"&url="+url //iframe的url
 		    });
 		    layer.full(index);
    	}else{
    		layer.alert('上传文件需要的业务编号及业务类型不能为空,请确认');
    	}
    }, 
    
    //打开文件上传页面
    open_file_upload_page:function(file_bus_id,url,upload_callback){
 		if(file_bus_id&&url&&upload_callback){
    		layer.open({
	   		  type: 2,
	   		  title: '文件上传',
	   		  shadeClose: true,
	   		  shade: 0.8,
	   		  area: ['30%', '40%'],
	   		  content: contextPath+"/common/fileload/toFileUpload?file_bus_id="+file_bus_id+"&url="+url+"&upload_callback="+upload_callback //iframe的url
 		    });
    	}else{
    		layer.alert('上传文件需要的业务编号、文件上传路径、回调函数不能为空,请确认');
    	}
    },
    //打开图片上传页面
    open_imgage_upload_page:function(file_bus_id,url,upload_callback){
    	if(file_bus_id&&url&&upload_callback){
    		layer.open({
	   		  type: 2,
	   		  title: '图片上传',
	   		  shadeClose: true,
	   		  shade: 0.8,
	   		  area: ['50%', '60%'],
	   		  content: contextPath+"/common/fileload/toImgUpload?file_bus_id="+file_bus_id+"&url="+url+"&upload_callback="+upload_callback
 		    });
    	}else{
    		layer.alert('上传文件需要的业务编号、文件上传路径、回调函数不能为空,请确认');
    	}
    },
    //打开图片上传页面(需填写文件说明)
    open_imgage_upload_with_desc_page:function(file_bus_id,url,upload_callback){
        if(file_bus_id&&url&&upload_callback){
            layer.open({
                type: 2,
                title: '图片上传',
                shadeClose: true,
                shade: 0.8,
                area: ['50%', '300px'],
                content: contextPath+"/common/fileload/toImgUploadWithDesc?file_bus_id="+file_bus_id+"&url="+url+"&upload_callback="+upload_callback
            });
        }else{
            layer.alert('上传文件需要的业务编号、文件上传路径、回调函数不能为空,请确认');
        }
    },
    //打开文件上传页面(需填写文件说明)
    open_file_upload_with_desc_page:function(file_bus_id,url,upload_callback,file_bus_type){
        if(file_bus_id&&url&&upload_callback){
            layer.open({
                type: 2,
                title: '附件上传',
                shadeClose: true,
                shade: 0.8,
                area: ['450px', '300px'],
                content: contextPath+"/common/fileload/toFileUploadWithDesc?file_bus_id="+file_bus_id+"&url="+url+"&upload_callback="+upload_callback+"&file_bus_type="+file_bus_type
            });
        }else{
            layer.alert('上传文件需要的业务编号、文件上传路径、回调函数不能为空,请确认');
        }
    },
    open_file_upload_special:function(file_bus_id,url,upload_callback){
        if(file_bus_id&&url&&upload_callback){
            layer.open({
                type: 2,
                title: '身份证、就创证照片上传',
                shadeClose: true,
                shade: 0.8,
                area: ['400px', '250px'],
                content: contextPath+"/common/fileload/toFileUploadSpecial?file_bus_id="+file_bus_id+"&url="+url+"&upload_callback="+upload_callback
            });
        }else{
            layer.alert('上传文件需要的业务编号、文件上传路径、回调函数不能为空,请确认');
        }
    },
     //打开excel上传页面
    open_excel_file_upload_page:function(excel_batch_excel_type,upload_callback,excel_batch_assistid){
 		if(excel_batch_excel_type && upload_callback){
    		layer.open({
	   		  type: 2,
	   		  title: 'excel文件上传',
	   		  shadeClose: true,
	   		  shade: 0.8,
              area: ['450px', '250px'],
	   		  content: contextPath+"/common/fileload/toExcelFileUpload?excel_batch_excel_type="+excel_batch_excel_type+"&upload_callback="+upload_callback+"&excel_batch_assistid="+excel_batch_assistid //iframe的url
 		    });
    	}else{
    		layer.alert('上传文件需要的业务编号、业务类型、回调函数不能为空,请确认');
    	}
    },
    //自定义打开excel上传页面
    open_excel_file_upload_page_customize:function(excel_batch_excel_type,title,upload_callback,excel_batch_assistid){
        if(excel_batch_excel_type&&upload_callback){
            layer.open({
                type: 2,
                title: title,
                shadeClose: true,
                shade: 0.8,
                area: ['450px', '250px'],
                content: contextPath+"/common/fileload/toExcelFileUpload?excel_batch_excel_type="+excel_batch_excel_type+"&upload_callback="+upload_callback+"&excel_batch_assistid="+excel_batch_assistid //iframe的url
            });
        }else{
            layer.alert('上传文件需要的业务编号、业务类型、回调函数不能为空,请确认');
        }
    },
     //下载
    download_file_by_id:function(file_uuid,type){
      if(file_uuid && type){
    	  var url=contextPath+"/common/fileload/download/"+file_uuid+"/"+type;
    	  window.open(url);
       }else{
   		  layer.alert('请先选择你要下载的文件编号');
   	  }
    }
}

var JPlaceHolder = {
	clear : function(){
		if(!('placeholder' in document.createElement('input'))){
			$('input[placeholder],textarea[placeholder]').each(function(){
				var self = $(this);
				if(self.val()!=''&&self.val()!=null){
					self.parent().find('.msgsss').css('display','none');
				}else{
					self.parent().find('.msgsss').css('display','block');
				}
			});
		} 
		
	},
    //初始化
    init : function(){
        if(!('placeholder' in document.createElement('input'))){   
		    $('input[placeholder]').each(function(){    
		     // var that = $(this),    
		     // text= that.attr('placeholder');  
		       // if (that.attr('type') == 'password') {//为密码域不能通过val来设置值显示内容，会显示星号，只能用dom来模拟
                  
                    var self = $(this), txt = self.attr('placeholder');
	               	var height = self.css('height');
	                var width = self.css('width');
	                var margin = self.css('margin-left');
	               	var img = self.css('background-image');
                    self.wrap($("<div></div>").css({position:'relative',float:'left', zoom:'1', border:'none', background:'none', padding:'none', margin:'none'}));
			        var pos = self.position(), h = self.outerHeight(true), paddingleft = self.css('padding-left');
			        var holder;
			        if(img==''||img==null||img=='none'){
				       	holder = $('<span class="msgsss" style="line-height:'+height+';width:'+width+'"></span>').text(txt).css({position:'absolute', left:pos.left+10, top:pos.top+2, paddingLeft:paddingleft,'color':'#aaa'}).appendTo(self.parent());
			        }else{
						holder = $('<span class="msgsss" style="line-height:'+height+';width:'+width+'"></span>').text(txt).css({position:'absolute', left:pos.left+10, top:pos.top+2, paddingLeft:paddingleft,'color':'#aaa'}).appendTo(self.parent());			        
			        }
                    if(self.val()==''||self.val()==null){
			            self.focusin(function(e) {
			            	self.parent().find('.msgsss').css('display','none');
			            	//$('.msgsss').css('display','none');
			            //    holder.hide();
			            }).focusout(function(e) {
			                if(!self.val()){
			                   self.parent().find('.msgsss').css('display','block');
			                }  
			            });
			            holder.click(function(e) {
			          //      holder.hide();
			                self.parent().find('.msgsss').css('display','none');
			                self.focus();
			                self.click();
			            });
                    }else{
                    	self.parent().find('.msgsss').css('display','none');
                    	self.focusin(function(e) {
			            	self.parent().find('.msgsss').css('display','none');
			            	//$('.msgsss').css('display','none');
			            //    holder.hide();
			            }).focusout(function(e) {
			                if(!self.val()){
			                   self.parent().find('.msgsss').css('display','block');
			                }  
			            });
                    	holder.click(function(e) {
			                self.parent().find('.msgsss').css('display','none');
			                self.focus();
			                self.click();
			            });
                    }
		            /*
                }else{
                	if(that.val()==""){     
				        that.val(text).addClass('placeholder');   
				        that.css({"color":"#aaa"});
				      }    
				      that.focus(function(){    
				        if(that.val()==text){    
				          that.val("").removeClass('placeholder');    
				        }    
				      })    
				      .blur(function(){    
				        if(that.val()==""){    
				          that.val(text).addClass('placeholder');    
				          that.css({"color":"#aaa"});
				        }    
				      });
                }
                */
		    });    
		  }   
    }
};

function setTab(name, cursel, n) {
	rc.setTab(name,cursel,n);
}

//图片错误
function imgError(obj){
	$(obj).src="/resource/image/no_picture.jpg";
}


/**
 * rc.paging.js
 * jQuery ajax分页插件
 * @author wengsh
 */
(function($){
	$.fn.paging = function(model,target,settings) {
		var _this = $(this);
		_this.settings=$.extend({},$.fn.paging.defaults,settings);
		var param='';
		if(_this.settings.pageSize){
			param+='limit='+_this.settings.pageSize;
		}else{
			param+='limit=10';
		}
		if(_this.settings.pageNum){
			param+='&curpage='+_this.settings.pageNum;
		}else{
			param+='&curpage=1';
		}
		if(_this.settings.formid!=''){
			param+=('&'+$('#'+_this.settings.formid).serialize());
		}
		var pages = ['2','6','10','15','20','30','40'];
		rc.ajax(_this.settings.url,param,function(response){
			var jsondata=response.obj;
			var modeldata=Handlebars.compile(model.html());
			var views = modeldata(jsondata);
			target.html(views);
			_this.settings.pageNum=response.obj.pageNum;
			_this.settings.pages=response.obj.pages;
			_this.settings.total=response.obj.total;
			var pagehtml="";
			if(parseInt(_this.settings.total,10)>0){
				pagehtml+="显示数量：<select id='pageSize' onchange=\"$.fn.paging.choosePageSize('"+_this.selector+"','"+model.selector+"','"+target.selector+"','"+_this.settings.url+"','"+1+"','"+_this.settings.pageSize+"','"+_this.settings.formid+"',"+_this.settings.ismask+")\">";
				for(var i=0;i<pages.length;i++){
					if(pages[i]==_this.settings.pageSize){
						pagehtml+="<option value='"+pages[i]+"' selected='selected'>"+pages[i]+"</option>"
					}else{
						pagehtml+="<option value='"+pages[i]+"'>"+pages[i]+"</option>"
					}
				}
				pagehtml+="</select>&nbsp;总记录数:<span style='color:red;font-weight:bold;'>"+_this.settings.total+"</span>&nbsp;";
			}else{
				pagehtml+="<span style='color:red;font-weight:bold;'>没有找到数据!<input type='hidden' id='pageSize' value='"+_this.settings.pageSize+"'/></span>"
			}

			if(parseInt(_this.settings.pages,10)>1){
				if(_this.settings.pageNum==1){
					pagehtml+="<a class='page' style='background-color: #ccc;'>首&nbsp;页</a>&nbsp;";
					pagehtml+="<a class='page' style='background-color: #ccc;'>上一页</a>&nbsp;";
				}else{
					pagehtml+="<a class='page' href='javascript:;' onclick=\"$.fn.paging.next('"+_this.selector+"','"+model.selector+"','"+target.selector+"','"+_this.settings.url+"','"+1+"','"+_this.settings.pageSize+"','"+_this.settings.formid+"',"+_this.settings.ismask+")\">首&nbsp;页</a>&nbsp;"
					pagehtml+="<a class='page' href='javascript:;' onclick=\"$.fn.paging.next('"+_this.selector+"','"+model.selector+"','"+target.selector+"','"+_this.settings.url+"','"+(parseInt(_this.settings.pageNum)-1)+"','"+_this.settings.pageSize+"','"+_this.settings.formid+"',"+_this.settings.ismask+")\">上一页</a>&nbsp;"
				}
				var begin=_this.settings.pageNum-2;
				var end=_this.settings.pageNum+3;
				if(_this.settings.pageNum<2){
					begin=0;
					end=_this.settings.pages>5?5:_this.settings.pages;
				}
				if(_this.settings.pageNum>_this.settings.pages-3){
					begin=_this.settings.pages-5<0?0:_this.settings.pages-5;
					end=_this.settings.pages;
				}
				for (var i=begin;i<end;i++){
					if(_this.settings.pageNum==(i+1)){
						pagehtml+="<span class='current'>"+(i+1)+"</span>&nbsp;"
					}else{
						pagehtml+="<a href='javascript:;' onclick=\"$.fn.paging.next('"+_this.selector+"','"+model.selector+"','"+target.selector+"','"+_this.settings.url+"','"+(i+1)+"','"+_this.settings.pageSize+"','"+_this.settings.formid+"',"+_this.settings.ismask+")\">"+(i+1)+"</a>&nbsp;"
					}
				}
				if(_this.settings.pageNum==_this.settings.pages){
					pagehtml+="<a class='page' style='background-color: #ccc;'>下一页</a>&nbsp;";
					pagehtml+="<a class='page' style='background-color: #ccc;'>尾&nbsp;页</a>&nbsp;";
				}else{
					pagehtml+="<a class='page' href='javascript:;' onclick=\"$.fn.paging.next('"+_this.selector+"','"+model.selector+"','"+target.selector+"','"+_this.settings.url+"','"+(parseInt(_this.settings.pageNum)+1)+"','"+_this.settings.pageSize+"','"+_this.settings.formid+"',"+_this.settings.ismask+")\">下一页</a>&nbsp;";
					pagehtml+="<a class='page' href='javascript:;' onclick=\"$.fn.paging.next('"+_this.selector+"','"+model.selector+"','"+target.selector+"','"+_this.settings.url+"','"+(parseInt(_this.settings.pages))+"','"+_this.settings.pageSize+"','"+_this.settings.formid+"',"+_this.settings.ismask+")\">尾&nbsp;页</a>";
				}
				//pagehtml+="|"
				//pagehtml+="&nbsp;&nbsp;当前页次<span style='color:red;font-weight:bold;'>"+(_this.settings.pageNum+1)+"</span>/"+_this.settings.pages+"页";
			}
			_this.html(pagehtml);
		},'post',null,_this.settings.ismask)
	}

	/**
	 * next and prev page
	 * @param pag pagediv
	 * @param model datamodelid
	 * @param target targetid
	 * @param url ajax url
	 * @param pageNum currentpage
	 * @param pagesize pagesize
	 * @param formid queryformid
	 */
	$.fn.paging.next = function(pag,model,target,url,pageNum,pagesize,formid,ismask) {
		$(pag).paging($(model),$(target),{url:url,pageNum:pageNum,pageSize:pagesize,formid:formid,ismask:ismask});
	}
	$.fn.paging.choosePageSize = function(pag,model,target,url,pageNum,pagesize,formid,ismask){
		$.fn.paging.next(pag,model,target,url,pageNum,$('#pageSize').val(),formid,ismask);
	}
	
	/**
	 * default param
	 * @type {{url: string, formid: string, pageNum: number, pageSize: number}}
	 */
	$.fn.paging.defaults={
		url:'',
		formid:'',
		ismask:true,
		pageNum:1,
		pageSize:10
	}
})(jQuery);


/**
 * rc.nopaging.js
 * jQuery ajax分页插件
 * @author wengsh
 * @date 2015-1-7
 */
(function($){
	$.fn.nopaging = function(model,target,settings) {
		var _this = $(this);
		_this.settings=$.extend({},$.fn.paging.defaults,settings);
		var param='';
		if(_this.settings.formid!=''){
			param+=('&'+$('#'+_this.settings.formid).serialize());
		}
		rc.ajax(_this.settings.url,param,function(response){
			var jsondata=response.obj;
			var modeldata=Handlebars.compile(model.html());
			var views = modeldata(jsondata);
			target.html(views);
			_this.settings.pageNum=response.obj.pageNum;
			_this.settings.pages=response.obj.pages;
			_this.settings.total=response.obj.total;
			var pagehtml="";
			_this.html(pagehtml);
		},'post',null,_this.settings.ismask)
	}
	/**
	 * default param
	 * @type {{url: string, formid: string, pageNum: number, pageSize: number}}
	 */
	$.fn.paging.defaults={
		url:'',
		formid:'',
		ismask:true,
		pageNum:0,
		pageSize:10
	}
})(jQuery);


$(function(){
		//注册一个比较大小的Helper,判断v1是否大于v2
		Handlebars.registerHelper("compare",function(v1,v2,options){
			if(v1>v2){
				//满足添加继续执行
				return options.fn(this);
			}else{
				//不满足条件执行{{else}}部分
				return options.inverse(this);
			}
		});
		//注册一个比较大小的Helper,判断v1是否等于v2
		Handlebars.registerHelper("equals",function(v1,v2,options){
			var v2_arr=[];
			try{
				v2_arr=v2.split(',');
			}catch(e){
				v2_arr.push(v2);
			}
			var b_equals=false;
			for(var i=0;i<v2_arr.length;i++){
				if(v1==v2_arr[i]){
					b_equals=true;
					break;
				}
			}
			if(b_equals){
				//满足添加继续执行
				return options.fn(this);
			}else{
				//不满足条件执行{{else}}部分
				return options.inverse(this);
			}
		});
		//注册一个比较大小的Helper,判断v1是否等于v2
		Handlebars.registerHelper("noequals",function(v1,v2,options){
			var v2_arr=[];
			try{
				v2_arr=v2.split(',');
			}catch(e){
				v2_arr.push(v2);
			}
			var b_equals=false;
			for(var i=0;i<v2_arr.length;i++){
				if(v1==v2_arr[i]){
					b_equals=true;
					break;
				}
			}
			if(b_equals){
				//满足添加继续执行
				return options.inverse(this);
			}else{
				//不满足条件执行{{else}}部分
				return options.fn(this);
			}
		});
		//注册索引+1的helper
		Handlebars.registerHelper("addOne",function(index){
			//返回+1之后的结果
			return index+1;
		});
})

function jiangese_set(tabid,start,color1,color2){
	$("#"+tabid+" tr").each(function(i,dom){
		if((i+1)>=start){
			if((i+1-start)%2==0){
				$(dom).css('background-color',color1);
			}else{
				$(dom).css('background-color',color2);
			}
		}
	});	
}


/*!
 * jquery.scrollLoading.js
 * by zhangxinxu http://www.zhangxinxu.com/wordpress/?p=1259
 * 2010-11-19 v1.0
 * 2012-01-13 v1.1 偏移值计算修改 position → offset
 * 2012-09-25 v1.2 增加滚动容器参数, 回调参数
 * 2014-08-11 v1.3 修复设置滚动容器参数一些bug, 以及误删posb值的一些低级错误
 */
(function($) {
	$.fn.scrollLoading = function(options) {
		var defaults = {
			attr: "data-url",
			container: $(window),
			callback: $.noop
		};
		var params = $.extend({}, defaults, options || {});
		params.cache = [];
		$(this).each(function() {
			var node = this.nodeName.toLowerCase(), url = $(this).attr(params["attr"]);
			//重组
			var data = {
				obj: $(this),
				tag: node,
				url: url
			};
			params.cache.push(data);
		});

		var callback = function(call) {
			if ($.isFunction(params.callback)) {
				params.callback.call(call.get(0));
			}
		};
		//动态显示数据
		var loading = function() {

			var contHeight = params.container.height();
			if (params.container.get(0) === window) {
				contop = $(window).scrollTop();
			} else {
				contop = params.container.offset().top;
			}

			$.each(params.cache, function(i, data) {
				var o = data.obj, tag = data.tag, url = data.url, post, posb;

				if (o) {
					post = o.offset().top - contop, posb = post + o.height();
					if ((post >= 0 && post < contHeight) || (posb > 0 && posb <= contHeight)) {
						if (url) {
							//在浏览器窗口内
							if (tag === "img") {
								//图片，改变src
								callback(o.attr("src", url));
							} else {
								o.load(url, {}, function() {
									callback(o);
								});
							}
						} else {
							// 无地址，直接触发回调
							callback(o);
						}
						data.obj = null;
					}
				}
			});
		};

		//事件触发
		//加载完毕即执行
		loading();
		//滚动执行
		params.container.bind("scroll", loading);
	};
})(jQuery);

/**
 * 
 * jQuery 基于 bootstrape table 插件
 * @author wengsh
 */
(function($){
	/**
	 * 表格初始化 
	 * options
	 */
	$.fn.inittable = function(options,callback) {
		 var _this = $(this);
		 _this.options=$.extend({},$.fn.inittable.defaults,options);
	     _this.bootstrapTable({
	     	//height: "600", 
	        method: 'post',
	        //url:_this.options.url,//要请求数据的文件路径
	        //height:_this.css('height'),//高度调整
	        //toolbar: '#toolbar',//指定工具栏
	        striped: true, //是否显示行间隔色
	        queryParams:function(params){
	        	if(_this.options.formid){
	        		var request_param=$.extend({},$('#'+_this.options.formid).serializeObject(),params);
                    return request_param
	        	}else{
	        		return params;
	        	}
	        },
             
	        //pagination:true,//是否分页
	        //onlyInfoPagination:true,//设置为 true 只显示总数据数，而不显示分页按钮。需要 pagination='true'
	        async:false,
	        contentType: "application/x-www-form-urlencoded",//必须要有！！！！
	        queryParamsType:'limit',//查询参数组织方式
	        sidePagination:'server',//指定服务器端分页
	        pageList:[5,10,20,30,50],//分页步进值
	        //showToggle:false,//是否显示切换按钮
	        showExport:false,//是否显示导出按钮
	        exportDataType:['basic','all','selected'],//导出文件文件模式 //导出表格方式（默认basic：只导出当前页的表格数据；all：导出所有数据；selected：导出选中的数据）
	        exportTypes:['excel'],  //导出文件类型  
	        showRefresh:false,//刷新按钮
	        showColumns:false,//显示列选择框
	        detailView:false,//是否显示子表
	        sortable:true,  //是否显示排序
	        sortOrder: "asc", //排序方式
	        search:false,
	        //filterControl:true,//过滤控制
	        //singleSelect:true,
	        clickToSelect: false,//是否启用点击选中行
	        buttonsAlign:'right',//按钮对齐方式
	        //表格行编辑后回调方法
	        onEditableSave: function (field, row, oldValue, $el) {
	            var param=JSON.stringify(row);
	            rc.ajax(_this.options.editable_url, row,function (response) {
					  layer.msg(response.message)
				});
	        },
	        onLoadSuccess : function(data){
				if(callback){
					callback(data);
				}
			}
         })
        //点击事件 
        _this.on('click-row.bs.table', function (e, row, $element) {
    		$('.success').removeClass('success');
    		$($element).addClass('success');
  	    });
	}

	 /**
     * 表格初始化_v2 contextype为application/json
     * options
     */
    $.fn.inittable_v2 = function(options,callback) {
		var _this = $(this);
        _this.options=$.extend({},$.fn.inittable.defaults,options);
        _this.bootstrapTable({
            //height: "600",
            method: 'post',
            //url:_this.options.url,//要请求数据的文件路径
            //height:_this.css('height'),//高度调整
            //toolbar: '#toolbar',//指定工具栏
            striped: true, //是否显示行间隔色
            queryParams:function(params){
                if(_this.options.formid){
                    var request_param=$.extend({},($('#'+_this.options.formid).serializeObject()),params);
                    return request_param
                }else{
                    return params;
                }
			},
			ajaxOptions:{
				headers: {"Authorization":'Bearer '+localStorage.getItem('token')}
			},
			responseHandler: function (res) {//这里我查看源码的，在ajax请求成功后，发放数据之前可以对返回的数据进行处理，返回什么部分的数据，比如我的就需要进行整改的！
				var res_transer={};
				console.debug(res);
				if(res.syscode==200){
					res_transer.total=res.obj.total;
					res_transer.rows=res.obj.list;
				}else{
					layer.msg(res.message);
				}
				return res_transer;
			},
            //pagination:true,//是否分页
            //onlyInfoPagination:true,//设置为 true 只显示总数据数，而不显示分页按钮。需要 pagination='true'
            async:false,
            queryParamsType:'limit',//查询参数组织方式
            sidePagination:'server',//指定服务器端分页
            pageList:[5,10,20,30,50],//分页步进值
            //showToggle:false,//是否显示切换按钮
            showExport:false,//是否显示导出按钮
            exportDataType:['basic','all','selected'],//导出文件文件模式 //导出表格方式（默认basic：只导出当前页的表格数据；all：导出所有数据；selected：导出选中的数据）
            exportTypes:['excel'],  //导出文件类型
            showRefresh:false,//刷新按钮
            showColumns:false,//显示列选择框
            detailView:false,//是否显示子表
            sortable:true,  //是否显示排序
            sortOrder: "asc", //排序方式
            search:false,
            //filterControl:true,//过滤控制
            //singleSelect:true,
            clickToSelect: false,//是否启用点击选中行
            buttonsAlign:'right',//按钮对齐方式
            //表格行编辑后回调方法
            onEditableSave: function (field, row, oldValue, $el) {
                var param=JSON.stringify(row);
                rc.ajax(_this.options.editable_url, row,function (response) {
                    layer.msg(response.message)
                });
            },
            onLoadSuccess : function(data){
                if(callback){
                    callback(data);
                }
            }
        })
        //点击事件
        _this.on('click-row.bs.table', function (e, row, $element) {
            $('.success').removeClass('success');
            $($element).addClass('success');
        });
    }
	
	/**
	 * 表格刷新
	 * url
	 */
	$.fn.refreshtable=function(url){
		var _this = $(this);
		var param={};
		if(url){
		   param.url=url;
		}else{
		   //alert('refreshtable函数需要url参数,不能为空')
		}
		_this.bootstrapTable('refresh', param);
	}
	
	
	/**
	 * 获取表格当前选中的checkbox、radio 值
	 * url
	 */
	$.fn.getAllTableSelections=function(field){
		var _this = $(this);
		var  getAllSelections=_this.bootstrapTable('getAllSelections');
		return getAllSelections;
	}
	
	/**
	 * 获取表格当前选中的checkbox、radio 值
	 * url
	 */
	$.fn.getData=function(field){
		var _this = $(this);
		var  alldata=_this.bootstrapTable('getData');
		return alldata;
	}
	
	/**
	 * 表格初始化默认参数
	 * form表单id
	 * url分页地址
	 * editable_url 行编辑后调用地址
	 */
	$.fn.inittable.defaults={
		formid:'',
		url:'',
		editable_url:''
	}
})(jQuery);


/**
 * closableTab 
 * 用于增加或删除tab
 */
var closableTab = {
	//frame加载完成后设置父容器的高度，使iframe页面与父页面无缝对接
	frameLoad:function (frame){
		var mainheight = $(frame).contents().find('body').height();
		$(frame).parent().height(mainheight);
    },

    //添加tab
	addTab:function(tabItem){ //tabItem = {id,name,url,closable}
		var id = "tab_seed_" + tabItem.id;
		var container ="tab_container_" + tabItem.id;
		$("li[id^=tab_seed_]").removeClass("active");
		$("div[id^=tab_container_]").removeClass("active");
		if(!$('#'+id)[0]){
			var li_tab = '<li role="presentation" class="" id="'+id+'"><a href="#'+container+'"  role="tab" data-toggle="tab" style="position: relative;padding:2px 20px 2px 15px">'+tabItem.name+"&nbsp;";
			if(tabItem.closable){
				li_tab = li_tab + '<i class="glyphicon glyphicon-remove " tabclose="'+id+'" style="position: absolute;right:4px;top: 4px;"  onclick="closableTab.closeTab(this)"></i></a></li> ';
			}else{
				li_tab = li_tab + '</a></li>';
			}
		
		 	var tabpanel = '<div role="tabpanel" class="tab-pane" id="'+container+'" style="width: 100%;height:100%;">'+
					        '<iframe src="'+tabItem.url+'" id="tab_frame_2" frameborder="0" style="overflow-x: hidden; overflow-y: hidden;width:100%;height: 100%"  onload="closableTab.frameLoad(this)"></iframe>'+
				            '</div>';
			$('.nav-tabs').append(li_tab);
			$('.tab-content').append(tabpanel);
		}
		$("#"+id).addClass("active");
		$("#"+container).addClass("active");
	},

	//关闭tab
	closeTab:function(item){
		var val = $(item).attr('tabclose');
		var containerId = "tab_container_"+val.substring(9);
   	    if($('#'+containerId).hasClass('active')){
   	    	$('#'+val).prev().addClass('active');
   	    	$('#'+containerId).prev().addClass('active');
   	    }
		$("#"+val).remove();
		$("#"+containerId).remove();
	}
}

var imgExt = new Array("png","jpg","jpeg","bmp","gif");//图片文件的后缀名
var docExt = new Array("doc","docx");//word文件的后缀名
var xlsExt = new Array("xls","xlsx");//excel文件的后缀名
var rarExt = new Array("rar","zip");//压缩文件的后缀名
var vodExt = new Array('avi', 'mp4', 'flv', 'mov', 'rmvb','3gp','mpeg');//视频文件的后缀名
var sodExt = new Array('wma', 'mp3');//音频文件的后缀名
/**
 * 使用循环的方式判断一个元素是否存在于一个数组中
 * @param {Object} arr 数组
 * @param {Object} value 元素值
 */
function isInArray(arr,value){
    for(var i = 0; i < arr.length; i++){
        if(value === arr[i]){
            return true;
        }
    }
    return false;
}

//打开帮助页面
function help(url_suffix) {
	layer.open({
		  type: 2,
		  title: '帮助手册',
		  shadeClose: true,
		  maxmin:true,
		  shade: 0.8,
		  area: ['82%', '90%'],
		  content: contextPath+'/module_help/'+url_suffix
	});
}

/*
 * A JavaScript implementation of the Secure Hash Algorithm, SHA-1, as defined
 * in FIPS PUB 180-1
 * Version 2.1-BETA Copyright Paul Johnston 2000 - 2002.
 * Other contributors: Greg Holt, Andrew Kepert, Ydnar, Lostinet
 * Distributed under the BSD License
 * See http://pajhome.org.uk/crypt/md5 for details.
 */
/*
 * Configurable variables. You may need to tweak these to be compatible with
 * the server-side, but the defaults work in most cases.
 */
var hexcase = 0; /* hex output format. 0 - lowercase; 1 - uppercase     */
var b64pad = ""; /* base-64 pad character. "=" for strict RFC compliance  */
var chrsz = 8; /* bits per input character. 8 - ASCII; 16 - Unicode    */
/*
 * These are the functions you'll usually want to call
 * They take string arguments and return either hex or base-64 encoded strings
 */
function hex_sha1(s) {
 return binb2hex(core_sha1(str2binb(s), s.length * chrsz));
}
function b64_sha1(s) {
 return binb2b64(core_sha1(str2binb(s), s.length * chrsz));
}
function str_sha1(s) {
 return binb2str(core_sha1(str2binb(s), s.length * chrsz));
}
function hex_hmac_sha1(key, data) {
 return binb2hex(core_hmac_sha1(key, data));
}
function b64_hmac_sha1(key, data) {
 return binb2b64(core_hmac_sha1(key, data));
}
function str_hmac_sha1(key, data) {
 return binb2str(core_hmac_sha1(key, data));
}
/*
 * Perform a simple self-test to see if the VM is working
 */
function sha1_vm_test() {
 return hex_sha1("abc") == "a9993e364706816aba3e25717850c26c9cd0d89d";
}
/*
 * Calculate the SHA-1 of an array of big-endian words, and a bit length
 */
function core_sha1(x, len) {
 /* append padding */
 x[len >> 5] |= 0x80 << (24 - len % 32);
 x[((len + 64 >> 9) << 4) + 15] = len;
 var w = Array(80);
 var a = 1732584193;
 var b = -271733879;
 var c = -1732584194;
 var d = 271733878;
 var e = -1009589776;
 for (var i = 0; i < x.length; i += 16) {
  var olda = a;
  var oldb = b;
  var oldc = c;
  var oldd = d;
  var olde = e;
  for (var j = 0; j < 80; j++) {
   if (j < 16) w[j] = x[i + j];
   else w[j] = rol(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1);
   var t = safe_add(safe_add(rol(a, 5), sha1_ft(j, b, c, d)), safe_add(safe_add(e, w[j]), sha1_kt(j)));
   e = d;
   d = c;
   c = rol(b, 30);
   b = a;
   a = t;
  }
  a = safe_add(a, olda);
  b = safe_add(b, oldb);
  c = safe_add(c, oldc);
  d = safe_add(d, oldd);
  e = safe_add(e, olde);
 }
 return Array(a, b, c, d, e);
}
/*
 * Perform the appropriate triplet combination function for the current
 * iteration
 */
function sha1_ft(t, b, c, d) {
 if (t < 20) return (b & c) | ((~b) & d);
 if (t < 40) return b ^ c ^ d;
 if (t < 60) return (b & c) | (b & d) | (c & d);
 return b ^ c ^ d;
}
/*
 * Determine the appropriate additive constant for the current iteration
 */
function sha1_kt(t) {
 return (t < 20) ? 1518500249 : (t < 40) ? 1859775393 : (t < 60) ? -1894007588 : -899497514;
}
/*
 * Calculate the HMAC-SHA1 of a key and some data
 */
function core_hmac_sha1(key, data) {
 var bkey = str2binb(key);
 if (bkey.length > 16) bkey = core_sha1(bkey, key.length * chrsz);
 var ipad = Array(16),
  opad = Array(16);
 for (var i = 0; i < 16; i++) {
  ipad[i] = bkey[i] ^ 0x36363636;
  opad[i] = bkey[i] ^ 0x5C5C5C5C;
 }
 var hash = core_sha1(ipad.concat(str2binb(data)), 512 + data.length * chrsz);
 return core_sha1(opad.concat(hash), 512 + 160);
}
/*
 * Add integers, wrapping at 2^32. This uses 16-bit operations internally
 * to work around bugs in some JS interpreters.
 */
function safe_add(x, y) {
 var lsw = (x & 0xFFFF) + (y & 0xFFFF);
 var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
 return (msw << 16) | (lsw & 0xFFFF);
}
/*
 * Bitwise rotate a 32-bit number to the left.
 */
function rol(num, cnt) {
 return (num << cnt) | (num >>> (32 - cnt));
}
/*
 * Convert an 8-bit or 16-bit string to an array of big-endian words
 * In 8-bit function, characters >255 have their hi-byte silently ignored.
 */
function str2binb(str) {
 var bin = Array();
 var mask = (1 << chrsz) - 1;
 for (var i = 0; i < str.length * chrsz; i += chrsz)
 bin[i >> 5] |= (str.charCodeAt(i / chrsz) & mask) << (24 - i % 32);
 return bin;
}
/*
 * Convert an array of big-endian words to a string
 */
function binb2str(bin) {
 var str = "";
 var mask = (1 << chrsz) - 1;
 for (var i = 0; i < bin.length * 32; i += chrsz)
 str += String.fromCharCode((bin[i >> 5] >>> (24 - i % 32)) & mask);
 return str;
}
/*
 * Convert an array of big-endian words to a hex string.
 */
function binb2hex(binarray) {
 var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
 var str = "";
 for (var i = 0; i < binarray.length * 4; i++) {
  str += hex_tab.charAt((binarray[i >> 2] >> ((3 - i % 4) * 8 + 4)) & 0xF) + hex_tab.charAt((binarray[i >> 2] >> ((3 - i % 4) * 8)) & 0xF);
 }
 return str;
}
/*
 * Convert an array of big-endian words to a base-64 string
 */
function binb2b64(binarray) {
 var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
 var str = "";
 for (var i = 0; i < binarray.length * 4; i += 3) {
  var triplet = (((binarray[i >> 2] >> 8 * (3 - i % 4)) & 0xFF) << 16) | (((binarray[i + 1 >> 2] >> 8 * (3 - (i + 1) % 4)) & 0xFF) << 8) | ((binarray[i + 2 >> 2] >> 8 * (3 - (i + 2) % 4)) & 0xFF);
  for (var j = 0; j < 4; j++) {
   if (i * 8 + j * 6 > binarray.length * 32) str += b64pad;
   else str += tab.charAt((triplet >> 6 * (3 - j)) & 0x3F);
  }
 }
 return str;
}