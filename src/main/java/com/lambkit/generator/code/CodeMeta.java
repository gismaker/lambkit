package com.lambkit.generator.code;

import com.lambkit.db.meta.TableMeta;

public class CodeMeta {

	private String name;
	private String content;
	private TableMeta table;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public TableMeta getTable() {
		return table;
	}
	public void setTable(TableMeta table) {
		this.table = table;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
