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
package com.lambkit.module.upms.client.auth;

import java.io.Serializable;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.lambkit.common.aop.AopKit;
import com.lambkit.common.util.EncryptUtils;
import com.lambkit.common.util.StringUtils;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.plugin.auth.IUser;
import com.lambkit.module.upms.common.UpmsConfig;
import com.lambkit.module.upms.rpc.model.UpmsRole;
import com.lambkit.module.upms.rpc.model.UpmsUser;
import com.lambkit.module.upms.rpc.api.UpmsApiService;
import com.lambkit.module.upms.rpc.service.impl.UpmsApiServiceImpl;

@SuppressWarnings("serial")
public class UpmsClientUser implements IUser, Serializable {
	
	private UpmsUser user;
	private int type = -1;
	
	public UpmsUser getUser() {
		return user;
	}

	public void setUser(UpmsUser user) {
		this.user = user;
	}

	public UpmsClientUser(UpmsUser user) {
		this.user = user;
	}

	@Override
	public Model<?> getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return user!=null ? user.getUsername() : null;
	}

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return user!=null ? user.getUserId() : null;
	}

	@Override
	public String getShowName() {
		// TODO Auto-generated method stub
		return user!=null ? user.getRealname() : null;
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return user.tableName();
	}

	@Override
	public boolean checkPassword(String password) {
		// TODO Auto-generated method stub
		if(user != null && StrKit.notBlank(password)) {
			String pswd = EncryptUtils.MD5(password + user.getSalt());
			return StrKit.notBlank(pswd) ? pswd.equals(user.getPassword()) : false;
		}
		return false;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user!=null ? user.getPassword() : null;
	}
	
	@Override
	public String getSalt() {
		// TODO Auto-generated method stub
		return user!=null ? user.getSalt() : null;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		if(type==-1 && user != null) {
			List<UpmsRole> role = getUpmsApiService().selectUpmsRoleByUpmsUserId(user.getUserId());
			if(role!=null && role.size() > 0) type = role.get(0).getRoleId().intValue();
		}
		return type;
	}
	
	private UpmsApiService getUpmsApiService() {
		UpmsConfig upmsConfig = ConfigManager.me().get(UpmsConfig.class);
		if("client".equals(upmsConfig.getType())) {
			return RpcKit.obtain(UpmsApiService.class);
		} else {
			return AopKit.newInstance(UpmsApiServiceImpl.class);
		}
    }

	@Override
	public boolean isLocked() {
		// TODO Auto-generated method stub
		return user.getLocked()==0 ? false : true;
	}
	
	@Override
	public IUser removeSecretInfo() {
		// TODO Auto-generated method stub
		if(user != null) {
			user.remove("password");
			user.remove("salt");
		}
		return this;
	}

	@Override
	public void updatePassword(String password) {
		// TODO Auto-generated method stub
		password = EncryptUtils.MD5(password + user.getSalt());
		user.setPassword(password);
	}

	@Override
	public void createPassword(String password) {
		// TODO Auto-generated method stub
		user.setSalt(StringUtils.uuid());
		password = EncryptUtils.MD5(password + user.getSalt());
		user.setPassword(password);
	}
	
}
