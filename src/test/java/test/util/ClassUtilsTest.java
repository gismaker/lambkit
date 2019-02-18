package test.util;

import com.jfinal.template.Directive;
import com.lambkit.common.util.ClassUtils;

public class ClassUtilsTest {

	public static void main(String[] args) {
		ClassUtils.scanPackageBySuper("com.lambkit.component.shiro", false, Directive.class);
	}
}
