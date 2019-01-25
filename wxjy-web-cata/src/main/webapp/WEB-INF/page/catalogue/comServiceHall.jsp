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
</head>

<body class="bg-light">

<tags:CataHeadTag />

<form class="container" id="form" action="<%=path%>/catalogue/comServiceHall" method="post">
  <div class="row">
    <div class="col-xs-6">
      <ol class="breadcrumb">
        <li class="active">单位服务大厅</li>
      </ol>
    </div>
    <div class="col-xs-3 col-xs-offset-3">
      <div class="input-group">
        <input type="text" class="form-control" id="keyword" value="${condition.keyword}" placeholder="请输入您想找的事项" >
        <span class="input-group-btn">
          <button class="btn btn-default" type="button" onclick="formSearch()">搜索</button>
        </span>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-xs-3 mb-20">
      <a href="" class="v-label">
        <img src="<%=staticPath%>/resource/catalogue/images/image_06.png">公务员管理
      </a>
    </div>
    <div class="col-xs-3 mb-20">
      <a href="" class="v-label">
        <img src="<%=staticPath%>/resource/catalogue/images/image_08.png">人事管理
      </a>
    </div>
    <div class="col-xs-3 mb-20">
      <a href="" class="v-label">
        <img src="<%=staticPath%>/resource/catalogue/images/image_10.png">人才工作
      </a>
    </div>
    <div class="col-xs-3 mb-20">
      <a href="" class="v-label">
        <img src="<%=staticPath%>/resource/catalogue/images/image_12.png">就业创业
      </a>
    </div>
    <div class="col-xs-3 mb-20">
      <a href="" class="v-label">
        <img src="<%=staticPath%>/resource/catalogue/images/image_19.png">社会保险
      </a>
    </div>
    <div class="col-xs-3 mb-20">
      <a href="" class="v-label">
        <img src="<%=staticPath%>/resource/catalogue/images/image_23.png">劳动关系
      </a>
    </div>
    <div class="col-xs-3 mb-20">
      <a href="" class="v-label">
        <img src="<%=staticPath%>/resource/catalogue/images/image_21.png">工资福利
      </a>
    </div>
    <div class="col-xs-3 mb-20">
      <a href="" class="v-label">
        <img src="<%=staticPath%>/resource/catalogue/images/image_24.png">其他事项
      </a>
    </div>
  </div>

  <div class="row">
    <div class="col-xs-2">
      <ul class="v-tab-head">
        <li <c:if test="${condition.searchType == '1'}">class="active"</c:if>>按主题</li>
        <li <c:if test="${condition.searchType == '2'}">class="active"</c:if>>按部门</li>
      </ul>
    </div>
    <div class="col-xs-10">
      <div class="v-tab-body">
        <input type="hidden" name="searchType" value="${condition.searchType}">
        <input type="hidden" name="busTypeId" value="${condition.busTypeId}">
        <input type="hidden" name="departmentId" value="${condition.departmentId}">
        <input type="hidden" name="keyword" value="${condition.keyword}">

        <div <c:if test="${condition.searchType == '2'}">class="hide"</c:if>>
          <a onclick="formSubmit('busTypeId', '')"
             <c:if test="${condition.searchType == '1' && empty condition.busTypeId}">class="active"</c:if>>不限</a>
          <c:forEach items="${busTypeList}" var="l">
            <a onclick="formSubmit('busTypeId', '${l.bus_type_id}')"
               <c:if test="${condition.busTypeId == l.bus_type_id}">class="active"</c:if>>
                ${l.bus_type_name}
                  (<span class="text-primary">${l.count}</span>)
            </a>
          </c:forEach>
        </div>

        <div <c:if test="${condition.searchType == '1'}">class="hide"</c:if>>
          <a onclick="formSubmit('departmentId', '')"
             <c:if test="${condition.searchType == '2' && empty condition.departmentId}">class="active"</c:if>>不限</a>
          <c:forEach items="${departmentList}" var="l">
            <a onclick="formSubmit('departmentId', '${l.department_id}')"
               <c:if test="${condition.departmentId == l.department_id}">class="active"</c:if>>
                ${l.department_name_abb}</a>
          </c:forEach>
        </div>

      </div>
    </div>
  </div>

  <c:forEach items="${listMap}" var="map">
    <div class="bg-white text-primary f18 mt-10" style="padding: 20px 30px;border-bottom: 2px solid #e1e0e0">
      <strong>${map.key}</strong>
    </div>
    <ul class="v-cata-list">
      <c:forEach items="${map.value}" var="l">
        <li>
          <!-- 注意火狐浏览器的white-space: nowrap问题 -->
          <div class="pull-right">
            <c:if test="${l.cata_is_net == '1'}">
              <a href="<%=path%>/${l.cata_url}" class="btn btn-primary-plain">在线办理</a>
            </c:if>
          </div>
          <a href="<%=path%>/catalogue/detail/${l.cata_id}" title="${l.cata_name}">${l.cata_name}</a>
        </li>
      </c:forEach>
    </ul>
  </c:forEach>

</form>

<input type="hidden" id="isLogin" value="${SHIRO_CURRENT_USER_INFO.usertype}">


<tags:CataFootTag/>

<script src="<%=staticPath%>/webjars/js/jQuery/all/jquery.js"></script>
<script src="<%=staticPath%>/webjars/js/jQuery/layer/layer.js"></script>
<script src="<%=staticPath%>/webjars/js/rc.all-1.0.js"></script>
<script src="<%=staticPath%>/webjars/js/bootstrap.min.js"></script>

<script>
  $('.v-tab-head li').hover(function () {
    var index = $('.v-tab-head li').index(this);
    $(this).addClass('active');
    $(this).siblings().removeClass('active');
    $('.v-tab-body').children().addClass('hide');
    $('.v-tab-body div').eq(index).removeClass('hide')
  });

  function formSubmit(name, value) {
    $('[name="' + name + '"]').val(value);
    switch (name) {
      case 'busTypeId':
        $('[name="searchType"]').val('1');
        $('[name="departmentId"]').val('');
        break;
      case 'departmentId':
        $('[name="searchType"]').val('2');
        $('[name="busTypeId"]').val('');
        break;
    }
    $('#form').submit();
  }

  function formSearch() {
    $('[name="keyword"]').val($('#keyword').val());
    $('#form').submit();
  }
</script>

</body>

</html>