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
package com.lambkit.plugin.mail.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;

/**
 * 
 * @author farmer
 *
 */
public class ConfigurableMimeFileTypeMap extends FileTypeMap {

	
	
	/**
	 * 
	 */
	private String[] mappings;

	/**
	 * 
	 */
	private FileTypeMap fileTypeMap;


	/**
	 * 
	 * @param mappings
	 * 	mappings
	 */
	public void setMappings(String[] mappings) {
		this.mappings = mappings;
	}

	/**
	 * 
	 */
	public void afterPropertiesSet() {
		getFileTypeMap();
	}

	/**
	 * 
	 * @return
	 * 	FileTypeMap
	 */
	protected final FileTypeMap getFileTypeMap() {
		if (this.fileTypeMap == null) {
			String name = "mime.types";
			try {
				this.fileTypeMap = createFileTypeMap(getClass().getResourceAsStream(name), this.mappings);
			}
			catch (IOException ex) {
				throw new IllegalStateException(
						"Could not load specified MIME type mapping file: " + name, ex);
			}
		}
		return this.fileTypeMap;
	}

	protected FileTypeMap createFileTypeMap(InputStream is, String[] mappings) throws IOException {
		
		MimetypesFileTypeMap fileTypeMap = null;
		if (is != null) {
			try {
				fileTypeMap = new MimetypesFileTypeMap(is);
			}
			finally {
				is.close();
			}
		}
		else {
			fileTypeMap = new MimetypesFileTypeMap();
		}
		if (mappings != null) {
			for (String mapping : mappings) {
				fileTypeMap.addMimeTypes(mapping);
			}
		}
		return fileTypeMap;
	}


	/**
	 * 
	 */
	@Override
	public String getContentType(File file) {
		return getFileTypeMap().getContentType(file);
	}

	/**
	 * 
	 */
	@Override
	public String getContentType(String fileName) {
		return getFileTypeMap().getContentType(fileName);
	}

}
