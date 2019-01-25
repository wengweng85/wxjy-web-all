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
  <link href="<%=staticPath%>/resource/catalogue/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="<%=staticPath%>/resource/catalogue/css/index.css" rel="stylesheet">
  <title>网上办事大厅</title>
  <style>
    html, body {
      height: 100%;
    }
    #allmap {
      height: 100%;
    }
    #allmap label {
      max-width: inherit;
    }
  </style>
</head>

<body>

<div id="allmap"></div>

<script src="<%=staticPath%>/resource/js/jQuery/all/jquery.js"></script>
<script src="<%=staticPath%>/webjars/js/jQuery/layer/layer.js"></script>

<script src="http://api.map.baidu.com/api?v=2.0&ak=9tV3N13NBB4XKuGcbdLZANdLI7V7dVpS"></script>

<script>

  var lon = '${lon}';
  var lat = '${lat}';
  var department = '${department}';
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


    var infoWindow = new BMap.InfoWindow('${address}');  // 创建信息窗口对象
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


  function fullScreen() {
    $('#allmap').toggleClass('full-screen');
  }

</script>

</body>

</html>