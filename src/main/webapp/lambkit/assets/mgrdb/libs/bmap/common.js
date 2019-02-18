
//var gdmarker = null;
//默认鼠标样式
var defaultCursor = "hand";
//创建marker
window.addMarker = function(data, tbid, type, tbtype) {
	if(tbtype===0) map.clearOverlays();
	if(tbtype===0) markerClusterer.clearMarkers();
	var bJH = data.length > 1000 ? true : false;
	//var nstep = bJH ? Math.floor(data.length / 100) : 1;
	//if(data.length > 1000) type="greatedata";
	var markers = [];
	for ( var i = 0; i < data.length; i++) {
		var json = data[i];
		var p0 = json.lon;
		var p1 = json.lat;
		if(json.lon==undefined || json.lon=="" || json.lon==null ||
				json.lat==undefined || json.lat=="" || json.lat==null){  
			if(bJH) continue;
			if(type=="greatedata") continue;
			p0 = 117.854781 + 0.0001 * i/100;
			p1 = 39.055557 + 0.0001 * i%100;
		}
		var point = new BMap.Point(p0, p1);
		if(type=="greatedata") {
			markers.push(point);
		} else {
			var marker;
			/*
			//icon不同
			var index = getIndex(json.orgcode);
			if(index > -1) {
				var iconImg = createIcon(index);
				marker = new BMap.Marker(point, {icon : iconImg});
			} else {
				marker = new BMap.Marker(point);
			}*/
			marker = new BMap.Marker(point);
			/*
			var iconImg = new BMap.Icon("http://api.map.baidu.com/img/markers.png",// /static/icon/map_marker.png
					new BMap.Size(22, 22));*/
			//var marker = new BMap.Marker(point, {icon : iconImg});
			//var iw = createInfoWindow(i);
			/*
			var label = new BMap.Label(json.name, {
				"offset" : new BMap.Size(22, 22)
			});
			label.setStyle({
				borderColor : "#808080",
				color : "#333",
				cursor : "pointer"
			});
			marker.setLabel(label);
			*/
			if(type=="clusterer") {
				markers.push(marker);
			} else {
				map.addOverlay(marker);
			}

			(function() {
				var _json = json;
				//var _iw = createInfoWindow(_json);
				var _marker = marker;
				_marker.addEventListener("click", function() {
					$.post(web_ctx + "/suc/pointdata",{id:_json.id},function(data){
				        if(data['flag']){
				        	infowin.setContent(getInfo(data['model']));
				        	_marker.openInfoWindow(infowin);
				        }
					});
					//this.enableDragging();
				});
				/*
				_marker.addEventListener("dragend", function() {
					var vurl = getActionKey(_json, tbid);
					vurl += "updateLonlat?id="+_json.id+"&lon="+this.point.lng+"&lat="+this.point.lat;
					$.post(vurl);
				});
				_marker.addEventListener("infowindowclose", function() {
					this.disableDragging();
				});*//*
				_iw.addEventListener("open", function() {
					_marker.getLabel().hide();
				});
				_iw.addEventListener("close", function() {
					_marker.getLabel().show();
				});
				label.addEventListener("click", function() {
					_marker.openInfoWindow(_iw);
				});*/
				/*
				 * if(!!json.isOpen){ label.hide(); _marker.openInfoWindow(_iw); }
				 */
			})();
		}//not great data
	}//for
	if(type=="greatedata") {
		if(tbtype===0) {
			var options = {
				     size: BMAP_POINT_SIZE_SMALL,
				     shape: BMAP_POINT_SHAPE_WATERDROP,
				     color: '#d340c3'
				};
				var pointCollection = createPointCollection(markers, options);
				map.addOverlay(pointCollection);  // 添加Overlay
		} else {
			//var vcolor = tbtype < 18 ? '#d340c3' : '#0000FF';
			//var vcolor = '#'+('00000'+(Math.random()*0x1000000<<0).toString(16)).slice(-6);
			var options = {
				     size: BMAP_POINT_SIZE_NORMAL,
				     shape: BMAP_POINT_SHAPE_CIRCLE,
				     color: $("#circle_" + tbtype).css("background-color")
				};
				var pointCollection = createPointCollection(markers, options);
				map.addOverlay(pointCollection);  // 添加Overlay
		}
	} else if(type=="clusterer") {
		//最简单的用法，生成一个marker数组，然后调用markerClusterer类即可。
		markerClusterer.addMarkers(markers);
		markerClusterer.setMaxZoom(17);
		/*
		map.addEventListener("zoomend", function(){
			//console.log(map.getZoom());
		});*/
	}
};

function createPointCollection(markers, options) {
	var pointCollection = new BMap.PointCollection(markers, options);  // 初始化PointCollection
	
	pointCollection.addEventListener('mouseover', function (e) {
		map.setDefaultCursor('pointer');
		/*
		map.removeOverlay(gdmarker);
		gdmarker = null;
		gdmarker = new BMap.Marker(e.point);
		map.addOverlay(gdmarker);
		(function() {
			gdmarker.addEventListener("click", function() {
				$.post(web_ctx + "/suc/pointdata",{lon:e.point.lng,lat:e.point.lat},function(data){
			        if(data['flag']){
			        	infowin.setContent(getInfo(data['model']));
			        	gdmarker.openInfoWindow(infowin);
			        } else {
			        	alert("搜索失败！");
			        }
				});
			});
		})();
		*/
	});
	
	pointCollection.addEventListener('mouseout', function (e) {
		map.setDefaultCursor(defaultCursor);
	});
	
    pointCollection.addEventListener('click', function (e) {
    	//var tbid = $("select[name='datatb']").val();,datatb:tbid
    	$.post(web_ctx + "/suc/pointdata",{lon:e.point.lng,lat:e.point.lat},function(data){
	        if(data['flag']){
	        	infowin.setContent(getInfo(data['model']));
	        	map.openInfoWindow(infowin, e.point);
	        }
		});
      //alert('单击点的坐标为：' + e.point.lng + ',' + e.point.lat);  // 监听点击事件
    });
    
    return pointCollection;
}

// 创建一个Icon
function createIcon(index) {
	var row = 0;
	if(index > 9) {
		index = index - 10;
		row = 1;
	}
	var icon = new BMap.Icon("/static/widget/mapcoding/makerIcon.png",new BMap.Size(30,40),{
				imageOffset: new BMap.Size(-30*row,-40*index) // 设置图片偏移
			});
	return icon;
}

function getIndex(orgcode) {
	var index = -1;
	if(orgcode==undefined || orgcode=="" || orgcode==null){  
		index = -1;
	} else {
		index = parseInt(orgcode)%100;
		if(index > 23) index -= 9;
		else if(index > 21) index -= 8;
		else if(index > 16) index -= 7;
		else if(index > 6) index -= 3;
	}
	return index;
}

function reset(data, tbid, type, tbtype) {
	defaultCursor = map.getDefaultCursor();
	if (data.length > 0) {
		//var json = data[0];
		//var p0 = json.lon;
		//var p1 = json.lat;
		addMarker(data, tbid, type, tbtype);// 向地图中添加marker
	} else {
		if(tbtype==0) map.clearOverlays();
		if(tbtype==0) markerClusterer.clearMarkers();
	}
}

//创建InfoWindow
var infowin = new BMap.InfoWindow();
function getInfo(json) {
	var editurl = web_ctx + "/suc/edit?id=" + json.id;
	var showurl = web_ctx + "/suc/" + json.id;
	var chtml = "";
	//"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>测试表格名城</h4>";
	chtml += "<a href='"+ showurl +"'><b class='iw_poi_title' title='" + json.tbdwxxmc + "'>" + json.tbdwxxmc
			+ "</b></a>";
	//chtml += "<div class='iw_poi_content'>统计年份：" + json.si_name + "</div>";
	//chtml += "<div class='iw_poi_content'>填报时间：" + long2time(json.modify_time) + "</div>";
	return chtml;
	/*
	var vurl = getActionKey(json);
	var vtbname = getTbName(json);
	var editurl = vurl + "edit?id=" + json.id;
	var showurl = vurl + "show?id=" + json.id;
	var chtml = "";
	//"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>测试表格名城</h4>";
	chtml += "<a href='"+ editurl +"'><b class='iw_poi_title' title='" + json.name + "'>" + json.name
			+ "</b></a>";
	chtml += "<div class='iw_poi_content'>所属表格：" + vtbname + "</div>";
	chtml += "<div class='iw_poi_content'>所在位置：" + json.orgname + "</div>";
	chtml += "<hr/>";
	chtml += "<div><a href=\"javascript:void(0)\" onclick=\"showWryInfo('" + showurl + "')\">详细</a> - ";
	chtml += "<a href='"+ editurl +"' target='_blank'>编辑</a></div>";
	return chtml;
	*/
}

function createInfoWindow(json) {
	var vurl = getActionKey(json);
	var vtbname = getTbName(json);
	var editurl = vurl + "edit?id=" + json.id;
	var showurl = vurl + "show?id=" + json.id;
	var chtml = "";
	//"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>测试表格名城</h4>";
	chtml += "<a href='"+ editurl +"'><b class='iw_poi_title' title='" + json.name + "'>" + json.name
			+ "</b></a>";
	chtml += "<div class='iw_poi_content'>所属表格：" + vtbname + "</div>";
	chtml += "<div class='iw_poi_content'>所在位置：" + json.orgname + "</div>";
	chtml += "<hr/>";
	chtml += "<div><a href=\"javascript:void(0)\" onclick=\"showWryInfo('" + showurl + "')\">详细</a> - ";
	chtml += "<a href='"+ editurl +"'>编辑</a></div>";
	var iw = new BMap.InfoWindow(chtml);
	return iw;
}

function getActionKey(json) {
	var tbn = json.tbname;
	tbn = tbn.replace('_', '/');
	return "/" + tbn + "/";
}

function showWryInfo(url) {
	  tipsWindown("详细信息","iframe:"+url, $(window).width()-60, $(window).height()-140,"true","","true","leotheme");
}


function long2time(ltime) {
	// 将当前时间换成时间格式字符串
var newDate = new Date();
newDate.setTime(ltime);
return newDate.format('yyyy-MM-dd hh:mm:ss');
}

 
Date.prototype.format = function(format) {
    var date = {
       "M+": this.getMonth() + 1,
       "d+": this.getDate(),
       "h+": this.getHours(),
       "m+": this.getMinutes(),
       "s+": this.getSeconds(),
       "q+": Math.floor((this.getMonth() + 3) / 3),
       "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
       format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
       if (new RegExp("(" + k + ")").test(format)) {
           format = format.replace(RegExp.$1, RegExp.$1.length == 1
              ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
       }
    }
    return format;
}
