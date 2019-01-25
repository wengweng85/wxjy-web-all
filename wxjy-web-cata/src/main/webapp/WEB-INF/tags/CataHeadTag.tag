<%@ tag pageEncoding="utf-8" body-content="empty" small-icon="" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag import="com.insigma.common.listener.AppConfig" %>
<%
  String staticPath = AppConfig.getProperties("website_static_resource_url");
  String path = request.getContextPath();
%>
<link rel="stylesheet" href="<%=staticPath%>/webjars/css/bootstrap.min.css">
<link href="<%=staticPath%>/resource/catalogue/css/index.css" rel="stylesheet">

<header class="header" style="margin-bottom: 5px;">
  <div class="container">
    <div class="row">
      <div class="col-xs-3">
        <a href="<%=path%>/">
          <img src="<%=staticPath%>/resource/catalogue/images/service_image_03.png" class="pull-left mt-10">
        </a>
      </div>
      <div class="col-xs-6">
        <ul class="v-nav">
          <li>
            <a href="<%=path%>/index" style="color:#fff;">首页</a>
          </li>
          <li>
            <a  href="<%=path%>/catalogue/perServiceHall" style="color:#fff;">个人服务大厅</a>
          </li>
          <li>
            <a  href="<%=path%>/catalogue/comServiceHall" style="color:#fff;">单位服务大厅</a>
          </li>
          <li>
            <a  href="<%=path%>/catalogue/list" style="color:#fff;">服务目录</a>
          </li>
          <li>
            <a  href="http://127.0.0.1:8091/wxjy-web-demo" style="color:#fff;">子站1</a>
          </li>
        </ul>
      </div>
      <div class="col-xs-3 text-white f18 text-right">
        <ul class="fr">
        <shiro:user>
          <li class="fl">&nbsp;&nbsp;
            <c:if test="${SHIRO_CURRENT_USER_INFO.usertype == '1'}">
              <a class="fl text-ellipsis" style="display: block;width: 200px;color:#FFF;"
                 href="<%=path%>/person/index">
                   ${SHIRO_CURRENT_USER_INFO.username}
                <c:if test="${SHIRO_CURRENT_USER_INFO.name != null}">
                  （${SHIRO_CURRENT_USER_INFO.name}）
                </c:if>
              </a>
              <div class="fl pl-10 pr-10">|</div>
              <a class="fl text-ellipsis" style="display: block;color:#FFF;" href="<%=path%>/plogin/outlogin">退出</a>
            </c:if>
            <c:if test="${SHIRO_CURRENT_USER_INFO.usertype == '2'}">
              <a class="fl text-ellipsis" style="display: block;width: 200px;color:#FFF;" href="<%=path%>/company/index">
                   ${SHIRO_CURRENT_USER_INFO.username}
                <c:if test="${SHIRO_CURRENT_USER_INFO.name != null}">
                  （${SHIRO_CURRENT_USER_INFO.name}）
                </c:if>
              </a>
              <div class="fl pl-10 pr-10">|</div>
              <a class="fl text-ellipsis" style="display: block;color:#FFF;" href="<%=path%>/clogin/outlogin">退出</a>
            </c:if>
          </li>
        </shiro:user>
        <shiro:guest>
          <li class="fl dropdown_new">
            <span>登录&nbsp;&nbsp;|</span>
            <div class="dropdown_new-content">
              <p><a href="<%=path%>/plogin/to">个人登录</a></p>
              <p><a href="<%=path%>/clogin/to">单位登录</a></p>
            </div>
          </li>
          <li class="fl dropdown_new">
            <span>&nbsp;&nbsp;注册</span>
            <div class="dropdown_new-content">
              <p><a href="<%=path%>/pregist/to">个人注册</a></p>
              <p><a href="<%=path%>/cregist/to">单位注册</a></p>
            </div>
          </li>
        </shiro:guest>
        </ul>
      </div>
    </div>
  </div>
</header>