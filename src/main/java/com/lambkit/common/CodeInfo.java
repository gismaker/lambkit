package com.lambkit.common;

/**
 * 接口code代码表
 * @author yangyong
 *
 */
public class CodeInfo {

	public String code;
	
	public String title;
	
	public String remark;
	
	public CodeInfo(String code, String title, String remark) {
		this.code = code;
		this.title = title;
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
