<%@ page import="com.insigma.common.listener.AppConfig" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page isELIgnored="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%
	  String staticPath = AppConfig.getProperties("website_static_resource_url");
	  String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="<%=staticPath%>/webjars/css/bootstrap.min.css">
  <link href="<%=staticPath%>/resource/catalogue/css/index.css" rel="stylesheet">
  <title>网上办事大厅</title>
  <style>
    .nav {
      background-color: #fff;
    }

    .nav > li {
      border-bottom: 1px dashed #ddd;
    }

    .nav > li:last-child {
      border-bottom: none;
    }

    .nav > li > a {
      text-align: center;
      padding: 15px !important;
      border-radius: inherit !important;
    }
  </style>
</head>

<body class="bg-light" data-spy="scroll" data-target="#myScrollspy" data-offset="20">

<tags:CataHeadTag/>

<div class="container">
  <div class="row">
    <div class="col-xs-6">
      <ol class="breadcrumb">
        <li class="active">服务目录</li>
      </ol>
    </div>
    <div class="col-xs-3 col-xs-offset-3">
      <form id="form" action="<%=path%>/catalogue/list" method="post">
        <div class="input-group">
          <input class="form-control" name="keyword" value="${condition.keyword}" placeholder="请输入您想找的服务">
          <span class="input-group-btn">
          <button class="btn btn-default">
            搜索
          </button>
        </span>
        </div>
      </form>
    </div>
  </div>

  <div style="overflow: hidden">
    <nav class="pull-left" id="myScrollspy" style="width: 200px;">
      <h2 class="text-center bg-primary" style="padding: 15px;margin: 0">总目录</h2>
      <ul class="nav nav-pills nav-stacked pinned">
        <c:forEach items="${busTypeList}" var="l">
          <li><a href="#${l.bus_type_id}">${l.bus_type_name}</a></li>
        </c:forEach>
      </ul>
    </nav>

    <div style="margin-left: 210px;">
      <c:forEach items="${busTypeList}" var="l">
        <div id="${l.bus_type_id}" class="mb-10">
          <div class="bg-white text-primary f18" style="padding: 20px 30px;border-bottom: 2px solid #e1e0e0">
            <strong>${l.bus_type_name}</strong>
          </div>
          <ul class="v-cata-list">
            <c:forEach items="${list[l.bus_type_id]}" var="item">
              <li>
                <!-- 注意火狐浏览器的white-space: nowrap问题 -->
                <div class="pull-right">
                  <c:if test="${item.cata_is_net == '1'}">
                    <a href="<%=path%>/${item.cata_url}" class="btn btn-primary-plain" target="_blank">在线办理</a>
                  </c:if>
                  <c:if test="${item.event_type_id == '70CC8779B8A3A0F8E0531100BC0A803D'}">
                    <a href="${item.cata_url}" class="btn btn-primary-plain" target="_blank">链接</a>
                  </c:if>
                  <c:if test="${item.event_type_id != '70CC8779B8A3A0F8E0531100BC0A803D'}">
                  <button type="button" class="btn btn-primary-plain" onclick="toggleCollect(this, '${item.cata_id}')">
                    <c:choose>
                      <c:when test="${item.is_collect == '1'}">
                        取消收藏
                      </c:when>
                      <c:otherwise>
                        收藏
                      </c:otherwise>
                    </c:choose>
                  </button>
                  </c:if>
                </div>
                <c:if test="${item.event_type_id != '70CC8779B8A3A0F8E0531100BC0A803D'}">
                <a href="<%=path%>/catalogue/detail/${item.cata_id}" title="${item.cata_name}" target="_blank">
                    ${item.cata_name}
                </a>
                </c:if>
                <c:if test="${item.event_type_id == '70CC8779B8A3A0F8E0531100BC0A803D'}">
                  <a href="${item.cata_url}" title="${item.cata_name}" target="_blank">
                      ${item.cata_name}
                  </a>
                </c:if>
              </li>
            </c:forEach>
          </ul>
        </div>
      </c:forEach>
    </div>
  </div>
</div>

<tags:CataFootTag/>

<script src="<%=staticPath%>/webjars/js/jQuery/all/jquery.js"></script>
<script src="<%=staticPath%>/webjars/js/jQuery/layer/layer.js"></script>
<script src="<%=staticPath%>/webjars/js/rc.all-1.0.js"></script>
<script src="<%=staticPath%>/webjars/js/bootstrap.min.js"></script>
<script src="<%=staticPath%>/webjars/js/jquery.pin.js"></script>

<script>
  $(".pinned").pin();

  // 收藏和取消收藏操作，先进行登录检查
  function toggleCollect(_this, cataId) {
    $.ajax({
	      url: '<%=path%>/person/catalogue/toggleCollect',
	      type: 'post',
	      data: {
	           cata_id: cataId
	      },
	      success: function(res) {
		        layer.alert(res.message);
		        $(_this).text().trim() === '收藏' ? $(_this).text('取消收藏') : $(_this).text('收藏');
	      }
    })
  }
</script>

</body>

</html>