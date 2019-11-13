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
package com.lambkit.module.upms;

import com.lambkit.Lambkit;
import com.lambkit.common.service.ServiceManager;
import com.lambkit.common.util.ScanJarStringSource;
import com.lambkit.core.aop.AopKit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.db.datasource.ActiveRecordPluginWrapper;
import com.lambkit.module.LambkitModule;
import com.lambkit.module.upms.rpc.api.UpmsApiService;
import com.lambkit.module.upms.rpc.api.UpmsFavoritesService;
import com.lambkit.module.upms.rpc.api.UpmsLogService;
import com.lambkit.module.upms.rpc.api.UpmsOrganizationService;
import com.lambkit.module.upms.rpc.api.UpmsPermissionService;
import com.lambkit.module.upms.rpc.api.UpmsRolePermissionService;
import com.lambkit.module.upms.rpc.api.UpmsRoleService;
import com.lambkit.module.upms.rpc.api.UpmsSystemService;
import com.lambkit.module.upms.rpc.api.UpmsTagService;
import com.lambkit.module.upms.rpc.api.UpmsUserOrganizationService;
import com.lambkit.module.upms.rpc.api.UpmsUserPermissionService;
import com.lambkit.module.upms.rpc.api.UpmsUserRoleService;
import com.lambkit.module.upms.rpc.api.UpmsUserService;
import com.lambkit.module.upms.rpc.model.UpmsFavorites;
import com.lambkit.module.upms.rpc.model.UpmsLog;
import com.lambkit.module.upms.rpc.model.UpmsOrganization;
import com.lambkit.module.upms.rpc.model.UpmsPermission;
import com.lambkit.module.upms.rpc.model.UpmsRole;
import com.lambkit.module.upms.rpc.model.UpmsRolePermission;
import com.lambkit.module.upms.rpc.model.UpmsSystem;
import com.lambkit.module.upms.rpc.model.UpmsTag;
import com.lambkit.module.upms.rpc.model.UpmsUser;
import com.lambkit.module.upms.rpc.model.UpmsUserOrganization;
import com.lambkit.module.upms.rpc.model.UpmsUserPermission;
import com.lambkit.module.upms.rpc.model.UpmsUserRole;
import com.lambkit.module.upms.rpc.service.impl.UpmsApiServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsApiServiceMock;
import com.lambkit.module.upms.rpc.service.impl.UpmsFavoritesServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsFavoritesServiceMock;
import com.lambkit.module.upms.rpc.service.impl.UpmsLogServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsLogServiceMock;
import com.lambkit.module.upms.rpc.service.impl.UpmsOrganizationServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsOrganizationServiceMock;
import com.lambkit.module.upms.rpc.service.impl.UpmsPermissionServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsPermissionServiceMock;
import com.lambkit.module.upms.rpc.service.impl.UpmsRolePermissionServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsRolePermissionServiceMock;
import com.lambkit.module.upms.rpc.service.impl.UpmsRoleServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsRoleServiceMock;
import com.lambkit.module.upms.rpc.service.impl.UpmsSystemServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsSystemServiceMock;
import com.lambkit.module.upms.rpc.service.impl.UpmsTagServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsTagServiceMock;
import com.lambkit.module.upms.rpc.service.impl.UpmsUserOrganizationServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsUserOrganizationServiceMock;
import com.lambkit.module.upms.rpc.service.impl.UpmsUserPermissionServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsUserPermissionServiceMock;
import com.lambkit.module.upms.rpc.service.impl.UpmsUserRoleServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsUserRoleServiceMock;
import com.lambkit.module.upms.rpc.service.impl.UpmsUserServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsUserServiceMock;
import com.lambkit.module.upms.server.tag.UpmsFavoritesDirective;
import com.lambkit.module.upms.server.tag.UpmsLogDirective;
import com.lambkit.module.upms.server.tag.UpmsOrganizationDirective;
import com.lambkit.module.upms.server.tag.UpmsPermissionDirective;
import com.lambkit.module.upms.server.tag.UpmsRoleDirective;
import com.lambkit.module.upms.server.tag.UpmsRolePermissionDirective;
import com.lambkit.module.upms.server.tag.UpmsSystemDirective;
import com.lambkit.module.upms.server.tag.UpmsTagDirective;
import com.lambkit.module.upms.server.tag.UpmsUserDirective;
import com.lambkit.module.upms.server.tag.UpmsUserOrganizationDirective;
import com.lambkit.module.upms.server.tag.UpmsUserPermissionDirective;
import com.lambkit.module.upms.server.tag.UpmsUserRoleDirective;

public class UpmsManager {

	private static final UpmsManager me = new UpmsManager();

	public static UpmsManager me() {
		return me;
	}
	
	private UpmsConfig config;
	
	private boolean register = false;
	
	public UpmsConfig getConfig() {
		if(config==null) {
			config = Lambkit.config(UpmsConfig.class);
		}
		return config;
	}
	
	public void mapping(ActiveRecordPluginWrapper arp) {
		arp.addSqlTemplate(new ScanJarStringSource("upms.sql"));
		
		arp.addMapping("upms_log", "log_id", UpmsLog.class);
		arp.addMapping("upms_organization", "organization_id", UpmsOrganization.class);
		arp.addMapping("upms_permission", "permission_id", UpmsPermission.class);
		arp.addMapping("upms_role", "role_id", UpmsRole.class);
		arp.addMapping("upms_role_permission", "role_permission_id", UpmsRolePermission.class);
		arp.addMapping("upms_system", "system_id", UpmsSystem.class);
		arp.addMapping("upms_user", "user_id", UpmsUser.class);
		arp.addMapping("upms_user_organization", "user_organization_id", UpmsUserOrganization.class);
		arp.addMapping("upms_user_permission", "user_permission_id", UpmsUserPermission.class);
		arp.addMapping("upms_user_role", "user_role_id", UpmsUserRole.class);
		arp.addMapping("upms_favorites", "id", UpmsFavorites.class);
		arp.addMapping("upms_tag", "id", UpmsTag.class);
	}
	
	public void addTag(LambkitModule lk) {
		lk.addTag("upmsLog", new UpmsLogDirective());
		lk.addTag("upmsOrganization", new UpmsOrganizationDirective());
		lk.addTag("upmsPermission", new UpmsPermissionDirective());
		lk.addTag("upmsRole", new UpmsRoleDirective());
		lk.addTag("upmsRolePermission", new UpmsRolePermissionDirective());
		lk.addTag("upmsSystem", new UpmsSystemDirective());
		lk.addTag("upmsUser", new UpmsUserDirective());
		lk.addTag("upmsUserOrganization", new UpmsUserOrganizationDirective());
		lk.addTag("upmsUserPermission", new UpmsUserPermissionDirective());
		lk.addTag("upmsUserRole", new UpmsUserRoleDirective());
		lk.addTag("upmsFavorites", new UpmsFavoritesDirective());
		lk.addTag("upmsTag", new UpmsTagDirective());
	}
	
	public void registerService(String group, String version, int port) {
		UpmsConfig upmsConfig = ConfigManager.me().get(UpmsConfig.class);
		if("client".equals(upmsConfig.getType())) {
			registerRemoteService(group, version, port);
		} else {
			registerLocalService(group, version, port);
		}
		register = true;
	}
	
	public void registerLocalService(String group, String version, int port) {
		ServiceManager.me().mapping(UpmsApiService.class, UpmsApiServiceImpl.class, UpmsApiServiceMock.class, group, version, port);
		ServiceManager.me().mapping(UpmsFavoritesService.class, UpmsFavoritesServiceImpl.class, UpmsFavoritesServiceMock.class, group, version, port);
		ServiceManager.me().mapping(UpmsLogService.class, UpmsLogServiceImpl.class, UpmsLogServiceMock.class, group, version, port);
		ServiceManager.me().mapping(UpmsOrganizationService.class, UpmsOrganizationServiceImpl.class, UpmsOrganizationServiceMock.class, group, version, port);
		ServiceManager.me().mapping(UpmsPermissionService.class, UpmsPermissionServiceImpl.class, UpmsPermissionServiceMock.class, group, version, port);
		ServiceManager.me().mapping(UpmsRoleService.class, UpmsRoleServiceImpl.class, UpmsRoleServiceMock.class, group, version, port);
		ServiceManager.me().mapping(UpmsRolePermissionService.class, UpmsRolePermissionServiceImpl.class, UpmsRolePermissionServiceMock.class, group, version, port);
		ServiceManager.me().mapping(UpmsSystemService.class, UpmsSystemServiceImpl.class, UpmsSystemServiceMock.class, group, version, port);
		ServiceManager.me().mapping(UpmsTagService.class, UpmsTagServiceImpl.class, UpmsTagServiceMock.class, group, version, port);
		ServiceManager.me().mapping(UpmsUserService.class, UpmsUserServiceImpl.class, UpmsUserServiceMock.class, group, version, port);
		ServiceManager.me().mapping(UpmsUserOrganizationService.class, UpmsUserOrganizationServiceImpl.class, UpmsUserOrganizationServiceMock.class, group, version, port);
		ServiceManager.me().mapping(UpmsUserPermissionService.class, UpmsUserPermissionServiceImpl.class, UpmsUserPermissionServiceMock.class, group, version, port);
		ServiceManager.me().mapping(UpmsUserRoleService.class, UpmsUserRoleServiceImpl.class, UpmsUserRoleServiceMock.class, group, version, port);
	}
	
	public void registerRemoteService(String group, String version, int port) {
		ServiceManager.me().remote(UpmsApiService.class, UpmsApiServiceMock.class, group, version, port);
		ServiceManager.me().remote(UpmsFavoritesService.class, UpmsFavoritesServiceMock.class, group, version, port);
		ServiceManager.me().remote(UpmsLogService.class, UpmsLogServiceMock.class, group, version, port);
		ServiceManager.me().remote(UpmsOrganizationService.class, UpmsOrganizationServiceMock.class, group, version, port);
		ServiceManager.me().remote(UpmsPermissionService.class, UpmsPermissionServiceMock.class, group, version, port);
		ServiceManager.me().remote(UpmsRoleService.class, UpmsRoleServiceMock.class, group, version, port);
		ServiceManager.me().remote(UpmsRolePermissionService.class, UpmsRolePermissionServiceMock.class, group, version, port);
		ServiceManager.me().remote(UpmsSystemService.class, UpmsSystemServiceMock.class, group, version, port);
		ServiceManager.me().remote(UpmsTagService.class, UpmsTagServiceMock.class, group, version, port);
		ServiceManager.me().remote(UpmsUserService.class, UpmsUserServiceMock.class, group, version, port);
		ServiceManager.me().remote(UpmsUserOrganizationService.class, UpmsUserOrganizationServiceMock.class, group, version, port);
		ServiceManager.me().remote(UpmsUserPermissionService.class, UpmsUserPermissionServiceMock.class, group, version, port);
		ServiceManager.me().remote(UpmsUserRoleService.class, UpmsUserRoleServiceMock.class, group, version, port);
	}
	/*
	//"lambkit-demo-rpc", "1.0", 15555
	public void export(Rpc rpc, String group, String version, int port) {
		System.out.println("upms rpc server info : " + group+", "+version+", "+port);
		rpc.serviceExport(UpmsApiService.class,new UpmsApiServiceImpl(), group, version, port);
		rpc.serviceExport(UpmsLogService.class,new UpmsLogServiceImpl(), group, version, port);
		rpc.serviceExport(UpmsOrganizationService.class,new UpmsOrganizationServiceImpl(), group, version, port);
		rpc.serviceExport(UpmsPermissionService.class,new UpmsPermissionServiceImpl(), group, version, port);
		rpc.serviceExport(UpmsRolePermissionService.class,new UpmsRolePermissionServiceImpl(), group, version, port);
		rpc.serviceExport(UpmsRoleService.class,new UpmsRoleServiceImpl(), group, version, port);
		rpc.serviceExport(UpmsSystemService.class,new UpmsSystemServiceImpl(), group, version, port);
		rpc.serviceExport(UpmsUserOrganizationService.class,new UpmsUserOrganizationServiceImpl(), group, version, port);
		rpc.serviceExport(UpmsUserPermissionService.class,new UpmsUserPermissionServiceImpl(), group, version, port);
		rpc.serviceExport(UpmsUserRoleService.class,new UpmsUserRoleServiceImpl(), group, version, port);
		rpc.serviceExport(UpmsUserService.class,new UpmsUserServiceImpl(), group, version, port);
	}
	*/
	
	
	public UpmsApiService getUpmsApiService() {
		UpmsConfig upmsConfig = ConfigManager.me().get(UpmsConfig.class);
		if("client".equals(upmsConfig.getType())) {
			return RpcKit.obtain(UpmsApiService.class);
		} else {
			return AopKit.get(UpmsApiServiceImpl.class);
		}
    }

	public boolean isRegister() {
		return register;
	}
}
