##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.web.directive;

import com.jfinal.kit.StrKit;
import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
import com.lambkit.common.util.StringUtils;
import $!{basepackage}.rpc.api.${classname}Service;
import $!{basepackage}.rpc.model.${classname};
import com.lambkit.web.directive.LambkitDirective;

public class ${classname}Directive extends LambkitDirective {

	@Override
	public void onRender(Env env, Scope scope, Writer writer) {
		// TODO Auto-generated method stub
#foreach($column in $columns)##
		String ${column.attrName} = getPara("${column.name}", scope);
#end
		int pagenum = getParaToInt("pagenum", scope, 0);
		int pagesize = getParaToInt("pagesize", scope, 0);
		String wheresql = getPara("sql", null);
		String sql = " from $!{tablename} where "; 
		if(wheresql == null) {
			sql += " 1=1 ";
#foreach($column in $columns)##
#if($column.type.indexOf('FLOAT') > -1 || $column.type.indexOf('NUMBER') > -1 || $column.type.indexOf('INT') > -1
		|| $column.type.indexOf('float') > -1 || $column.type.indexOf('number') > -1 || $column.type.indexOf('int') > -1)##
			if(StringUtils.hasText(${column.attrName})) sql += " and ${column.name}=" + ${column.attrName};//$column.type
#elseif(${column.fldconfig.datatype}!='Type')##
			if(StringUtils.hasText(${column.attrName})) sql += " and ${column.name} like '%" + ${column.attrName} + "%'";//$column.type
#end##
#end##
		} else {
			sql += wheresql;
		}
		
		String orderby = getPara("orderby", scope, null);
		
		if(StrKit.notBlank(orderby)) {
			sql += " order by " + orderby;
		}
		
		${classname}Service service = ${classname}.service();
		
		String tagEntityKeyname = getPara("key", scope, "entity");
		if(pagenum==0) {
			scope.set(tagEntityKeyname, service.dao().findFirst("select *" + sql));
		} else {
			if(pagesize==0) {
				scope.set(tagEntityKeyname, service.dao().find("select *" + sql));
			} else {
				scope.set(tagEntityKeyname, service.dao().paginate(pagenum, pagesize, "select *", sql));
			}
		}
        renderBody(env, scope, writer);
	}

}
