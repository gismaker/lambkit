## JAVA 分布式快速开发框架 Lambkit

Lambkit是基于JFinal的分布式Javaweb快速开发框架，其核心设计目标是极速开发，快速应用。将项目开发的基本要素集合成企业级开发解决方案，快速搞定项目，为您节约更多时间，去陪恋人、家人和朋友 ;)

Lambkit是在我们项目开发过程中不断学习和积累起来的一个基础开发框架，我们将一直不断完善和更新。

交流QQ群：276782534

### Lambkit有如下主要特点
- 集成了多种流行技术：shiro、redis、ehcache、swagger、montan、zbus、TongLinkQ。
- 开发了多种基础功能：mq、rpc、mail、WebSocket、分布式节点、反向代理、代码自动生成、动态表单、动态SQL、多维分析、表格动态管理。
- 实现了多种应用功能：微服务架构、基于zbus或motan的RPC框架、用户与权限管理、基于Redis的分布式session技术、基于zbus的消息框架、E-Mail后台发送技术、后台接口展示技术、分布式节点动态管理、自定义反向代理。
- 配套了多种独立系统：UPMS单点登录系统（来源于项目[zheng](https://gitee.com/shuzheng/zheng)）、Mgrdb表格管理系统等，应用于不同的行业。

### Lambkit启动方式

- 下载lambkit源码
- 导入Ecplise（import->Existing Maven Projects）,java使用1.8版本
- MySQL中导入lambkit_opensource.sql数据库
- lambkit.properties中填写lambkit.db.password=数据库密码
- 启动Redis
- 右键com.lambkit.LambkitApplication->Run As->Java Application
- 启动完成

![输入图片说明](https://images.gitee.com/uploads/images/2019/0604/163339_1942786c_136253.png "启动完成2.png")

### Lambkit管理平台

- 启动完成后，输入网址：[http://localhost:8080/lambkit/dev]，进入主页

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/152134_1fcee603_136253.png "TIM截图20190219130726.png")

- 控制台
  
用于开发人员查看系统启动后，运行的Controller、Model、Handler、Plugin、模板语言标签，以及配置的Route、配置文件内容等信息。

controller信息

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/152705_bb0b74bf_136253.png "1-2-controller.png")

model和table

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/152721_b54189e9_136253.png "1-4-model.png")

plugin信息

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/152736_108ff3d6_136253.png "1-5-plugin.png")

模板语言标签信息

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/152744_12cf7684_136253.png "1-6-tag.png")

- 节点管理
  
用于分布式节点的管理和维护。

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/152821_5f3e45c0_136253.png "节点管理.png")

- 数据管理

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/152843_c3ca1d37_136253.png "数据管理-主页.png")

该表格的所有接口

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/152851_fadf74fa_136253.png "数据管理-接口页.png")

该表格的数据列表

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/152858_dac77c41_136253.png "数据管理-列表页.png")

- 帮助文档

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/152912_c5aa78ab_136253.png "帮助文档.png")


### Lambkit代码自动生成
代码自动生成有三部分组成，代码自动生成引擎、代码模板和Mgrdb模块组成。
- 代码自动生成引擎用于生成内容并保存。
- Mgrdb模块用于保存数据库内所有表格的相关配置。

#### 使用模板

默认使用工程目录下的template的模板，在“template历史版本”里面有upms模板和通用模板1.1、1.2版本可以参考使用。

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



