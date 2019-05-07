package com.lambkit.common.dao;

import java.io.Serializable;

public class FileModel implements Serializable {

	private static final long serialVersionUID = -6856206450295256106L;
	
	private String fileName;
	
	public FileModel(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
