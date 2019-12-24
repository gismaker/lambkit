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
package com.lambkit.plugin.mail;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.render.RenderManager;
import com.lambkit.plugin.mail.core.JavaMailSender;
import com.lambkit.plugin.mail.core.JavaMailSenderImpl;
import com.lambkit.plugin.mail.core.MimeMessageHelper;
import com.lambkit.plugin.mail.exception.MailSendException;
import com.lambkit.plugin.mail.mockhttp.MockHttpServletRequest;
import com.lambkit.plugin.mail.mockhttp.MockHttpServletResponse;

/**
 * 邮件发送
 * @author farmer
 *
 */
public class MailPro {
	
	private JavaMailSenderImpl mailSender = null;
	
	private String from = null;
	
	/**
	 * 构造函数
	 */
	MailPro(Properties props) {
		mailSender = new JavaMailSenderImpl();
		mailSender.setHost(props.getProperty("host"));
		mailSender.setPort(Integer.parseInt(props.getProperty("port")));
		mailSender.setUsername(props.getProperty("username"));
		mailSender.setPassword(props.getProperty("password"));
		mailSender.setProtocol(props.getProperty("protocol","smtp"));
		mailSender.setJavaMailProperties(props);
		this.from = props.getProperty("username");
	}
	
	public MailPro(MailConfig config) {
		// TODO Auto-generated constructor stub
		mailSender = new JavaMailSenderImpl();
		mailSender.setHost(config.getHost());
		mailSender.setPort(config.getPort());
		mailSender.setUsername(config.getUsername());
		mailSender.setPassword(config.getPassword());
		mailSender.setProtocol(config.getProtocol());
		mailSender.setJavaMailProperties(config.getProps());
		this.from = config.getUsername();
	}
	
	/**
	 * 
	 * @param to
	 * @param cc
	 * @param subject
	 * @param text
	 */
	public void send(String to,List<String> cc,  String subject, String text) {
		send(to, cc, subject, text, new ArrayList<File>());
	}
	
	/**
	 * 
	 * @param to
	 * @param cc
	 * @param subject
	 * @param text
	 * @param attachments
	 */
	public void send(String to,List<String> cc, String subject, String text, List<File> attachments) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		//
		try {
			MimeMessageHelper mimeMessageHelper = null;
			if(attachments != null && attachments.size()>0){
				mimeMessageHelper =	new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");
			}else{
				mimeMessageHelper =	new MimeMessageHelper(mimeMessage,"UTF-8");
			}
			mimeMessageHelper.setTo(to);
			if(cc != null){				
				mimeMessageHelper.setCc(cc.toArray(new String[]{}));
			}
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setFrom(from);
			mimeMessageHelper.setText(text,true);
			if(attachments != null && attachments.size()>0){
				for (File file : attachments) {
					mimeMessageHelper.addAttachment(file.getName(), file);
				}
			}
			mailSender.send(mimeMessage);
		}catch (Exception e) {
			throw new MailSendException("邮件发送失败！",e);
		}
	}
	
	/**
	 * 
	 * @param to
	 * @param cc
	 * @param subject
	 * @param viewPath
	 * @param dataMap
	 */
	public void send(String to, List<String> cc, String subject, String viewPath, Map<String, Object> dataMap) {
		send(to, cc, subject, viewPath, dataMap, null);
	}
	
	/**
	 * 
	 * @param to
	 * @param cc
	 * @param subject
	 * @param viewPath
	 * @param dataMap
	 * @param attachments
	 */
	public void send(String to, List<String> cc, String subject, String viewPath, Map<String, Object> dataMap, List<File> attachments) {
	    HttpServletResponse response = (HttpServletResponse) Proxy.newProxyInstance(
	    		getClass().getClassLoader(), new Class[]{HttpServletResponse.class}, new MockHttpServletResponse());
	    HttpServletRequest request = (HttpServletRequest) Proxy.newProxyInstance(
	    		getClass().getClassLoader(), new Class[]{HttpServletRequest.class}, new MockHttpServletRequest());
	    for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
	        request.setAttribute(entry.getKey(), entry.getValue());
		}
	    RenderManager.me().getRenderFactory().getRender(viewPath).setContext(request, response).render();
	    String text;
		try {
			text = response.getWriter().toString();
			send(to, cc, subject, text, attachments);
		} catch (IOException e) {
			throw new MailSendException("邮件发送失败！",e);
		}
	}
	
	/**
	 * 获取JavaMailSender
	 * @return
	 */
	public JavaMailSender getMailSender(){
		return mailSender;
	}
	
}
