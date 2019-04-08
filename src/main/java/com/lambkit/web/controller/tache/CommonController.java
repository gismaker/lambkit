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

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.NotAction;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.Lambkit;
import com.lambkit.common.util.DateTimeUtils;
import com.lambkit.common.util.StringUtils;
import com.lambkit.db.mgr.Chart;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.db.mgr.MgrdbService;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.web.WebConfig;
import com.lambkit.web.controller.BaseController;
import com.lambkit.web.controller.Widget;
import com.lambkit.web.controller.WidgetManager;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2016-01-22
 * @version 1.0
 * @since 1.0
 */
//@Before(AppLoginInterceptor.class)
@RequiresRoles(value={"superadmin","super","admin"}, logical=Logical.OR)
public class CommonController extends BaseController {
	
	//@Before({RuleRoleInterceptor.class,UserAuthInterceptor.class})
	public void index() {
		MgrTable tbc = getBase(MgrConstants.VIEW);
		if(tbc==null) {
			renderError(404);
			return;
		}
		
		boolean bact = false;
		String act = getPara("act");
		if(StringUtils.hasText(act)) {
			Widget action = WidgetManager.me().getWidget(act);
			if(action!=null) {
				action.execute(this);
				bact = true;
			}
		}
		if(!bact) {
			//默认情况
			list();
		}
	}

	/**
	 * 表格列表页
	 */
	//@Before(RuleRoleInterceptor.class)
	public void list()
	{
		MgrTable tbc = getBase(MgrConstants.VIEW, "viewfldorder, fldorder, fldid");
		if (tbc == null) {
			renderError(404);
			return;
		}
		
		System.out.println("getContextPath:  "+this.getRequest().getContextPath());
		System.out.println("getRequestURI:  "+this.getRequest().getRequestURI());
		String sql = getSearchSQL(tbc); 
		System.out.println("getSearchSQL sql:" +sql);
		Integer pNumber = getParaToInt("pageNum", 1);
		Integer pSize =  getParaToInt("numPerPage", 15);
		String select = getSelectSQLOfView(tbc);
		System.out.println("getSelectSQLOfView select sql :"+ select +" "+ sql);
		Page<Record> page = Db.paginate(pNumber, pSize, select, sql);
		//for(Record rod : page.getList())
			//recordLongToDate(rod, tbc);
		setAttr("page", page); 
		setAttr("old", getParaMap());
		//keepPara();
		renderFreeMarker("list.html");
	}

	/**
	 * 图表页
	 */
	public void chart() {
		MgrTable tbc = getBase(MgrConstants.OLAP);
		if(tbc==null) {
			renderError(404);
			return;
		}
		setAttr("old", getParaMap());
		//keepPara();
		renderFreeMarker("chart.html");
	}
	
	/**
	 * 地图页
	 */
	public void map() {
		MgrTable tbc = getBase(MgrConstants.MAP);
		if(tbc==null) {
			renderError(404);
			return;
		}
		setAttr("old", getParaMap());
		//keepPara();
		renderFreeMarker("map.html");
	}
	/**
	 * Json方式获取表格列表数据
	 
	public void ajaxlist()
	{
		TableConfig tbc = getBase();
		if (tbc == null) {
			renderError(404);
			return;
		}
		String sql = getSearchSQL(tbc);
		System.out.println("sql:" +sql);
		setAttr("list", Db.find(getSelectSQLOfView(tbc) + sql));
		setAttr("flag", true);
		renderJson();
	}
	*/
	
	public void page() {
		MgrTable tbc = getBase(MgrConstants.VIEW);
		if (tbc == null) {
			renderError(404);
			return;
		}
		String sql = getSearchSQL(tbc);
		//System.out.println("sql:" +sql);
		Integer pNumber = getParaToInt("pageNum", 1);
		Integer pSize =  getParaToInt("numPerPage", 25);
		String select = getSelectSQLOfView(tbc); 
		System.out.println("select : "+select);
		Page<Record> page = Db.paginate(pNumber, pSize, select, sql);
		setAttr("page", page);
		setAttr("flag", true);
		renderJson();
	} 
	
	@NotAction
	private String getSelectSQLOfView(MgrTable tbc) {
		return "select " + MgrdbManager.me().getService().getSelectNamesOfView(tbc, "");
		
	}
	
	@NotAction
	private String getSearchSQL(MgrTable tbc) { 
		return " from " + tbc.getName() + " " + getThisSearchWhereSQL(tbc, "") + getOrderBy(tbc.getModel().getPrimaryKey());
	} 
	
	/***
	 * 根据字段拼接order by sql
	 * @param fid 字段名称
	 * @return
	 */
	@NotAction
	private String getOrderBy(String fid)
	{
		String orderby = getParaTrans("orderby");
		if(StringUtils.hasText(orderby)) {
			return " ORDER BY " + fid +" "+orderby +" ";
		}
		return " ORDER BY " + fid +" ";
	}
	
	@NotAction
	public String getSearchWhereSQL(String para, String prefixAndName, String stype) {
		String sql = "";
		if(StringUtils.hasText(getParaTrans(para))) {
			String info = getParaTrans(para);
			if(info.startsWith("{") && info.endsWith("}")) {
				info = info.substring(1, info.length()-1);
				if(info.equalsIgnoreCase("empty")) {
					sql += " and "+ prefixAndName + "<>''";
				} else if(info.equalsIgnoreCase("null")) {
					sql += " and "+ prefixAndName + "is null";
				} 
			} else if(info.startsWith("@")) {
				//包含
				info = info.substring(1);
				if(info.startsWith("-") || info.startsWith(",")) info = info.substring(1);
				if(info.endsWith("-") || info.endsWith(",")) info = info.substring(0, info.length()-1);
				if(info.contains("%")) {
					sql += " and "+ prefixAndName + " like '" + info + "'";
				} else if(info.contains("-")) { // && !info.startsWith("-") && !info.endsWith("-")) {
					String[] infos = info.split("-");
					if(infos.length > 1) {
						sql += " and "+ prefixAndName + " between '" + infos[0] + "' and '" + infos[1] + "'";
					}
				} else if(info.contains(",")) {
					info = info.replace(",", "','");
					sql += " and "+ prefixAndName + " IN ('" + info + "')";
				} else {
					sql += " and "+ prefixAndName + " like '%" + info + "%'";
				}
			} else if(info.startsWith("#")) {
				//不包含
				info = info.substring(1);
				if(info.startsWith("-") || info.startsWith(",")) info = info.substring(1);
				if(info.endsWith("-") || info.endsWith(",")) info = info.substring(0, info.length()-1);
				if(info.contains("%")) {
					sql += " and "+ prefixAndName + " not like '" + info + "'";
				} else if(info.contains("-")) { // && !info.startsWith("-") && !info.endsWith("-")) {
					String[] infos = info.split("-");
					if(infos.length > 1) {
						sql += " and "+ prefixAndName + " not between '" + infos[0] + "' and '" + infos[1] + "'";
					}
				} else if(info.contains(",")) {
					info = info.replace(",", "','");
					sql += " and "+ prefixAndName + " NOT IN ('" + info + "')";
				} else {
					sql += " and "+ prefixAndName + info;
				}
			} else if(info.startsWith("=") || info.startsWith(">") || info.startsWith("<")
					|| info.startsWith("like") || info.startsWith("LIKE") 
					|| info.startsWith("not") || info.startsWith("NOT")
					|| info.startsWith("between") || info.startsWith("BETWEEN")
					|| info.startsWith("is") || info.startsWith("IS")
					|| info.startsWith("in") || info.startsWith("IN")) {
				if(StringUtils.hasText(stype) && !stype.equals("number")) {
					info = info.replace("$", "'");
					sql += " and "+ prefixAndName + " " + info;
				} else {
					//加入判断，防止SQL盲注
					String num = info.substring(1).trim();
					if(StringUtils.isNumeric(num)) {
						sql += " and "+ prefixAndName + " " + info;
					}
				}
				
			} else if(info.contains("%")) {
				sql += " and "+ prefixAndName + " like '" + info + "'";
			} else {
				if(StringUtils.hasText(stype) && stype.equals("number")) {
					if(StringUtils.isNumeric(info)) {
						//加入判断，防止SQL盲注
						sql += " and "+ prefixAndName + " = " + info;
					}
				} else {
					//sql += " and "+ prefixAndName + " like '%" + info + "%'";
					sql += " and "+ prefixAndName + " = '" + info + "'";
				}
			}
		}
		//System.out.println("search sql : " + para+","+prefixAndName+", "+getParaTrans(para)+"----"+sql);
		return sql;
	}

	@NotAction
	private String getThisSearchWhereSQL(MgrTable tbc, String prefix) {
		String sql = " where 1=1 "; 
		List<? extends IField> flds = tbc.getFieldList();
		for(int i=0; i<flds.size(); i++) {
			IField fld = flds.get(i);
			if(fld.getIsselect().toUpperCase().equals("Y"))
			{
				if(fld.getDatatype().startsWith("float") ||
						fld.getDatatype().startsWith("double") ||
						fld.getDatatype().startsWith("long") ||
						fld.getDatatype().startsWith("number") ||
						fld.getDatatype().startsWith("int") ||
						fld.getDatatype().startsWith("numeric")) {
					sql += getSearchWhereSQL(fld.getName(), prefix+fld.getName(), "number");
				} else {
					sql += getSearchWhereSQL(fld.getName(), prefix+fld.getName(), "varchar");
				}
			}
			/*
			if(fld.getName().equalsIgnoreCase("orgcode")) {
				if(StringUtils.hasText(getParaTrans("orgxj"))) sql += " and "+prefix+"orgcode='"+getParaTrans("orgxj") + "'";
				else if(StringUtils.hasText(getParaTrans("orgsf")) &&
							!getParaTrans("orgsf").startsWith("86") &&
							!getParaTrans("orgsf").startsWith("00")) {
					sql += " and "+prefix+"orgcode like '"+ getParaTrans("orgsf").substring(0, 2) + "%'";
				}
			}
			*/
		}
		//user role manager
		/*
		UsersModel user = (UsersModel) getUser();
		if (user != null) {
			int roleid = AuthManager.me().getRoleService().getRoleId();
			//添加查询权限
		}
		*/
		System.out.println("getThisSearchWhereSQL sql:" +sql);
		return sql;
	}
	
	/**
	 * 导出Excel
	 */
	public void excel()
	{
		MgrTable tbc = getBase(MgrConstants.NONE);
		if (tbc == null) {
			renderError(404);
			return;
		}
		
		MgrdbService tcs = MgrdbManager.me().getService();
		
		WebConfig config = Lambkit.config(WebConfig.class);
		if(!config.isExcel() || tcs==null) {
			renderNull();
			return;
		}
		String sql = getSelectSQLOfView(tbc) + getSearchSQL(tbc); 
		//System.out.println("sql:" +sql);
		//--------------------------------------------
		
		getResponse().setContentType("application/octet-stream; charset=GBK");
		String fileName = tbc.getModel().getTitle();
		
		OutputStream os = null;
		try {
			getResponse().setHeader("Content-Disposition","attachment;filename="+
						new String(fileName.getBytes("gb2312"), "ISO8859-1" )+".xls");//指定下载的文件名
			getResponse().setContentType("application/vnd.ms-excel");
			
			os = getResponse().getOutputStream();
			
			tcs.exportExcel(tbc,os,sql);
			
			//解决getOutputStream() has already been called for this response问题 
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//log("${tablename}", "${tbshortname}/excel", "导出Excel");
		renderNull();
	}
	
	/**
	 * 自动补全
	 */
	@Clear
	//@Before(AppLoginInterceptor.class)
	public void autoc() {
		MgrTable tbc = getBase(MgrConstants.NONE);
		if (tbc == null) {
			setAttr("flag", false);
			renderJson();
			return;
		}
		
		String fldname = getPara("fldname");
		String keyword = getPara("keyword");
		
		if(StringUtils.hasText(fldname)) {
			String sql = " from \""+tbc.getName()+"\" " + getThisSearchWhereSQL(tbc, "");
			if(StringUtils.hasText(keyword)) {
				sql += " and \""+fldname+"\" like '" + keyword + "%'";
			} else {
				sql += " and \""+fldname+"\" is not null and \""+fldname+"\" <> '' group by \""+fldname+"\" ORDER BY \""+fldname+"\" DESC";
			}
			Page<Record> m = Db.paginate(1, 10, "select \""+fldname+"\" as value ", sql);
			renderJson(m.getList());
			return;
		}
		setAttr("flag", false);
		renderJson();
	}

	
	/**
	 * 获取一条记录
	 */
	public void get() {
		MgrTable tbc = getBase(MgrConstants.VIEW);
		if (tbc == null) {
			setAttr("flag", false);
			renderJson();
			return;
		}
		IField fld = MgrdbManager.me().getService().getFieldDao().findFirstByTbid(tbc.getModel().getId(), MgrConstants.VIEW, "iskey", "Y");
		if(fld!=null) {
			String model_id = getPara(0) ==null ? getPara("id") : getPara(0);
			Record rcd = null;
			if(fld.getDatatype().startsWith("int")) {
				rcd = Db.findById(tbc.getName(), fld.getName(), Integer.valueOf(model_id));
			}
			setAttr("model", rcd);
			setAttr("flag", true);
			renderJson();
		}
	}
	
	/**
	 * 跳转到添加页面
	 */
	public void add()
	{
		MgrTable tbc = getBase(MgrConstants.EDIT, "editorder, fldorder, fldid");
		if (tbc == null) {
			renderError(404);
			return;
		}
		Date date =new Date(); 
		setAttr("datenow",DateFormat.getDateInstance(DateFormat.MEDIUM).format(date)); 
		setAttr("isadd", true);
		setAttr("title", "添加界面");
		setAttr("uuid",getUser().getUuid());
		setAttr("auth", getCache("auth"));
		renderFreeMarker("edit.html");
	}
	
	/**
	 * 跳转到编辑页面
	 * edit/0
	 */
	//@Before(RoleEtcInterceptor.class)
	public void show() {
		MgrTable tbc = getBase(MgrConstants.VIEW);
		if (tbc == null) {
			renderError(404);
			return;
		}
		if(StringUtils.hasText(tbc.getModel().getPrimaryKey())) {
			String model_id = getPara("id");
			Record rcd = Db.findById(tbc.getName(), model_id);
			
			boolean flag = AuthManager.me().getRoleService().ownTask(this, rcd.getStr("user_uuid"));
			//超级管理员也可以编辑
			flag = flag ? flag : AuthManager.me().getRoleService().isAdmin();
			if(flag) {
				setAttr("isadd", false);
				setAttr("model", rcd);
				renderFreeMarker("show.html");
			} else {
				render("/static/error/self.html");
			}
		} else {
			renderError(404);
		}
	}
	
	/**
	 * 跳转到编辑页面
	 * edit/0
	 */
	//@Before({RoleEtcInterceptor.class,RuleRoleInterceptor.class,UserAuthInterceptor.class})
	public void edit()
	{
		MgrTable tbc = getBase(MgrConstants.EDIT, "editorder, fldorder, fldid");
		if (tbc == null) {
			renderError(404);
			return;
		}
		setAttr("link", getPara(1) == null ? getPara("link") : getPara(1));
		if(StringUtils.hasText(tbc.getModel().getPrimaryKey())) {
			Integer model_id = getParaToInt(0) == null ? getParaToInt("id") : getParaToInt(0);
			Record rcd = Db.findById(tbc.getName(),tbc.getModel().getPrimaryKey(), model_id); 
			boolean flag = AuthManager.me().getRoleService().ownTask(this, rcd.getStr("user_uuid"));
			//超级管理员也可以编辑
			flag = flag ? flag : AuthManager.me().getRoleService().isAdmin();
			if(flag) {
				//recordLongToDate(rcd,tbc);
				//格式化时间 
				setAttr("isadd", false);
				setAttr("model", rcd);
				setAttr("tag",getParaToLong("tag", null));
				setAttr("auth", getCache("auth"));
				renderFreeMarker("edit.html");
			} else {
				render("/static/error/self.html");
			}
		} else {
			renderError(404);
		}
	}
	
	/**
	 * 保存数据
	 * 
	 * 注：对于日期类型让转换函数自动转换
	 * 日期 逻辑： 数据库中保存 long类型，界面显示日期类型
	 * 保存：界面日期->转换函数->数据库long
	 * 查询：数据库long->转换函数->界面日期
	 * at = json 或 redirect(url)中的url
	 */
	//@Before({RoleEtcInterceptor.class,CommonValidator.class,UserAuthInterceptor.class})
	@Before(CommonValidator.class)
	public void save() {
		MgrTable tbc = getBase(MgrConstants.MAP_EDIT);
		if (tbc == null) {
			renderAt(false);
			return;
		}  
		System.out.println("tablename:"+tbc.getName());
		//IField kfld = IField.dao.findFirstByTbid(tbc.getModel().getTbid(), "iskey", "Y");
		//String id = kfld.getName();
		String idname = tbc.getModel().getPrimaryKey();
		Record m = new Record();
		List<? extends IField> flds = tbc.getFieldList();
		setRecord(m,flds, true);
		//m.set("id", StringUtils.getRandString32());
		System.out.println("id_name:"+idname);
		m.remove(idname);
		//String geomSQL="";
		/*
		if(!StringUtils.hasText(getPara("model.lon")) || !StringUtils.hasText(getPara("model.lat"))) {
			m.set("location_status", "0");
		} else {
			m.set("location_status", "2");
			double lon = Double.valueOf(getPara("model.lon", "0"));
			double lat = Double.valueOf(getPara("model.lat", "0"));
			if(getPara("lon_type", "").equals("1") || getPara("lat_type", "").equals("1")) {
				//百度坐标
				m.setLonlat("lon", "lat", lon, lat, false);
			} else {
				//84坐标
				m.setLonlat("lon", "lat", lon, lat, true);
			}
		}
		*/
		//recordDateToLong(m, tbc);
		System.out.println("Record:"+m);
		boolean flag = false;
		try{
		    flag = Db.save(tbc.getName(),idname, m); 
		    
		    if(flag) {
		    	//log(tbc.getModel().getTableName(), m.get(tbc.getModel().getPkname()), "common/save", "保存数据");
		    	
		    	//m.getColumns().containsKey("geom")
		    	MgrdbService tcs = MgrdbManager.me().getService();
				IField geomfld = tcs.getFieldDao().findGeomField(tbc.getModel().getId(), MgrConstants.MAP);
				if(geomfld!=null &&
						StringUtils.hasText(getPara("model.lon")) && StringUtils.hasText(getPara("model.lat")))
				{
					/***
					 * 更新位置信息
					 */
					double lon = Double.valueOf(getPara("model.lon", "0"));
					double lat = Double.valueOf(getPara("model.lat", "0"));
					//获取geom信息 UPDATE data_busines SET geom = st_geomfromtext('POINT(116.407159 39.90467)',4326) where gid=;
					String ssql = "UPDATE "+tbc.getName()+" SET geom = st_geomfromtext('POINT("+lon+" "+lat+")') where gid= " + m.get(tbc.getModel().getPrimaryKey());
					Db.update(ssql);
				}
				
				if(m.getColumns().containsKey("dataid") && m.get("dataid")==null) {
					m.set("dataid", m.get(tbc.getModel().getPrimaryKey()));
					Db.update(tbc.getName(), tbc.getModel().getPrimaryKey(),m);
				}
		    }
		}catch(Exception ex) {
			//log(tbc.getModel().getTableName(), "data/save", "保存数据异常："+ex.getMessage());   
		}
		renderAt(flag);
	}
	
	@NotAction
	private void setRecord(Record m,List<? extends IField> flds, boolean bsave)
	{
		for(int i=0; i<flds.size(); i++) {
			IField fld = flds.get(i);  
			if(StrKit.notBlank(getPara("model."+fld.getName())))
			{  
				//需要进行类型转换
				if(fld.getDatatype().startsWith("float") || fld.getDatatype().startsWith("double")
						 || fld.getDatatype().startsWith("number") || fld.getDatatype().startsWith("numeric")) {
					m.set(fld.getName(), Double.parseDouble(getPara("model."+fld.getName())));
				} else if(fld.getDatatype().startsWith("int4") || fld.getDatatype().startsWith("int(4)")|| fld.getDatatype().startsWith("int(10)")) { 
					m.set(fld.getName(), getParaToInt("model."+fld.getName()));
				} else if(fld.getDatatype().startsWith("int8") || fld.getDatatype().startsWith("int(11)")) { 
					m.set(fld.getName(), getParaToLong("model."+fld.getName()));
				} else if(fld.getDatatype().startsWith("long")) {
					m.set(fld.getName(), getParaToLong("model."+fld.getName())); 
				} else if(fld.getDatatype().startsWith("datetime")) { 
					String dt = getPara("model."+fld.getName());
					if (dt != null && !"".equals(dt.trim())) {
						Date d = DateTimeUtils.stringToDate(dt.trim());
						m.set(fld.getName(), DateTimeUtils.dateToTimestamp(d)); 
					}
				} else if(fld.getDatatype().startsWith("date")) { 
					//需要将java.util.date 转换成Timestamp
					Date d = getParaToDate("model."+fld.getName()); 
					m.set(fld.getName(), DateTimeUtils.dateToTimestamp(d)); 
				} else {
					m.set(fld.getName(), getPara("model."+fld.getName())); 
				} 
			} else {
				Long time = DateTimeUtils.getCurrentTimeLong(); 
				if(bsave) {
					if(fld.getName().equals("create_time") || "create_time".equals(fld.getStr("ext05"))) {
						m.set(fld.getName(), time);
					}
					if(fld.getName().equals("check_status") || "status".equals(fld.getStr("ext05"))) {
						if(hasUser() && getUser().getType()==0) {
							m.set(fld.getName(), "1");
						} else {
							m.set(fld.getName(), "0");
						}
					}
					if(fld.getName().equals("create_user") || "create_user".equals(fld.getStr("ext05"))) {
						if(hasUser()) {
							m.set(fld.getName(), getUser().getId());
						}
					}
				} else {
					if(fld.getName().equals("reply_time") || "reply_time".equals(fld.getStr("ext05"))) {
						m.set(fld.getName(), time);
					}
					if(fld.getName().equals("reply_user") || "reply_user".equals(fld.getStr("ext05"))) {
						if(hasUser()) {
							m.set(fld.getName(), getUser().getId());
						}
					}
				}
				if(fld.getName().equals("modify_time") || "modify_time".equals(fld.getStr("ext05"))) {
					m.set(fld.getName(), time);
				}
				if(hasUser() && fld.getName().equals("user_uuid")) {
					m.set("user_uuid", getUser().getUuid());
				}
			}
			if(!m.getColumns().containsKey("dataid") && fld.getName().equals("dataid")) {
				m.set("dataid", null);
			}
		}
	}
	
	
	
	
	/**
	 * 保存数据
	 * at = json 或 redirect(url)中的url
	 */
	//@Before({RoleEtcInterceptor.class,CommonValidator.class,UserAuthInterceptor.class})
	@Before(CommonValidator.class)
	public void update() {
		MgrTable tbc = getBase(MgrConstants.MAP_EDIT);
		if (tbc == null) {
			renderAt(false);
			return;
		}
		
		Record m = new Record();
		List<? extends IField> flds = tbc.getFieldList();
		setRecord(m,flds, false);
		
		if(m.getColumns().containsKey("dataid") &&
				!StringUtils.hasText(getPara("model.dataid"))) {
			m.set("dataid", m.get(tbc.getModel().getPrimaryKey()));
		}
		//recordDateToLong(m, tbc);
		//IField fld = IField.dao.findFirstByTbid(tbc.getModel().getTbid(), "iskey", "Y");
		//boolean flag = Db.update(tbc.getModel().getTableName(), fld.getName(),m);
		System.out.println("Record:"+m);
		boolean flag = Db.update(tbc.getName(), tbc.getModel().getPrimaryKey(),m);
		if(flag) {
			//log(tbc.getModel().getTableName(), m.get(tbc.getModel().getPkname()), "data/update", "更新数据");
			MgrdbService tcs = MgrdbManager.me().getService();
			IField geomfld = tcs.getFieldDao().findGeomField(tbc.getModel().getId(), MgrConstants.MAP);
			if(geomfld!=null &&
					StringUtils.hasText(getPara("model.lon")) && StringUtils.hasText(getPara("model.lat")))
			{
				/***
				 * 更新位置信息
				 */
				double lon = Double.valueOf(getPara("model.lon", "0"));
				double lat = Double.valueOf(getPara("model.lat", "0"));
				//获取geom信息 UPDATE data_busines SET geom = st_geomfromtext('POINT(116.407159 39.90467)',4326) where gid=;
				String ssql = "UPDATE "+tbc.getName()+" SET "+geomfld.getName()+" = st_geomfromtext('POINT("+lon+" "+lat+")') where gid= " + m.get(tbc.getModel().getPrimaryKey());
				System.out.println(ssql);
				Db.update(ssql);
			}
		}
		renderAt(flag);
	}
	
	//@Before({RuleRoleInterceptor.class,UserAuthInterceptor.class})
	public void status() {
		MgrTable tbc = getBase(MgrConstants.NONE);
		if (tbc == null) {
			renderAt(false);
			return;
		}
		int model_id = getParaToInt("id");
		//IField fld = IField.dao.findFirstByTbid(tbc.getModel().getTbid(), "iskey", "Y");
		//Record record = Db.findById(tbc.getModel().getTableName(), fld.getName(),model_id);
		Record record = Db.findById(tbc.getName(), tbc.getModel().getPrimaryKey(),model_id);
		record.set(getPara("k", "check_status"), getParaToInt("s", 1));
		//boolean del = Db.update(tbc.getModel().getTableName(),fld.getName(), record);
		boolean del = Db.update(tbc.getName(),tbc.getModel().getPrimaryKey(), record);
		System.out.println(del);
		if(del) {
			renderAt(true);
			//log(tbc.getModel().getTableName(), model_id, "data/check", "审核数据"); 
		}
		else renderAt(false);
	}
	
	//@Before({RoleEtcInterceptor.class,UserAuthInterceptor.class})
	public void delete() {
		MgrTable tbc = getBase(MgrConstants.NONE);
		if (tbc == null) {
			renderAt(false);
			return;
		}
		int model_id = getParaToInt("id");
		boolean del = Db.deleteById(tbc.getName(), tbc.getModel().getPrimaryKey(), model_id);
		if(del) {
			renderAt(true);
			//log(tbc.getModel().getTableName(), model_id, "data/delete", "删除数据"); 
		}
		else renderAt(false);
	}
	
	//@Before({RoleEtcInterceptor.class,UserAuthInterceptor.class})
	public void delete_pl() {
		MgrTable tbc = getBase(MgrConstants.NONE);
		if (tbc == null) {
			renderAt(false);
			return;
		}
		String[] ids = getParaValues("id");
		boolean del = false;
		for(String model_id : ids)
		{
			del = Db.deleteById(tbc.getName(), tbc.getModel().getPrimaryKey(), Integer.parseInt(model_id));
			if(del) {
				//log(tbc.getModel().getTableName(), model_id, "data/delete_pl", "批量删除数据"); 
			}
		}
		renderAt(del); 
	} 
	/**
	 * ECharts配置
	 */
	public void echart() {
		MgrTable tbc = getBase(MgrConstants.OLAP);
		if (tbc == null) {
			setAttr("flag", false);
			renderJson();
		}
		
		String cls = getPara("cls");
		if(!StringUtils.hasText(cls)) {
			setAttr("flag", false);
			renderJson();
		}
		Chart chart = new Chart(true);
		chart.setClassify(cls);
		String serias = getPara("serias");
		if(!StringUtils.hasText(serias)) {
			setAttr("flag", false);
			setAttr("msg", "列值不存在，请选择列值！");
			renderJson();
			return;
		}
		String yuns = getPara("yuns", "SUM");
		chart.setOperation(yuns);
		String sql = getThisSearchWhereSQL(tbc, "");
		int borg = 1;
		chart.setClassifyName(tbc.getName(), cls);
		chart.setSerias(tbc, serias, "");
		sql = "SELECT " + cls + " as cls,"+ chart.getSeriasSQL() + " FROM \"" 
						+ tbc.getName() + "\" " + sql +" GROUP BY "  + cls + " ORDER BY " + cls;
		chart.setDataSQL(sql);
		
		System.out.println(sql);
		
		String type = getPara("type", "table");
		if(type.equalsIgnoreCase("table")) {
			getTableChart(chart, borg);
		} else if(type.equalsIgnoreCase("mapdata")) {
			getMapDataChart(chart, borg);
		}
	}
	
	@NotAction
	private void getTableChart(Chart chart, int borg) {
		String lname = "行政区划";
		if(borg==0) {
    		lname = chart.getClassifyName();
    	} else if(borg==2) {
    		lname = "山脉体系";
    	} else if(borg >= 860000) {
    		lname = "全国";
    	}
		List<String> titlelist = new ArrayList<String>();
		titlelist.add(lname);
		titlelist.addAll(chart.getSeriasNames());
		setAttr("tbhead", titlelist);
		List<String> keylist = new ArrayList<String>();
		if(borg != 860000) keylist.add("cls");
		for(int i=0; i<chart.getSeriasNames().size();i++) {
			keylist.add("tjs"+i);
		}
		setAttr("colname", keylist);
		List<Record> list = Db.find(chart.getDataSQL());
		if(list.size() > 100) {
			setAttr("flag", false);
			setAttr("msg", "分类值多余100，请增加过滤条件或更换行值！");
			renderJson();
			return;
		}
		if(borg > 0) {
			for (Record m : list) {
	        	String sname = m.get("cls", "").toString();
	        	if(sname=="") continue;
	        	if(!StringUtils.hasText(sname)) sname = m.get("cls", "").toString();
	        	m.set("cls", sname);
			}
		}
		setAttr("list", list);
		setAttr("orgsign", borg);
		setAttr("seriaslen", chart.getSeriasNames().size());
		setAttr("flag", true);
		renderJson();
	}
	
	@NotAction
	private void getMapDataChart(Chart chart, int borg) {
		if(borg==0) {
			setAttr("flag", false);
			setAttr("msg", "行值必须为区划或山脉体系！");
			renderJson();
			return;
		}
		String lname = "行政区划代码";
		if(borg==2) {
    		lname = "山脉体系";
    	} else if(borg >= 860000) {
    		lname="国家";
    	}
		List<String> titlelist = new ArrayList<String>();
		if(borg < 110000) titlelist.add("行政区划代码");
		if(borg < 110000) titlelist.add("行政区划名称");
		titlelist.add(lname);
		titlelist.addAll(chart.getSeriasNames());
		titlelist.add("中心坐标");
		setAttr("tbhead", titlelist);
		List<String> keylist = new ArrayList<String>();
		if(borg < 110000) keylist.add("code");
		if(borg < 110000) keylist.add("name");
		keylist.add("cls");
		for(int i=0; i<chart.getSeriasNames().size();i++) {
			keylist.add("tjs"+i);
		}
		keylist.add("centerpoint");
		keylist.add("points");
		setAttr("colname", keylist);
		List<Record> list = Db.find(chart.getDataSQL());
		if(borg < 110000) {
			for (Record m : list) {
				String sname = borg > 2 ? m.get("code", "").toString() : m.get("cls", "").toString();
        		if(sname=="") continue;
        		if(borg==1) m.set("code", sname);
        		//m.set("centerpoint", MapService.bo.getCodeCenter(sname));
        		//m.set("points", MapService.bo.getCodePoints(sname));
			}
		} else {
			for (Record m : list) {
				m.set("centerpoint", "POINT(11897270.5785311 4192418.127385)");
			}
		}
		setAttr("list", list);
		setAttr("orgsign", borg);
		setAttr("seriaslen", chart.getSeriasNames().size());
		setAttr("flag", true);
		renderJson();
	}
	
}