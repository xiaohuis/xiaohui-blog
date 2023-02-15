## 小辉个人博客练习项目
#### 1、项目介绍：
此个人博客系统为个人练习项目，分前台和后台。
前台拥有主页、分类管理、标签管理、友链管理、归档管理、博客管理、关于我等模块。前台用户无需登录，即可操作相关模块功能，并且还可以在博客的详情页查看评论内容和发表评论。前台用户用例图如下图所示。


![前台用户用例图](https://file.xiaohuis.top//upload/2023/02/aee106e9b63347dca337fab9b01c5187.png "前台用户用例图")
后台拥有首页、博客管理、评论管理、分类标签管理、友链管理、系统管理和附件管理等模块。后台博主需要登录并且拥有对应权限才能操作对应功能。后台管理员用例图如下图所示。


![后台管理员用例图](https://file.xiaohuis.top//upload/2023/02/aee106e9b63347dca337fab9b01c5186.png "后台管理员用例图")

个人博客系统前台功能模块图如下图所示。
![前台功能模块图](https://file.xiaohuis.top//upload/2023/02/aee106e9b63347dca337fab9b01c5188.png "前台功能模块图")

个人博客系统后台功能模块图如下图所示。

![后台功能模块图](https://file.xiaohuis.top//upload/2023/02/aee106e9b63347dca337fab9b01c5189.png "后台功能模块图")

#### 2、使用技术栈：
后端技术：Spring、Spring MVC、MyBatis、Spring Boot（后使用）、Spring Security、Redis
前端技术：thymeleaf 、Semantic UI

ps：前端部分是使用别人的项目，再根据自身需求做出部分修改。而后端部分则是自己设计编写。

#### 3、运行截图：
![前台](https://file.xiaohuis.top//upload/2023/02/aee106e9b63347dca337fab9b01c5190.png.png "前台")

![后台](https://file.xiaohuis.top//upload/2023/02/aee106e9b63347dca337fab9b01c5191.png.png "后台")


#### 4、小辉博客体验地址：
前台：https://www.xiaohuis.top/

后台：https://www.xiaohuis.top/admin/login  (账号：user1，密码：user123)


#### 5、小辉博客需修改的配置：
1、七牛云API中的密钥凭证ACCESS_KEY与SECRET_KEY，仓库BUCKET等；
2、JwtAuthenticationTokenFilter类和article.js中的重定向链接需要更换；
3、application-dev.yml中的配置需要修改，如MySQL和redis数据库的相关配置；
4、application-prod.yml中的配置也需要修改，如ssl，MySQL和redis数据库的相关配置；
5、SendMailUtil类中的相关配置需要修改，如sendEmailAccount、sendEmailPassword等；
6、如果需要，ProjectExceptionAdvice统一异常处理类也可以修改一下，修改发送异常的邮箱，如果不需要，则可以注释掉。
