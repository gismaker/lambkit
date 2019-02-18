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

import java.util.Date;

import com.lambkit.plugin.mail.exception.MailParseException;

/**
 * 
 * @author farmer
 *
 */
public interface MailMessage {

	public void setFrom(String from) throws MailParseException;

	public void setReplyTo(String replyTo) throws MailParseException;

	public void setTo(String to) throws MailParseException;

	public void setTo(String[] to) throws MailParseException;

	public void setCc(String cc) throws MailParseException;

	public void setCc(String[] cc) throws MailParseException;

	public void setBcc(String bcc) throws MailParseException;

	public void setBcc(String[] bcc) throws MailParseException;

	public void setSentDate(Date sentDate) throws MailParseException;

	public void setSubject(String subject) throws MailParseException;

	public void setText(String text) throws MailParseException;

}
