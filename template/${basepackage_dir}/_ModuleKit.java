##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage};

import com.lambkit.common.service.ServiceManager;
import com.lambkit.db.datasource.ActiveRecordPluginWrapper;
import com.lambkit.module.LambkitModule;

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
import $!{basepackage}.web.tag.${table.modelName}Directive;
#end##

#parse("/template/java_author.include")
public class _ModuleKit {

	public static void mapping(ActiveRecordPluginWrapper arp) {
#foreach ($table in $tables)##	
#if(!${table.primaryKey})##
		arp.addMapping("$table.name", ${table.modelName}.class);
#else##
		arp.addMapping("$table.name", "${table.primaryKey}", ${table.modelName}.class);
#end##
#end##
	}
	
	public static void addTag(LambkitModule lk) {
#foreach ($table in $tables)
		lk.addTag("$table.attrName", new ${table.modelName}Directive());
#end##
	}
			
	public static void registerLocalService(String group, String version, int port) {
#foreach ($table in $tables)##	
		ServiceManager.me().mapping(${table.modelName}Service.class, ${table.modelName}ServiceImpl.class, ${table.modelName}ServiceMock.class, group, version, port);
#end##
	}
	
	public static void registerRemoteService(String group, String version, int port) {
#foreach ($table in $tables)##	
		ServiceManager.me().remote(${table.modelName}Service.class, ${table.modelName}ServiceMock.class, group, version, port);
#end##
	}
}

