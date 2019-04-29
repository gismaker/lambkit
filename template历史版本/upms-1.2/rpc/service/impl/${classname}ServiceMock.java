##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.rpc.service.impl;

import com.lambkit.common.service.BaseServiceMock;

import $!{basepackage}.rpc.model.${classname};
import $!{basepackage}.rpc.api.${classname}Service;

#parse("/template/java_author.include")
public class ${classname}ServiceMock extends BaseServiceMock<${classname}> implements ${classname}Service {
}