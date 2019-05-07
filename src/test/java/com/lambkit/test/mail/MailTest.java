package com.lambkit.test.mail;

public class MailTest {
	/*
	1、创建邮件配置文件：

	#邮箱HOST
	host=smtp.qq.com
	#协议
	protocol=smtp
	#端口
	port=465
	#发送邮箱
	username=
	#密码
	password=
	#权限认证
	mail.smtp.auth=true
	#超时时间
	mail.smtp.timeout=5000
	#是否是ssl
	mail.smtp.ssl.enable=true
	
	2、JFinalConfig中启用插件

	me.add(new MailPlugin(PropKit.use("mail.properties").getProperties()));
	
	3、发送内容固定邮件

	普通邮件：MailKit.send(“收件人”,Arrays.asList(“抄送1″,”抄送2”), “邮件标题”, “邮件内容”);
	附件邮件：MailKit.send(“收件人”,Arrays.asList(“抄送1″,”抄送2”), “邮件标题”, “邮件内容”,Arrays.asList(new File(“附件1”),new File(“附件2”)));
	
	4、发送模板类邮件

	插件除了支持内容固定的邮件外，还支撑模板邮件，模板默认使用为Jfinal的IMainRenderFactory的模板

	普通邮件：
	Map dataMap = new HashMap();
	dataMap.put(“var1”, “变量1”);
	dataMap.put(“var2”, “变量2”);
	MailKit.send(“收件人”,Arrays.asList(“抄送1″,”抄送2”), “邮件标题”, “模板路径”,dataMap);
	附件邮件：
	MailKit.send(“收件人”,Arrays.asList(“抄送1″,”抄送2”), “邮件标题”, “模板路径”,dataMap,Arrays.asList(new File(“附件1”),new File(“附件2”)));
	
	5、多个邮件源支持

	插件不仅仅支持一个邮件发送源，还可以极速的支持多个邮件发送源

	1、启动插件是指定发送源名称：me.add(new MailPlugin(“mail2”,PropKit.use(“mail2.properties”).getProperties()));
	2、发送邮件时指定发送源：MailKit.use(“mail2”).send(…);
	
	*/
}
