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

import java.io.InputStream;

import javax.mail.internet.MimeMessage;

import com.lambkit.plugin.mail.exception.MailException;

public interface JavaMailSender extends MailSender {

	/**
	 * Create a new JavaMail MimeMessage for the underlying JavaMail Session
	 * of this sender. Needs to be called to create MimeMessage instances
	 * that can be prepared by the client and passed to send(MimeMessage).
	 * @return the new MimeMessage instance
	 * @see #send(MimeMessage)
	 * @see #send(MimeMessage[])
	 */
	MimeMessage createMimeMessage();

	/**
	 * Create a new JavaMail MimeMessage for the underlying JavaMail Session
	 * of this sender, using the given input stream as the message source.
	 * @param contentStream the raw MIME input stream for the message
	 * @return the new MimeMessage instance
	 * @throws MailException
	 * in case of message creation failure
	*/
	MimeMessage createMimeMessage(InputStream contentStream) throws MailException;

	/**
	 * Send the given JavaMail MIME message.
	 * The message needs to have been created with {@link #createMimeMessage()}.
	 * @param mimeMessage message to send
	 * @throws MailException
	 * in case of failure when sending the message
	 * @see #createMimeMessage
	 */
	void send(MimeMessage mimeMessage) throws MailException;

	/**
	 * Send the given array of JavaMail MIME messages in batch.
	 * The messages need to have been created with {@link #createMimeMessage()}.
	 * @param mimeMessages messages to send
	 * @throws MailException
	 * in case of failure when sending a message
	 * @see #createMimeMessage
	 */
	void send(MimeMessage[] mimeMessages) throws MailException;

	/**
	 * Send the JavaMail MIME message prepared by the given MimeMessagePreparator.
	 * <p>Alternative way to prepare MimeMessage instances, instead of
	 * {@link #createMimeMessage()} and {@link #send(MimeMessage)} calls.
	 * Takes care of proper exception conversion.
	 * @param mimeMessagePreparator the preparator to use
	 * @throws org.springframework.mail.MailException
	 * in case of failure when sending the message
	 */
	void send(MimeMessagePreparator mimeMessagePreparator) throws MailException;

	/**
	 * Send the JavaMail MIME messages prepared by the given MimeMessagePreparators.
	 * <p>Alternative way to prepare MimeMessage instances, instead of
	 * {@link #createMimeMessage()} and {@link #send(MimeMessage[])} calls.
	 * Takes care of proper exception conversion.
	 * @param mimeMessagePreparators the preparator to use
	 * @throws MailException
	 * in case of failure when sending a message
	 */
	void send(MimeMessagePreparator[] mimeMessagePreparators) throws MailException;

}
