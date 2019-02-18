##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.tag;

import java.io.IOException;
import java.util.Map;

import $!{basepackage}.rpc.query.${classname}Query;
import com.lambkit.common.util.StringUtils;
import com.lambkit.web.tag.base.BaseDirectiveModel;
import com.jfinal.render.FreeMarkerRender;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

#parse("/template/java_author.include")
/**
 * ${tablename}标签<br>
 * 参数：{id:主键}
 * 返回值：{entity:${tablename}信息}
 * @author lamb
 *
 */
public class ${classname}Directive extends BaseDirectiveModel {

	@Override
	public void executeMe(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
#foreach($column in $columns)##
		String ${column.attrName} = get(params, "${column.name}");
#end
		int pagenum = getInt(params, "pagenum", 0);
		int pagesize = getInt(params, "pagesize", 0);
		String wheresql = get(params, "sql", null);
		String sql = " from $!{tablename} where "; 
		if(wheresql == null) {
			sql += " 1=1 ";
#foreach($column in $columns)##
#if($column.type.indexOf('FLOAT') > -1 || $column.type.indexOf('NUMBER') > -1 || $column.type.indexOf('INT') > -1)##
			if(StringUtils.hasText(${column.attrName})) sql += " and ${column.name}=" + ${column.attrName};//$column.type
#elseif(${column.fldconfig.datatype}!='Type')##
			if(StringUtils.hasText(${column.attrName})) sql += " and ${column.name} like '%" + ${column.attrName} + "%'";//$column.type
#end##
#end##
		} else {
			sql += wheresql;
		}
		
		String keyname = get(params, "key", "entity");
		if(pagenum==0) {
			env.setVariable(keyname, FreeMarkerRender.getConfiguration().getObjectWrapper().wrap(
					$!{classname}Query.me().dao().findFirst("select *" + sql)));
		} else {
			if(pagesize==0) {
				env.setVariable(keyname, FreeMarkerRender.getConfiguration().getObjectWrapper().wrap(
						$!{classname}Query.me().dao().find("select *" + sql)));
			} else {
				env.setVariable(keyname, FreeMarkerRender.getConfiguration().getObjectWrapper().wrap(
						$!{classname}Query.me().dao().paginate(pagenum, pagesize, "select *", sql)));
			}
		}
        body.render(env.getOut());
	}
}
