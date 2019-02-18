##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.rpc.model;

import com.jfinal.kit.StrKit;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.module.meta.MetaMgrConfig;
import com.lambkit.module.meta.MetaMgrManager;

import $!{basepackage}.rpc.model.base.Base${classname};
import $!{basepackage}.rpc.api.${classname}Service;
import $!{basepackage}.rpc.service.impl.${classname}ServiceImpl;

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
