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
package com.lambkit.web.validator;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
import com.lambkit.common.util.StringUtils;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.db.mgr.MgrdbService;

public abstract class BaseValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		if(!isValidate(c)) {
			return;
		}
		setShortCircuit(true);
		
		MgrTable tbc = getBase(c);
		if (tbc == null) {
			if(isUseMgrTable()) {
				addError("msg", "config info is null");
			}
			return;
		}
		
		String prefix = getPrefix();
		prefix = prefix == null ? "model." : prefix + ".";
		
		List<? extends IField> flds = tbc.getFieldList();
		for(int i=0; i<flds.size(); i++) {
			IField fld = flds.get(i); 
			System.out.println("Fldname: "+fld.getName()+"  value:  "+c.getPara(prefix+fld.getName()));
			if(fld.getIsedit().equals("N") ||
					fld.getIsmustfld().equals("N") ||
					fld.getIskey().equals("Y")) { 
				System.out.println("Isedit:"+fld.getIsedit()+
						"  Ismustfld:"+fld.getIsmustfld()+
						"  Iskey:"+fld.getIskey()); 
				if(!StringUtils.hasText(c.getPara(prefix+fld.getName()))){ 
					//System.out.println("continue"); 
					continue;
				}
			}  
			if(fld.getDatatype().startsWith("float ") || fld.getDatatype().startsWith("double ")
					 || fld.getDatatype().startsWith("number ") || fld.getDatatype().startsWith("numeric ")
					 || fld.getDatatype().startsWith("decimal ")) {
				validateDouble(prefix+fld.getName(), "msg_"+fld.getName(), "请您输入"+fld.getTitle()+"!并且是数字!"); 
			} else if(fld.getDatatype().startsWith("int ") || fld.getDatatype().startsWith("smallint ") ||
					fld.getDatatype().startsWith("tinyint ")) { 
				validateInteger(prefix+fld.getName(), "msg_"+fld.getName(), "请您输入"+fld.getTitle()+"!并且是整数!"); 
			} else if(fld.getDatatype().startsWith("long ") || fld.getDatatype().startsWith("bigint ")) {
				validateLong(prefix+fld.getName(), "msg_"+fld.getName(), "请您输入"+fld.getTitle()+"!并且是整数!"); 
			} else if(fld.getDatatype().startsWith("date")) {
				validateDate(prefix+fld.getName(), "msg_"+fld.getName(), "请您输入"+fld.getTitle()+"!并且是日期!"); 
			} else {
				validateRequired(prefix+fld.getName(), "msg_"+fld.getName(), "请您输入"+fld.getTitle()+"!"); 
			}
		}
		
		validateExt(c);
	}

	@Override
	protected void handleError(Controller c) {
		if(c.getPara("at")!=null && c.getPara("at").equalsIgnoreCase("json")) {
			c.setAttr("error", true);
			c.renderJson();
		} else {
			c.keepPara();
			c.setAttr("token", "1");
			//CommonService.bo.setAttr(c, null);
			c.render("edit.html");
		}
	}
	
	protected MgrTable getBase(Controller c) {
		String tbname = getTableName(c);
		if(StrKit.notBlank(tbname)) {
			MgrdbService tcs = MgrdbManager.me().getService();
			MgrTable tbc = tcs!=null ? tcs.createTable(tbname, MgrConstants.EDIT) : null;
			if(tbc!=null) {
				if(tbc.getModel()!=null) {
					c.setAttr("tag", tbc.getModel().getId());
				}
				c.setAttr("mgrdb", tbc);
			}
			return tbc;
		}
		return null;
	}
	
	/**
	 * 获取表格id
	 * @param c
	 * @return
	 */
	protected abstract String getTableName(Controller c);
	
	/**
	 * 获取表单前缀
	 * @return
	 */
	protected abstract String getPrefix();

	/**
	 * 自定义验证扩展
	 * @param c
	 */
	protected void validateExt(Controller c) {
	}
	
	/**
	 * 是否开启验证
	 * @return
	 */
	protected boolean isValidate(Controller c) {
		return true;
	}

	/**
	 * 验证是否需要mgrtable
	 * @return
	 */
	protected boolean isUseMgrTable() {
		return true;
	}
}
