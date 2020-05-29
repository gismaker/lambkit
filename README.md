## JAVA 分布式快速开发框架 Lambkit

Lambkit是基于JFinal的分布式Javaweb快速开发框架，其核心设计目标是极速开发，快速应用。将项目开发的基本要素集合成企业级开发解决方案，快速搞定项目，为您节约更多时间，去陪恋人、家人和朋友 ;)

Lambkit是在我们项目开发过程中不断学习和积累起来的一个基础开发框架，我们将一直不断完善和更新。

交流QQ群：276782534

### Lambkit有如下主要特点
- 集成了多种流行技术：shiro、redis、ehcache、swagger、montan、dubbo、zbus、activemq、hadoop、hbase、kafka。
- 开发了多种基础功能：消息框架mq、rpc、mail、WebSocket、Api路由、Api网关、分布式节点、反向代理、代码自动生成、动态表单、动态SQL、多维分析、表格动态管理。
- 实现了多种应用功能：微服务架构、用户与权限管理[token、jwt]、分布式session、E-Mail发送、分布式节点动态管理。
- 配套了多种独立系统：UPMS单点登录系统（lambkit-zheng项目）、CMS内容管理系统（lambkit-zheng项目）、Lambkit管理平台（lambkit-admin项目）、Mgrdb表格管理系统等，应用于不同的行业。

### Lambkit启动方式

**源码启动**

- 下载lambkit源码
- 导入Ecplise（import->Existing Maven Projects）,java使用1.8版本
- 右键com.lambkit.LambkitApplication->Run As->Java Application
- 启动完成

![输入图片说明](https://images.gitee.com/uploads/images/2019/0604/163339_1942786c_136253.png "启动完成2.png")

**maven 依赖**

```xml
<dependency>
    <groupId>com.lambkit</groupId>
    <artifactId>lambkit</artifactId>
    <version>1.0.3</version>
</dependency>
```

**Hello World**

```java
public class IndexController extends LambkitController {
	public void index() {
		renderText("hello world!");
	}
}
```

```java
public class TestApplicationStart extends LambkitApplicationContext {
	@Override
	public void configModule(LambkitModule module) {
		LambkitModule config = new LambkitModule() {
    		@Override
    		public void configRoute(Routes me) {
    			me.add("/", IndexController.class, "");
    		}
		};
		module.addModule(config);
	}
	
	public static void main(String[] args) {
		LambkitApplication.run(TestApplicationStart.class, null);
	}
}
```

### Lambkit代码自动生成
代码自动生成有三部分组成，代码自动生成引擎、代码模板和Mgrdb模块组成。
- 代码自动生成引擎用于生成内容并保存。
- Mgrdb模块用于保存数据库内所有表格的相关配置。

#### 使用模板

默认使用工程目录下的template的模板，在“MSCH模板库”里面有upms模板和通用模板1.2、1.3版本可以参考使用。

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/153132_fe058526_136253.png "代码自动生成1.png")

#### 启动生成程序

如果没有进行表格配置，应当lambkit.generator.mgrdb为normal模式下运行SysconfigIniTest，初始化表格配置。
再运行GeneratorTest，配置前缀、排除表格或仅包含表格，生成代码。

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/153122_fe11f748_136253.png "代码自动生成3.png")

#### 生成代码包如下

代码生成后可在配置的输出地址浏览代码文件，如果输出地址是本工程，可刷新工程查看。

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/153140_280a3b3a_136253.png "代码自动生成2.png")

#### 生成代码的使用

model直接可以获取Service的实例，如果是RPC的是模式下，model就会远程获取Service，使用代码可以不用更改。

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/160359_765648f6_136253.png "model使用.png")



## 更多支持

**Lambkit 极速开发，快速应用，QQ群欢迎你的加入: 276782534**

**Lambkit 官方网站：[http://www.lambkit.com](http://www.lambkit.com)**

Lambkit涉及到的acvitemq-consul-nginx-redis-zookeeper软件Windows绿色版
链接：https://pan.baidu.com/s/1FLfyBDKO1PMO-lVHy6eG8w 
提取码：z6oe 


