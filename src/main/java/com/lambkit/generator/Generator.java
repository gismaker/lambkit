package com.lambkit.generator;

public abstract class Generator implements IGenerator {

	protected GeneratorContext context;

	public Generator(GeneratorContext context) {
		this.context = context;
	}

	public GeneratorContext getContext() {
		return context;
	}

	public void setContext(GeneratorContext context) {
		this.context = context;
	}
	
}
