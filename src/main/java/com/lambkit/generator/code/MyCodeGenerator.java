/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */package com.lambkit.generator.code;

import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;

public class MyCodeGenerator {

	public void generate(String basePackage, Kv data) {
		if (StrKit.isBlank(basePackage)) {
			throw new IllegalArgumentException("basePackage can not be blank.");
		}
		CodeMetaBuilder builder = new CodeMetaBuilder();
		List<CodeMeta> codeMetas = builder.getCodeMeta(data);
		generate(basePackage, data, codeMetas);
	}
	
	public void generate(String basePackage, Kv data, List<CodeMeta> codeMetas) {
		if (StrKit.isBlank(basePackage)) {
			throw new IllegalArgumentException("basePackage can not be blank.");
		}
		CodeGenerator codeGen = new CodeGenerator();

		String modelPackage = basePackage + ".model";
		String baseModelPackage = modelPackage + ".base";
		String modelDir = PathKit.getWebRootPath() + "/src/main/java/" + modelPackage.replace(".", "/");
		String baseModelDir = PathKit.getWebRootPath() + "/src/main/java/" + baseModelPackage.replace(".", "/");

		data.set("modelPackageName", modelPackage);
		data.set("baseModelPackageName", baseModelPackage);
		data.set("generateDaoInModel", false);
		codeGen.setTemplate("/com/lambkit/generator/code/model_template.jf").setOutputDir(modelDir)
				.setJavaName("@Model").generate(codeMetas, data);

		codeGen.setTemplate("/com/lambkit/generator/code/base_model_template.jf").setOutputDir(baseModelDir)
				.setJavaName("Base@Model").generate(codeMetas, data);

		String servicePackage = basePackage + ".service";
		String serviceImplPackage = servicePackage + ".impl";
		String serviceDir = PathKit.getWebRootPath() + "/src/main/java/" + servicePackage.replace(".", "/");
		String serviceImplDir = PathKit.getWebRootPath() + "/src/main/java/" + serviceImplPackage.replace(".", "/");

		data.set("servicePackageName", servicePackage);
		data.set("serviceImplPackageName", serviceImplPackage);
		data.set("generateDaoInModel", false);
		codeGen.setTemplate("/com/lambkit/generator/code/service_template.jf").setOutputDir(serviceDir)
				.setJavaName("@ModelService").generate(codeMetas, data);

		codeGen.setTemplate("/com/lambkit/generator/code/service_impl_template.jf").setOutputDir(serviceImplDir)
				.setJavaName("@ModelServiceImpl").generate(codeMetas, data);

	}
}
