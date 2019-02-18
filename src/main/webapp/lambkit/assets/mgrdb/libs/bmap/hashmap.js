function HashMap() {
	var size = 0;
	var entry = new Object();
	
	this.put = function (key, value) {
		entry[key] = value;
		size++;
	};
	
	this.putAll = function (map) {
		if (typeof map == "object" && !map.sort) {
			for (var key in map) {
				this.put(key, map[key]);
			}
		} else {
			throw "输入类型不正确，必须是HashMap类型！";
		}
	};
	
	this.get = function (key) {
		return entry[key];
	};
	
	this.remove = function (key) {
		if (size == 0)
			return;
		delete entry[key];
		size--;
	};
	
	this.containsKey = function (key) {
		if (entry[key]) {
			return true;
		}
		return false;
	};
	
	this.containsValue = function (value) {
		for (var key in entry) {
			if (entry[key] == value) {
				return true;
			}
		}
		return false;
	};
	
	this.clear = function () {
		entry = new Object();
		size = 0;
	};
	
	this.isEmpty = function () {
		return size == 0;
	};
	
	this.size = function () {
		return size;
	};
	
	this.keySet = function () {
		var keys = new Array();
		for (var key in entry) {
			keys.push(key);
		}
		return keys;
	};
	
	this.entrySet = function () {
		var entrys = new Array();
		for (var key in entry) {
			var et = new Object();
			et[key] = entry[key];
			entrys.push(et);
		}
		return entrys;
	};
	
	this.values = function () {
		var values = new Array();
		for (var key in entry) {
			values.push(entry[key]);
		}
		return values;
	};
	
	this.each = function (cb) {
		for (var key in entry) {
			cb.call(this, key, entry[key]);
		}
	};
	
	this.toString = function () {
		return obj2str(entry);
	};
	
	function obj2str(o) {
		var r = [];
		if (typeof o == "string")
			return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
		if (typeof o == "object") {
			for (var i in o)
				r.push("\"" + i + "\":" + obj2str(o[i]));
			if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {
				r.push("toString:" + o.toString.toString());
			}
			r = "{" + r.join() + "}";
			return r;
		}
		return o.toString();
	}
}