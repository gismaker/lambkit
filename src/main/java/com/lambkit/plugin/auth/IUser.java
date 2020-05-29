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

import com.jfinal.plugin.activerecord.Model;
import com.lambkit.plugin.auth.IUser;

public interface IUser {
	
	/**
	 * 获取User模型
	 * @return
	 */
	public Model<?> getModel();
	
	public String getClassName();

	/**
	 * 用户登录名称
	 * @return
	 */
	public String getName();
	
	/**
	 * 用户id号
	 * @return
	 */
	public Object getId();
	
	/**
	 * 显示的名称
	 * @return
	 */
	public String getShowName();
	
	/**
	 * UUID
	 * @return
	 */
	public String getUuid();
	
	/**
	 * 用户数据表名称
	 * @return
	 */
	public String getTableName();
	
	/**
	 * 检查密码是否与本用户相同
	 * @param password 为加密前的密码（明码）
	 * @return
	 */
	public boolean checkPassword(String password);
	
	/**
	 * 获取密码
	 * @return
	 */
	public String getPassword();
	
	public String getSalt();
	
	/**
	 * 修改密码
	 * @param password 未加密
	 */
	public void updatePassword(String password);
	/**
	 * 新增密码
	 * @param password
	 */
	public void createPassword(String password);
	/**
	 * 用户类型
	 * @return
	 */
	public int getType();
	
	/**
	 * 是否锁定
	 * @return
	 */
	public boolean isLocked();
	
	/**
	 * 去掉密码相关信息，用于分享用户信息
	 * @return
	 */
	public IUser removeSecretInfo();
	
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
}
