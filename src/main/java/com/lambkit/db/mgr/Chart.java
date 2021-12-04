/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.db.mgr;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.lambkit.common.util.StringUtils;

/**
 * 图表数据获取
 * @author yangyong
 */
public class Chart {
	private String classify;
	private String serias;
	private String seriasSQL;
	private String operation;
	private List<String> seriasNames;
	private String classifyName;
	private String dataSQL;
	private Object[] sqlParas;
	
	public Chart() {
		seriasNames = new ArrayList<String>();
		seriasSQL = "";
	}
	
	public Chart(boolean isUseMgrTable) {
		seriasNames = new ArrayList<String>();
		seriasSQL = "";
	}
	
	private IFieldDao getFieldDao() {
		return MgrdbManager.me().getService().getFieldDao();
	}
	
	public void setClassifyName(Object tbid, String classifyName) {
		if(tbid==null) return;
		IFieldDao fdao = getFieldDao();
		if(fdao==null) return;
		IField fldm = fdao.findFirstByTbid(tbid, MgrConstants.NONE, "fldname", classifyName);
		classifyName = fldm==null ? classifyName : fldm.getTitle();
		setClassifyName(classifyName);
	}
	
	public void setClassifyName(String tbname, String classifyName) {
		if(StrKit.isBlank(tbname)) return;
		IFieldDao fdao = getFieldDao();
		if(fdao==null) return;
		IField fldm = fdao.findFirstByTbName(tbname, MgrConstants.NONE, "fldname", classifyName);
		classifyName = fldm==null ? classifyName : fldm.getTitle();
		setClassifyName(classifyName);
	}
	
	public void addSerias(Object tbid, String serias_name) {
		if(tbid==null) return;
		IFieldDao fdao = getFieldDao();
		if(fdao==null) return;
		IField fldserias = fdao.findFirstByTbid(tbid, MgrConstants.NONE, "fldname", serias_name);
		serias_name = fldserias!=null ? fldserias.getTitle() : serias_name;
		addSerias(serias_name);
	}
	
	public void addSerias(String tbname, String serias_name) {
		if(StrKit.isBlank(tbname)) return;
		IFieldDao fdao = getFieldDao();
		if(fdao==null) return;
		IField fldserias = fdao.findFirstByTbName(tbname, MgrConstants.NONE, "fldname", serias_name);
		serias_name = fldserias!=null ? fldserias.getTitle() : serias_name;
		addSerias(serias_name);
	}
	
	public void addSerias(String serias_name) {
		seriasNames.add(serias_name);
	}
	
	public String getSelectSQL() {
		String selectSQL = "";
		if(StringUtils.hasText(classify)) selectSQL += " " + classify + " as cls ";
		if(StringUtils.hasText(seriasSQL)) selectSQL += "," + seriasSQL;
		selectSQL += " ";
		return selectSQL;
	}
	
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getSeriasSQL() {
		return seriasSQL;
	}
	public void setSeriasSQL(String seriasSQL) {
		this.seriasSQL = seriasSQL;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public List<String> getSeriasNames() {
		return seriasNames;
	}
	public void setSeriasNames(List<String> seriasNames) {
		this.seriasNames = seriasNames;
	}

	public String getClassifyName() {
		return classifyName;
	}
	
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getSerias() {
		return serias;
	}

	public void setSerias(String serias) {
		this.serias = serias;
	}

	public String getDataSQL() {
		return dataSQL;
	}

	public void setDataSQL(String dataSQL) {
		this.dataSQL = dataSQL;
	}
	
	/**
	 * 设置系列
	 * @param chart
	 * @param serias
	 * @param prefix
	 * @return
	 */
	public Chart setSerias(MgrTable tbc, String serias, String prefix) {
		if(!StringUtils.hasText(serias)) {
			return this;
		}
		String yuns = this.getOperation();
		if(serias.contains(",")) {
			String[] sers = serias.split(",");
			serias = "";
			int tji = 0;
			if(yuns.contains(",")) {
				String[] yunss = yuns.split(",");
				for (int i = 0; i < sers.length; i++) {
					String ss=  sers[i];
					String yun = StringUtils.hasText(yunss[i]) ? yunss[i] : yunss[0];
					if(StringUtils.hasText(ss)) {
						String ssSQL = getOneSerias(tbc, ss, yun, prefix);
						if(StringUtils.hasText(ss)) {
							serias += "," + ssSQL + tji;
							this.addSerias(tbc.getName(), ss);
							tji++;
						}
					}
				}
			} else {
				for (int i = 0; i < sers.length; i++) {
					String ss=  sers[i];
					if(StringUtils.hasText(ss)) {
						String ssSQL = getOneSerias(tbc, ss, yuns, prefix);
						if(StringUtils.hasText(ss)) {
							serias += "," + ssSQL + tji;
							this.addSerias(tbc.getName(), ss);
							tji++;
						}
					}
				}
			}
			this.setSeriasSQL(serias.substring(1));
		} else {
			this.setSeriasSQL(getOneSerias(tbc, serias, yuns, prefix)+"0");
			this.addSerias(tbc.getName(), serias);
		}
		return this;
	}

	/**
	 * 获取单个序列的SQL
	 * @param serias
	 * @param yuns
	 * @param prefix
	 * @return
	 */
	public String getOneSerias(MgrTable tbc, String serias, String yuns, String prefix) {
		serias = serias.trim();
		String serias_type = "";
		IField fld = null;
		if(tbc!=null && tbc.getModel()!=null && tbc.getModel().getId()!=null) {
			serias_type = MgrdbManager.me().getService().getColumnType(tbc.getModel().getId(), serias);
			if(serias_type=="") {
				return null;
			}
			IFieldDao fdao = getFieldDao();
			if(fdao!=null) {
				fld = fdao.findFirstByTbid(tbc.getModel().getId(), MgrConstants.NONE, "fldname", serias);
			}
		}
		if(fld!=null && fld.getIskey().equals("Y")) {
			yuns = "COUNT";
		} else if(serias_type.endsWith("Integer") ||
			serias_type.endsWith("Long") ||
			serias_type.endsWith("Float") ||
			serias_type.endsWith("Double") || 
			serias_type.startsWith("int") ||
			serias_type.startsWith("long") ||
			serias_type.startsWith("float") ||
			serias_type.startsWith("double") ||
			serias_type.startsWith("number") ||
			serias_type.startsWith("numeric")) {
			if(yuns.equalsIgnoreCase("COUNT")) yuns = "SUM";
		} else {
			yuns = "COUNT";
		}
		return serias = yuns + "(" + prefix + serias + ") as tjs";
	}

	public Object[] getSqlParas() {
		return sqlParas;
	}

	public void setSqlParas(Object[] sqlParas) {
		this.sqlParas = sqlParas;
	}
}
