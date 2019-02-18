
var lastOrgName='none';
var currentPolygonMap = new HashMap();

function putBoundary(name, badd) {
	var bdary = new BMap.Boundary();
	bdary.get(name, function(rs){
		for (var i = 0; i < rs.boundaries.length; i++) {
			var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#FF0000",fillColor:""}); //建立多边形覆盖物
			currentPolygonMap.put(name, ply);
			if(badd) {
				lastOrgName = name;
				map.addOverlay(ply);
			}
		}
	});
}

function refreshBoundary(name) {
	clearBoundary();
	lastOrgName = name;
	if(name=="none") return;
	else if(name=="all") {
		currentPolygonMap.each(function(key, ply){
			if(ply!=undefined || ply!=null) map.addOverlay(ply);
		});
	}
	else {
		var ply = currentPolygonMap.get(name);
		//console.log(name, ply);
		if(ply!=undefined || ply!=null) map.addOverlay(ply);
		else putBoundary(name, true);
	}
}

function clearBoundary() {
	if(lastOrgName=="none") {
		return;
	}
	else if(lastOrgName=="all") {
		currentPolygonMap.each(function(key, value){
			map.removeOverlay(value);
		});
	}
	else {
		map.removeOverlay(currentPolygonMap.get(lastOrgName));
	}
}

