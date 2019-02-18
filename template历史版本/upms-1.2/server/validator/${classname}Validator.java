##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.server.validator;

import com.jfinal.core.Controller;
##import com.jfinal.kit.StrKit;
import com.lambkit.web.validator.BaseValidator;

##import $!{basepackage}.rpc.model.${classname};
import $!{basepackage}.rpc.api.${classname}Service;

#parse("/template/java_author.include")
public class ${classname}Validator extends BaseValidator {

	@Override
	protected String getTableName(Controller c) {
		return ${classname}.service().getTableName();
	}
	
	@Override
	protected String getPrefix() {
		return "model";//StrKit.firstCharToLowerCase(${classname}.class.getSimpleName());
	}
}
