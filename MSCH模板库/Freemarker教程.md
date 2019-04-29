Freemarker模板教程

# 基础语法

## 0、注释

//注释
<#-- 注释内容 -->

格式部分,不会输出

## 1、字符输出
${emp.name?if_exists}　　　　　　// 变量存在，输出该变量，否则不输出
${emp.name!}　　　　　　　　　　  // 变量存在，输出该变量，否则不输出

${emp.name?default("xxx")}     // 变量不存在，取默认值xxx 
${emp.name!"xxx"}    　　　　　　// 变量不存在，取默认值xxx
常用内部函数：

${"123<br>456"?html}    　　// 对字符串进行HTML编码，对html中特殊字符进行转义
${"str"?cap_first}    　　  // 使字符串第一个字母大写 
${"Str"?lower_case}        // 将字符串转换成小写 
${"Str"?upper_case}        // 将字符串转换成大写
${"str"?trim}              // 去掉字符串前后的空白字符
字符串的两种拼接方式拼接：

${"hello${emp.name!}"}     // 输出hello+变量名
${"hello"+emp.name!}       // 使用+号来连接，输出hello+变量名
可以通过如下语法来截取子串:


<#assign str = "abcdefghijklmn"/>

// 方法1
${str?substring(0,4)}  // 输出abcd

// 方法2
${str[0]}${str[4]}    // 结果是ae
${str[1..4]}    　　　 // 结果是bcde

// 返回指定字符的索引
${str?index_of("n")}

## 2、日期输出
${emp.date?string('yyyy-MM-dd')} //日期格式

## 3、数字输出(以数字20为例)

${emp.name?string.number}    　// 输出20
${emp.name?string.currency}    // ￥20.00 
${emp.name?string.percent}     // 20%
${1.222?int} 　　　　　　　　　　 // 将小数转为int，输出1

<#setting number_format="percent"/>    // 设置数字默认输出方式('percent',百分比)
<#assign answer=42/>    　　　　　　　　 // 声明变量 answer 42
#{answer}    　　　　　　　　 // 输出 4,200%
${answer?string}    　　　　 // 输出 4,200%
${answer?string.number} 　　// 输出 42
${answer?string.currency}  // 输出 ￥42.00
${answer?string.percent} 　// 输出 4,200%
#{answer}    　　　　　　　　// 输出 42

数字格式化插值可采用#{expr;format}形式来格式化数字,其中format可以是:
mX:小数部分最小X位
MX:小数部分最大X位

如下面的例子:
<#assign x=2.582/>
<#assign y=4/>
 #{x; M2}    // 输出2.58
 #{y; M2}    // 输出4
 #{x; m2}    // 输出2.58
 #{y; m2}    // 输出4.0
 #{x; m1M2}  // 输出2.58
 #{x; m1M2}  // 输出4.0

##  4、申明变量

// 声明变量,插入布尔值进行显示,注意不要用引号
<#assign foo=false/> 
// 当为true时输出"yes",否则输出"no"
${foo?string("yes","no")} 

申明变量的几种方式
<#assign name=value> 
<#assign name1=value1 name2=value2 ... nameN=valueN> 
<#assign same as above... in namespacehash>

<#assign name> 
capture this 
</#assign>

<#assign name in namespacehash> 
capture this 
</#assign>

## 5、比较运算符

表达式中支持的比较运算符有如下几个:
= 或 == ：判断两个值是否相等.
!= ：判断两个值是否不等.
> 或 gt ：判断左边值是否大于右边值
>= 或 gte ：判断左边值是否大于等于右边值
< 或 lt ：判断左边值是否小于右边值
<= 或 lte ：判断左边值是否小于等于右边值

## 6、算术运算符

FreeMarker表达式中完全支持算术运算,
FreeMarker支持的算术运算符包括:+, - , * , / , % 
注意：
（1）、运算符两边必须是数字
（2）、使用+运算符时,如果一边是数字,一边是字符串,就会自动将数字转换为字符串再连接,如:${3 + "5"},结果是:35

## 7、逻辑运算符

逻辑运算符有如下几个:
逻辑与:&&
逻辑或:||
逻辑非:!
逻辑运算符只能作用于布尔值,否则将产生错误

## 8、FreeMarker中的运算符优先级如下(由高到低排列):

①、一元运算符:!
②、内建函数:?
③、乘除法:*, / , %
④、加减法:- , +
⑤、比较:> , < , >= , <= (lt , lte , gt , gte)
⑥、相等:== , = , !=
⑦、逻辑与:&&
⑧、逻辑或:||
⑨、数字范围:..
实际上,我们在开发过程中应该使用括号来严格区分,这样的可读性好,出错少

## 9、if 逻辑判断（注意：elseif 不加空格）

//逻辑判断
<#if condition>
...
<#elseif condition2>
...
<#elseif condition3>
...
<#else>
...
</#if>

if 空值判断

// 当 photoList 不为空时
<#if photoList??>...</#if> 

值得注意的是,${..}只能用于文本部分,不能用于表达式,下面的代码是错误的:
<#if ${isBig}>Wow!</#if>
<#if "${isBig}">Wow!</#if>

// 正确写法
<#if isBig>Wow!</#if> 

## 10、switch (条件可为数字，可为字符串)
//
<#switch value> 
<#case refValue1> 
....
<#break> 
<#case refValue2> 
....
<#break> 
<#case refValueN> 
....
<#break> 
<#default> 
.... 
</#switch>

## 11、集合 & 循环

// 遍历集合:
<#list empList! as emp> 
    ${emp.name!}
</#list>

// 可以这样遍历集合:
<#list 0..(empList!?size-1) as i>
    ${empList[i].name!}
</#list>

// 与jstl循环类似,也可以访问循环的状态。

empList?size 　　　// 取集合的长度
emp_index: 　　　　// int类型，当前对象的索引值 
emp_has_next:     // boolean类型，是否存在下一个对象

// 使用<#break>跳出循环
<#if emp_index = 0><#break></#if>

// 集合长度判断 
<#if empList?size != 0></#if> // 判断=的时候,注意只要一个=符号,而不是==

<#assign l=0..100/>    // 定义一个int区间的0~100的集合，数字范围也支持反递增,如100..2
<#list 0..100 as i> 　　// 等效于java for(int i=0; i <= 100; i++)
　　${i}
</#list>

// 截取子集合：
empList[3..5] //返回empList集合的子集合,子集合中的元素是empList集合中的第4-6个元素

// 创建集合：
<#list ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"] as x>

// 集合连接运算,将两个集合连接成一个新的集合
<#list ["星期一","星期二","星期三"] + ["星期四","星期五","星期六","星期天"] as x>

// 除此之外,集合元素也可以是表达式,例子如下:
[2 + 2, [1, 2, 3, 4], "whatnot"]

// seq_contains：判断序列中的元素是否存在
<#assign x = ["red", 16, "blue", "cyan"]> 
${x?seq_contains("blue")?string("yes", "no")}    // yes
${x?seq_contains("yellow")?string("yes", "no")}  // no
${x?seq_contains(16)?string("yes", "no")}        // yes
${x?seq_contains("16")?string("yes", "no")}      // no

// seq_index_of：第一次出现的索引
<#assign x = ["red", 16, "blue", "cyan", "blue"]> 
${x?seq_index_of("blue")}  // 2

// sort_by：排序（升序）
<#list movies?sort_by("showtime") as movie></#list>

// sort_by：排序（降序）
<#list movies?sort_by("showtime")?reverse as movie></#list>

// 具体介绍：
// 不排序的情况：
<#list movies as moive>
　　<a href="${moive.url}">${moive.name}</a>
</#list>

//要是排序，则用
<#list movies?sort as movie>
　　<a href="${movie.url}">${movie.name}</a>
</#list>

// 这是按元素的首字母排序。若要按list中对象元素的某一属性排序的话，则用
<#list moives?sort_by(["name"]) as movie>
　　<a href="${movie.url}">${movie.name}</a>
</#list>

//这个是按list中对象元素的[name]属性排序的，是升序，如果需要降序的话，如下所示：
<#list movies?sort_by(["name"])?reverse as movie>
　　<a href="${movie.url}">${movie.name}</a>
</#list>

## 12、Map对象

// 创建map
<#assign scores = {"语文":86,"数学":78}>

// Map连接运算符
<#assign scores = {"语文":86,"数学":78} + {"数学":87,"Java":93}>

// Map元素输出
emp.name       // 全部使用点语法
emp["name"]    // 使用方括号

## 13、FreeMarker支持如下转义字符:

\" ：双引号(u0022)
\' ：单引号(u0027)
\\ ：反斜杠(u005C)
\n ：换行(u000A)
\r ：回车(u000D)
\t ：Tab(u0009)
\b ：退格键(u0008)
\f ：Form feed(u000C)
\l ：<
\g ：>
\a ：&
\{ ：{
\xCode ：直接通过4位的16进制数来指定Unicode码,输出该unicode码对应的字符.

如果某段文本中包含大量的特殊符号,FreeMarker提供了另一种特殊格式:可以在指定字符串内容的引号前增加r标记,在r标记后的文件将会直接输出.看如下代码:
${r"${foo}"} // 输出 ${foo}
${r"C:/foo/bar"} // 输出 C:/foo/bar

## 14、include指令

// include指令的作用类似于JSP的包含指令:
<#include "/test.ftl" encoding="UTF-8" parse=true>

// 在上面的语法格式中,两个参数的解释如下:
encoding="GBK"  // 编码格式
parse=true 　　 // 是否作为ftl语法解析,默认是true，false就是以文本方式引入,注意:在ftl文件里布尔值都是直接赋值的如parse=true,而不是parse="true"

## 15、import指令

// 类似于jsp里的import,它导入文件，然后就可以在当前文件里使用被导入文件里的宏组件
<#import "/libs/mylib.ftl" as my>
// 上面的代码将导入/lib/common.ftl模板文件中的所有变量,交将这些变量放置在一个名为com的Map对象中，"my"在freemarker里被称作namespace

## 16、compress 压缩

// 用来压缩空白空间和空白的行 
<#compress> 
    ... 
</#compress>

<#t> // 去掉左右空白和回车换行 

<#lt>// 去掉左边空白和回车换行 

<#rt>// 去掉右边空白和回车换行 

<#nt>// 取消上面的效果

## 17、escape,noescape 对字符串进行HTML编码

// escape指令导致body区的插值都会被自动加上escape表达式,但不会影响字符串内的插值,只会影响到body内出现的插值,使用escape指令的语法格式如下:
<#escape x as x?html> 
　　First name: ${firstName} 
<#noescape>Last name: ${lastName}</#noescape> 
　　Maiden name: ${maidenName} 
</#escape>

// 相同表达式
First name: ${firstName?html} 
Last name: ${lastName} 
Maiden name: ${maidenName?html}

--------------------------------------------------------------------
# 高级语法

## 1、global全局赋值语法

// 全局赋值
<#global name=value> 

<#global name1=value1 name2=value2 ... nameN=valueN> 

<#global name> 
　　capture this 
</#global>

// 利用这个语法给变量赋值，那么这个变量在所有的namespace中是可见的，如果这个变量被当前的assign语法覆盖如<#global x=2><#assign x=1>在当前页面里x=2将被隐藏，或者通过${.globals.x} 来访问

## 2、setting 语法

// 用来设置整个系统的一个环境 
locale // zh_CN 中文环境
number_format 
boolean_format 
date_format , time_format , datetime_format 
time_zone 
classic_compatible
// 例1：
<#setting number_format="percent"/>    // 设置数字默认输出方式('percent',百分比)

// 例2：
// 假如当前是匈牙利的设置，然后修改成美国
${1.2} // 输出1,2
<#setting locale="en_US"> 
${1.2} // 输出1.2,因为匈牙利是采用", "作为十进制的分隔符，美国是用". "

##  3、macro宏指令

--------------------
例子1：

<#-- 定义宏 -->
<#macro test foo bar="Bar" baaz=-1> 
　　Text: ${foo}, ${bar}, ${baaz}
</#macro>

<#-- 使用宏 -->
<@test foo="a" bar="b" baaz=5*5/>  // 输出：Text: a, b, 25
<@test foo="a" bar="b"/>    　　　　// 输出：Text: a, b, -1
<@test foo="a" baaz=5*5-2/> 　　　　// 输出：Text: a, Bar, 23
<@test foo="a"/>                   // 输出：Text: a, Bar, -1

-------------------
例子2：

<#-- 定义一个循环输出的宏 -->
<#macro list title items> 
　　${title}
　　<#list items as x>
　　　　*${x}
　　</#list> 
</#macro> 

<#-- 使用宏 -->
<@list items=["mouse", "elephant", "python"] title="Animals"/>
// 输出Animals *mouse *elephant *python

------------------
例子3：

<#-- 嵌套宏 -->
<#macro border>
　　<table>
　　　　<#nested>
　　</table>
</#macro>

<#-- 嵌套宏使用 -->
<@border>
　　<tr><td>hahaha</td></tr>
</@border> 
输出结果：
<table>
　　<tr><td>hahaha</td></tr>
</table>

-----------------------
例子4：在nested指令中使用循环变量时,可以使用多个循环变量,看如下代码:

<#-- 循环嵌套宏 -->
<#macro repeat count>
　　<#list 1..count as x>
　　　　<#nested x, x/2, x==count> // 使用nested指令时指定了三个循环变量
　　</#list>
</#macro>

<#-- 使用宏 -->
<@repeat count = 4; c, halfc, last>
　　${c}. ${halfc}<#if last> Last!</#if>
</@repeat>
// 输出结果：
// 1. 0.5
// 2. 1
// 3. 1.5
// 4. 2 Last!

freemarker 宏嵌套nested 的使用:
http://blog.sina.com.cn/s/blog_7e5699790100z59g.html

## 4、结束macro指令

// return指令用于结束macro指令
<#-- 创建宏 -->
<#macro book>
　　spring
　　<#return>
　　j2ee
</#macro>

<#-- 使用宏 -->
<@book />
// 上面的代码输出:spring,而j2ee位于return指令之后,不会输出.



