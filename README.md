## 前言
### 🍊缘由
#### 想让没体验过chatGPT的小伙伴，学习对接并复刻出自己的chatGPT
> **ChatGPT火爆**的同时，**国内访问限制较多**，其他对接过的产品前提也是有**次数限制**，有很多小伙伴没法痛快的玩耍。想通过此文手把手从0开始，让我们通过**Springboot+uniapp+uview2对接OpenAI-Java**，复刻出一个我们自己的chatGPT，并支持H5/APP/小程序多端发布。

******
### ⏲️本文阅读时长
#### 约20分钟
***
### 🎯主要目标
#### 1.前端实现ChatGPT聊天页面
> 通过**uniapp+uview2** 完成基本注册登录页，实现聊天界面，并模仿ChatGPT实现**打字机打印效果**

#### 2.后端与OpenAI实现对接
> 通过**SpringBoot对接OpenAI接口**，实现ChatGpt聊天基本流程

#### 3.前后端接口对接
> 通过接口对接，实现全流程，并通过uniapp打包测试与AI聊天


******
###  🎨 水一波图
**自制ChatGPT 聊天gif动图**

![请添加图片描述](https://img-blog.csdnimg.cn/4dcc8b619f394699a68bc85ef97b3d8e.gif)
**登录注册图**![请添加图片描述](https://img-blog.csdnimg.cn/b13bb7b4b7a24f23986b8f381c51a30d.png)
**聊天图**
![请添加图片描述](https://img-blog.csdnimg.cn/b03da1b0dcee48839d190ce6db59c7fd.png)
******

###  🍭体验地址
#### 关注公众号【JavaDog程序狗】，即可获取在线体验
![在这里插入图片描述](https://img-blog.csdnimg.cn/d01ed0c497a1433b8163badb37e20e4b.png)
******

###  🍩源码
>  关注公众号【JavaDog程序狗】，发送【ChatGPT源码】即可无套路获得

![在这里插入图片描述](https://img-blog.csdnimg.cn/4d3d3c7a16264b1bba7d9b1c0826e1ab.png)

******

### 🍪前置条件
####  拥有自己的openai 账号
> 需要API keys管理的SECRET KEY，作为调用接口的参数

关于如何注册账号？请参考本狗文章[【ChatGPT】手摸手，带你玩转ChatGPT](https://mp.weixin.qq.com/s/9wEelbTN6kaChkCQHmgJMQ)

![在这里插入图片描述](https://img-blog.csdnimg.cn/113a5b098e0348b88544c9d31982690e.png)
 🌰Tips: 如果没有账号可以**关注公众号**【JavaDog程序狗】回复【ChatGPT】即可获得账号
 
******

###  🍓猜你喜欢
####  1.[【SpringBoot】还不会SpringBoot项目模块分层？来这手把手教你](https://blog.csdn.net/baidu_25986059/article/details/128739849)
####  2.[【ChatGPT】手摸手，带你玩转ChatGPT](https://mp.weixin.qq.com/s/9wEelbTN6kaChkCQHmgJMQ)
####  3.[【Apifox Helper】自动生成接口文档，IDEA+Apifox懒人必备](https://blog.csdn.net/baidu_25986059/article/details/128991855)
******

## 正文
### 1.准备
#### 开发工具
| 工具| 版本 | 用途 |
| --- | ----- |  ----- |
| IDEA|  2021.3.2 |Java开发工具 |
| HBuilder X | 3.6.18.20230117 |前端开发者服务的通用 IDE |

#### 账号申请
| 账号| 功能 | key |
| --- | ----- |  ----- |
| openai |  API keys |SECRET KEY |

#### 主要组件
##### 前端
| 插件 | 版本 | 用途 |
| --- | ----- |  ----- |
| uview-ui|  ^2.0.31 |多平台快速开发的UI框架 |
| mescroll-uni | 1.3.7 |mescroll高性能的下拉刷新上拉加载组件 |

##### 后端
| 插件 | 版本 | 用途 |
| --- | ----- |  ----- |
| jdk |  1.8 |java环境 |
| lombok | 1.18.16 |代码简化插件 |
| maven | 3.6.3 |包管理工具 |
| druid| 1.1.24 | JDBC组件 |
| hutool| 5.7.20 | Java工具类库|
| mybatis-plus| 3.4.1 | 基于 MyBatis 增强工具|
| mysql | 8.0 / 5.7 | 数据库 |
| **openai-gpt3-java**| 0.10.0 | OpenAI提供的 API|

**什么是openai-gpt3-java？**
>**面向Java的openai gpt3模型的封装依赖**，目前OpenAI只开放了GPT3模型的相关API，经过实际验证，GPT3模型的API，在回答问题的水平上已经接近**ChatGPT 70~80%的水平**，但与官网模型还是稍有差异，功能性不全。

******
### 2.功能分析

后端：提供用户**登录，注册，聊天等接口，对接openai-gpt3-java**。

前端：画出**登录，注册，聊天页面，消息分页，下拉加载历史数据，AI消息打印展现效果**等页面操作。

对接：前后端对接，实现**模仿ChatGPT与智能AI聊天**基本功能。
******
### 3.开发
#### 后端代码总览
![在这里插入图片描述](https://img-blog.csdnimg.cn/fb0dc6511d224c318e44f8c2fee06d87.png)


具体搭建此处省略，如有疑问请参考[【SpringBoot】还不会SpringBoot项目模块分层？来这手把手教你](https://blog.csdn.net/baidu_25986059/article/details/128739849) 完全跟着复制分层即可。还有doc文件夹下有DB脚本，别忘跑！

#### 后端-关键点
引入**openai-gpt3-java**依赖，版本为 <openai.version>0.10.0</openai.version>
```xml
 <!-- 导入openai依赖 -->
 <dependency>
      <groupId>com.theokanning.openai-gpt3-java</groupId>
      <artifactId>client</artifactId>
      <version>${openai.version}</version>
 </dependency>
```

调用**openai-gpt3-java**方法
```java
// 调用openai-gpt3-java方法
OpenAiService service = new OpenAiService(OPENAPI_TOKEN, TIMEOUT);
CompletionRequest.CompletionRequestBuilder builder = CompletionRequest.builder()
        .model(MODEL)
        .prompt(String.format(PROMPT, message.getMsgContent()))
        .temperature(TEMPERATURE)
        .maxTokens(1000)
        .topP(TOPP)
        .frequencyPenalty(FREQUENCYPENALTY)
        .presencePenalty(PRESENCEPENALTY);
CompletionRequest completionRequest = builder.build();
List<CompletionChoice> questionAnswer = service.createCompletion(completionRequest).getChoices();
String endQuestionAnswer = "";
for (CompletionChoice completionChoice : questionAnswer) {
    endQuestionAnswer = endQuestionAnswer.concat(completionChoice.getText());
}
return endQuestionAnswer;
```
参考github文档[https://github.com/TheoKanning/openai-java](https://github.com/TheoKanning/openai-java)

参数分析

 1. OPENAPI_TOKEN
>  访问[https://platform.openai.com/account/api-keys](https://platform.openai.com/account/api-keys)，在openai 账号管理**API keys**中找到**SECRET KEY**，这个是调用接口凭据

![在这里插入图片描述](https://img-blog.csdnimg.cn/757a481d08674945949c577d737ec23b.png)

2. TIMEOUT
> 超时时间，毫秒为单位，本狗写了30000，共30秒

 3. MODEL
> 模型，本狗写入text-davinci-003，模型是AI聊天

 4. PROMPT
> 对话，与AI接口交互的内容，如【你是谁？】

 5. TEMPERATURE
> 模型将承担风险的高低，如0.9

 6. TOPP
> 情绪采样，如1.0

7. FREQUENCYPENALTY
> 频率处罚系数，如0.0

8. PRESENCEPENALTY
> 重复处罚系数，如0.6

******

#### 前端代码总览

![在这里插入图片描述](https://img-blog.csdnimg.cn/b86efe1eef794dc99890fdf98039900f.png)
#### 前端-关键点
**如何实现打字机效果？**
> 原理就是采用定时器，每次截取一个字节进行内容填充

```js
// interval
intervalFunc(){
   // 深拷贝内容
   let content = uni.$u.deepClone(this.targetContent);
   // 记录次数
   this.times++;
   if(this.times == content.length){
     clearInterval(this.interval)
   }
   this.targetMsg.msgContent = content.substring(0,this.times);
   this.$set(this.msgList, this.msgList.length-1, this.targetMsg);
   this.$nextTick(function () {
     this.mescroll.scrollTo(99999, 0)
   })
 },
```

例：AI返回的数据为【我真的好喜欢你！】
每次substring截取一个字符，然后进行填充渲染结果为
我
我真
我真的
我真的好
...

这样就可以实现简单的**打字机效果**，网上还有通过CSS形式实现的，都可参考


### 4.运行
#### 后端
> 修改环境变量

![在这里插入图片描述](https://img-blog.csdnimg.cn/da185331388043d7aa9c7f4f7f04f123.png)

> 直接在IDEA运行启动

![在这里插入图片描述](https://img-blog.csdnimg.cn/72914fc2e42742b3bb46733372722e09.png)

#### 前端
> 先在项目目录中执行 **npm i** ，下载依赖

![在这里插入图片描述](https://img-blog.csdnimg.cn/d218a74b1c28493d8f435715171f7c1b.png)

> 修改环境变量，在/common/config/env.js下

![在这里插入图片描述](https://img-blog.csdnimg.cn/182c921e805d476988eff5ace87dea9d.png)

> 然后再HBuilder X工具中点击运行即可

![在这里插入图片描述](https://img-blog.csdnimg.cn/bbc87d26f5b040c8b87e2308eaf8e20f.png)
## 总结
本文简单实现了**OpenAI-Java接口对接**，**模仿ChatGPT**做了一个**简易AI聊天**，其中功能较为简单，适用于**学习练手**，其中略过了很多基础的搭建环节，大家如果对从零一步一步搭建项目比较感兴趣或期望较高，请再评论区留言，反响多的话我会再出一版**从零开始，手把手搭建项目的文章**。


## 写在最后
欢迎大家关注公众号【JavaDog程序狗】，**任何留言**需要我都会**及时回复**，希望我们一同进步

![在这里插入图片描述](https://img-blog.csdnimg.cn/e74f3636c05a430eab8819333fa004eb.jpeg)

| JavaDog| 狗屋地址 |
| :----:| :----: | 
| 个人博客 | [https://blog.javadog.net](https://blog.javadog.net) | 
| 公众号 | [https://mp.weixin.qq.com/s/_vgnXoQ8FSobD3OfRAf5gw](https://mp.weixin.qq.com/s/_vgnXoQ8FSobD3OfRAf5gw) | 
| CSDN  | [https://blog.csdn.net/baidu_25986059](https://blog.csdn.net/baidu_25986059) | 
| 掘金 | [https://juejin.cn/user/2172290706716775](https://juejin.cn/user/2172290706716775)| 
| 知乎 | [https://www.zhihu.com/people/JavaDog](https://www.zhihu.com/people/JavaDog) | 
| 简书| [https://www.jianshu.com/u/1ff9c6bdb916](https://www.jianshu.com/u/1ff9c6bdb916) | 
| gitee|[https://gitee.com/javadog-net](https://gitee.com/javadog-net)  | 
| GitHub|[https://github.com/javadog-net](https://github.com/javadog-net)| 
