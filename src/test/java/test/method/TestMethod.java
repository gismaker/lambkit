package test.method;
import java.lang.reflect.Method;

import com.lambkit.common.aop.AopKit;
import com.lambkit.common.util.ArrayUtils;

public class TestMethod {

	public static void main(String[] args) {
		TestMethodObj obj = AopKit.newInstance(TestMethodObj.class);
		
        Method[] methods = obj.getClass().getMethods();
        if (ArrayUtils.isNotEmpty(methods)) {
            for (Method m : methods) {
                if (m.getName().startsWith("set")) {
                    System.out.println(m.getName() + "," + m.getParameterTypes().length);
                }
            }
        }
	}
}
