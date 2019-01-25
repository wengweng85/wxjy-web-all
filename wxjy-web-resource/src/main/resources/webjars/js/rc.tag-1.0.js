/**
 * rc.tag-1.0.js
 *
 * 用于网站中的各类标签通用配置js
 * @author wengsh
 * 
 * version 1.0
 * 修改记录 wengsh
 * v1.0 提供select框架通用配置js
 *      提供日期类通用配置js
 *
 */
$(function() {
	
	var select_config = {
		".select2" : {}
	};
	
	var select_with_ajax_config = {
		".select2withajax" : {}
	};
	
	/**通用日期配置*/
	var date_config = {
		".form_date" : {}
	};
	
	var datetime_config = {
		".form_datetime" : {}
	};
	
	var time_config = {
		".form_time" : {}
	};
	
	
	/*for (var selector in select_config){
		$(selector).select2({
		   tags: false,
		   width: "100%", //设置下拉框的宽度
		   //minimumResultsForSearch: Infinity,
		   placeholder:'请选择',
		   language: "zh-CN", //设置 提示语言
		   closeOnSelect:false,
		   //minimumInputLength: 1,  //最小需要输入多少个字符才进行查询
		   allowClear: true, //是否允许清空选中
		   selectOnClose:false,
		   maximumSelectionLength:3
		})
	}*/
	
	
	/*for (var selector in select_config){
		$(selector).select2({
		   tags: false,
		   width: "100%", //设置下拉框的宽度
		   language: "zh-CN", //设置 提示语言
		   closeOnSelect:false,
		   minimumInputLength: 1,  //最小需要输入多少个字符才进行查询
		   allowClear: true, //是否允许清空选中
		   selectOnClose:false,
		   maximumSelectionLength:3,
		   formatResult: function formatRepo(repo){return repo.text;}, // 函数用来渲染结果
           formatSelection: function formatRepoSelection(repo){return repo.text;}, // 函数用于呈现当前的选择
		   ajax: {
		      url: contextPath+"/codetype/query",
		      dataType: 'json',
		      delay: 250,
		      data: function (params) {
		        return {
		          q: params.term,
		          code_type:'AAB800'
		        };
		     },
		     processResults: function (data) {
		        return {
		           results: data
		        };
		     },
		     cache: true
		     }
		})
	}*/
	
	
	for (var selector in date_config){
        $(selector).each(function(){
            $(this).datetimepicker({
                format : "yyyy-mm-dd",
                minView: "2",//设置只显示到月份
                todayBtn:true,
                todayHighlight:true,
                autoclose:true
            })

            var endDate = $(this).find('input').attr('endDate');
            var startDate = $(this).find('input').attr('startDate');
            if(typeof(endDate)!="undefined"){
                $(this).on('click',function(e){
                    var endDateValue = $("#"+endDate).val();
                    if(endDateValue == ''){
                        $(this).datetimepicker("setEndDate",'9999-12-31');
					}else{
                        $(this).datetimepicker("setEndDate", endDateValue);
					}
                });
            }
            if(typeof(startDate)!="undefined"){
                $(this).on('click',function(e){
                    var startDateValue = $("#"+startDate).val();
                    if(startDateValue == ''){
                        $(this).datetimepicker("setStartDate",'0000-01-01');
                    }else{
                        $(this).datetimepicker("setStartDate", startDateValue);
                    }
                });
            }
        });
	}
	
	for (var selector in datetime_config){
		$(selector).datetimepicker({
			format : "yyyy-mm-dd hh:ii:ss",
			todayHighlight:true,
			todayBtn:true
		})
	}
	
	for (var selector in time_config){
		$(selector).datetimepicker({
			format : "hh:ii",
			startView:'0',
			todayHighlight:true,
			todayBtn:true
		})
	}
	
	//js switch选择框
	$('.js-switch').bootstrapSwitch({
	    onText:"是",  
        offText:"否",  
        onColor:"success",  
        offColor:"default",  
        size:"small",  
        onSwitchChange:function(event,state){  
	        if(state==true){  
	            $(this).val("1");  
	        }else{  
	            $(this).val("0");  
	        }  
        }  
	});
	
	
	
	
});

/**
 * tc tag 
 * @type 
 */
var rc_tag = {
	/**
	 * 动态从数据库中获取代码
	 * @param {} codetype
	 * @param {} filter
	 */
	dynamic_get_codevalue_by_codetype_and_filter:function(current_selector,codetype,filter,value){
		if(codetype&&filter){
			var url=contextPath+'/sys/codetype/getCodeValueList';
			rc.ajax(url,{code_type:codetype,filter:filter},function(response){
				//将当前节点后的所有子选择框清空
				$(current_selector).empty();
				//$(current_selector).selectpicker('refresh');
				//数据加模型生成新的option数组
				var modeldata=Handlebars.compile($('#tpl_option').html());
				if(value){
					response.value=value;
				}
				var views = modeldata(response);
			    $(current_selector).append(views);
			    $(current_selector).selectpicker('refresh');
			})
		}else{
			layer.msg('代码类型及过滤代码不能为空');
		}
	},
	/**
	 * 动态从数据库中获取代码
	 * @param {} codetype
	 * @param {} filter
	 */
	dynamic_get_codevalue_by_codetype:function(current_selector,codetype,value){
		if(codetype){
			var url=contextPath+'/sys/codetype/getCodeValueList';
			rc.ajax(url,{code_type:codetype},function(response){
				//将当前节点后的所有子选择框清空
				$(current_selector).empty();
				//$(current_selector).selectpicker('refresh');
				//数据加模型生成新的option数组
				var modeldata=Handlebars.compile($('#tpl_option').html());
				if(value){
					response.value=value;
				}
				var views = modeldata(response);
			    $(current_selector).append(views);
			    $(current_selector).selectpicker('refresh');
			})
		}else{
			layer.msg('代码类型不能为空');
		}
	}
}