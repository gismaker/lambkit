##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.service;

#foreach ($table in $tables)
import $!{basepackage}.service.mock.${table.modelName}ServiceMock;
#end##
#foreach ($table in $tables)
import $!{basepackage}.service.impl.${table.modelName}ServiceImpl;
#end##

import com.lambkit.common.service.ServiceManager;

#parse("/template/java_author.include")
public class _Api_MappingKit {

	public static void mapping(String group, String version, int port) {
#foreach ($table in $tables)##	
		ServiceManager.me().mapping(${table.modelName}Service.class, ${table.modelName}ServiceImpl.class, ${table.modelName}ServiceMock.class, group, version, port);
#end##
	}
	
	public static void remote(String group, String version, int port) {
#foreach ($table in $tables)##	
		ServiceManager.me().remote(${table.modelName}Service.class, ${table.modelName}ServiceMock.class, group, version, port);
#end##
	}
}

