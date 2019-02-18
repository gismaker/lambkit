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
package com.lambkit.module;

import com.lambkit.common.base.Consts;
import com.lambkit.core.event.Event;
import com.lambkit.core.event.EventListener;
import com.lambkit.core.event.annotation.Listener;

@Listener(action = Consts.EVENT_STARTED)
public class StartListener implements EventListener {

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		/*
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		//TimeUtils.startTime("start event print");
		System.out.println("ActiveRecord numbers: " + SystemManager.me().getInfo().getActiveRecords().size());
		for (ActiveRecordInfo info : SystemManager.me().getInfo().getActiveRecords()) {
			System.out.println("ActiveRecord@{config-name:" + info.getName() + ", db-name:" + info.getDbname() + "}");
		}
		for (Routes.Route route : SystemManager.me().getInfo().getActionMapping().getRoutes()) {
			Class<? extends Controller> controller = route.getControllerClass();
			System.out.println("Controller@{key:" + route.getControllerKey() + ", class:" + controller.getName() + "}");
		}
		for (RpcInfo info : SystemManager.me().getInfo().getRpcs()) {
			System.out.println("Rpc@{name:" + info.getName() + ", " + info.getGroup() + ", " + info.getVersion() + ", " + info.getPort() + "}");
		}
		for (TableMappingInfo mapping : SystemManager.me().getInfo().getMappings()) {
			System.out.println("Model@{name:" + mapping.getTableName() + ", " + mapping.getPrimaryKey() + ", " + mapping.getClassName() + "}");
		}
		*/
		/*
		Map<String, Table> tables = SystemManager.me().getInfo().getTables();
		for (String name : tables.keySet()) {
			Table table = tables.get(name);
			if(table==null) continue;
			System.out.println("Table@{name:" + table.getName() + ", " + table.getPrimaryKey() + ", " + table.getModelClass().getName() + "}");
		}
		*/
		//TimeUtils.endTime("start event print");
		/*
		System.out.println("-------------------------------------------------");
		Map<String, DataSourceInfo> dataSources = SystemManager.me().getDataSources();
		for (String key : dataSources.keySet()) {
			DataSourceInfo info = dataSources.get(key);
			System.out.println("Db config: " + info.getConfigName() + ", name: " + info.getActiveRecord().getDbname());
			for (TableInfo table : info.getTables()) {
				System.out.print("Table@{");
				System.out.print("name: " + table.getName());
				System.out.print(", primaryKey: " + table.getMeta().getPrimaryKey());
				if(table.getMapping()!=null) System.out.print(", className: " + table.getMapping().getClassName());
				System.out.println("}");
//				System.out.println("-----------+---------+------+-----+---------+----------------");
//				System.out.println(" Field     | Type    | Null | Key | Default | Remarks");
//				System.out.println("-----------+---------+------+-----+---------+----------------");
//				for (ColumnMeta col : table.getMeta().columnMetas) {
//					System.out.print(col.name+"	| "+col.type+" | ");
//					System.out.print(col.isNullable+" | ");
//					System.out.print(col.isPrimaryKey+" | ");
//					System.out.print(col.defaultValue+" | ");
//					System.out.println(col.remarks);
//				}
			}
		}
		*/
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Please visit the website(http://127.0.0.1/lambkit/dev) for runtime details.");
	}
	
}
