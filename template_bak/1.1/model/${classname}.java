##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.model;

import com.jfinal.kit.StrKit;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.module.meta.MetaMgrConfig;
import com.lambkit.module.meta.MetaMgrManager;

import $!{basepackage}.model.base.Base${classname};
import $!{basepackage}.service.${classname}Service;
import $!{basepackage}.service.impl.${classname}ServiceImpl;

#parse("/template/java_author.include")
public class ${classname} extends Base${classname}<${classname}> {

	private static final long serialVersionUID = 1L;
	
	public static ${classname}Service service() {
		return ServiceKit.inject(${classname}Service.class, ${classname}ServiceImpl.class);
	}
	
	public ${classname}() {
		MetaMgrConfig config = MetaMgrManager.me().getConfig();
		String dbconfig = config.getDbconfig();
		if(StrKit.notBlank(dbconfig)) {
			this.use(dbconfig);
		}
	}
}
