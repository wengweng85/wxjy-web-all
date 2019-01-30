<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="com.insigma.common.listener.AppConfig" %>
<%@ page isELIgnored="false"%>
<%
       String gateway_base_url = AppConfig.getProperties("gateway_base_url");
       String website_static_resource_nginx_url =AppConfig.getProperties("website_static_resource_nginx_url");
       String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>${SYS_TITLE }</title>
    <meta name="keywords" content="xxx">
    <meta name="description" content="xxxx">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <link href="<%=website_static_resource_nginx_url%>/webjars/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=website_static_resource_nginx_url%>/webjars/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=website_static_resource_nginx_url%>/webjars/css/animate.min.css" rel="stylesheet">
    <link href="<%=website_static_resource_nginx_url%>/webjars/css/style.min.css" rel="stylesheet">
    <link href="<%=website_static_resource_nginx_url%>/webjars/css/rc.css" rel="stylesheet">
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div style="width: 20px;"  class="sidebar-collapse" >
                  <ul class="nav" id="side-menu">
                 <li class="nav-header">
                        <div class="logo-element"><i class="fa fa-university"></i>
                        </div>
                        <div class="appelement">
                        <span style="color: #f9f9f9;font-weight: bold;text-align: center;">
							${SYS_TITLE}
                        </span>
                        </div>
                 </li>
                 <script id="tpl_menus" type="text/x-handlebars-template" > 
                    {{#each this}} 
                    <li class="fa-hover">
                        <a href="#" >
                            <i class="fa fa-detail  {{#if iconcss}} fa-bookmark-o {{else}} {{iconcss}} {{/if}} "></i>
                            <span class="nav-label">{{permname}}</span>
                            {{#if child}}<span class="fa arrow"></span> {{/if}} 
                        </a>
                        {{#if child}}
                        <ul class="nav nav-second-level collapse">
                               {{#each child}} 
						       <li>
                                      {{#if child}}
		                                           <a   {{#equals isblanktarget 0 }}   class="J_menuItem"  href="<%=path%>/{{url}}"   {{/equals}}  {{#equals isblanktarget 1 }}   target='_blank' {{/equals}} >
	                                                       <i class="fa fa-detail {{iconcss}}"></i>{{permname}}<span class="fa arrow"></span>
		                                           </a>
		                                             <ul class="nav nav-third-level collapse">
                                                       {{#each child}} 
			                                           <li>
			                                 	              {{#if child}}
			                                 	  	 	          <a {{#equals isblanktarget 0 }}   class="J_menuItem"  {{/equals}} href="<%=path%>/{{url}}"  {{#equals isblanktarget 1 }}  target='_blank' {{/equals}}>
				                                                  <i class="fa fa-detail {{iconcss}}"></i>{{permname}}<span class="fa arrow"></span>
				                                                  </a>
			                                 	  	 	          <ul class="nav nav-fourth-level">
			                                 	  	 		          {{#each child}} 
			                                 	  	 		          <li>
			                                 	  	 			       <a {{#equals isblanktarget 0 }}  class="J_menuItem" {{/equals}}  href="<%=path%>/{{url}}" {{#equals isblanktarget 1 }}  target='_blank' {{/equals}}>
		                                 						       <i class="fa fa-detail {{iconcss}}"></i>{{fourthperm.permname}}</a>
		                                 					         <li>
			                                 	  	 		          {{/each}}	
			                                 	  	 	         </ul>
			                                 	  	        {{else}}
			                                 	  	        	<a 
			                                 	  		        {{#equals isblanktarget 0 }}  class="J_menuItem" {{/equals}}  href="<%=path%>/{{url}}" {{#equals isblanktarget 1 }}  target='_blank' {{/equals}}>
				                                                <i class="fa fa-detail ${thirdperm.iconcss}"></i>{{thirdperm.permname}}
				                                               </a>
			                                 	             {{/if}}
			                                           </li>
			                                           {{/each}}
		                                           </ul>
		                                {{else}}
		                                 <a {{#equals isblanktarget 0 }}  class="J_menuItem" {{/equals}}   href="<%=path%>/{{url}}" {{#equals isblanktarget 1 }}  target='_blank'  {{/equals}}>
		                                 <i class="fa fa-detail {{iconcss}}"></i>{{permname}}</a>
		                                {{/if}}
						         </li>
                            {{/each}}
                         </ul>
                        {{/if}}
                     </li>
                    {{/each}}
            </script>
            </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row content-tabs">
                 <!-- 切换按钮 -->
	            <button class="roll-nav roll-left J_tabLeft"> 
	                <a class="navbar-minimalize" style="display: block;color: #39aef5;font-size: 18px;" href="#" ><i class="fa fa-bars"></i> </a>
	            </button>
                <!-- 左滚动 -->
                <button class="roll-nav roll-left-backword J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab"  data-id="index_v1.html" >首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown" >
                        <script type="text/javascript">
                           try{
                        	   var userino_str=localStorage.getItem('userinfo');
                        	   if(userino_str){
	                               	var userinfo=JSON.parse(userino_str);
	       	                        document.write(userinfo.name);
                               }else{
       	                            document.write("未登录");
                               }
                           } catch(err)
                           {
                        	   document.write("未登录");
                           }
                        </script>
                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li>
                            <a id="updPassword"><i class="fa fa-user-secret"></i> 修改密码</a>
                        </li>
                    </ul>
                </div>
                <a href="javascript:void(0)" onclick="logout()"  class="roll-nav roll-right J_tabExit"><i class="fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%"   frameborder="0" data-id="index_v1.html" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy;2019浙江网新恩普软件有限公司
                </div>
            </div>
        </div>
        
        <!-- 右键菜单 -->
        <div id="context-menu">
          <ul role="menu" class="dropdown-menu ">
               <li class="J_tabShowActive"><a>定位当前选项卡</a>
               </li>
               <li class="divider"></li>
               <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
               </li>
               <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
               </li>
          </ul>
        </div>
        <!--右侧部分结束-->
    </div>
    <script src="<%=website_static_resource_nginx_url%>/webjars/js/jQuery/all/jquery.js"></script>
    <script src="<%=website_static_resource_nginx_url%>/webjars/js/bootstrap.min.js"></script>
	<script src="<%=website_static_resource_nginx_url%>/webjars/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="<%=website_static_resource_nginx_url%>/webjars/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=website_static_resource_nginx_url%>/webjars/js/plugins/layer/layer.min.js"></script>
    <script src="<%=website_static_resource_nginx_url%>/webjars/js/hplus.min.js"></script>
    <script src="<%=website_static_resource_nginx_url%>/webjars/js/contabs.js"></script>
    <script src="<%=website_static_resource_nginx_url%>/webjars/js/bootstrap-contextmenu.js"></script>
    <script src="<%=website_static_resource_nginx_url%>/webjars/js/json2.js"></script>
    <script src="<%=website_static_resource_nginx_url%>/webjars/js/rc.all-2.0.js"></script>
    <script type="text/javascript" >
    /**
     * rc.admin.index-1.0.js
     *
     * 基于hplus的管理控制台主页面js
     * @author wengsh
     *
     */ 
    function c() {
		var o = $(this).attr("href"), m = $(this).data("index"), l = $(this).text(), k = true;
		if (o == undefined || o==='#' || $.trim(o).length == 0) {
			return false
		}
		top.$(".J_menuTab").each(function() {
			if ($(this).data("id") == o) {
				if (!$(this).hasClass("active")) {
					$(this).addClass("active").siblings(".J_menuTab").removeClass("active");
					g(this);
					top.$(".J_mainContent .J_iframe").each(function() {
						if ($(this).data("id") == o) {
							$(this).show().siblings(".J_iframe").hide();
							return false
						}
					})
				}
				k = false;
				return false
			}
		});
		
		if (k) {
			var p = '<a href="javascript:;" class="active J_menuTab" data-id="'+ o + '">' + l + ' <i class="fa fa-times-circle"></i></a>';
			top.$(".J_menuTab").removeClass("active");
			var n = '<iframe class="J_iframe" name="iframe' + m
					+ '" width="100%" height="100%" src="' + o
					+ '" frameborder="0" data-id="' + o
					+ '" seamless></iframe>';
			top.$(".J_mainContent").find("iframe.J_iframe").hide().parents(".J_mainContent").append(n);
			top.$(".J_menuTabs .page-tabs-content").append(p);
			g($(".J_menuTab.active"))
		}
		return false
	}

     $(function() {
        	  var menus_str = localStorage.getItem(contextPath+'/menus');
        	  if(menus_str){
        		    console.debug("data from localstorage");
        	    	var menus=JSON.parse(menus_str);
        	    	render(menus);
        	  }else{
        		  var url="<%=gateway_base_url%>/api-authorize/menus";
            	  rc.api_post(url,null,
                    function(response){ 
                        localStorage.setItem(contextPath+'/menus',JSON.stringify(response.obj));
                        render(response.obj);
                    }
                 )
        	  }
        });
        
        //模版渲染
        function render(jsondata,model,target){
    		var modeldata=Handlebars.compile($('#tpl_menus').html());
    		var views = modeldata(jsondata);
    		$('#side-menu').append(views);
    		$("#side-menu").metisMenu();
    		$(".J_menuItem").on("click", c);
        }
        
        $('.J_menuTabs').contextmenu({
          target: '#context-menu',
          before: function (e) {
            e.preventDefault();
            return true;
          }
        });

        // 
        $('#updPassword').click(function() {
            var tabItem = {
                id: 'updPassword',
                name: '修改密码',
                url: contextPath+'/updPassword/view'
            }
            addTab(tabItem);
        });

        //addtab 
        function addTab(tabItem) {
            var url = tabItem.url, id = tabItem.id, name = tabItem.name, flag = true;
            if (url == undefined || url === '#' || $.trim(url).length == 0) {
                return false;
            }
            top.$(".J_menuTab").each(function() {
                if ($(this).data("id") == url) {
                    if (!$(this).hasClass("active")) {
                        $(this).addClass("active").siblings(".J_menuTab").removeClass("active");
                        g(this);
                        top.$(".J_mainContent .J_iframe").each(function() {
                            if ($(this).data("id") == url) {
                                $(this).show().siblings(".J_iframe").hide();
                                return false;
                            }
                        })
                    }
                    flag = false;
                    return false;
                }
            });
            if (flag) {
                var p = '<a href="javascript:;" class="active J_menuTab" data-id="'+ url + '">' + name + ' <i class="fa fa-times-circle"></i></a>';
                top.$(".J_menuTab").removeClass("active");
                var n = '<iframe class="J_iframe" name="iframe' + id
                    + '" width="100%" height="100%" src="' + url
                    + '" frameborder="0" data-id="' + url
                    + '" seamless></iframe>';
                top.$(".J_mainContent").find("iframe.J_iframe").hide().parents(".J_mainContent").append(n);
                top.$(".J_menuTabs .page-tabs-content").append(p);
                g($(".J_menuTab.active"))
            }
            return false;
        }

        function g(n) {
            var o = f($(n).prevAll()), q = f($(n).nextAll());
            var l = f($(".content-tabs").children().not(".J_menuTabs"));
            var k = $(".content-tabs").outerWidth(true) - l;
            var p = 0;
            if ($(".page-tabs-content").outerWidth() < k) {
                p = 0
            } else {
                if (q <= (k - $(n).outerWidth(true) - $(n).next().outerWidth(true))) {
                    if ((k - $(n).next().outerWidth(true)) > q) {
                        p = o;
                        var m = n;
                        while ((p - $(m).outerWidth()) > ($(".page-tabs-content").outerWidth() - k)) {
                            p -= $(m).prev().outerWidth();
                            m = $(m).prev()
                        }
                    }
                } else {
                    if (o > (k - $(n).outerWidth(true) - $(n).prev().outerWidth(true))) {
                        p = o - $(n).prev().outerWidth(true)
                    }
                }
            }
            $(".page-tabs-content").animate({
                marginLeft : 0 - p + "px"
            }, "fast")
        }

        function f(l) {
            var k = 0;
            $(l).each(function() {
                k += $(this).outerWidth(true)
            });
            return k
        }
        
        function logout(){
        	rc. remove_token();
        	window.location.href=contextPath;
        }
    </script>
   
</body>
</html>