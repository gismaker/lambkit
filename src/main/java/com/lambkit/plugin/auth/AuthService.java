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
package com.lambkit.plugin.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.core.Controller;
import com.lambkit.common.LambkitResult;
import com.lambkit.plugin.auth.IUser;

public interface AuthService {
	
	String getSessionId(HttpServletRequest request);
	/**
	 * 角色服务
	 * @return
	 */
	RoleService getRoleService();
	/**
	 * 登录
	 * @param c
	 */
	LambkitResult login(HttpServletRequest request);
	LambkitResult login(Controller controller);
	LambkitResult login(HttpServletRequest request, String username, String password, boolean rememberMe);
	/**
	 * client无密认证
	 * @param username
	 */
	void login(HttpServletRequest request, String username);
	/**
	 * 退出
	 * @param c
	 */
	LambkitResult logout(HttpServletRequest request);
	LambkitResult logout(Controller controller);
	/**
	 * token验证用户
	 * @param appid 注册系统名称，单点登陆时需要
	 * @param username 用户名称
	 * @param sessionId 用户的token
	 * @return
	 */
	IUser authenticate(String appid, String username, String sessionId);
	/**
	 * 已登录的用户
	 *
	 * @return
	 */
	IUser getUser();
	/**
	 * 将用户密码加密
	 * @param password 明码,未加密
	 * @return
	 */
	String getPasswordSecurity(String username, String password);
	/**
	 * 获取登录的用户信息
	 * 
	 * @param userName
	 * @return
	 */
	IUser findByUsernameForLogin(String userName);
	/**
	 * 已认证通过的用户。不包含已记住的用户，这是与user标签的区别所在。与notAuthenticated搭配使用
	 *
	 * @return 通过身份验证：true，否则false
	 */
	boolean authenticated();
	/**
	 * 未认证通过用户，与authenticated标签相对应。与guest标签的区别是，该标签包含已记住用户。。
	 *
	 * @return 没有通过身份验证：true，否则false
	 */
	boolean notAuthenticated();
	/**
	 * 认证通过或已记住的用户。与guset搭配使用。
	 *
	 * @return 用户：true，否则 false
	 */
	boolean user();
	/**
	 * 验证当前用户是否为“访客”，即未认证（包含未记住）的用户。用user搭配使用
	 *
	 * @return 访客：true，否则false
	 */
	boolean guest();
	/**
	 * 判断rule权限是否为guest权限
	 * @param controlkey
	 * @return
	 */
	Boolean isGuestRule(String permission);
	/**
	 * 验证当前用户是否属于该角色？
	 *
	 * @param roleid
	 *            角色id
	 * @return 属于该角色：true，否则false
	 */
	Boolean hasRole(int roleid);
	/**
	 * 验证当前用户是否属于该角色？,使用时与lacksRole 搭配使用
	 *
	 * @param roleName
	 *            角色名
	 * @return 属于该角色：true，否则false
	 */
	Boolean hasRole(String roleName);
	/**
	 * 与hasRole标签逻辑相反，当用户不属于该角色时验证通过。
	 *
	 * @param roleName
	 *            角色名
	 * @return 不属于该角色：true，否则false
	 */
	Boolean lacksRole(String roleName);
	/**
	 * 验证当前用户是否属于以下任意一个角色。
	 *
	 * @param roleNames
	 *            角色列表
	 * @return 属于:true,否则false
	 */
	boolean hasAnyRoles(String roleNames);
	/**
	 * 验证当前用户是否属于以下所有角色。
	 *
	 * @param roleNames
	 *            角色列表
	 * @return 属于:true,否则false
	 */
	boolean hasAllRoles(String roleNames);
	/**
	 * 验证当前用户是否拥有指定权限
	 * @param ruleid
	 * @return
	 */
	Boolean hasRule(int ruleid);
	/**
	 * 验证当前用户是否拥有指定权限,使用时与lacksPermission 搭配使用
	 *
	 * @param permission
	 *            权限名
	 * @return 拥有权限：true，否则false
	 */
	boolean hasRule(String permission);
	/**
	 * 与hasPermission标签逻辑相反，当前用户没有制定权限时，验证通过。
	 *
	 * @param permission
	 *            权限名
	 * @return 拥有权限：true，否则false
	 */
	boolean lacksRule(String permission);
	/**
	 * 验证当前用户是否属于以下任意一个权限。
	 *
	 * @param permissions
	 *            权限列表
	 * @return 属于:true,否则false
	 */
	boolean hasAnyRules(String permissions);
	/**
	 * 验证当前用户是否属于以下所有权限。
	 *
	 * @param permissions
	 *            权限列表
	 * @return 属于:true,否则false
	 */
	boolean hasAllRules(String roleNames);
	/**
	 * 获取角色列表
	 * @param id
	 * @return
	 */
	List<?> getRoles(Object userid);
	/**
	 * 获取权限列表
	 * @param id
	 * @return
	 */
	List<?> getRules(Object userid);
}
