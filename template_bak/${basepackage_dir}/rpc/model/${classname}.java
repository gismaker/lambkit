##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.rpc.model;

import $!{basepackage}.rpc.model.base.Base${classname};

#parse("/template/java_author.include")
public class ${classname} extends Base${classname}<${classname}> {

	private static final long serialVersionUID = 1L;
}
