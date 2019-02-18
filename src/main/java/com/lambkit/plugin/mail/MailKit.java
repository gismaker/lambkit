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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lambkit.plugin.mail.core.JavaMailSender;

/**
 * 发送邮件工具
 * @author farmer
 *
 */
public class MailKit {
	
	static Map<String, MailPro> proMap = new HashMap<String, MailPro>();
	
	static MailPro mailPro = null;
	
	/**
	 * 
	 * @param configName
	 * @param mailPro
	 */
	static void init(String configName , MailPro mailPro){
		if(proMap.get(configName) != null){
			throw new RuntimeException(configName+"配置的Mail已经存在！");
		}
		proMap.put(configName, mailPro);
		if(MailPlugin.MAIN_CONFIG.equals(configName)){
			MailKit.mailPro = mailPro;
		}
	}
	
	/**
	 * 
	 * @param configName
	 * @return
	 */
	public static MailPro use(String configName){
		MailPro mailPro = proMap.get(configName);
		if(mailPro == null){
			throw new RuntimeException(configName+"配置的Mail不存在！");
		}
		return mailPro;
	}
	
	/**
	 * 发送邮件
	 * @param to
	 * 	收件人
	 * @param cc
	 * 	发件人
	 * @param subject
	 * 	主题
	 * @param text
	 * 	内容
	 */
	public static void send(String to,List<String> cc,String subject,String text){
		mailPro.send(to, cc, subject, text);
	}
	
	/**
	 * 发送邮件
	 * @param to
	 * 	收件人
	 * @param cc
	 * 	发件人
	 * @param subject
	 * 	主题
	 * @param text
	 * 	内容
	 * @param attachments
	 * 	附件
	 * 
	 */
	public static void send(String to,List<String> cc,String subject,String text,List<File> attachments){
		mailPro.send(to, cc, subject, text,attachments);
	}
	
	/**
	 * 
	 * @param to
	 * @param cc
	 * @param subject
	 * @param viewpath
	 * @param dataMap
	 */
	public static void send(String to,List<String> cc ,String subject,String viewpath ,Map<String, Object> dataMap){
		mailPro.send(to, cc, subject, viewpath , dataMap);
	}
	
	/**
	 * 
	 * @param to
	 * @param cc
	 * @param subject
	 * @param viewpath
	 * @param dataMap
	 * @param attachments
	 */
	public static void send(String to,List<String> cc ,String subject,String viewpath ,Map<String, Object> dataMap,List<File> attachments){
		mailPro.send(to, cc, subject, viewpath , dataMap , attachments);
	}

	/**
	 * 获取JavaMailSender
	 * @return
	 * 当前实例的JavaMailSender，用来定制化更复杂的邮件发送需求
	 */
	public static JavaMailSender getMailSender(){
		return mailPro.getMailSender();
	}
	
}
