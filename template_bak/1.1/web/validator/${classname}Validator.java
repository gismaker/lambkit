##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.web.validator;

import com.jfinal.core.Controller;
##import com.jfinal.kit.StrKit;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.web.validator.BaseValidator;

##import $!{basepackage}.model.${classname};
import $!{basepackage}.service.${classname}Service;

#parse("/template/java_author.include")
public class ${classname}Validator extends BaseValidator {

	@Override
	protected String getTableName(Controller c) {
		${classname}Service service = ServiceKit.inject(${classname}Service.class);
		return service.getTableName();
	}
	
	@Override
	protected String getPrefix() {
		return "model";//StrKit.firstCharToLowerCase(${classname}.class.getSimpleName());
	}
}
