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
 */
package com.lambkit.generator.impl;

import java.util.Map;
import com.lambkit.generator.GeneratorContext;
import com.lambkit.generator.IGenerator;

public class CommonGenerator implements IGenerator {
	@Override
	public void generate(GeneratorContext g, String templatePath, Map<String, Object> options) {
		// TODO Auto-generated method stub
		Map<String, Object> templateModel = g.createTemplateModel();
		templateModel.putAll(options);
		g.generate(templateModel, templatePath);
	}

	@Override
	public Object execute(GeneratorContext g, String templateFilePath, Map<String, Object> options) {
		// TODO Auto-generated method stub
		return g.execute(options, templateFilePath);
	}
}
