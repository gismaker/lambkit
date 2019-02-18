##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.rpc.service.impl;

import com.jfinal.aop.Enhancer;
import com.lambkit.common.service.BaseModelServiceImpl;
import $!{basepackage}.rpc.api.${classname}Service;
import $!{basepackage}.rpc.model.${classname};

#parse("/template/java_author.include")
public class ${classname}ServiceImpl extends BaseModelServiceImpl<${classname}> implements ${classname}Service {
	
	private ${classname} DAO = null;
	
	public ${classname} dao() {
		if(DAO==null) {
			DAO = Enhancer.enhance(${classname}.class.getName(), ${classname}.class);
		}
		return DAO;
	}
}
