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

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 
 * @author farmer
 *
 */
public class SimpleMailMessage implements MailMessage, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2920330500439593437L;

	private String from;

	private String replyTo;

	private String[] to;

	private String[] cc;

	private String[] bcc;

	private Date sentDate;

	private String subject;

	private String text;


	/**
	 * Create a new <code>SimpleMailMessage</code>.
	 */
	public SimpleMailMessage() {
	}

	/**
	 * Copy constructor for creating a new <code>SimpleMailMessage</code> from the state
	 * of an existing <code>SimpleMailMessage</code> instance.
	 * @throws IllegalArgumentException if the supplied message is <code>null</code> 
	 */
	public SimpleMailMessage(SimpleMailMessage original) {
		Assert.notNull(original, "The 'original' message argument cannot be null");
		this.from = original.getFrom();
		this.replyTo = original.getReplyTo();
		if (original.getTo() != null) {
			this.to = copy(original.getTo());
		}
		if (original.getCc() != null) {
			this.cc = copy(original.getCc());
		}
		if (original.getBcc() != null) {
			this.bcc = copy(original.getBcc());
		}
		this.sentDate = original.getSentDate();
		this.subject = original.getSubject();
		this.text = original.getText();
	}


	public void setFrom(String from) {
		this.from = from;
	}

	public String getFrom() {
		return this.from;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setTo(String to) {
		this.to = new String[] {to};
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getTo() {
		return this.to;
	}

	public void setCc(String cc) {
		this.cc = new String[] {cc};
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getCc() {
		return cc;
	}

	public void setBcc(String bcc) {
		this.bcc = new String[] {bcc};
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}


	/**
	 * Copy the contents of this message to the given target message.
	 * @param target the <code>MailMessage</code> to copy to
	 * @throws IllegalArgumentException if the supplied <code>target</code> is <code>null</code> 
	 */
	public void copyTo(MailMessage target) {
		Assert.notNull(target, "The 'target' message argument cannot be null");
		if (getFrom() != null) {
			target.setFrom(getFrom());
		}
		if (getReplyTo() != null) {
			target.setReplyTo(getReplyTo());
		}
		if (getTo() != null) {
			target.setTo(getTo());
		}
		if (getCc() != null) {
			target.setCc(getCc());
		}
		if (getBcc() != null) {
			target.setBcc(getBcc());
		}
		if (getSentDate() != null) {
			target.setSentDate(getSentDate());
		}
		if (getSubject() != null) {
			target.setSubject(getSubject());
		}
		if (getText() != null) {
			target.setText(getText());
		}
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("SimpleMailMessage: ");
		sb.append("from=").append(this.from).append("; ");
		sb.append("replyTo=").append(this.replyTo).append("; ");
		sb.append("to=").append(arrayToCommaDelimitedString(this.to)).append("; ");
		sb.append("cc=").append(arrayToCommaDelimitedString(this.cc)).append("; ");
		sb.append("bcc=").append(arrayToCommaDelimitedString(this.bcc)).append("; ");
		sb.append("sentDate=").append(this.sentDate).append("; ");
		sb.append("subject=").append(this.subject).append("; ");
		sb.append("text=").append(this.text);
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SimpleMailMessage)) {
			return false;
		}
		SimpleMailMessage otherMessage = (SimpleMailMessage) other;
		return (nullSafeEquals(this.from, otherMessage.from) &&
				nullSafeEquals(this.replyTo, otherMessage.replyTo) &&
				java.util.Arrays.equals(this.to, otherMessage.to) &&
				java.util.Arrays.equals(this.cc, otherMessage.cc) &&
				java.util.Arrays.equals(this.bcc, otherMessage.bcc) &&
				nullSafeEquals(this.sentDate, otherMessage.sentDate) &&
				nullSafeEquals(this.subject, otherMessage.subject) &&
				nullSafeEquals(this.text, otherMessage.text));
	}

	@Override
	public int hashCode() {
		int hashCode = (this.from == null ? 0 : this.from.hashCode());
		hashCode = 29 * hashCode + (this.replyTo == null ? 0 : this.replyTo.hashCode());
		for (int i = 0; this.to != null && i < this.to.length; i++) {
			hashCode = 29 * hashCode + (this.to == null ? 0 : this.to[i].hashCode());
		}
		for (int i = 0; this.cc != null && i < this.cc.length; i++) {
			hashCode = 29 * hashCode + (this.cc == null ? 0 : this.cc[i].hashCode());
		}
		for (int i = 0; this.bcc != null && i < this.bcc.length; i++) {
			hashCode = 29 * hashCode + (this.bcc == null ? 0 : this.bcc[i].hashCode());
		}
		hashCode = 29 * hashCode + (this.sentDate == null ? 0 : this.sentDate.hashCode());
		hashCode = 29 * hashCode + (this.subject == null ? 0 : this.subject.hashCode());
		hashCode = 29 * hashCode + (this.text == null ? 0 : this.text.hashCode());
		return hashCode;
	}


	private static String[] copy(String[] state) {
		String[] copy = new String[state.length];
		System.arraycopy(state, 0, copy, 0, state.length);
		return copy;
	}

	public static boolean nullSafeEquals(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}
		if (o1.equals(o2)) {
			return true;
		}
		if (o1.getClass().isArray() && o2.getClass().isArray()) {
			if (o1 instanceof Object[] && o2 instanceof Object[]) {
				return Arrays.equals((Object[]) o1, (Object[]) o2);
			}
			if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
				return Arrays.equals((boolean[]) o1, (boolean[]) o2);
			}
			if (o1 instanceof byte[] && o2 instanceof byte[]) {
				return Arrays.equals((byte[]) o1, (byte[]) o2);
			}
			if (o1 instanceof char[] && o2 instanceof char[]) {
				return Arrays.equals((char[]) o1, (char[]) o2);
			}
			if (o1 instanceof double[] && o2 instanceof double[]) {
				return Arrays.equals((double[]) o1, (double[]) o2);
			}
			if (o1 instanceof float[] && o2 instanceof float[]) {
				return Arrays.equals((float[]) o1, (float[]) o2);
			}
			if (o1 instanceof int[] && o2 instanceof int[]) {
				return Arrays.equals((int[]) o1, (int[]) o2);
			}
			if (o1 instanceof long[] && o2 instanceof long[]) {
				return Arrays.equals((long[]) o1, (long[]) o2);
			}
			if (o1 instanceof short[] && o2 instanceof short[]) {
				return Arrays.equals((short[]) o1, (short[]) o2);
			}
		}
		return false;
	}
	
	public static String nullSafeToString(Object obj) {
		if (obj == null) {
			return "null";
		}
		if (obj instanceof String) {
			return (String) obj;
		}
		if (obj instanceof Object[]) {
			return nullSafeToString((Object[]) obj);
		}
		if (obj instanceof boolean[]) {
			return nullSafeToString((boolean[]) obj);
		}
		if (obj instanceof byte[]) {
			return nullSafeToString((byte[]) obj);
		}
		if (obj instanceof char[]) {
			return nullSafeToString((char[]) obj);
		}
		if (obj instanceof double[]) {
			return nullSafeToString((double[]) obj);
		}
		if (obj instanceof float[]) {
			return nullSafeToString((float[]) obj);
		}
		if (obj instanceof int[]) {
			return nullSafeToString((int[]) obj);
		}
		if (obj instanceof long[]) {
			return nullSafeToString((long[]) obj);
		}
		if (obj instanceof short[]) {
			return nullSafeToString((short[]) obj);
		}
		String str = obj.toString();
		return (str != null ? str : "");
	}
	
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}
	
	public static String arrayToCommaDelimitedString(Object[] arr) {
		return arrayToDelimitedString(arr, ",");
	}
	
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (isEmpty(arr)) {
			return "";
		}
		if (arr.length == 1) {
			return nullSafeToString(arr[0]);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}
}
