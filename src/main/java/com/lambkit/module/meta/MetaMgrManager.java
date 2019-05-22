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
 */package com.lambkit.module.meta;

import com.lambkit.Lambkit;
import com.lambkit.common.service.ServiceManager;
import com.lambkit.core.rpc.RpcConfig;
import com.lambkit.db.datasource.ActiveRecordPluginWrapper;
import com.lambkit.db.mgr.MgrdbConfig;
import com.lambkit.module.LambkitModule;
import com.lambkit.module.meta.model.MetaApp;
import com.lambkit.module.meta.model.MetaApi;
import com.lambkit.module.meta.model.MetaStore;
import com.lambkit.module.meta.model.MetaField;
import com.lambkit.module.meta.model.MetaFieldDimession;
import com.lambkit.module.meta.model.MetaFieldEdit;
import com.lambkit.module.meta.model.MetaFieldList;
import com.lambkit.module.meta.model.MetaFieldMap;
import com.lambkit.module.meta.model.MetaFieldMeasure;
import com.lambkit.module.meta.model.MetaFieldRelation;
import com.lambkit.module.meta.model.MetaFile;
import com.lambkit.module.meta.model.MetaFileCatalog;
import com.lambkit.module.meta.model.MetaFileCatalogMapping;
import com.lambkit.module.meta.model.MetaImage;
import com.lambkit.module.meta.model.MetaStoreDb;
import com.lambkit.module.meta.model.MetaImageSet;
import com.lambkit.module.meta.model.MetaStoreResource;
import com.lambkit.module.meta.model.MetaStoreRoute;
import com.lambkit.module.meta.model.MetaTable;
import com.lambkit.module.meta.model.MetaTheme;
import com.lambkit.module.meta.service.MetaApiService;
import com.lambkit.module.meta.service.MetaAppService;
import com.lambkit.module.meta.service.MetaFieldDimessionService;
import com.lambkit.module.meta.service.MetaStoreService;
import com.lambkit.module.meta.service.MetaFieldEditService;
import com.lambkit.module.meta.service.MetaFieldListService;
import com.lambkit.module.meta.service.MetaFieldMapService;
import com.lambkit.module.meta.service.MetaFieldMeasureService;
import com.lambkit.module.meta.service.MetaFieldRelationService;
import com.lambkit.module.meta.service.MetaFieldService;
import com.lambkit.module.meta.service.MetaFileCatalogMappingService;
import com.lambkit.module.meta.service.MetaFileCatalogService;
import com.lambkit.module.meta.service.MetaFileService;
import com.lambkit.module.meta.service.MetaImageService;
import com.lambkit.module.meta.service.MetaStoreDbService;
import com.lambkit.module.meta.service.MetaImageSetService;
import com.lambkit.module.meta.service.MetaStoreResourceService;
import com.lambkit.module.meta.service.MetaStoreRouteService;
import com.lambkit.module.meta.service.MetaTableService;
import com.lambkit.module.meta.service.MetaThemeService;
import com.lambkit.module.meta.service.impl.MetaApiServiceImpl;
import com.lambkit.module.meta.service.impl.MetaApiServiceMock;
import com.lambkit.module.meta.service.impl.MetaAppServiceImpl;
import com.lambkit.module.meta.service.impl.MetaAppServiceMock;
import com.lambkit.module.meta.service.impl.MetaFieldDimessionServiceImpl;
import com.lambkit.module.meta.service.impl.MetaFieldDimessionServiceMock;
import com.lambkit.module.meta.service.impl.MetaStoreServiceImpl;
import com.lambkit.module.meta.service.impl.MetaStoreServiceMock;
import com.lambkit.module.meta.service.impl.MetaFieldEditServiceImpl;
import com.lambkit.module.meta.service.impl.MetaFieldEditServiceMock;
import com.lambkit.module.meta.service.impl.MetaFieldListServiceImpl;
import com.lambkit.module.meta.service.impl.MetaFieldListServiceMock;
import com.lambkit.module.meta.service.impl.MetaFieldMapServiceImpl;
import com.lambkit.module.meta.service.impl.MetaFieldMapServiceMock;
import com.lambkit.module.meta.service.impl.MetaFieldMeasureServiceImpl;
import com.lambkit.module.meta.service.impl.MetaFieldMeasureServiceMock;
import com.lambkit.module.meta.service.impl.MetaFieldRelationServiceImpl;
import com.lambkit.module.meta.service.impl.MetaFieldRelationServiceMock;
import com.lambkit.module.meta.service.impl.MetaFieldServiceImpl;
import com.lambkit.module.meta.service.impl.MetaFieldServiceMock;
import com.lambkit.module.meta.service.impl.MetaFileCatalogMappingServiceImpl;
import com.lambkit.module.meta.service.impl.MetaFileCatalogMappingServiceMock;
import com.lambkit.module.meta.service.impl.MetaFileCatalogServiceImpl;
import com.lambkit.module.meta.service.impl.MetaFileCatalogServiceMock;
import com.lambkit.module.meta.service.impl.MetaFileServiceImpl;
import com.lambkit.module.meta.service.impl.MetaFileServiceMock;
import com.lambkit.module.meta.service.impl.MetaImageServiceImpl;
import com.lambkit.module.meta.service.impl.MetaImageServiceMock;
import com.lambkit.module.meta.service.impl.MetaStoreDbServiceImpl;
import com.lambkit.module.meta.service.impl.MetaStoreDbServiceMock;
import com.lambkit.module.meta.service.impl.MetaImageSetServiceImpl;
import com.lambkit.module.meta.service.impl.MetaImageSetServiceMock;
import com.lambkit.module.meta.service.impl.MetaStoreResourceServiceImpl;
import com.lambkit.module.meta.service.impl.MetaStoreResourceServiceMock;
import com.lambkit.module.meta.service.impl.MetaStoreRouteServiceImpl;
import com.lambkit.module.meta.service.impl.MetaStoreRouteServiceMock;
import com.lambkit.module.meta.service.impl.MetaTableServiceImpl;
import com.lambkit.module.meta.service.impl.MetaTableServiceMock;
import com.lambkit.module.meta.service.impl.MetaThemeServiceImpl;
import com.lambkit.module.meta.service.impl.MetaThemeServiceMock;
import com.lambkit.module.meta.web.tag.MetaApiDirective;
import com.lambkit.module.meta.web.tag.MetaAppDirective;
import com.lambkit.module.meta.web.tag.MetaStoreDirective;
import com.lambkit.module.meta.web.tag.MetaImageSetDirective;
import com.lambkit.module.meta.web.tag.MetaFieldDimessionDirective;
import com.lambkit.module.meta.web.tag.MetaFieldDirective;
import com.lambkit.module.meta.web.tag.MetaFieldEditDirective;
import com.lambkit.module.meta.web.tag.MetaFieldListDirective;
import com.lambkit.module.meta.web.tag.MetaFieldMapDirective;
import com.lambkit.module.meta.web.tag.MetaFieldMeasureDirective;
import com.lambkit.module.meta.web.tag.MetaFieldRelationDirective;
import com.lambkit.module.meta.web.tag.MetaFileCatalogDirective;
import com.lambkit.module.meta.web.tag.MetaFileCatalogMappingDirective;
import com.lambkit.module.meta.web.tag.MetaFileDirective;
import com.lambkit.module.meta.web.tag.MetaImageDirective;
import com.lambkit.module.meta.web.tag.MetaStoreDbDirective;
import com.lambkit.module.meta.web.tag.MetaStoreResourceDirective;
import com.lambkit.module.meta.web.tag.MetaStoreRouteDirective;
import com.lambkit.module.meta.web.tag.MetaTableDirective;
import com.lambkit.module.meta.web.tag.MetaThemeDirective;

public class MetaMgrManager {

	private static final MetaMgrManager me = new MetaMgrManager();

	public static MetaMgrManager me() {
		return me;
	}
	
	public void mapping(ActiveRecordPluginWrapper arp) {
		// TODO Auto-generated method stub
		arp.addMapping("meta_app", "id", MetaApp.class);
		arp.addMapping("meta_store", "id", MetaStore.class);
		
		arp.addMapping("meta_store_db", "id", MetaStoreDb.class);
		arp.addMapping("meta_table", "id", MetaTable.class);
		arp.addMapping("meta_field", "id", MetaField.class);
		arp.addMapping("meta_field_edit", "id", MetaFieldEdit.class);
		arp.addMapping("meta_field_list", "id", MetaFieldList.class);
		arp.addMapping("meta_field_map", "id", MetaFieldMap.class);
		arp.addMapping("meta_field_relation", "id", MetaFieldRelation.class);
		arp.addMapping("meta_field_dimession", "id", MetaFieldDimession.class);
		arp.addMapping("meta_field_measure", "id", MetaFieldMeasure.class);
		arp.addMapping("meta_theme", "id", MetaTheme.class);
		
		arp.addMapping("meta_store_route", "id", MetaStoreRoute.class);
		arp.addMapping("meta_api", "id", MetaApi.class);
		
		arp.addMapping("meta_store_resource", "id", MetaStoreResource.class);
		arp.addMapping("meta_file", "id", MetaFile.class);
		arp.addMapping("meta_file_catalog", "id", MetaFileCatalog.class);
		arp.addMapping("meta_file_catalog_mapping", "catelog_id,file_id", MetaFileCatalogMapping.class);
		
		arp.addMapping("meta_image_set", "id", MetaImageSet.class);
		arp.addMapping("meta_image", "id", MetaImage.class);
	}

	public void addTag(LambkitModule lk) {
		// TODO Auto-generated method stub
		lk.addTag("metaApp", new MetaAppDirective());
		
		lk.addTag("metaStore", new MetaStoreDirective());
		lk.addTag("metaStoreDb", new MetaStoreDbDirective());
		lk.addTag("metaStoreRoute", new MetaStoreRouteDirective());
		lk.addTag("metaStoreResource", new MetaStoreResourceDirective());
		
		lk.addTag("metaTable", new MetaTableDirective());
		
		lk.addTag("metaField", new MetaFieldDirective());
		lk.addTag("metaFieldEdit", new MetaFieldEditDirective());
		lk.addTag("metaFieldDimession", new MetaFieldDimessionDirective());
		lk.addTag("metaFieldList", new MetaFieldListDirective());
		lk.addTag("metaFieldMeasure", new MetaFieldMeasureDirective());
		lk.addTag("metaFieldMap", new MetaFieldMapDirective());
		lk.addTag("metaFieldRelation", new MetaFieldRelationDirective());
		lk.addTag("metaTheme", new MetaThemeDirective());
		
		lk.addTag("metaApi", new MetaApiDirective());
		
		lk.addTag("metaFile", new MetaFileDirective());
		lk.addTag("metaFileCatalog", new MetaFileCatalogDirective());
		lk.addTag("metaFileCatalogMapping", new MetaFileCatalogMappingDirective());
		
		lk.addTag("metaImage", new MetaImageDirective());
		lk.addTag("metaImageSet", new MetaImageSetDirective());
	}
	
	public void registerLocalService() {
		registerLocalService(getRpcGroup(), getRpcVersion(), getRpcPort());
	}
	
	public void registerLocalService(String group, String version, int port) {
		ServiceManager.me().mapping(MetaFieldMapService.class, MetaFieldMapServiceImpl.class, MetaFieldMapServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaStoreService.class, MetaStoreServiceImpl.class, MetaStoreServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaFieldListService.class, MetaFieldListServiceImpl.class, MetaFieldListServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaTableService.class, MetaTableServiceImpl.class, MetaTableServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaStoreDbService.class, MetaStoreDbServiceImpl.class, MetaStoreDbServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaFieldEditService.class, MetaFieldEditServiceImpl.class, MetaFieldEditServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaFieldService.class, MetaFieldServiceImpl.class, MetaFieldServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaThemeService.class, MetaThemeServiceImpl.class, MetaThemeServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaAppService.class, MetaAppServiceImpl.class, MetaAppServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaApiService.class, MetaApiServiceImpl.class, MetaApiServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaStoreResourceService.class, MetaStoreResourceServiceImpl.class, MetaStoreResourceServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaFileService.class, MetaFileServiceImpl.class, MetaFileServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaFileCatalogService.class, MetaFileCatalogServiceImpl.class, MetaFileCatalogServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaFileCatalogMappingService.class, MetaFileCatalogMappingServiceImpl.class, MetaFileCatalogMappingServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaImageService.class, MetaImageServiceImpl.class, MetaImageServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaImageSetService.class, MetaImageSetServiceImpl.class, MetaImageSetServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaStoreRouteService.class, MetaStoreRouteServiceImpl.class, MetaStoreRouteServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaFieldDimessionService.class, MetaFieldDimessionServiceImpl.class, MetaFieldDimessionServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaFieldMeasureService.class, MetaFieldMeasureServiceImpl.class, MetaFieldMeasureServiceMock.class, group, version, port);
		ServiceManager.me().mapping(MetaFieldRelationService.class, MetaFieldRelationServiceImpl.class, MetaFieldRelationServiceMock.class, group, version, port);
	}
	
	public void registerRemoteService() {
		registerRemoteService(getRpcGroup(), getRpcVersion(), getRpcPort());
	}
	
	public void registerRemoteService(String group, String version, int port) {
		ServiceManager.me().remote(MetaFieldMapService.class, MetaFieldMapServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaStoreService.class, MetaStoreServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaFieldListService.class, MetaFieldListServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaTableService.class, MetaTableServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaStoreDbService.class, MetaStoreDbServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaFieldEditService.class, MetaFieldEditServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaFieldService.class, MetaFieldServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaThemeService.class, MetaThemeServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaAppService.class, MetaAppServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaApiService.class, MetaApiServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaStoreResourceService.class, MetaStoreResourceServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaFileService.class, MetaFileServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaFileCatalogService.class, MetaFileCatalogServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaFileCatalogMappingService.class, MetaFileCatalogMappingServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaImageService.class, MetaImageServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaImageSetService.class, MetaImageSetServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaStoreRouteService.class, MetaStoreRouteServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaFieldDimessionService.class, MetaFieldDimessionServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaFieldMeasureService.class, MetaFieldMeasureServiceMock.class, group, version, port);
		ServiceManager.me().remote(MetaFieldRelationService.class, MetaFieldRelationServiceMock.class, group, version, port);
	}
	
	public int getRpcPort() {
		return Lambkit.config(RpcConfig.class).getDefaultPort();
	}
	public String getRpcGroup() {
		return Lambkit.config(RpcConfig.class).getDefaultGroup();
	}
	public String getRpcVersion() {
		return getConfig().getVersion();
	}
	public MgrdbConfig getConfig() {
		return Lambkit.config(MgrdbConfig.class);
	}
}
