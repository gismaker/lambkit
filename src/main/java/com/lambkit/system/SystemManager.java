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
package com.lambkit.system;

import java.util.Map;

import com.lambkit.system.db.DataSourceInfo;
import com.lambkit.system.db.DataSourceInfoBuilder;
import com.lambkit.system.info.ActiveRecordInfo;
import com.lambkit.system.info.RpcInfo;
import com.lambkit.system.info.TableMappingInfo;
import com.lambkit.system.info.TagInfo;

public class SystemManager {

	private SystemInfo info = new SystemInfo();
	private Map<String, DataSourceInfo> dataSources;
	
	private static final SystemManager me = new SystemManager();

	private SystemManager() {
	}
	
	public static SystemManager me() {
		return me;
	}
	
	public void init() {
		info.afterJFinalStart();
		DataSourceInfoBuilder builder = new DataSourceInfoBuilder();
		dataSources = builder.buildMap(info);
	}

	public SystemInfo getInfo() {
		return info;
	}
	
	public void addTag(String name, TagInfo info) {
		getInfo().addTag(name, info);
	}

	public void addActiveRecord(ActiveRecordInfo ari) {
		getInfo().addActiveRecord(ari);
	}
	
	public void addRpc(RpcInfo rpc) {
		getInfo().addRpc(rpc);
	}
	
	public void addMapping(TableMappingInfo mapping) {
		getInfo().addMapping(mapping);
	}

	public Map<String, DataSourceInfo> getDataSources() {
		return dataSources;
	}

	public void setDataSources(Map<String, DataSourceInfo> dataSources) {
		this.dataSources = dataSources;
	}
}
