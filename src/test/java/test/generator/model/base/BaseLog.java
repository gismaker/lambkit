package test.generator.model.base;

import com.jfinal.plugin.activerecord.IBean;

import com.lambkit.common.dao.BaseModel;

@SuppressWarnings("serial")
public abstract class BaseLog<M extends BaseLog<M>> extends BaseModel<M> implements IBean {

	public String tableName() {
		return "upms_log";
	}
	
	public void setLogId(java.lang.Integer logId) {
		set("log_id", logId);
	}
	
	public java.lang.Integer getLogId() {
		return getInt("log_id");
	}

	public void setDescription(java.lang.String description) {
		set("description", description);
	}
	
	public java.lang.String getDescription() {
		return getStr("description");
	}

	public void setUsername(java.lang.String username) {
		set("username", username);
	}
	
	public java.lang.String getUsername() {
		return getStr("username");
	}

	public void setStartTime(java.lang.Long startTime) {
		set("start_time", startTime);
	}
	
	public java.lang.Long getStartTime() {
		return getLong("start_time");
	}

	public void setSpendTime(java.lang.Integer spendTime) {
		set("spend_time", spendTime);
	}
	
	public java.lang.Integer getSpendTime() {
		return getInt("spend_time");
	}

	public void setBasePath(java.lang.String basePath) {
		set("base_path", basePath);
	}
	
	public java.lang.String getBasePath() {
		return getStr("base_path");
	}

	public void setUri(java.lang.String uri) {
		set("uri", uri);
	}
	
	public java.lang.String getUri() {
		return getStr("uri");
	}

	public void setUrl(java.lang.String url) {
		set("url", url);
	}
	
	public java.lang.String getUrl() {
		return getStr("url");
	}

	public void setMethod(java.lang.String method) {
		set("method", method);
	}
	
	public java.lang.String getMethod() {
		return getStr("method");
	}

	public void setParameter(java.lang.String parameter) {
		set("parameter", parameter);
	}
	
	public java.lang.String getParameter() {
		return getStr("parameter");
	}

	public void setUserAgent(java.lang.String userAgent) {
		set("user_agent", userAgent);
	}
	
	public java.lang.String getUserAgent() {
		return getStr("user_agent");
	}

	public void setIp(java.lang.String ip) {
		set("ip", ip);
	}
	
	public java.lang.String getIp() {
		return getStr("ip");
	}

	public void setResult(java.lang.String result) {
		set("result", result);
	}
	
	public java.lang.String getResult() {
		return getStr("result");
	}

	public void setPermissions(java.lang.String permissions) {
		set("permissions", permissions);
	}
	
	public java.lang.String getPermissions() {
		return getStr("permissions");
	}

}
