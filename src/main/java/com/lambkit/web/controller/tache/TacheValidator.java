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
package com.lambkit.web.controller.tache;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.lambkit.common.util.StringUtils;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.db.mgr.MgrdbService;

public class TacheValidator extends Validator {

	String tbcnn = "";
	 
	@Override
	protected void validate(Controller c) {
		setShortCircuit(true);
		
		MgrTable tbc = getBase(c);
		if (tbc == null) {
			return;
		}
		tbcnn = tbc.getModel().getTitle();
		
		List<? extends IField> flds = tbc.getFieldList();
		for(int i=0; i<flds.size(); i++) {
			IField fld = flds.get(i); 
			System.out.println("Fldname: "+fld.getName()+"  value:  "+c.getPara("model."+fld.getName()));
			if(fld.getIsedit().equals("N") ||
					fld.getIsmustfld().equals("N") ||
					fld.getIskey().equals("Y")) { 
				System.out.println("Isedit:"+fld.getIsedit()+
						"  Ismustfld:"+fld.getIsmustfld()+
						"  Iskey:"+fld.getIskey()); 
				if(!StringUtils.hasText(c.getPara("model."+fld.getName()))){ 
					System.out.println("continue"); 
					continue;
				}
			}  
			if(fld.getDatatype().startsWith("float") || fld.getDatatype().startsWith("double")
					 || fld.getDatatype().startsWith("number") || fld.getDatatype().startsWith("numeric")) {
				validateDouble("model."+fld.getName(), "msg_"+fld.getName(), "请您输入"+fld.getTitle()+"!并且是数字!"); 
			} else if(fld.getDatatype().startsWith("int4") || fld.getDatatype().startsWith("int(10)")) { 
				validateInteger("model."+fld.getName(), "msg_"+fld.getName(), "请您输入"+fld.getTitle()+"!并且是整数!"); 
			} else if(fld.getDatatype().startsWith("long") || fld.getDatatype().startsWith("int8")) {
				validateLong("model."+fld.getName(), "msg_"+fld.getName(), "请您输入"+fld.getTitle()+"!并且是整数!"); 
			} else if(fld.getDatatype().startsWith("date")) {
				validateDate("model."+fld.getName(), "msg_"+fld.getName(), "请您输入"+fld.getTitle()+"!并且是日期!"); 
			} else {
				validateRequired("model."+fld.getName(), "msg_"+fld.getName(), "请您输入"+fld.getTitle()+"!"); 
			}
		}
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
			c.setAttr("title", tbcnn);
			c.render("/app/common/edit.html");
		}
	}
	
	private MgrTable getBase(Controller c) {
		Long tbid=c.getParaToLong("tag", null);
		if(tbid!=null) {
			MgrdbService tcs = MgrdbManager.me().getService();
			MgrTable tbc = tcs!=null ? tcs.createTable(tbid, MgrConstants.EDIT) : null;
			c.setAttr("tag", tbc.getModel().getId());
			c.setAttr("mgrdb", tbc);
			return tbc;
		}
		return null;
	}
}
