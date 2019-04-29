package com.lambkit.generator.test;

import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.lambkit.common.app.DefaultApplication;
import com.lambkit.common.app.LambkitApplication;
import com.lambkit.generator.code.CodeGenerator;
import com.lambkit.generator.code.CodeMeta;
import com.lambkit.generator.code.CodeMetaBuilder;

public class CodeGeneratorTest {
	public void generate(String basePackage, Kv data) {
		if (StrKit.isBlank(basePackage)) {
			throw new IllegalArgumentException("basePackage can not be blank.");
		}
		CodeMetaBuilder builder = new CodeMetaBuilder();
		List<CodeMeta> codeMetas = builder.getCodeMeta(data);
		CodeGenerator codeGen = new CodeGenerator();

		String modelPackage = basePackage + ".model";
		String baseModelPackage = modelPackage + ".base";
		String modelDir = PathKit.getWebRootPath() + "/src/test/java/" + modelPackage.replace(".", "/");
		String baseModelDir = PathKit.getWebRootPath() + "/src/test/java/" + baseModelPackage.replace(".", "/");

		data.set("modelPackageName", modelPackage);
		data.set("baseModelPackageName", baseModelPackage);
		data.set("generateDaoInModel", false);
		codeGen.setTemplate("/com/lambkit/generator/code/model_template.jf").setOutputDir(modelDir)
				.setJavaName("@Model").generate(codeMetas, data);

		codeGen.setTemplate("/com/lambkit/generator/code/base_model_template.jf").setOutputDir(baseModelDir)
				.setJavaName("Base@Model").generate(codeMetas, data);

		String servicePackage = basePackage + ".service";
		String serviceImplPackage = servicePackage + ".impl";
		String serviceDir = PathKit.getWebRootPath() + "/src/test/java/" + servicePackage.replace(".", "/");
		String serviceImplDir = PathKit.getWebRootPath() + "/src/test/java/" + serviceImplPackage.replace(".", "/");

		data.set("servicePackageName", servicePackage);
		data.set("serviceImplPackageName", serviceImplPackage);
		data.set("generateDaoInModel", false);
		codeGen.setTemplate("/com/lambkit/generator/code/service_template.jf").setOutputDir(serviceDir)
				.setJavaName("@ModelService").generate(codeMetas, data);

		codeGen.setTemplate("/com/lambkit/generator/code/service_impl_template.jf").setOutputDir(serviceImplDir)
				.setJavaName("@ModelServiceImpl").generate(codeMetas, data);

	}
	
	public static void main(String[] args) {
		LambkitApplication server = new DefaultApplication();
		server.run();
		
		Kv data = Kv.by("tableRemovePrefixes", "upms_");
		data.set("includedTables", "upms_log");
		CodeGeneratorTest cg = new CodeGeneratorTest();
		cg.generate("test.generator", data);
		
		server.stop();
		System.out.println("-----------------over---------------");
	}
}
