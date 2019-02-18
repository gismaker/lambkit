##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.validator;

import com.jfinal.core.Controller;
##import com.jfinal.kit.StrKit;
import com.lambkit.web.validator.BaseValidator;

##import $!{basepackage}.rpc.model.${classname};
import $!{basepackage}.rpc.query.${classname}Query;

#parse("/template/java_author.include")
public class ${classname}Validator extends BaseValidator {

	@Override
	protected String getTableName(Controller c) {
		return ${classname}Query.me().getTableName();
	}
	
	@Override
	protected String getPrefix() {
		return "model";//StrKit.firstCharToLowerCase(${classname}.class.getSimpleName());
	}
}
