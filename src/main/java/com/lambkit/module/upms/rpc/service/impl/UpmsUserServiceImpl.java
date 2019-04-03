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
package com.lambkit.module.upms.rpc.service.impl;

import com.lambkit.common.service.BaseModelServiceImpl;
import com.lambkit.common.util.ClassNewer;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.rpc.api.UpmsUserService;
import com.lambkit.module.upms.rpc.model.UpmsUser;
import com.lambkit.module.upms.rpc.sql.UpmsUserCriteria;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-10-25
 * @version 1.0
 * @since 1.0
 * ${tbcnn}
 */
public class UpmsUserServiceImpl extends BaseModelServiceImpl<UpmsUser> implements UpmsUserService {
	
	private UpmsUser DAO = null;
	
	public UpmsUser dao() {
		if(DAO==null) {
			DAO = ClassNewer.singleton(UpmsUser.class);
		}
		return DAO;
	}
	
	public UpmsUser createUser(UpmsUser upmsUser) {
        Example upmsUserExample = UpmsUserCriteria.create()
                .andUsernameEqualTo(upmsUser.getUsername()).example().setSelectSql(" count(*) ") ;
        Long count = count(upmsUserExample);
        if (count!=null && count > 0) {
            return null;
        }
        upmsUser.save();
        return upmsUser;
    }
}
