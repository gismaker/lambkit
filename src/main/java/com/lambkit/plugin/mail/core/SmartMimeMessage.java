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

import javax.activation.FileTypeMap;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author farmer
 *
 */
class SmartMimeMessage extends MimeMessage {

	private final String defaultEncoding;

	private final FileTypeMap defaultFileTypeMap;


	/**
	 * Create a new SmartMimeMessage.
	 * @param session the JavaMail Session to create the message for
	 * @param defaultEncoding the default encoding, or <code>null</code> if none
	 * @param defaultFileTypeMap the default FileTypeMap, or <code>null</code> if none
	 */
	public SmartMimeMessage(Session session, String defaultEncoding, FileTypeMap defaultFileTypeMap) {
		super(session);
		this.defaultEncoding = defaultEncoding;
		this.defaultFileTypeMap = defaultFileTypeMap;
	}


	/**
	 * Return the default encoding of this message, or <code>null</code> if none.
	 */
	public final String getDefaultEncoding() {
		return this.defaultEncoding;
	}

	/**
	 * Return the default FileTypeMap of this message, or <code>null</code> if none.
	 */
	public final FileTypeMap getDefaultFileTypeMap() {
		return this.defaultFileTypeMap;
	}

}
