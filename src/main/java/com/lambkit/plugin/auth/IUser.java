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
}
