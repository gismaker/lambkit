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
package com.lambkit.common;

import com.lambkit.common.bean.ActiveRecordBean;
import com.lambkit.common.bean.RpcBean;
import com.lambkit.common.bean.TableMappingBean;
import com.lambkit.common.bean.TagBean;

public class LambkitManager {

	private LambkitBean info = new LambkitBean();
	
	private static final LambkitManager me = new LambkitManager();

	private LambkitManager() {
	}
	
	public static LambkitManager me() {
		return me;
	}
	
	public void init() {
		info.afterJFinalStart();
	}

	public LambkitBean getInfo() {
		return info;
	}
	
	public void addTag(String name, TagBean info) {
		getInfo().addTag(name, info);
	}

	public void addRpc(RpcBean rpc) {
		getInfo().addRpc(rpc);
	}
	
	public void addActiveRecord(ActiveRecordBean ari) {
		getInfo().addActiveRecord(ari);
	}
	
	public void addMapping(TableMappingBean mapping) {
		getInfo().addMapping(mapping);
	}
}
