var bcenter = false;

//window.onload = loadJScript;  //异步加载地图

//百度地图API功能
function loadJScript(cbak) {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/api?v=2.0&ak=ShISlKhdEZ4PSVpAaScOIAdt&callback=" + cbak;
	document.body.appendChild(script);
}

// 创建和初始化地图函数：
function initMap(lon, lat, zoom) {
	// 创建地图实例，关闭底图可点功能, 设置地图允许的最小级别
	window.map = new BMap.Map("allmap", {
		minZoom : 4,
		enableMapClick : false
	});
	map.setMapStyle({
		  styleJson:[
		             {
		                    "featureType": "road",
		                    "elementType": "all",
		                    "stylers": {
		                              "lightness": 20
		                    }
		          },
		          {
		                    "featureType": "highway",
		                    "elementType": "labels",
		                    "stylers": {
		                              "color": "#f49935"
		                    }
		          },
		          {
		                    "featureType": "railway",
		                    "elementType": "all",
		                    "stylers": {
		                              "visibility": "off"
		                    }
		          },
		          {
		                    "featureType": "local",
		                    "elementType": "labels",
		                    "stylers": {
		                              "visibility": "off"
		                    }
		          },
		          {
		                    "featureType": "water",
		                    "elementType": "all",
		                    "stylers": {
		                              "color": "#d1e5ff"
		                    }
		          },
		          {
		                    "featureType": "highway",
		                    "elementType": "labels",
		                    "stylers": {
		                              "visibility": "off"
		                    }
		          }
		]
		});
	// 开启鼠标滚轮缩放
	map.enableScrollWheelZoom();
	// 添加默认缩放平移控件
	map.addControl(new BMap.NavigationControl());
	// 初始化地图,设置中心点坐标和地图级别
	map.centerAndZoom(new BMap.Point(lon, lat), zoom);
	map.addControl(new BMap.MapTypeControl());
	/*
	 * 	var mapType1 = new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]});
	var mapType2 = new BMap.MapTypeControl({anchor: BMAP_ANCHOR_TOP_LEFT});
	 */
	
	map.addControl(new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT}));
	bcenter = true;
}

// 创建和初始化地图函数：
function initMapOfScreen(lon, lat, zoom) {
	// 创建地图实例，关闭底图可点功能, 设置地图允许的最小级别
	window.map = new BMap.Map("allmap", {
		minZoom : 4,
		enableMapClick : false
	});
	map.setMapStyle({
		  styleJson: mapBlueBlackStyleJson
		});
	// 开启鼠标滚轮缩放
	map.enableScrollWheelZoom();
	// 添加默认缩放平移控件
	//map.addControl(new BMap.NavigationControl());
	// 初始化地图,设置中心点坐标和地图级别
	map.centerAndZoom(new BMap.Point(lon, lat), zoom);
	//map.addControl(new BMap.MapTypeControl());
	/*
	 * 	var mapType1 = new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]});
	var mapType2 = new BMap.MapTypeControl({anchor: BMAP_ANCHOR_TOP_LEFT});
	 */
	
	//map.addControl(new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT}));
	bcenter = true;
}

function initMaker() {
	window.markerClusterer = new BMapLib.MarkerClusterer(map, {});
}

function mapBounds() {
	//获取地图显示范围
	return map.getBounds();
	//var bs = map.getBounds();   //获取可视区域
	//var bssw = bs.getSouthWest();   //可视区域左下角
	//var bsne = bs.getNorthEast();   //可视区域右上角
}

var mapBlueBlackStyleJson = [
          {
                    "featureType": "water",
                    "elementType": "all",
                    "stylers": {
                              "color": "#021019"
                    }
          },
          {
                    "featureType": "highway",
                    "elementType": "geometry.fill",
                    "stylers": {
                              "color": "#000000"
                    }
          },
          {
                    "featureType": "highway",
                    "elementType": "geometry.stroke",
                    "stylers": {
                              "color": "#147a92"
                    }
          },
          {
                    "featureType": "arterial",
                    "elementType": "geometry.fill",
                    "stylers": {
                              "color": "#000000"
                    }
          },
          {
                    "featureType": "arterial",
                    "elementType": "geometry.stroke",
                    "stylers": {
                              "color": "#0b3d51"
                    }
          },
          {
                    "featureType": "local",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#000000"
                    }
          },
          {
                    "featureType": "land",
                    "elementType": "all",
                    "stylers": {
                              "color": "#08304b"
                    }
          },
          {
                    "featureType": "railway",
                    "elementType": "geometry.fill",
                    "stylers": {
                              "color": "#000000"
                    }
          },
          {
                    "featureType": "railway",
                    "elementType": "geometry.stroke",
                    "stylers": {
                              "color": "#08304b"
                    }
          },
          {
                    "featureType": "subway",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -70
                    }
          },
          {
                    "featureType": "building",
                    "elementType": "geometry.fill",
                    "stylers": {
                              "color": "#000000"
                    }
          },
          {
                    "featureType": "all",
                    "elementType": "labels.text.fill",
                    "stylers": {
                              "color": "#857f7f"
                    }
          },
          {
                    "featureType": "all",
                    "elementType": "labels.text.stroke",
                    "stylers": {
                              "color": "#000000"
                    }
          },
          {
                    "featureType": "building",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#022338"
                    }
          },
          {
                    "featureType": "green",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#062032"
                    }
          },
          {
                    "featureType": "boundary",
                    "elementType": "all",
                    "stylers": {
                              "color": "#1e1c1c"
                    }
          },
          {
                    "featureType": "manmade",
                    "elementType": "all",
                    "stylers": {
                              "color": "#022338"
                    }
          }
];

var mapBlackStyleJson = [
          {
                    "featureType": "land",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#212121"
                    }
          },
          {
                    "featureType": "building",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#2b2b2b"
                    }
          },
          {
                    "featureType": "highway",
                    "elementType": "all",
                    "stylers": {
                              "lightness": -42,
                              "saturation": -91
                    }
          },
          {
                    "featureType": "arterial",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -77,
                              "saturation": -94
                    }
          },
          {
                    "featureType": "green",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#1b1b1b"
                    }
          },
          {
                    "featureType": "water",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#181818"
                    }
          },
          {
                    "featureType": "subway",
                    "elementType": "geometry.stroke",
                    "stylers": {
                              "color": "#181818"
                    }
          },
          {
                    "featureType": "railway",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -52
                    }
          },
          {
                    "featureType": "all",
                    "elementType": "labels.text.stroke",
                    "stylers": {
                              "color": "#313131"
                    }
          },
          {
                    "featureType": "all",
                    "elementType": "labels.text.fill",
                    "stylers": {
                              "color": "#8b8787"
                    }
          },
          {
                    "featureType": "manmade",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#1b1b1b"
                    }
          },
          {
                    "featureType": "local",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -75,
                              "saturation": -91
                    }
          },
          {
                    "featureType": "subway",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -65
                    }
          },
          {
                    "featureType": "railway",
                    "elementType": "all",
                    "stylers": {
                              "lightness": -40
                    }
          },
          {
                    "featureType": "boundary",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#8b8787",
                              "weight": "1",
                              "lightness": -29
                    }
          }
];