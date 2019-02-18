##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.rpc.service.impl;

import com.lambkit.common.service.BaseModelServiceImpl;
import $!{basepackage}.rpc.api.${classname}Service;
import $!{basepackage}.rpc.model.${classname};
import $!{basepackage}.rpc.query.${classname}Query;

#parse("/template/java_author.include")
public class ${classname}ServiceImpl extends BaseModelServiceImpl<${classname}> implements ${classname}Service {
	
	//protected final ${classname} DAO = new ${classname}();
	
	public ${classname} dao() {
		return ${classname}Query.me().dao();//DAO;
	}
}
