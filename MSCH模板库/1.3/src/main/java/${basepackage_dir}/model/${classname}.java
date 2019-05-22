##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.model;

import com.jfinal.kit.StrKit;
import com.lambkit.Lambkit;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.db.sql.column.Column;
import $!{basepackage}.MschConfig;
import $!{basepackage}.model.base.Base${classname};
import $!{basepackage}.model.sql.${classname}Criteria;
import $!{basepackage}.service.${classname}Service;
import $!{basepackage}.service.impl.${classname}ServiceImpl;

#parse("/template/java_author.include")
public class ${classname} extends Base${classname}<${classname}> {

	private static final long serialVersionUID = 1L;
	
	public static ${classname}Service service() {
		return ServiceKit.inject(${classname}Service.class, ${classname}ServiceImpl.class);
	}
	
	public static ${classname}Criteria sql() {
		return new ${classname}Criteria();
	}
	
	public static ${classname}Criteria sql(Column column) {
		${classname}Criteria that = new ${classname}Criteria();
		that.add(column);
        return that;
    }

	public ${classname}() {
		MschConfig config = Lambkit.config(MschConfig.class);
		String dbconfig = config.getDbconfig();
		if(StrKit.notBlank(dbconfig)) {
			this.use(dbconfig);
		}
	}
}
