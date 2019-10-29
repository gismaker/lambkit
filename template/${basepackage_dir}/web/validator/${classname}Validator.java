##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.web.validator;

import com.jfinal.core.Controller;
##import com.jfinal.kit.StrKit;
import com.lambkit.web.validator.LambkitValidator;

import $!{basepackage}.model.${classname};

#parse("/template/java_author.include")
public class ${classname}Validator extends LambkitValidator {

	@Override
	protected String getTableName(Controller c) {
		return ${classname}.service().getTableName();
	}
	
	@Override
	protected String getPrefix() {
		return "model";//StrKit.firstCharToLowerCase(${classname}.class.getSimpleName());
	}
}
