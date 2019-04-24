# 什么是velocity
Velocity是一个基于java的模板引擎（template engine）。它允许任何人仅仅简单的使用模板语言（template language）来引用由java代码定义的对象。

## 变量定义
 #set($maxValue=5)
 #set($name="Bob")
 #set($arrayName=["element1","element2",...])

## 注释
 ## This is a single line comment.
 #*
 Thus begins a multi-line comment. Online visitors won't
 see this text because the Velocity Templating Engine will
 ignore it.
 *#

## 静态引用输出
Velocity 遇到一个不能处理的引用时，一般他会直接输出这个引用$email 的写法,页面上会看到的是$email，如下例,我们可以在$后面加上一个!号，那么就会输出空白:

<input type="text" name="email" value="$email"/>
<input type="text" name="email" value="$!email"/>

## 转义字符
如果email 己定义了(比如它的值是foo),而这里你却想输出$email. 这样一个字符串,就需要使用转义字符”\”.

 ## The following line defines $email in this template:
 #set( $email = "foo" )
 $email
 \$email
 \\$email
 \\\$email
上面的模板在web 页面上的输出将是:

 foo
 $email
 \foo
 \$email

## 关于null
但注意：如果右边的操作数是一个属性或命令的引用而返回null,那么赋值将不会成功，且在随后也不能再取出使用. 如下例:

 #set( $result = $query.criteria("name"))
 The result of the first query is $result
 #set( $result = $query.criteria("address"))
 The result of the second query is $result
 
如果$query.criteria("name") 返回的是字符串"bill", 但$query.criteria("address") 返回null,上面的输出结果将是:

The result of the first query is bill
The result of the second query is bill

又如下例：

 #set( $criteria = ["name", "address"] )
 #foreach($criterion in $criteria )
    #set($result=$query.criteria($criterion) )
    #if($result)
        Query was successful
 #end
 
在上例中，就不能依赖if($result)来决定查询是否成功. #set右边如果是null会 它将不能被赋其它值.一个解决办法是，每次都将$result设为false:

 #set( $criteria = ["name", "address"] )
 #foreach($criterion in $criteria )
    #set($result = false)
    #set($result=$query.criteria($criterion) )
    #if($result)
        Query was successful
 #end

## 条件判断
 #if($foo<10)
    <strong>Go North</strong>
 #elseif($foo==10)
    <strong>Go East</strong>
 #elseif($bar==6)
    <strong>Go South</strong>
 #else
    <strong>Go West</strong>
 #end

## 循环
通过引用变量$velocityCount 可以访问到Velocity 提供的计数器:

 <table>
 #foreach( $customer in $customerList )
    <tr><td>$velocityCount</td><td>$customer.Name</td></tr>
 #end
 </table>

$velocityCount是默认的计数器引用，你可以在配置velocity.properties 中改成你喜欢的:

## include
include脚本元素让模板设计者可以在模板中引入一个本地文件, 这个被引入的文件将不会经过Velocity 的解析. 安全起见，可以引放的文件只是是配置参数TEMPLATE_ROOT所定义目录下的，默认为当前目录下.

 #include( "one.txt" )
多个文件或者用变量名代替：

 #include( "greetings.txt", $seasonalstock )

## parse
parse元素指示可以引入一个包含TVL 的本地文件，这个文件将被Veloict engine 解析输出。

 #parse( "me.vm" )
与#include 指令不同, #parse 可以从引入的模板中得到变量引用.但#parse 指令只能接受一个参数.

VTL templates 被#parse 的模板中还可以再包含#parse 声明，默认的深度为10，这是由配置参数directive.parse.max.depth 在文件velocity.properties 中决定的，你可以修改它以适合项目要求

## stop
stop指令用来指示在模板的某处，engine 停止解析，这一般用来调用。用法很简单.

 #stop

## 宏调用
macro指令让模板设计者可以将些重复、相关的的脚本版断定义为一个功能块.

 #macro( tablerows $color $somelist )
 #foreach( $something in $somelist )
    <tr><td bgcolor=$color>$something</td></tr>
 #end
 #end
然后，我们在页面中来使用：

 #set( $greatlakes = ["Superior","Michigan","Huron","Erie","Ontario"] )
 #set( $color = "blue" )
 <table>
    #tablerows( $color $greatlakes )
 </table>
输出结果：

<table>
    <tr><td bgcolor="blue">Superior</td></tr>
    <tr><td bgcolor="blue">Michigan</td></tr>
    <tr><td bgcolor="blue">Huron</td></tr>
    <tr><td bgcolor="blue">Erie</td></tr>
    <tr><td bgcolor="blue">Ontario</td></tr>
</table>
如果将宏#tablerows($color $list) 定义到一个模板库中(Velocimacros template library), 其它模板就都可以访问它了.

尽量不要直接在模板中使用#parse() 包含#macro() 指令.因为#parse() 动作在运行时执行,时会有一个在VM 中查找元素的过程.