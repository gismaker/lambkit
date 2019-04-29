##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.model.base;

import com.jfinal.plugin.activerecord.IBean;

import com.lambkit.common.dao.BaseModel;

#parse("/template/java_author.include")
@SuppressWarnings("serial")
public abstract class Base${classname}<M extends Base${classname}<M>> extends BaseModel<M> implements IBean {

	public String getTableName() {
		return "$tablename";
	}
    
#foreach($column in $columns)##
	public ${column.javaType} get${column.upperName}() {
		return this.get("${column.name}");
	}

	public void set${column.upperName}(${column.javaType} ${column.attrName}) {
		this.set("${column.name}", ${column.attrName});
	}
#end##
}
