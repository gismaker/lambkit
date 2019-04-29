##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.rpc.model;

import com.jfinal.kit.StrKit;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.db.sql.column.Column;
import com.lambkit.module.upms.common.UpmsConfig;
import com.lambkit.module.upms.common.UpmsManager;

import $!{basepackage}.rpc.model.base.Base${classname};
import $!{basepackage}.rpc.model.sql.${classname}Criteria;
import $!{basepackage}.rpc.api.${classname}Service;
import $!{basepackage}.rpc.service.impl.${classname}ServiceImpl;

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
		UpmsConfig config = UpmsManager.me().getConfig();
		String dbconfig = config.getDbconfig();
		if(StrKit.notBlank(dbconfig)) {
			this.use(dbconfig);
		}
	}
}
