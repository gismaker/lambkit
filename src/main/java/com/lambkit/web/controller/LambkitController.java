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
package com.lambkit.web.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.InvalidSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.core.ActionException;
import com.jfinal.core.Controller;
import com.jfinal.core.NotAction;
import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.JsonRender;
import com.lambkit.common.LambkitResult;
import com.lambkit.common.AjaxResult;
import com.lambkit.common.util.DateTimeUtils;
import com.lambkit.common.util.RequestUtils;
import com.lambkit.common.util.StringUtils;
import com.lambkit.core.gateway.GatewayRender;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.db.mgr.MgrdbService;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.plugin.auth.IUser;
import com.lambkit.plugin.auth.cache.UserInfo;
import com.lambkit.db.sql.condition.ConditionBuilder;
import com.lambkit.web.controller.Widget;
import com.lambkit.web.render.MimeTypeRender;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器基类
 */
public abstract class LambkitController extends Controller {

	private final static Logger _log = LoggerFactory.getLogger(LambkitController.class);

	/**
	 * 统一异常处理, 未完成
	 * @param request
	 * @param response
	 * @param exception
	 */
	public void exceptionHandler(Exception exception) {
		_log.error("统一异常处理：", exception);
		setAttr("ex", exception);
		if (null != getRequest().getHeader("X-Requested-With") && getRequest().getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
			setAttr("requestHeader", "ajax");
		}
		if (exception instanceof UnauthorizedException) {
			// shiro没有权限异常
			renderError(403);
		} else if (exception instanceof InvalidSessionException) {
			// shiro会话已过期异常
			render("/error/error.html");
		} else {
			render("/error/error.html");
		}
	}
	
	/**
     * <获取参数map>
     * 
     * @return 参数map
     * @throws Exception 
     */
    protected Kv getParameterMap() {
        Kv resultMap = Kv.create();
        Map<String, String[]> tempMap = getRequest().getParameterMap();
        Set<String> keys = tempMap.keySet();
        for (String key : keys) {
            //byte source [] = getRequest().getParameter(key).getBytes("iso8859-1");
            //String modelname = new String (source,"UTF-8");
            resultMap.set(key, getPara(key));
        }
        return resultMap;
    }
	
	/**
     * 获取当前网址
     *
     * @return
     */
	@NotAction
    public String getBaseUrl() {
        HttpServletRequest req = getRequest();
        int port = req.getServerPort();
        return port == 80
                ? String.format("%s://%s%s", req.getScheme(), req.getServerName(), req.getContextPath())
                : String.format("%s://%s%s%s", req.getScheme(), req.getServerName(), ":" + port, req.getContextPath());

    }

	/**
	 * 获取mgrdb配置
	 * @return
	 */
	@NotAction
	public MgrTable getBase(int type, boolean attr, String orderby) {
		//convert tbid type (int) to Long
		Long tbid=getParaToLong(0) == null ? getParaToLong("tag", null) : getParaToLong(0);
		if(tbid!=null) {
			return getTable(tbid, type, attr, orderby);
		}
		return null;
	}
	
	@NotAction
	public MgrTable getBase(boolean attr) {
		return getBase(MgrConstants.NONE, attr, null);
	}
	
	@NotAction
	public MgrTable getBase(int type, boolean attr) {
		return getBase(type, attr, null);
	}
	
	@NotAction
	public MgrTable getBase() {
		return getBase(MgrConstants.NONE, true, null);
	}
	
	@NotAction
	public MgrTable getBase(int type) {
		return getBase(type, true, null);
	}
	
	@NotAction
	public MgrTable getBase(String orderby) {
		return getBase(MgrConstants.NONE, true, orderby);
	}
	
	@NotAction
	public MgrTable getBase(int type, String orderby) {
		return getBase(type, true, orderby);
	}
	
	String mgrdbName = null;
	
	protected void setMgrdbName(String mgrdbName) {
		this.mgrdbName = mgrdbName;
	}
	
	protected MgrdbService getTableConfigService() {
		return StrKit.isBlank(mgrdbName) ? MgrdbManager.me().getService() : MgrdbManager.me().getService(mgrdbName);
	}
	
	protected MgrTable getTableDefault(Object tbid, boolean attr) {
		return getTable(tbid, MgrConstants.NONE, attr);
	}
	
	protected MgrTable getTableEdit(Object tbid, boolean attr) {
		return getTable(tbid, MgrConstants.EDIT, attr);
	}
	
	protected MgrTable getTableView(Object tbid, boolean attr) {
		return getTable(tbid, MgrConstants.VIEW, attr);
	}
	
	protected MgrTable getTable(Object tbid, int type, boolean attr) {
		return getTable(tbid, type, attr, null);
	}
	
	protected MgrTable getTable(Object tbid, int type, boolean attr, String orderby) {
		MgrTable t = getAttr("mgrdb");
		if(t!=null) return t;
		else if(tbid!=null) {
			MgrTable tbc = getTableConfigService() !=null ? getTableConfigService().createTable(tbid, type, orderby) : null;
			if(attr) {
				if(tbc.getModel()!=null) {
					setAttr("tag", tbc.getModel().getId());
				}
				setAttr("mgrdb", tbc);
			}
			return tbc;
			/*
			TableConfig tbc = null;
			Object obj = CacheKit.get("ehCacheTableData", "tbconfig" + tbid);
			if(obj==null) {
				tbc = getTableConfigService() !=null ? getTableConfigService().createTable(tbid) : null;
				// 保存uset
				CacheKit.put("ehCacheTableData", "tbconfig" + tbid, tbc);
			} else {
				tbc = (TableConfig) obj;
			}
			return tbc;
			*/
		}
		return null;
	}
	
	protected MgrTable getTableDefault(String tbname, boolean attr) {
		return getTable(tbname, MgrConstants.NONE, attr);
	}
	
	protected MgrTable getTableEdit(String tbname, boolean attr) {
		return getTable(tbname, MgrConstants.EDIT, attr);
	}
	
	protected MgrTable getTableView(String tbname, boolean attr) {
		return getTable(tbname, MgrConstants.VIEW, attr);
	}
	
	protected MgrTable getTable(String tbname, int type, boolean attr) {
		return getTable(tbname, type, attr, null);
	}
	
	protected MgrTable getTable(String tbname, int type, boolean attr, String orderby) {
		MgrTable t = getAttr("mgrdb");
		if(t!=null) return t;
		else if(StrKit.notBlank(tbname)) {
			MgrTable tbc = getTableConfigService() !=null ? getTableConfigService().createTable(tbname, type, orderby) : null;
			if(attr) {
				if(tbc.getModel()!=null) {
					setAttr("tag", tbc.getModel().getId());
				}
				setAttr("mgrdb", tbc);
			}
			return tbc;
			/*
			TableConfig tbc = null;
			Object obj = CacheKit.get("ehCacheTableData", "tbconfig" + tbid);
			if(obj==null) {
				tbc = new TableConfig(tbid);
				// 保存uset
				CacheKit.put("ehCacheTableData", "tbconfig" + tbid, tbc);
			} else {
				tbc = (TableConfig) obj;
			}
			return tbc;
			*/
		}
		return null;
	}
	
	protected void keepTable(MgrTable tbc) {
		if(tbc==null) return;
		for(IField fld : tbc.getFieldList()) {
			String val = getPara(fld.getName());
			if(StrKit.notBlank(val)) {
				fld.putAttr("val", val);
			}
		}
	}
	
	protected void keepTable(MgrTable tbc, Model model) {
		if (tbc == null) return;
		if(model==null) return;
		List fields = tbc.getFieldList();
		for (int i = 0; i < fields.size(); i++) {
			IField fld = (IField) fields.get(i);
			Object val = model.get(fld.getName());
			fld.putAttr("val", val);
		}
	}

	protected void setTableAttr(MgrTable tbc, Record model) {
		if (tbc == null) return;
		if(model==null) return;
		List fields = tbc.getFieldList();
		for (int i = 0; i < fields.size(); i++) {
			IField fld = (IField) fields.get(i);
			Object val = model.get(fld.getName());
			fld.putAttr("val", val);
		}
	}

	protected void setTableAttr(MgrTable tbc, Map<String, Object> model) {
		if (tbc == null) return;
		if(model==null) return;
		List fields = tbc.getFieldList();
		for (int i = 0; i < fields.size(); i++) {
			IField fld = (IField) fields.get(i);
			Object val = model.get(fld.getName());
			fld.putAttr("val", val);
		}
	}
	
	protected MgrTable getTable(String tbname, int type, String attrName) {
		if(StrKit.notBlank(tbname)) {
			MgrTable tbc = getTableConfigService() !=null ? getTableConfigService().createTable(tbname, type) : null;
			setAttr(attrName, tbc);
			return tbc;
		}
		return null;
	}
	
	protected void setTable(String tbname, int type, String attrName) {
		getTable(tbname, type, attrName);
	}
	
	/**
	 * 用户是否存在，(等同于用户是否登陆)
	 * 
	 * @return
	 */
	protected boolean hasUser() {
		if(getSession()==null) {
			System.out.println("http session is null");
		}
		return AuthManager.me().hasUser(this);
	}

	/**
	 * 用户缓存信息
	 * 
	 * @return
	 */
	protected UserInfo getUserCache() {
		return AuthManager.me().getCache().getUserInfo(getSession().getId());
	}

	protected void saveCache(String key, Object value) {
		AuthManager.me().getCache().saveInfo(key, value, getSession().getId());
	}

	protected Object getCache(String key) {
		return AuthManager.me().getCache().getInfo(key, getSession().getId());
	}

	/**
	 * 用户
	 * 
	 * @return
	 */
	protected IUser getUser() {
		return AuthManager.me().getUser(this);
	}
	
	protected int loginStatus(String username) {
		return AuthManager.me().getCache().loginStatus(username, getSession().getId());
	}

	protected UserInfo saveUser(IUser user, String ctime) {
		this.setSessionAttr("lambuser", user.getId());//new OnlineSession(getRemoteLoginUserIp(), user.getName(), ctime));
		return AuthManager.me().getCache().saveUser(user, getSession().getId());
	}

	protected void removeUser() {
		String sessionid = getSession().getId();
		this.getSession().invalidate();
		AuthManager.me().getCache().removeUser(sessionid);
	}

	/**
	 * 将模型参数注入到Controller中
	 * @param model
	 */
	protected void setAttrs(Model<?> model) {
        String[] names = model._getAttrNames();
        for (String str : names) {
            setAttr(str, model.get(str));
        }
    }
	
	protected void setAttrs(Record model) {
        String[] names = model.getColumnNames();
        for (String str : names) {
            setAttr(str, model.get(str));
        }
    }
	
	@NotAction
	public BigInteger[] getParaValuesToBigInteger(String name) {
		String[] values = getRequest().getParameterValues(name);
		if (values == null)
			return null;
		BigInteger[] result = new BigInteger[values.length];
		for (int i = 0; i < result.length; i++)
			result[i] = new BigInteger(values[i]);
		return result;
	}

	@NotAction
	public BigInteger getParaToBigInteger() {
		return toBigInteger(getPara(), null);
	}

	@NotAction
	public BigInteger getParaToBigInteger(int index) {
		return toBigInteger(getPara(index), null);
	}

	@NotAction
	public BigInteger getParaToBigInteger(int index, BigInteger defaultValue) {
		return toBigInteger(getPara(index), defaultValue);
	}

	@NotAction
	public BigInteger getParaToBigInteger(String name) {
		return toBigInteger(getRequest().getParameter(name), null);
	}

	@NotAction
	public BigInteger getParaToBigInteger(String name, BigInteger defaultValue) {
		return toBigInteger(getRequest().getParameter(name), defaultValue);
	}

	protected BigInteger toBigInteger(String value, BigInteger defaultValue) {
		try {
			if (value == null || "".equals(value.trim()))
				return defaultValue;
			value = value.trim();
			if (value.startsWith("N") || value.startsWith("n"))
				return new BigInteger(value).negate();
			return new BigInteger(value);
		} catch (Exception e) {
			throw new ActionException(404, "Can not parse the parameter \"" + value + "\" to BigInteger value.");
		}
	}
	
	protected BigInteger toBigInteger(Object value, BigInteger defaultValue) {
		if(value instanceof String) return toBigInteger(value, defaultValue);
		else if(value instanceof BigInteger) return value==null ? defaultValue : (BigInteger) value;
		else if(value instanceof Long) return value==null ? defaultValue : BigInteger.valueOf((Long)value);
		return toBigInteger(value.toString(), defaultValue);
	}
	
	@NotAction
	public Float getParaToFloat(int index) {
		return toFloat(getPara(index), null);
	}

	@NotAction
	public Float getParaToFloat(int index, Float defaultValue) {
		return toFloat(getPara(index), defaultValue);
	}

	@NotAction
	public Float getParaToFloat(String name) {
		return toFloat(getRequest().getParameter(name), null);
	}

	@NotAction
	public Float getParaToFloat(String name, Float defaultValue) {
		return toFloat(getRequest().getParameter(name), defaultValue);
	}
	
	private Float toFloat(String value, Float defaultValue) {
		try {
			if (StrKit.isBlank(value))
				return defaultValue;
			value = value.trim();
			if (value.startsWith("N") || value.startsWith("n"))
				return -Float.parseFloat(value.substring(1));
			return Float.parseFloat(value);
		}
		catch (Exception e) {
			throw new ActionException(400, "Can not parse the parameter \"" + value + "\" to Float value.");
		}
	}
	
	@NotAction
	public Double getParaToDouble(int index) {
		return toDouble(getPara(index), null);
	}

	@NotAction
	public Double getParaToDouble(int index, Double defaultValue) {
		return toDouble(getPara(index), defaultValue);
	}

	@NotAction
	public Double getParaToDouble(String name) {
		return toDouble(getRequest().getParameter(name), null);
	}

	@NotAction
	public Double getParaToDouble(String name, Double defaultValue) {
		return toDouble(getRequest().getParameter(name), defaultValue);
	}
	
	private Double toDouble(String value, Double defaultValue) {
		try {
			if (StrKit.isBlank(value))
				return defaultValue;
			value = value.trim();
			if (value.startsWith("N") || value.startsWith("n"))
				return -Double.parseDouble(value.substring(1));
			return Double.parseDouble(value);
		}
		catch (Exception e) {
			throw new ActionException(400, "Can not parse the parameter \"" + value + "\" to Double value.");
		}
	}
	
	/**
	 * 防止参数的sql注入  
	 * @param name
	 * @return
	 */
	@NotAction
	public String getParaTrans(String name) {
		String param  = this.getPara(name);
		if(param!=null && param.trim().length() > 0) {
			return StringUtils.transactSQLInjection(param);
		}
		return null;
	}
	
	@NotAction
	public String getParaTrans(String name, String defaultvalue) {
		String param  = this.getPara(name);
		if(param!=null && param.trim().length() > 0) {
			return StringUtils.transactSQLInjection(param);
		}
		return defaultvalue;
	}
	
	/**
	 * 拼装SQL的order by语句
	 * @param prefix
	 * @return
	 */
	@NotAction
	public String getOrderBySQL(String prefix) {
		return getOrderBySQL(prefix, "");
	}
	
	@NotAction
	public String getOrderBySQL(String prefix, String defaultstr) {
		String orderby = getParaTrans("orderby");
		if(StringUtils.hasText(orderby)) {
			return " ORDER BY " + orderby;
		}
		return defaultstr;
	}
	/**
	 * 拼装SQL的where语句
	 * @param para id
	 * @param prefixAndName p.id
	 * @param stype number
	 * @return and p.id = ***
	 
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
					sql += " and "+ prefixAndName + info;
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
				}
				sql += " and "+ prefixAndName + " " + info;
			} else if(info.contains("%")) {
				sql += " and "+ prefixAndName + " like '" + info + "'";
			} else {
				if(StringUtils.hasText(stype) && stype.equals("number")) {
					sql += " and "+ prefixAndName + " = " + info;
				} else {
					//sql += " and "+ prefixAndName + " like '%" + info + "%'";
					sql += " and "+ prefixAndName + " = '" + info + "'";
				}
			}
		}
		//System.out.println("search sql : " + para+","+prefixAndName+", "+getParaTrans(para)+"----"+sql);
		return sql;
	}
	*/
	
	/**
	 * 获取时间段，并组装成sql语句, 数据库中time以bigint保存
	 * @param key
	 * @param st
	 * @param et
	 * @return
	 * @throws DataFormatException 
	 */
	protected String getTimeSQLWithLong(String key, String st, String et) throws DataFormatException {
		String stime = getPara(st);
		String etime = getPara(et);
		if(StringUtils.hasText(stime) && StringUtils.hasText(etime)) {
			return " and " + key + " BETWEEN "+ DateTimeUtils.tolong(stime) 
					+ " AND " + DateTimeUtils.tolong(etime);
		}
		return "";
	}
	
	/**
	 * 获取时间段，并组装成sql语句, 数据库中time以datetime保存
	 * @param key
	 * @param st
	 * @param et
	 * @return
	 */
	protected String getTimeSQL(String key, String st, String et) {
		String stime = getPara(st);
		String etime = getPara(et);
		if(StringUtils.hasText(stime) && StringUtils.hasText(etime)) {
			return " and " + key + " BETWEEN '"+ stime + "' AND '" + etime + "'";
		}
		return "";
	}
	
	protected String idsToList(String[] model_ids, String split) {
		String idlist = "";
		if(model_ids==null || model_ids.length < 1) return idlist;
		for (String str : model_ids) {
			if(StringUtils.hasText(str)) idlist += str + split;
		}
		idlist = idlist.substring(0, idlist.length()-1);
		return idlist;
	}
	
	/**
	 * 获取对象数组
	 * @param modelClass
	 * @return
	 */
	@NotAction
	public <T> List<T> getModels(Class<T> modelClass) {
		return getModels(modelClass, StrKit.firstCharToLowerCase(modelClass.getSimpleName()));
	}

	/**
	 * 获取前端传来的数组对象并响应成Model列表
	 */
	@NotAction
	public <T> List<T> getModels(Class<T> modelClass, String modelName) {
		List<String> indexes = getIndexes(modelName);
		List<T> list = new ArrayList<T>();
		for (String index : indexes) {
			T m = getModel(modelClass, modelName + "[" + index + "]");
			if (m != null) {
				list.add(m);
			}
		}
		return list;
	}
	
	/**
	 * 提取model对象数组的标号
	 */
	private List<String> getIndexes(String modelName) {
		// 提取标号
		List<String> list = new ArrayList<String>();
		String modelNameAndLeft = modelName + "[";
		Map<String, String[]> parasMap = getRequest().getParameterMap();
		for (Map.Entry<String, String[]> e : parasMap.entrySet()) {
			String paraKey = e.getKey();
			if (paraKey.startsWith(modelNameAndLeft)) {
				String no = paraKey.substring(paraKey.indexOf("[") + 1, paraKey.indexOf("]"));
				if (!list.contains(no)) {
					list.add(no);
				}
			}
		}
		return list;
	}
	
	@NotAction
	public boolean keepPara(String attr) {
		boolean kp = getParaToBoolean("kp", true);
		if(kp) {
			if(StrKit.notBlank(attr)) {
				setAttr(attr, getParaMap());
			} else {
				keepPara();
			}
			return true;
		}
		return false;
	}
	
	@NotAction
	public WidgetResult operateWidget() {
		String akey = getPara(0)==null ? getPara("request") : getPara(0);
		if(StrKit.notBlank(akey)) {
			Widget action = WidgetManager.me().getWidget(akey);
			if(action!=null) {
				return action.execute(this);
			}
		}
		return null;
	}
	
	@NotAction
	public void renderWidget() {
		WidgetManager.me().render(this);
	}
	
	@NotAction
	public void renderWidget(String templatePath) {
		WidgetManager.me().render(this, templatePath);
	}
	
	/**
	 * 通过p参数render
	 * @param flag
	 */
	@NotAction
	public boolean renderOfPage(String syspath) {
		String html = getPara("p");
		if(StrKit.notBlank(html)) {
			render(syspath + html + ".html");
			return true;
		}
		return false;
	}
	
	@NotAction
	public boolean renderOfPageAtInf() {
		return renderOfPage("/WEB-INF/");
	}
	
	@NotAction
	public boolean renderOfPageAtRoot() {
		return renderOfPage("/");
	}
	
	@NotAction
	public boolean renderOfPageAtApp() {
		return renderOfPage("/WEB-INF/app/");
	}
	
	/**
	 * 通过at参数render
	 * @param flag
	 */
	@NotAction
	public void renderAt(boolean flag) {
		String at = getPara("at");
		if(!StringUtils.hasText(at) || at.equalsIgnoreCase("json")) {
			renderJson(flag);
		} else {
			redirect(at);
		}
	}
	
	@NotAction
	public void renderAt(Object obj) {
		String at = getPara("at");
		if(!StringUtils.hasText(at) || at.equalsIgnoreCase("json")) {
			renderJson(obj);
		} else {
			redirect(at);
		}
	}
	
	@NotAction
	public void renderAt(boolean flag, String type, String info) {
		String at = getPara("at");
		if(!StringUtils.hasText(at) || at.equalsIgnoreCase("json")) {
			renderJsonMsg(flag, type, info);
		} else {
			redirect(at);
		}
	}
	
	@NotAction
	public void renderProxy(String targetName, String targetUri) {
		render(GatewayRender.by(targetName, targetUri));
	}
	
	public void renderGateway(String targetName, String targetUri) {
		render(GatewayRender.by(targetName, targetUri));
	}
	
	@NotAction
	public void renderResultJson(int code, String message, Object data) {
		renderJson(new AjaxResult(code, message, data));
	}
	
	@NotAction
	public void renderResultJson(int code, String type, String message, Object data) {
		renderJson(new AjaxResult(code, type, message, data));
	}
	
	@NotAction
	public void renderAjaxResultForSuccess() {
		renderAjaxResult("success", 0, null);
	}

	public void renderAjaxResultForSuccess(String message) {
		renderAjaxResult(message, 0, null);
	}

	public void renderAjaxResultForSuccess(String message, Object data) {
		renderAjaxResult(message, 0, data);
	}

	@NotAction
	public void renderAjaxResultForError() {
		renderAjaxResult("error", 1, null);
	}

	public void renderAjaxResultForError(String message) {
		renderAjaxResult(message, 1, null);
	}

	public void renderAjaxResult(String message, int errorCode) {
		renderAjaxResult(message, errorCode, null);
	}

	public void renderAjaxResult(String message, int errorCode, Object data) {
		LambkitResult ar = new LambkitResult(errorCode, message, data);
		if (isIEBrowser()) {
			render(new JsonRender(ar).forIE());
		} else {
			renderJson(ar);
		}
	}

	/**
	 * 跳转错误页
	 */
	@NotAction
	public void renderError(String url, String msg, Integer... time) {
		this.setAttr("url", url);
		this.setAttr("error", msg);
		this.setAttr("the_time", time.length==0 ? 6 : time[0].intValue()); //默认6秒
		render("/commons/error.html");
	}
	
	protected void renderJsonMsg(boolean flag, String type, String info) {
		/*
		this.setAttr("flag", flag);
		this.setAttr("type", type);
		this.setAttr("info", info);
		*/
		renderJson(new AjaxResult(flag ? 1 : 0, type, info, null));
	}
	
	@Override
	public void renderJson() {
		// TODO Auto-generated method stub
		if(getAttr("REQUEST")!=null || getAttr("jp.menu")!=null) {
			removeAttr("REQUEST");
			removeAttr("jp.menu");//
			removeAttr("jp.menus");//
			removeAttr("i18n");//Class com.jfinal.json.JFinalJson can not access a member of class java.util.Collections$EmptySet with modifiers "public"
		}
		/*
		java.util.Enumeration<String> enu = getAttrNames();
		while (enu.hasMoreElements()) {
			String string = (String) enu.nextElement();
			System.out.println(string);
		}*/
		super.renderJson();
	}
	
	public void renderImage(String imageFileName) {
		render(new MimeTypeRender(imageFileName));
	}
	
	@NotAction
	public boolean autoList(MgrTable tbc) {
		if (tbc == null) {
			return false;
		}
		
		String fldname = getPara("fldname");
		String keyword = getPara("keyword");
		
		if(StringUtils.hasText(fldname)) {
			ConditionBuilder cb = getConditionsSQL(tbc).build("");
			String sql = " from \""+tbc.getName()+"\" where 1=1 " + cb.getSql();
			if(StringUtils.hasText(keyword)) {
				sql += " and \""+fldname+"\" like '" + keyword + "%'";
			} else {
				sql += " and \""+fldname+"\" is not null and \""+fldname+"\" <> '' group by \""+fldname+"\" ORDER BY \""+fldname+"\" DESC";
			}
			Page<Record> m = Db.paginate(1, 10, "select \""+fldname+"\" as value ", sql);
			renderJson(m.getList());
			return true;
		}
		return false;
	}
	
	protected ConditionBuilder getConditionsSQL(MgrTable tbc) {
		ConditionBuilder builder = new ConditionBuilder();
		//ConditonsParam sqlParam = new ConditonsParam(new Conditions());
		List<? extends IField> flds = tbc.getFieldList();
		for(int i=0; i<flds.size(); i++) {
			IField fld = flds.get(i);
			if(fld.getIsselect().toUpperCase().equals("Y"))
			{
				builder.append(fld.getName(), getParaTrans(fld.getName()), fld.getDatatype());
			}
		}
		//ConditonsParam sqlParam = builder.get(prefix);
        //System.out.println("getConditionsSQL sql:" + sqlParam.getSql());
		return builder;
	}
	
	/**
	 * 是否是手机浏览器
	 * 
	 * @return
	 */
	public boolean isMoblieBrowser() {
		return RequestUtils.isMoblieBrowser(getRequest());
	}

	/**
	 * 是否是微信浏览器
	 * 
	 * @return
	 */
	public boolean isWechatBrowser() {
		return RequestUtils.isWechatBrowser(getRequest());
	}

	/**
	 * 是否是IE浏览器
	 * 
	 * @return
	 */
	public boolean isIEBrowser() {
		return RequestUtils.isIEBrowser(getRequest());
	}

	/**
     * 是否是multpart的请求（带有文件上传的请求）
     *
     * @return
     */
    @NotAction
    public boolean isMultipartRequest() {
        return RequestUtils.isMultipartRequest(getRequest());
    }
	
    /**
     * 是否是ajax请求
     *
     * @return
     */
    @NotAction
    public boolean isAjaxRequest() {
        return RequestUtils.isAjaxRequest(getRequest());
    }
	
	@NotAction
	public String getSystemPath() {
		return "http://" + getRequest().getRemoteHost() + ":"
				+ getRequest().getLocalPort() + getRequest().getContextPath();
	}
	
	protected boolean isPOST() {
		return getRequest().getMethod().equals("POST");
	}
	
	protected boolean isGET() {
		return getRequest().getMethod().equals("GET");
	}
	
	protected boolean isMethod(String methodName) {
		return getRequest().getMethod().equals(methodName);
	}
	
	protected String ctx() {
		return getRequest().getContextPath();
	}
	
	protected String ctxd() {
		String ctx = getRequest().getContextPath();
		ctx = StrKit.notBlank(ctx) ? ctx.substring(1) + "/" : ctx;
		return ctx;
	}
}
