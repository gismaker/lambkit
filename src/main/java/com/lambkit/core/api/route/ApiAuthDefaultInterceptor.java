package com.lambkit.core.api.route;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;

import com.lambkit.common.util.StringUtils;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.plugin.auth.IUser;

public class ApiAuthDefaultInterceptor extends ApiAuthInterceptor {

	IUser theUser = null;
	
	protected IUser getUser(HttpServletRequest request) {
		if(theUser==null) {
			String appid = request.getHeader("appid");
			String username = request.getHeader("author");
			String sessionId = request.getHeader("sessionid");
			theUser = AuthManager.me().getService().authenticate(appid, username, sessionId);
		}
		return theUser;
	}
	
	
	@Override
	protected boolean hasGuest(ApiInvocation inv) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected boolean hasUser(ApiInvocation inv) {
		// TODO Auto-generated method stub
		IUser user = getUser(inv.getRequest());
		return user==null ? false : true;
	}

	@Override
	protected boolean hasAuthentication(ApiInvocation inv) {
		// TODO Auto-generated method stub
		IUser user = getUser(inv.getRequest());
		return user==null ? false : true;
	}

	@Override
	protected boolean hasRoles(ApiInvocation inv, String[] value, Logical logical) {
		// TODO Auto-generated method stub
		IUser user = getUser(inv.getRequest());
		if(user==null) {
			return false;
		}
		if(value.length < 1) return true;
		String roles = StringUtils.join(value, ",", null, null);
		if(logical==Logical.AND) {
			return user.hasAllRoles(roles);
		} else {
			return user.hasAnyRoles(roles);
		}
	}

	@Override
	protected boolean hasPermissions(ApiInvocation inv, String[] value, Logical logical) {
		// TODO Auto-generated method stub
		IUser user = getUser(inv.getRequest());
		if(user==null) {
			return false;
		}
		if(value.length < 1) return true;
		String perms = StringUtils.join(value, ",", null, null);
		if(logical==Logical.AND) {
			return user.hasAllRules(perms);
		} else {
			return user.hasAnyRules(perms);
		}
	}


}
