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
package com.lambkit.plugin.mail.exception;

/**
 * 
 * @author farmer
 *
 */
public class MailParseException extends MailException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8089227557387828745L;

	/**
	 * Constructor for MailParseException.
	 * @param msg the detail message
	 */
	public MailParseException(String msg) {
		super(msg);
	}

	/**
	 * Constructor for MailParseException.
	 * @param msg the detail message
	 * @param cause the root cause from the mail API in use
	 */
	public MailParseException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * Constructor for MailParseException.
	 * @param cause the root cause from the mail API in use
	 */
	public MailParseException(Throwable cause) {
		super("Could not parse mail", cause);
	}

}
