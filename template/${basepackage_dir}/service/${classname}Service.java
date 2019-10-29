##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.service;

import com.lambkit.common.service.LambkitService;

import $!{basepackage}.model.${classname};

#parse("/template/java_author.include")
public interface ${classname}Service extends LambkitService<${classname}> {
}
