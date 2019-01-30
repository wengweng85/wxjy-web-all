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