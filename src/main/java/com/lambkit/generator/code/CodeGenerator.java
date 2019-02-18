package com.lambkit.generator.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.JavaKeyword;
import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;

public class CodeGenerator {

	private String engineName;
	protected String template = "/com/lambkit/generator/code/model_template.jf";
	protected String outputDir;
	protected String javaName;//Base@Model,@Model将会使用ModelName代替
	
	//protected boolean generateDaoInModel = false;
	//protected boolean generateChainSetter = false;
	
	protected JavaKeyword javaKeyword = JavaKeyword.me;
	
	/**
	 * 针对 Model 中七种可以自动转换类型的 getter 方法，调用其具有确定类型返回值的 getter 方法
	 * 享用自动类型转换的便利性，例如 getInt(String)、getStr(String)
	 * 其它方法使用泛型返回值方法： get(String)
	 * 注意：jfinal 3.2 及以上版本 Model 中的六种 getter 方法才具有类型转换功能
	 */
	@SuppressWarnings("serial")
	protected Map<String, String> getterTypeMap = new HashMap<String, String>() {{
		put("java.lang.String", "getStr");
		put("java.lang.Integer", "getInt");
		put("java.lang.Long", "getLong");
		put("java.lang.Double", "getDouble");
		put("java.lang.Float", "getFloat");
		put("java.lang.Short", "getShort");
		put("java.lang.Byte", "getByte");
	}};
	
	public CodeGenerator() {
		this.engineName = "forCode";
		Engine engine = Engine.create(engineName);
		engine.setSourceFactory(new ClassPathSourceFactory());
		engine.addSharedMethod(new StrKit());
		engine.addSharedObject("getterTypeMap", getterTypeMap);
		engine.addSharedObject("javaKeyword", javaKeyword);
	}
	
	public CodeGenerator(String engineName) {
		this.engineName = StrKit.isBlank(engineName) ? "forCode" : engineName;
		Engine engine = Engine.create(engineName);
		engine.setSourceFactory(new ClassPathSourceFactory());
		engine.addSharedMethod(new StrKit());
		engine.addSharedObject("getterTypeMap", getterTypeMap);
		engine.addSharedObject("javaKeyword", javaKeyword);
	}
	
	/**
	 * 使用自定义模板
	 */
	public CodeGenerator setTemplate(String template) {
		this.template = template;
		return this;
	}
	/**
	 * 输出路径
	 * @param outputDir
	 * @return
	 */
	public CodeGenerator setOutputDir(String outputDir) {
		this.outputDir = outputDir;
		return this;
	}
	
	/**
	 * 设置java名称
	 * @param javaName
	 * @return
	 */
	public CodeGenerator setJavaName(String javaName) {
		this.javaName = javaName;
		return this;
	}
	/*
	public void generate(List<CodeMeta> codeMeta, Kv data) {
		System.out.println("Generate model ...");
		System.out.println("Model Output Dir: " + outputDir);
		
		Engine engine = Engine.create("forCode");
		engine.setSourceFactory(new ClassPathSourceFactory());
		engine.addSharedMethod(new StrKit());
		
		for (CodeMeta modelMeta : codeMeta) {
			genModelContent(modelMeta, data);
		}
		writeToFile(codeMeta);
	}
	*/
	
	public void generate(List<CodeMeta> codeMeta, Kv data) {
		template = StrKit.isBlank(template) ? data.getStr("template") : template;
		outputDir = StrKit.isBlank(outputDir) ? data.getStr("outputDir") : outputDir;
		if (StrKit.isBlank(template)) {
			throw new IllegalArgumentException("template can not be blank.");
		}
		if (StrKit.isBlank(outputDir)) {
			throw new IllegalArgumentException("outputDir can not be blank.");
		}
		javaName = StrKit.isBlank(javaName) ? data.getStr("javaName") : javaName;
		if (StrKit.isBlank(javaName)) {
			throw new IllegalArgumentException("javaName can not be blank.");
		}
		System.out.println("Generate model ...");
		System.out.println("Model Output Dir: " + outputDir);
		
		for (CodeMeta modelMeta : codeMeta) {
			String codeName = javaName;
			codeName = codeName.replace("@Model", modelMeta.getTable().getModelName());
			modelMeta.setName(codeName);
			genModelContent(modelMeta, data);
		}
		writeToFile(codeMeta);
	}
	
	protected void genModelContent(CodeMeta modelMeta, Kv data) {
		data.set("tableMeta", modelMeta.getTable());
		String ret = Engine.use(engineName).getTemplate(template).renderToString(data);
		modelMeta.setContent(ret);
	}
	
	protected void writeToFile(List<CodeMeta> codeMeta) {
		try {
			for (CodeMeta modelMeta : codeMeta) {
				writeToFile(modelMeta);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 若 model 文件存在，则不生成，以免覆盖用户手写的代码
	 */
	protected void writeToFile(CodeMeta modelMeta) throws IOException {
		File dir = new File(outputDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		String target = outputDir + File.separator + modelMeta.getName() + ".java";
		
		File file = new File(target);
		if (file.exists()) {
			return ;	// 若 Model 存在，不覆盖
		}
		
		FileWriter fw = new FileWriter(file);
		try {
			fw.write(modelMeta.getContent());
		}
		finally {
			fw.close();
		}
	}
}
