##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.controller;

import $!{basepackage}.MschController;
import $!{basepackage}.model.${classname};
import $!{basepackage}.web.validator.${classname}Validator;
import com.jfinal.aop.Before;

public class ${classname}Controller extends MschController<${classname}> {

	@Override
	public void index() {
		// TODO Auto-generated method stub
		super.index();
	}
	
	@Override
	public void list() {
		// TODO Auto-generated method stub
		super.list();
	}
	
	@Override
	public void view() {
		// TODO Auto-generated method stub
		super.view();
	}
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		super.add();
	}
	
	@Override
	public void edit() {
		// TODO Auto-generated method stub
		super.edit();
	}
	
	@Override
	public void get() {
		// TODO Auto-generated method stub
		super.get();
	}
	
	@Override
	public void page() {
		// TODO Auto-generated method stub
		super.page();
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		super.delete();
	}
	
	@Before(${classname}Validator.class)
	public void save() {
		doSave(getModel(${classname}.class, "model"));
	}
	
	@Before(${classname}Validator.class)
	public void update() {
		doUpdate(getModel(${classname}.class, "model"));
	}
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return ${classname}.service().getTableName();
	}

	@Override
	protected ${classname} findById(Object id) {
		// TODO Auto-generated method stub
		return ${classname}.service().findById(id);
	}
	
	@Override
	protected String getTemplatePath() {
		// TODO Auto-generated method stub
		return "${attrName}/";
	}
}
