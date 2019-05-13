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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.handler.Handler;
import com.jfinal.plugin.IPlugin;
import com.lambkit.common.bean.ActionMapping;
import com.lambkit.common.bean.ActiveRecordBean;
import com.lambkit.common.bean.HandlerBean;
import com.lambkit.common.bean.InterceptorBean;
import com.lambkit.common.bean.PluginBean;
import com.lambkit.common.bean.RpcBean;
import com.lambkit.common.bean.TableMappingBean;
import com.lambkit.common.bean.TagBean;

public class LambkitBean {
	private Constants constants;
	private ActionMapping actionMapping;
	private List<PluginBean> plugins;
	private List<InterceptorBean> interceptors;
	private List<HandlerBean> handlers;
	private Map<String, TagBean> tags;
	//所有的表格
	//private Map<String, Table> tables;
	//private List<TableMeta> tableMetas;
	//所有的model
	private List<TableMappingBean> mappings;
	//所有的类
	private List<String> clazzs;
	//ActiveRecordPlugin list 数据库加载配置
	private List<ActiveRecordBean> activeRecords;
	private List<RpcBean> rpcs;

	public void afterJFinalStart() {
		actionMapping.initMapping();
		//initTables();
	}

	public void setRoutes(Routes routes) {
		actionMapping = new ActionMapping(routes);
	}

	public void setPlugins(Plugins plugins) {
		if (plugins == null)
			return;
		if (this.plugins == null) {
			this.plugins = new ArrayList<>();
		}
		List<IPlugin> ips = plugins.getPluginList();
		for (IPlugin iPlugin : ips) {
			this.plugins.add(new PluginBean(iPlugin.getClass()));
		}
	}

	public void setHandlers(Handlers handlers) {
		if (handlers == null)
			return;
		if (this.handlers == null) {
			this.handlers = new ArrayList<>();
		}
		List<Handler> hds = handlers.getHandlerList();
		for (Handler handler : hds) {
			this.handlers.add(new HandlerBean(handler.getClass().getName()));
		}
	}

	public void addTag(String name, TagBean info) {
		if (tags == null) {
			tags = new HashMap<String, TagBean>();
		}
		tags.put(name, info);
	}
	
	public void addActiveRecord(ActiveRecordBean ari) {
		if(activeRecords==null) {
			activeRecords = new ArrayList<>();
		}
		activeRecords.add(ari);
	}
	
	public void addRpc(RpcBean rpc) {
		if(rpcs==null) {
			rpcs = new ArrayList<>();
		}
		rpcs.add(rpc);
	}

	public void addMapping(TableMappingBean mapping) {
		if(mappings==null) {
			mappings = new ArrayList<>();
		}
		mappings.add(mapping);
	}
	/**
	 * tables init
	
	public void initTables() {
		tables = new HashMap<>();
		/*
		Set<Class<?>> tableClasses = ClassUtils.scanPackageBySuper("", false, Model.class);
		for (Class<?> clazz : tableClasses) {
			Table table = TableMapping.me().getTable((Class<? extends Model>) clazz);
			if (table != null) {
				tables.put(table.getName(), table);
			}
		}
		for (TableMappingInfo mapping : getMappings()) {
			try {
				Table table = TableMapping.me().getTable((Class<? extends Model>) Class.forName(mapping.getClassName()));
				if (table != null) {
					tables.put(mapping.getTableName(), table);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Map<String, Table> getTables() {
		if (tables == null)
			initTables();
		return tables;
	}

	public void setTables(Map<String, Table> tables) {
		this.tables = tables;
	}
	*/
	
	public Constants getConstants() {
		return constants;
	}

	public void setConstants(Constants constants) {
		this.constants = constants;
	}

	public List<PluginBean> getPlugins() {
		return plugins;
	}

	public void setPlugins(List<PluginBean> plugins) {
		this.plugins = plugins;
	}

	public List<InterceptorBean> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(List<InterceptorBean> interceptors) {
		this.interceptors = interceptors;
	}

	public Map<String, TagBean> getTags() {
		return tags;
	}

	public void setTags(Map<String, TagBean> tags) {
		this.tags = tags;
	}

	public ActionMapping getActionMapping() {
		return actionMapping;
	}

	public void setActionMapping(ActionMapping actionMapping) {
		this.actionMapping = actionMapping;
	}

	public List<HandlerBean> getHandlers() {
		return handlers;
	}

	public void setHandlers(List<HandlerBean> handlers) {
		this.handlers = handlers;
	}

	public List<String> getClazzs() {
		if(clazzs==null) {
			clazzs = new ArrayList<>();
		}
		return clazzs;
	}

	public void setClazzs(List<String> clazzs) {
		this.clazzs = clazzs;
	}

	public List<ActiveRecordBean> getActiveRecords() {
		if(activeRecords==null) {
			activeRecords = new ArrayList<>();
		}
		return activeRecords;
	}

	public void setActiveRecords(List<ActiveRecordBean> activeRecords) {
		this.activeRecords = activeRecords;
	}

	public List<RpcBean> getRpcs() {
		if(rpcs==null) {
			rpcs = new ArrayList<>();
		}
		return rpcs;
	}

	public void setRpcs(List<RpcBean> rpcs) {
		this.rpcs = rpcs;
	}

	public List<TableMappingBean> getMappings() {
		if(mappings==null) {
			mappings = new ArrayList<>();
		}
		return mappings;
	}

	public void setMappings(List<TableMappingBean> mappings) {
		this.mappings = mappings;
	}
}
