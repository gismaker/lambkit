package com.lambkit.core.api.json;

public class ApiJsonManager {

	private static ApiJsonManager manage = null;
	
	public static ApiJsonManager me() {
		if(manage==null) {
			manage = new ApiJsonManager();
		}
		return manage;
	}
	
	//private Map<String, >
}
