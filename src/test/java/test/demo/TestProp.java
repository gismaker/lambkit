package test.demo;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

public class TestProp {

	public static void main(String[] args) {
		Prop p = PropKit.use("prop/t.properties");
		System.out.println(p.get("lambkit.version"));
	}
}
