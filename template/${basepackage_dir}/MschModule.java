##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage};

import com.jfinal.config.Routes;
import com.jfinal.kit.StrKit;
import com.lambkit.Lambkit;
import com.lambkit.common.service.ServiceManager;
import com.lambkit.core.rpc.RpcConfig;
import com.lambkit.db.datasource.ActiveRecordPluginWrapper;
import com.lambkit.module.LambkitModule;

import $!{basepackage}.MschConfig;
#foreach ($table in $tables)
import $!{basepackage}.model.${table.modelName};
#end##
#foreach ($table in $tables)
import $!{basepackage}.service.${table.modelName}Service;
#end##
#foreach ($table in $tables)
import $!{basepackage}.service.impl.${table.modelName}ServiceImpl;
import $!{basepackage}.service.impl.${table.modelName}ServiceMock;
#end##
#foreach ($table in $tables)
import $!{basepackage}.web.tag.${table.modelName}Marker;
#end##

#parse("/template/java_author.include")
public class MschModule extends LambkitModule  {

	@Override
	public void configMapping(ActiveRecordPluginWrapper arp) {
		if(StrKit.isBlank(getConfig().getDbconfig())) {
			mapping(arp);
		}
	}
	
	@Override
	public void configMapping(String name, ActiveRecordPluginWrapper arp) {
		super.configMapping(name, arp);
		if(StrKit.notBlank(name) && name.equals(getConfig().getDbconfig())) {
			mapping(arp);
		}
	}
	
	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		addTag(this);
		if("server".equals(getConfig().getServerType())) {
			registerLocalService();
		} else if("client".equals(getConfig().getServerType())) {
			registerRemoteService();
		} 
	}
	
	public void mapping(ActiveRecordPluginWrapper arp) {
#foreach ($table in $tables)##	
#if(!${table.primaryKey})##
		arp.addMapping("$table.name", ${table.modelName}.class);
#else##
		arp.addMapping("$table.name", "${table.primaryKey}", ${table.modelName}.class);
#end##
#end##
	}
	
	public void addTag(LambkitModule lk) {
#foreach ($table in $tables)
		lk.addTag("$table.attrName", new ${table.modelName}Marker());
#end##
	}
			
	public void registerLocalService() {
		registerLocalService(getRpcGroup(), getRpcVersion(), getRpcPort());
	}
	
	public void registerLocalService(String group, String version, int port) {
#foreach ($table in $tables)##	
		ServiceManager.me().mapping(${table.modelName}Service.class, ${table.modelName}ServiceImpl.class, ${table.modelName}ServiceMock.class, group, version, port);
#end##
	}
	
	public void registerRemoteService() {
		registerRemoteService(getRpcGroup(), getRpcVersion(), getRpcPort());
	}
	
	public void registerRemoteService(String group, String version, int port) {
#foreach ($table in $tables)##	
		ServiceManager.me().remote(${table.modelName}Service.class, ${table.modelName}ServiceMock.class, group, version, port);
#end##
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
	public MschConfig getConfig() {
		return Lambkit.config(MschConfig.class);
	}
}

