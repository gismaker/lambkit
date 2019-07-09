package com.lambkit.test.generator;


import com.jfinal.kit.Kv;
import com.lambkit.Application;
import com.lambkit.LambkitApplication;
import com.lambkit.generator.code.MyCodeGenerator;

public class CodeGeneratorTest {
	public static void main(String[] args) {
		LambkitApplication application = new LambkitApplication(Application.class);
		application.setWebEnvironment(false);
		application.run(args);
		
		Kv data = Kv.by("tableRemovePrefixes", "upms_");
		data.set("includedTables", "upms_log");
		MyCodeGenerator cg = new MyCodeGenerator();
		cg.generate("test.generator", data);
		
		application.stop();
		System.out.println("-----------------over---------------");
	}
}
