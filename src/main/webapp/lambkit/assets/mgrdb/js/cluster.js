    var map=null; //地图对象
  	var pointLayer,pointLayerSource=null; //矢量点图层pointLayer和图层的数据源pointLayerSource，用于显示渲染的点
  	var polygonLayer,polygonLayerSource=null; //矢量面图层polygonLayer和图层的数据源polygonLayerSource，用于显示渲染的面
  	var pointClusterLayer,pointClusterLayerSource=null; //矢量点聚类图层pointClusterLayer和图层的数据源pointClusterLayerSource，用于显示渲染的点
  	//渲染颜色列表
  	var colorList=new Array();
  	colorList.push('#FFAE00');
  	colorList.push('#FF2436');
  	colorList.push('#FF008C');
  	colorList.push('#0000FF');
  	//根据地物编号、地物总数和分类数获取渲染颜色
  	function getColorByIndex(featureIndex,featureNum,classNum)
  	{
  		//获取每个分类的地物个数
  		var classFeatureNum= Math.floor(featureNum/classNum);
  		//获取当前地物所在的分类
  		var classIndex = Math.floor(featureIndex/classFeatureNum);
  		if(classIndex>=classNum)
  		{
  			classIndex=classNum-1;
  		}
  		
  		return colorList[classIndex];
  	}

  	function initMap() {
  		 //本地geoserver全国县城切片图层
  	      var format = 'image/png';
  		  var chinaCounty = new ol.layer.Image({
  	        source: new ol.source.ImageWMS({
  	          ratio: 1,
  	          url: geoServerUrl,
  	          params: {'FORMAT': format,
  	                   'VERSION': '1.1.1',  
  	                LAYERS: 'zhongyao:china_county_3857',
  	                STYLES: '',
  	          }
  	        })
  	      });
  		  chinaCounty.setOpacity(0.5);
  		  
  		  //地图中心点坐标
  		  var coor = ol.proj.transform([116.40969, 39.89945], 'EPSG:4326', 'EPSG:3857');	
  	 
  		  //地图对象
  		  map = new ol.Map({
  	        target: document.getElementById('map'),
  	        layers: [
  	             //天地图适量底图    
  	             new ol.layer.Tile({
  	                     source:new ol.source.XYZ({
  	                             url:"http://t2.tianditu.com/DataServer?T=vec_w&x={x}&y={y}&l={z}"
  	                     })
  	             }),   
  	             //天地图标注图层
  	             new ol.layer.Tile({
  	                     source:new ol.source.XYZ({
  	                             url: "http://t2.tianditu.com/DataServer?T=cva_w&x={x}&y={y}&l={z}"
  	                     })        
  	             }),
  	             //本地geoserver全国县城切片图层
  	             chinaCounty
  	        ],
  	        view: new ol.View({
  	           center:coor,
  	           zoom:5
  	        })
  	      });
  			
  			//初始化polygonLayer并添加到地图中			
  			polygonLayerSource = new ol.source.Vector({});
  			var polygonLayer = new ol.layer.Vector({
  			   source: polygonLayerSource
  			});
  			map.addLayer(polygonLayer);
  			
  		    //初始化pointLayer并添加到地图中			
  			pointLayerSource = new ol.source.Vector({});
  			var pointLayer = new ol.layer.Vector({
  			   source: pointLayerSource
  			});
  			map.addLayer(pointLayer);
  			
  			//统计数据获取
  			getData();
  			
  			
  			//单击查询属性-------------------------------------------------------
  			//为map定义click事件，同样是事件，该事件依赖于map，而mousemove则和map无关
  			 map.on('click', function(evt) {
  			 var pixel = map.getEventPixel(evt.originalEvent);
  			 //alert('屏幕坐标'+pixel);
  			 var coordinate = map.getEventCoordinate(evt.originalEvent);
  			// alert('地图坐标'+coordinate);
  			 
  			 var hit =null;
  			 hit = map.forEachFeatureAtPixel(pixel, function(feature, pointLayer) {
  			    return feature;
  			  });
  			  if(hit!=null)
  			  {
  			     alert("地物为："+hit.getId());
  			  }

  			/*  var features = pointLayerSource.getFeaturesAtCoordinate(coordinate);
  			 alert('点击处地物数为：'+features.length); */
  			 
  			});
  	}

  	//点击查询

  	//统计数据获取
  	var staticticData;
  	function getData()
  	{
  		  $.ajax({
  				type : "POST",
  				url : "ajaxMapList",
  				dataType : "json",
  				success : function(msg) {
  					staticticData=  msg.list;
  					addMarker1();
  				},
  				error : function() {
  					alert("调用出现错误，更新失败");
  				}
  			});	
  	}

  	//地图清空
  	function mapClear()
  	{
  		//清空pointLayerSource和polygonLayerSource
  		pointLayerSource.clear();
  		polygonLayerSource.clear();
  		source.clear();
  	}

  	//数据点绘制-------------------------------------------------------
  	function  addMarker1()
  	{
  		drawPoints();	
  	}
  	function drawPoints()
  	{
  		//获取点的半径和文本大小
  		var radius = getRadius();
  		var textSize = getTextSize();

  		var length = staticticData.length;
  		var wktFormatter = new ol.format.WKT(); 
  		
  		for (var i = 0; i < length; i++) {
  			//获取代表区域总数
  			var textVal = staticticData[i].DBQYZS;
  			//获取渲染颜色
  			var color=getColorByIndex(textVal,50,4);
  			//点渲染样式
  			var ptstyle =new ol.style.Style({
  			      text:new ol.style.Text({
  			      fill: new ol.style.Fill({color: '#7A7A7A'}),
  				  text:textVal,
  				  scale:textSize
  				    }),
  				    image: new ol.style.Circle({
  				        radius: radius,
  				        fill: new ol.style.Fill({
  				          color: color
  				        }),
  				        stroke: new ol.style.Stroke
  				        ({color: 'blue', width: 1})
  			   })
  			});
  			//生成点并添加到地图上
  			var pt = wktFormatter.readFeature(staticticData[i].CENTERPOINT);
  			pt.setStyle(ptstyle);
  			pt.setId(staticticData[i].ORGCODE);
  			pointLayerSource.addFeature(pt);
  		}
  		}

  	//根据分辨率获取点的渲染半径
  	function getRadius()
  	{
  		var zoom = map.getView().getZoom();
  		var diff = zoom-3;	
  		return 5+diff*2;
  	}
  	//根据分辨率获取点的本文大小
  	function getTextSize()
  	{
  		var zoom = map.getView().getZoom();
  		var diff = zoom-4;
  		return 1+diff*0.2;
  	}

  	//多边形绘制------------------------------------------------------------
  	function addPolygon1()
  	{
  		drawPolygons();
  	}

  	function drawPolygons()
  	{
  		var length = staticticData.length;
  		var wktFormatter = new ol.format.WKT();

  		for (var i = 0; i < length; i++) {
  			var polygon = wktFormatter.readFeature(staticticData[i].POINTS);
  			//获取渲染颜色
  			var color=getColorByIndex(staticticData[i].DBQYMJ,4000,4);
  			
  			var polygonStyle =new ol.style.Style({
  				fill: new ol.style.Fill({ //矢量图层填充颜色，以及透明度
  				    color: color
  				  }),
  				  stroke: new ol.style.Stroke({ //边界样式
  				    color: '#319FD3',
  				    width: 1
  				  })
  			});
  			//生成面并添加到地图上
  			polygon.setStyle(polygonStyle);
  			polygonLayerSource.addFeature(polygon);
  		}
  		
  	}
  	
  	$(document).ready(
  			function() {
  				initMap();
  			});