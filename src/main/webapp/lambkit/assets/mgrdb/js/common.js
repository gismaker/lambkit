/**系统配置start**/
excelmaxnum = 0;
/**系统配置end**/

$(function(){
	/*
	//表格行，鼠标放上去变色
	$(".tr:odd").css("background", "#FFFCEA");
	$(".tr:odd").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#FFFCEA");
		});
	});
	$(".tr:even").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#fff");
		});
	}); */

	/*ie6,7下拉框美化*/
    if ( $.browser.msie ){
    	if($.browser.version == '7.0' || $.browser.version == '6.0'){
    		$('.select').each(function(i){
			   $(this).parents('.select_border,.select_containers').width($(this).width()+5); 
			 });
    		
    	}
    }
});

//页码设置，并提交表单
function paginate(pagesize, pagenumber) {
	$("input[name=numPerPage]").val(pagesize);
	$("input[name=pageNum]").val(pagenumber);
	var form = document.getElementById("mysearchform");
	form.submit();
}

//提交表单
function formaction() {
	var form = document.getElementById("mysearchform");
	form.submit();
}

//跳转
function navto(url) {
	//location.href = url+"?"+$("#mysearchform").serialize(); 
	$("#mysearchform").attr("action", url);
	formaction();
}

//顶层页面跳转
function navtopto(url) {
	window.open(url+"?"+$("#mysearchform").serialize()); 
}

//重置表单
function formreset() {
	var form = document.getElementById("mysearchform");
	form.reset();
}

///----------------------------------
/// 搜索对话框
///----------------------------------
function searchMapDialog() {
    //console.log("searchmapdata");
    var ww = $(window).width();
    if(ww < 820) {
  	  	tipsWindown("搜索","id:searchmapdata", ww-60, $(window).height()-140,"false","","false","dlgsmd");
        } else {
	  		tipsWindown("搜索","id:searchmapdata",800,180,"false","","false","dlgsmd");
        }
}

function searchMapData() {
	  $(".windown-dlgsmd #mysearchform input").each(function(index,element){
		  if(isValue($(this).val())) {
		  	$("#searchmapdata #mysearchform input").eq(index).attr("value", $(this).val());
		  }
  	}); 
	  $(".windown-dlgsmd #mysearchform select").each(function(index,element){
		  if(isValue($(this).val())) {
		  	$("#searchmapdata #mysearchform select[name='"+this.name+"'] option[value='"+$(this).val()+"']").attr("selected", "selected");
  	  }
  	}); 
	  reloadSearchData(".windown-dlgsmd ");
	  closeWindown();
}

function isValue(keyVal) {
	  if(keyVal==undefined || keyVal=="" || keyVal==null){  
  	  return false;
	  }else{
		  return true;
	  }
}

///----------------------------------
/// 状态设置
///----------------------------------

function listpage2post(url, param, ckey) {
	$.post(url, param, function(data) {
		if (data) {
			alert("操作完成！");
			if(ckey==null || ckey=='') window.location.reload();
			else window.location.href = ckey + "/list?pageNum=1&numPerPage="+ $("input[name=numPerPage]").val();
		} else {
			alert("操作失败！");
		}
	});
}

function check(ckey, id, status, refresh) {
	if(refresh) listpage2post(ckey+"/check", {id:id, s:status}, null);
	else listpage2post(ckey+"/check", {id:id, s:status}, ckey);
}

function check_pl(ckey, refresh) {
	if(refresh) listpage2post(ckey+"check_pl", $("#myform").serializeArray(), null);    	
	else listpage2post(ckey+"check_pl", $("#myform").serializeArray(), ckey);  
}

function del(ckey, id, status, refresh) {
	if(refresh) listpage2post(ckey+"/delete", {id:id, s:status}, null);
	else  listpage2post(ckey+"/delete", {id:id, s:status}, ckey);
}

function del_pl(ckey, refresh) {
	if(refresh) listpage2post(ckey+"delete_pl", $("#myform").serializeArray(), null);    	  
	else  listpage2post(ckey+"delete_pl", $("#myform").serializeArray(), ckey);    
}


function savePageType(url, type) {
	$.post(url, {pagetype:type}, function(data) {
		if (data) {
			alert("操作完成！");
		} else {
			alert("操作失败！");
		}
	});
}

function imageshow(param) {
	layer.use('extend/layer.ext.js');
	/*
	var mt = "";
	if(m=='pid') mt = "id";
	if(m=='code') mt = "code";
	var url = m=="demo" ? '/photo?m=demo' : '/photo?m=' + m + "&" + mt + "=" + param;
	$.getJSON(url, function(json){
	    layer.photos({
	        photos: json
	    });
	});
	*/
	//无真实数据，暂用测试数据
	$.getJSON('/photo?m=demo', function(json){
	    layer.photos({
	        photos: json
	    });
	});
}

function imageshow(m, param) {
	layer.use('extend/layer.ext.js');
	//无真实数据，暂用测试数据
	$.getJSON('/photo?m=demo', function(json){
	    layer.photos({
	        photos: json
	    });
	});
	/*
	var mt = "";
	if(m=='pid') mt = "id";
	if(m=='code') mt = "code";
	var url = m=="demo" ? '/photo?m=demo' : '/photo?m=' + m + "&" + mt + "=" + param;
	$.getJSON(url, function(json){
	    layer.photos({
	        photos: json
	    });
	});
	*/
	/*
	layer.open({
	    type: 2,
	    area: ['452px', '268px'],
	    fix: false, //不固定
	    maxmin: false,
	    content: '/app/public/imageshow.html?' + param
	});
	*/
}

/*********
 * ECharts
 **********/
function echartsAxisLabel(option, width){
	if(option && option.toolbox && option.toolbox.feature && option.toolbox.feature.dataView && option.toolbox.feature.dataView.show) 
		option.toolbox.feature.dataView.show = false;
	if(option.series[0].type=='bar' || option.series[0].type=='line') {
		option.xAxis[0].axisLabel={interval:0};
		if(option.xAxis[0].data.length>15) {
			for(var i=1; i<option.xAxis[0].data.length; i+=2) {
				option.xAxis[0].data[i] = '\n' + option.xAxis[0].data[i];
			}
		}
		option.grid = {
				"x": 120,
				"x2": 80,
				"y": 80,
				"y2": 60
			}
	}
	return option;
}