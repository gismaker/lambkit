package com.lambkit.core.api.route;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.StrKit;
import com.lambkit.Lambkit;
import com.lambkit.plugin.jwt.Auth;
import com.lambkit.plugin.jwt.IJwtAble;
import com.lambkit.plugin.jwt.JwtConfig;
import com.lambkit.plugin.jwt.JwtKit;

/**
 * Api的用户权限处理
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.core.api.route
 */
public class ApiJwtAuthInterceptor implements ApiInterceptor {

	@Override
	public void intercept(ApiInvocation inv) {
		// TODO Auto-generated method stub
		//获取接口
		Method method = inv.getMethod();
		if (handleMethod(method, inv.getRequest())) {
            inv.invoke();
        } else {
        	inv.setErrorValue("token error");
        }
	}
	
	/**
     * 权限角色判断机制
     *
     * @param method
     * @param controller
     * @return
     */
    private boolean handleMethod(Method method, HttpServletRequest request) {
        // 判断是否有这个注解
        if (method.isAnnotationPresent(Auth.class)) {
            Auth auth = method.getAnnotation(Auth.class);
            IJwtAble jwt_me = (IJwtAble) getMe(request); // 从请求头解析出me
            if (jwt_me == null) return false;            // 直接无权访问

            String[] handlerArray = auth.withForces();
            if (handlerArray != null && handlerArray.length > 0) return withForcesHandle(handlerArray, jwt_me);

            handlerArray = auth.withRoles();
            if (handlerArray != null && handlerArray.length > 0) return withRolesHandle(handlerArray, jwt_me);

            handlerArray = auth.hasForces();
            if (handlerArray != null && handlerArray.length > 0)
                return hasForcesHandle(handlerArray, jwt_me);
            handlerArray = auth.hasRoles();
            return hasRolesHandle(handlerArray, jwt_me);
        }
        // 没有这个注解直接放行
        return true;
    }

    /**
     * 类级别判断机制
     *
     * @param clazz
     * @param controller
     * @return
     */
    private boolean handleClass(Class clazz, Method method, HttpServletRequest request) {
        // 方法上如果有了以方法上的为准
        if ( !method.isAnnotationPresent(Auth.class) && clazz.isAnnotationPresent(Auth.class)) {
            Auth auth = (Auth) clazz.getAnnotation(Auth.class);
            IJwtAble jwt_me = (IJwtAble) getMe(request); // 从请求头解析出me
            if (jwt_me == null) return false;   // 直接无权访问

            String[] handlerArray = auth.withForces();
            if (handlerArray != null && handlerArray.length > 0)
                return withForcesHandle(handlerArray, jwt_me);

            handlerArray = auth.withRoles();
            if (handlerArray != null && handlerArray.length > 0)
                return withRolesHandle(handlerArray, jwt_me);

            handlerArray = auth.hasForces();
            if (handlerArray != null && handlerArray.length > 0)
                return hasForcesHandle(handlerArray, jwt_me);

            handlerArray = auth.hasRoles();
            return hasRolesHandle(handlerArray, jwt_me);
        }
        // 没有这个注解直接放行
        return true;
    }

    /**
     * 优先级第一--并关系权限处理方案
     *
     * @param withForces
     * @param me
     * @return
     */
    private boolean withForcesHandle(String[] withForces, IJwtAble me) {
    	Set<String> forces = me.getForces();
        if (null != forces && forces.size() > 0 && forces.containsAll(Arrays.asList(withForces))) return true;
        return false;
    }

    /**
     * 优先级第二--并关系角色处理方案
     *
     * @param withRoles
     * @param me
     * @return
     */
    private boolean withRolesHandle(String[] withRoles, IJwtAble me) {
        Set<String> roles = me.getRoles();
        if (null != roles && roles.containsAll(Arrays.asList(withRoles))) return true;
        return false;
    }

    /**
     * 优先级第三--或关系权限处理方案
     *
     * @param hasForces
     * @param me
     * @return
     */
    private boolean hasForcesHandle(String[] hasForces, IJwtAble me) {
    	Set<String> forces = me.getForces();
        for (String force : hasForces)
            if (forces.contains(force)) {
                return true;
            }
        return false;
    }

    /**
     * 优先级第四--或关系角色处理方案
     *
     * @param hasRoles
     * @param me
     * @return
     */
    private boolean hasRolesHandle(String[] hasRoles, IJwtAble me) {
    	Set<String> roles = me.getRoles();
        for (String role : hasRoles)
            if (roles.contains(role)) {
                return true;
            }
        return false;
    }
    
    /**
	 * 从请求头解析出me
	 *
	 * @param request
	 * @return
	 */
	protected IJwtAble getMe(HttpServletRequest request) {
		IJwtAble me = (IJwtAble) request.getAttribute("me");
		if (null != me)
			return me;
		JwtConfig config = Lambkit.config(JwtConfig.class);
		String header = config.getHeader();
		String tokenPrefix = config.getTokenPrefix();
		String authHeader = request.getHeader(header);
		if (StrKit.isBlank(authHeader) || authHeader.length() < tokenPrefix.length())
			return null;
		String authToken = authHeader.substring(tokenPrefix.length());
		String jwtUser = JwtKit.getJwtUser(authToken); // 从token中解析出jwtAble
		if (jwtUser != null) {
			Date created = JwtKit.getCreatedDateFormToken(authToken);
			me = JwtKit.getJwtBean(jwtUser, created);
			request.setAttribute("me", me);
		}
		return me;
	}
}
