package com.lambkit.common;

/**
 * 参数信息
 * @author yangyong
 *
 */
public class ParamInfo {

	/**
	 * 名称
	 */
	public String name;
	
	/**
	 * 别名，含义
	 */
	public String title;
	
	/**
	 * 类型
	 */
	public String type;
	
//	/**
//	 * 单位
//	 */
//	public String unit;
	
	/**
	 * 备注
	 */
	public String remark;
	
	/**
	 * 约束，非空
	 */
	public String required;
	
	public Object ext;
	
	public ParamInfo(String name, String title, String remark) {
		this.name = name;
		this.title = title;
		this.remark = remark;
	}
	
	public ParamInfo(String name, String title, String type, String remark) {
		this.name = name;
		this.title = title;
		this.type = type;
		this.remark = remark;
	}
	
	public ParamInfo(String name, String title, String type, String required, String remark) {
		this.name = name;
		this.title = title;
		this.type = type;
		this.required = required;
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	public String getUnit() {
//		return unit;
//	}
//
//	public void setUnit(String unit) {
//		this.unit = unit;
//	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public Object getExt() {
		return ext;
	}

	public void setExt(Object ext) {
		this.ext = ext;
	}
	
	
}
