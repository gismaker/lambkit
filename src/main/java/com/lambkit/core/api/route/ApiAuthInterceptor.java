package com.lambkit.core.api.route;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;

/**
 * Api的用户权限处理
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.core.api.route
 */
public abstract class ApiAuthInterceptor implements ApiInterceptor {

	@Override
	public void intercept(ApiInvocation inv) {
		// TODO Auto-generated method stub
		//获取接口
		Method method = inv.getMethod();
		boolean flag = false;
		//获取注解
		RequiresGuest guest = method.getAnnotation(RequiresGuest.class);
		if(guest!=null) {
			flag = hasGuest(inv);
		}
		RequiresUser user = method.getAnnotation(RequiresUser.class);
		if(user!=null) {
			flag = hasUser(inv);
		}
		RequiresAuthentication auth = method.getAnnotation(RequiresAuthentication.class);
		if(auth!=null) {
			flag = hasAuthentication(inv);
		}
		RequiresRoles roles = method.getAnnotation(RequiresRoles.class);
		if(roles!=null) {
			flag = hasRoles(inv, roles.value(), roles.logical());
		}
		RequiresPermissions perms = method.getAnnotation(RequiresPermissions.class);
		if(perms!=null) {
			flag = hasPermissions(inv, perms.value(), perms.logical());
		}
		
		if(flag) {
			inv.invoke();
		} else {
			Object error = inv.getErrorValue();
			if(error!=null) {
				inv.setErrorValue("auth error");
			}
		}
	}

	protected abstract boolean hasGuest(ApiInvocation inv);
	protected abstract boolean hasUser(ApiInvocation inv);
	protected abstract boolean hasAuthentication(ApiInvocation inv);
	protected abstract boolean hasRoles(ApiInvocation inv, String[] value, Logical logical);
	protected abstract boolean hasPermissions(ApiInvocation inv, String[] value, Logical logical);
}
