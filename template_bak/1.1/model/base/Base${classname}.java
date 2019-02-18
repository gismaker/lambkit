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
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
#foreach($column in $columns)##
        sb.append(", ${column.name}=").append(get${column.upperName}());
#end##
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Base${classname} other = (Base${classname}) that;
        return 
#foreach($column in $columns)##
#if($velocityCount==1)##
			(this.get${column.upperName}() == null ? other.get${column.upperName}() == null : this.get${column.upperName}().equals(other.get${column.upperName}()))
#elseif($velocityCount<${columns.size()})##
			&& (this.get${column.upperName}() == null ? other.get${column.upperName}() == null : this.get${column.upperName}().equals(other.get${column.upperName}()))
#else##
			&& (this.get${column.upperName}() == null ? other.get${column.upperName}() == null : this.get${column.upperName}().equals(other.get${column.upperName}()));
#end##
#end##
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        #foreach($column in $columns)##
        result = prime * result + ((get${column.upperName}() == null) ? 0 : get${column.upperName}().hashCode());
        #end##
        return result;
    }
}
