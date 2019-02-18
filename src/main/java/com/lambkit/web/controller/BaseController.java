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

import com.jfinal.aop.Before;
import com.jfinal.core.ActionException;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.NotAction;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.common.base.ResultJson;
import com.lambkit.common.util.DateTimeUtils;
import com.lambkit.common.util.RequestUtils;
import com.lambkit.common.util.StringUtils;
import com.lambkit.core.http.proxy.ProxyKit;
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器基类
 */
public abstract class BaseController extends Controller {

	private final static Logger _log = LoggerFactory.getLogger(BaseController.class);

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
     * 获取当前网址
     *
     * @return
     */
    @Before(NotAction.class)
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
	@Before(NotAction.class)
	public MgrTable getBase(int type, boolean attr, String orderby) {
		//convert tbid type (int) to Long
		Long tbid=getParaToLong(0) == null ? getParaToLong("tag", null) : getParaToLong(0);
		if(tbid!=null) {
			return getTable(tbid, type, attr, orderby);
		}
		return null;
	}
	
	@Before(NotAction.class)
	public MgrTable getBase(boolean attr) {
		return getBase(MgrConstants.NONE, attr, null);
	}
	
	@Before(NotAction.class)
	public MgrTable getBase(int type, boolean attr) {
		return getBase(type, attr, null);
	}
	
	@Before(NotAction.class)
	public MgrTable getBase() {
		return getBase(MgrConstants.NONE, true, null);
	}
	
	@Before(NotAction.class)
	public MgrTable getBase(int type) {
		return getBase(type, true, null);
	}
	
	@Before(NotAction.class)
	public MgrTable getBase(String orderby) {
		return getBase(MgrConstants.NONE, true, orderby);
	}
	
	@Before(NotAction.class)
	public MgrTable getBase(int type, String orderby) {
		return getBase(type, true, orderby);
	}
	
	private MgrdbService getTableConfigService() {
		return MgrdbManager.me().getService();
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
	
	@Before(NotAction.class)
	public BigInteger[] getParaValuesToBigInteger(String name) {
		String[] values = getRequest().getParameterValues(name);
		if (values == null)
			return null;
		BigInteger[] result = new BigInteger[values.length];
		for (int i = 0; i < result.length; i++)
			result[i] = new BigInteger(values[i]);
		return result;
	}

	@Before(NotAction.class)
	public BigInteger getParaToBigInteger() {
		return toBigInteger(getPara(), null);
	}

	@Before(NotAction.class)
	public BigInteger getParaToBigInteger(int index) {
		return toBigInteger(getPara(index), null);
	}

	@Before(NotAction.class)
	public BigInteger getParaToBigInteger(int index, BigInteger defaultValue) {
		return toBigInteger(getPara(index), defaultValue);
	}

	@Before(NotAction.class)
	public BigInteger getParaToBigInteger(String name) {
		return toBigInteger(getRequest().getParameter(name), null);
	}

	@Before(NotAction.class)
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
	
	/**
	 * 防止参数的sql注入  
	 * @param name
	 * @return
	 */
	@Before(NotAction.class)
	public String getParaTrans(String name) {
		String param  = this.getPara(name);
		if(param!=null && param.trim().length() > 0) {
			return StringUtils.transactSQLInjection(param);
		}
		return null;
	}
	
	@Before(NotAction.class)
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
	@Before(NotAction.class)
	public String getOrderBySQL(String prefix) {
		return getOrderBySQL(prefix, "");
	}
	
	@Before(NotAction.class)
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
	@Before(NotAction.class)
	public <T> List<T> getModels(Class<T> modelClass) {
		return getModels(modelClass, StrKit.firstCharToLowerCase(modelClass.getSimpleName()));
	}

	/**
	 * 获取前端传来的数组对象并响应成Model列表
	 */
	@Before(NotAction.class)
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
	
	@Before(NotAction.class)
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
	
	@Before(NotAction.class)
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
	
	@Before(NotAction.class)
	public void renderWidget() {
		WidgetManager.me().render(this);
	}
	
	@Before(NotAction.class)
	public void renderWidget(String templatePath) {
		WidgetManager.me().render(this, templatePath);
	}
	
	/**
	 * 通过p参数render
	 * @param flag
	 */
	@Before(NotAction.class)
	public boolean renderOfPage(String syspath) {
		String html = getPara("p");
		if(StrKit.notBlank(html)) {
			render(syspath + html + ".html");
			return true;
		}
		return false;
	}
	
	@Before(NotAction.class)
	public boolean renderOfPageAtInf() {
		return renderOfPage("/WEB-INF/");
	}
	
	@Before(NotAction.class)
	public boolean renderOfPageAtRoot() {
		return renderOfPage("/");
	}
	
	@Before(NotAction.class)
	public boolean renderOfPageAtApp() {
		return renderOfPage("/WEB-INF/app/");
	}
	
	/**
	 * 通过at参数render
	 * @param flag
	 */
	@Before(NotAction.class)
	public void renderAt(boolean flag) {
		String at = getPara("at");
		if(!StringUtils.hasText(at) || at.equalsIgnoreCase("json")) {
			renderJson(flag);
		} else {
			redirect(at);
		}
	}
	
	@Before(NotAction.class)
	public void renderAt(Object obj) {
		String at = getPara("at");
		if(!StringUtils.hasText(at) || at.equalsIgnoreCase("json")) {
			renderJson(obj);
		} else {
			redirect(at);
		}
	}
	
	@Before(NotAction.class)
	public void renderAt(boolean flag, String type, String info) {
		String at = getPara("at");
		if(!StringUtils.hasText(at) || at.equalsIgnoreCase("json")) {
			renderJsonMsg(flag, type, info);
		} else {
			redirect(at);
		}
	}
	
	@Before(NotAction.class)
	public void renderProxy(String targetName, String targetUri) {
		render(ProxyKit.render(targetName, targetUri));
	}
	
	@Before(NotAction.class)
	public void renderResultJson(int code, String message, Object data) {
		renderJson(new ResultJson(code, message, data));
	}
	
	@Before(NotAction.class)
	public void renderResultJson(int code, String type, String message, Object data) {
		renderJson(new ResultJson(code, type, message, data));
	}
	/**
	 * 跳转错误页
	 */
	@Before(NotAction.class)
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
		renderJson(new ResultJson(flag ? 1 : 0, type, info, null));
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
	
	@Before(NotAction.class)
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
     * 是否是multpart的请求（带有文件上传的请求）
     *
     * @return
     */
    @Before(NotAction.class)
    public boolean isMultipartRequest() {
        return RequestUtils.isMultipartRequest(getRequest());
    }
	
    /**
     * 是否是ajax请求
     *
     * @return
     */
    @Before(NotAction.class)
    public boolean isAjaxRequest() {
        return RequestUtils.isAjaxRequest(getRequest());
    }
	
	@Before(NotAction.class)
	public String getSystemPath() {
		return "http://" + getRequest().getRemoteHost() + ":"
				+ getRequest().getLocalPort() + getRequest().getContextPath();
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
