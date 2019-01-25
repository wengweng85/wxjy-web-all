<%@ page import="com.insigma.common.listener.AppConfig" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page isELIgnored="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%
	  String staticPath = AppConfig.getProperties("website_static_resource_url");
	  String fileModule = AppConfig.getProperties("fileModule");
	  String websiteDomain = AppConfig.getProperties("website_domain");
	  String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="<%=staticPath%>/webjars/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=staticPath%>/webjars/css/font-awesome.min.css" rel="stylesheet">
  <link href="<%=staticPath%>/resource/catalogue/css/index.css" rel="stylesheet">
  <title>网上办事大厅</title>
  <style>
    .nav-tabs > li {
      width: 16.66%;
      border-right: 1px solid #40a9ff !important;
      border-radius: inherit !important;
    }

    .nav-tabs > li:last-child {
      border-right: none !important;
    }

    .nav-tabs > li > a {
      text-align: center;
      margin-right: 0 !important;
      border-radius: inherit !important;
      border:none !important;
    }

    .nav-tabs > li.active > a,
    .nav-tabs > li.active > a:hover,
    .nav-tabs > li.active > a:focus {
      font-weight: 600 !important;
      color: #fff !important;
      cursor: default !important;
      background-color: #40a9ff !important;
    }

    .btn-default {
      color: #0068b7 !important;
      border:1px solid #0068b7 !important;
    }

    .tab-pane {
      min-height: 400px;
    }

    .tab-pane > p {
      line-height: 1.8em;
      letter-spacing: .1em;
    }

    #allmap label {
      max-width: inherit;
    }
  </style>
</head>

<body class="bg-light">

<tags:CataHeadTag/>

<div class="container">
  <ol class="breadcrumb">
    <li><a href="<%=path%>/catalogue/list">服务目录</a></li>
    <li class="active">办事指南</li>
  </ol>

  <div class="bg-white border p-20">
    <h3 class="text-center mb-20" style="margin-top: 0">${catalogue.cata_name}</h3>

    <div class="row mt-10">
      <div class="col-xs-2 text-right text-gray">业务类型：</div>
      <div class="col-xs-4">${catalogue.bus_type_name}</div>
      <div class="col-xs-2 text-right text-gray">行政类型：</div>
      <div class="col-xs-4">${catalogue.power_type_name}</div>
    </div>
    <div class="row mt-10">
      <div class="col-xs-2 text-right text-gray">事项类型：</div>
      <div class="col-xs-4">${catalogue.event_type_name}</div>
      <div class="col-xs-2 text-right text-gray">事项编码：</div>
      <div class="col-xs-4">${catalogue.cata_code}</div>
    </div>
    <div class="row mt-10">
      <div class="col-xs-2 text-right text-gray">服务对象：</div>
      <div class="col-xs-4">${catalogue.consumer_type_name}</div>
      <div class="col-xs-2 text-right text-gray">受理部门：</div>
      <div class="col-xs-4">${catalogue.department_name}</div>
    </div>
    <div class="row mt-10">
      <div class="col-xs-2 text-right text-gray">受理地址：</div>
      <div class="col-xs-10">${catalogue.department_address}</div>
    </div>
    <div class="row mt-10">
      <div class="col-xs-2 text-right text-gray">联系人：</div>
      <div class="col-xs-4">${catalogue.department_linkman}</div>
      <div class="col-xs-2 text-right text-gray">联系电话：</div>
      <div class="col-xs-4">${catalogue.department_tel}</div>
    </div>
    <div class="row mt-10">
      <div class="col-xs-2 text-right text-gray">法定办理时限：</div>
      <div class="col-xs-4">${catalogue.cata_hand_time_limit}</div>
      <div class="col-xs-2 text-right text-gray">承诺办理时限：</div>
      <div class="col-xs-4">${catalogue.cata_promise_time_limit}</div>
    </div>
    <div class="row mt-10">
      <div class="col-xs-2 text-right text-gray">是否收费：</div>
      <div class="col-xs-4">${catalogue.cata_is_charge}</div>
      <div class="col-xs-2 text-right text-gray">监督投诉电话：</div>
      <div class="col-xs-4">${catalogue.cata_complaint_tel}</div>
    </div>
    <div class="row mt-10">
      <div class="col-xs-2 text-right text-gray">办理时间：</div>
      <div class="col-xs-10">${catalogue.department_work_time}</div>
    </div>
    <div class="row mt-10">
      <div class="col-xs-2 text-right text-gray">事项描述：</div>
      <div class="col-xs-10">${catalogue.cata_desc}</div>
    </div>

    <hr class="text-center" width="90%" style="border:1px dashed #EBEEF5"/>

    <div class="text-center">
      <c:if test="${catalogue.cata_is_net == '1'}">
        <a href="<%=path%>/${catalogue.cata_url}" class="btn btn-default btn-lg" style="width: 200px;margin-right: 40px;">
          <i class="fa fa-desktop" aria-hidden="true"></i>&nbsp;在线办理
        </a>
      </c:if>

      <!--
      <button type="button" class="btn btn-default btn-lg" style="width: 200px;margin-right: 40px;">
        <i class="fa fa-commenting-o" aria-hidden="true"></i><a href="http://www.12333sb.com/shebaoka/shanxi/ankang.html" style="color:#0068b7;text-decoration:none;" target="_blank">&nbsp;网上咨询</a>

      </button>
      -->
      <button type="button" class="btn btn-default btn-lg" style="width: 200px;margin-right: 40px;" onclick="toggleCollect(this, '${catalogue.cata_id}')">
        <c:choose>
          <c:when test="${catalogue.is_collect == '1'}">
            <i class="fa fa-star" aria-hidden="true"></i>&nbsp;取消收藏
          </c:when>
          <c:otherwise>
            <i class="fa fa-star-o" aria-hidden="true"></i>&nbsp;加入收藏
          </c:otherwise>
        </c:choose>
      </button>
    </div>
  </div>

  <div class="bg-white border p-20 mt-10">
    <c:if test="${catalogue.cata_is_net == '0'}">
      <ul id="myTab" class="nav nav-tabs" aria-hidden="false">
        <li class="active"><a href="#tab1" data-toggle="tab">法律依据</a></li>
        <li><a href="#tab2" data-toggle="tab">申请条件</a></li>
        <li><a href="#tab3" data-toggle="tab">申请材料</a></li>
        <li><a href="#tab4" data-toggle="tab">办理流程</a></li>
        <li><a href="#tab5" data-toggle="tab">办理流程图</a></li>
        <li><a href="#tab6" data-toggle="tab">办理地点</a></li>
      </ul>
      <div id="myTabContent" class="tab-content" aria-hidden="false">
        <div class="tab-pane fade p-20 in active" id="tab1">
          <p>${catalogue.cata_establish}</p>
        </div>
        <div class="tab-pane fade p-20" id="tab2">
          <p>${catalogue.cata_hand_term}</p>
        </div>
        <div class="tab-pane fade p-20" id="tab3">
          <p>${catalogue.cata_app_material}</p>
            <!--资料下载-->
         <ul class="">
             <c:forEach items="${detailList}" var="l">
               <li>

                 <div class=""></div>
                 <div class="">
                   <table class="table table-condensed" style="margin: 0">
                     <tbody>
                     <c:forEach items="${l.attachList}" var="attch">
                       <tr>
                         <td width="85%">${attch.cata_attch_name}</td>
                         <td>
                           <a href="javascript:void(0)" class="btn btn-primary btn-sm" onclick="downLoadFile('${attch.cata_attch_file_location}','${attch.cata_attch_name}')">下载</a>
                           <!--
                           <c:choose>
                             <c:when test="${attch.cata_attch_filetype == 'jpg' || attch.cata_attch_filetype == 'png'}">
                               <a href="<%=fileModule%>${attch.cata_attch_file_location}" class="btn btn-primary btn-sm" target="_blank">预览</a>
                             </c:when>
                             <c:when test="${attch.cata_attch_filetype == 'txt' || attch.cata_attch_filetype == 'pdf'}">
                               <a href="<%=fileModule%>${attch.cata_attch_file_location}" class="btn btn-primary btn-sm" target="_blank">预览</a>
                             </c:when>
                             <c:otherwise>
                               <a href="http://view.officeapps.live.com/op/view.aspx?src=<%=websiteDomain%>${attch.cata_attch_file_location}" target="_blank" class="btn btn-primary btn-sm">预览</a>
                             </c:otherwise>
                           </c:choose>
                           -->
                         </td>
                       </tr>
                     </c:forEach>
                     </tbody>
                   </table>
                 </div>
               </li>
             </c:forEach>
           </ul>
           <!--资料下载-->
        </div>
        <div class="tab-pane fade p-20" id="tab4">
          <p>${catalogue.cata_process}</p>
          <ul class="cbp_tmtimeline">
            <c:forEach items="${detailList}" var="l">
              <li>
                <div class="cbp_tmtime">${l.cata_detail_name}</div>
                <div class="cbp_tmicon"></div>
                <div class="cbp_tmlabel">
                  <p>${l.cata_detail_desc}</p>
                </div>
              </li>
            </c:forEach>
          </ul>
        </div>
        <div class="tab-pane fade p-20" id="tab5">
          <img src="<%=fileModule%>${catalogue.cata_process_pic}" alt="" class="center-block img-responsive">
        </div>
        <div class="tab-pane fade p-20" id="tab6">
          <div class="row">
            <c:if test="${catalogue.department_longitude != null && catalogue.department_latitude != null}">
              <div class="col-xs-8">
                <div id="allmap" style="height: 400px;" onclick="fullScreen()"></div>
              </div>
            </c:if>
            <div class="col-xs-4">
              <div class="panel panel-default">
                <div class="panel-heading">交通方式</div>
                <div class="panel-body">${catalogue.department_route}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </c:if>
  </div>
</div>

<tags:CataFootTag/>

<script src="<%=staticPath%>/webjars/js/jQuery/all/jquery.js"></script>
<script src="<%=staticPath%>/webjars/js/jQuery/layer/layer.js"></script>
<script src="<%=staticPath%>/webjars/js/rc.all-1.0.js"></script>
<script src="<%=staticPath%>/webjars/js/bootstrap.min.js"></script>

<script src="http://api.map.baidu.com/api?v=2.0&ak=9tV3N13NBB4XKuGcbdLZANdLI7V7dVpS"></script>

<script >

  var lon = '${catalogue.department_longitude}';
  var lat = '${catalogue.department_latitude}';
  var department = '${catalogue.department_name}';
  var address = '${catalogue.department_address}';
  if (lon && lat) {
    var point = new BMap.Point(Number(lon), Number(lat));
    initMap(point);
  }

  function initMap(point) {
    // 百度地图API功能
    var map = new BMap.Map("allmap");

    var marker = new BMap.Marker(point);

    map.centerAndZoom(point, 15);  // 初始化地图,设置中心点坐标和地图级别

    map.addOverlay(marker);             // 将标注添加到地图中

    //添加地图类型控件
    map.addControl(new BMap.MapTypeControl({
      mapTypes:[ BMAP_NORMAL_MAP, BMAP_HYBRID_MAP ]
    }));
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

    // 比例尺
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件

    map.addControl(top_left_control);
    map.addControl(top_left_navigation);


    var infoWindow = new BMap.InfoWindow(address);  // 创建信息窗口对象
    marker.addEventListener('click', function(){
      map.openInfoWindow(infoWindow,point); //开启信息窗口
    });

    var opts = {
      position : point,    // 指定文本标注所在的地理位置
      offset   : new BMap.Size(15, -30)    //设置文本偏移量
    };
    var label = new BMap.Label(department, opts);  // 创建文本标注对象
    map.addOverlay(label);

    // 覆盖区域图层测试
    map.addTileLayer(new BMap.PanoramaCoverageLayer());

    var stCtrl = new BMap.PanoramaControl(); //构造全景控件
    stCtrl.setOffset(new BMap.Size(10, 40));
    map.addControl(stCtrl);//添加全景控件
  }

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
  
  function fullScreen() {
    layer.open({
      type: 2,
      title: false,
      shadeClose: true,
      shade: 0.2,
      area: ['80%', '80%'],
      content: '<%=path%>/catalogue/map?lon=' + lon + '&lat=' + lat + '&department=' + department + '&address=' + address
    });
  }
  function downLoadFile(path,name) {
      window.location.href=encodeURI("<%=fileModule%>"+path+"?attname="+name);
  }
</script>

</body>

</html>