##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.rpc.query;

import com.lambkit.common.dao.BaseModelQueryImpl;

import $!{basepackage}.rpc.model.${classname};

#parse("/template/java_author.include")
public class ${classname}Query extends BaseModelQueryImpl<${classname}> {

	protected final ${classname} DAO = new ${classname}();
	private static final ${classname}Query QUERY = new ${classname}Query();

	public static ${classname}Query me() {
		return QUERY;
	}
	
	public ${classname} dao() {
		return DAO;
	}
	
	@Override
	public Class<${classname}> getModelClass() {
		// TODO Auto-generated method stub
		return ${classname}.class;
	}
	
}
