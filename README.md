# cy-generator
**项目说明** 
- 适用所有主流数据库生成前后端代码（适用前后端分离项目）
- 优化了mysql表注释获取不到问题，及过期方法功能改进等
- 新增模版一、模版二
- 此项目由rapid为基础扩展

**项目使用说明** 
- 修改generator.xml文件，outRoot改为项目根目录，数据库改为对应即可（generator.xml，application.yml）
- 运行GenApplication文件即可生成代码在outRoot目录下（注：修改表名要存在库中）
- 运行CyApplication文件即可启动spring boot项目
- 生成后的代码放到相应目录下，启动项目，可以直接访问http://localhost:[端口号]/modules/[业务模块]/[功能模块].html
- 访问路径也可以直接参考生成的menu.sql文件

**项目结构** 
```
cy-generator
├─biz 业务模块（copy生成的java文件到此目录）
│ 
├─generator 代码生成工具类
│ 
├─sdk 公用基类及工具类
│ 
├─CyApplication 项目启动入口
│  
├─GenApplication 代码生成入口
│
├──resources 
│  ├─mapper SQL对应的XML文件
│  ├─static 第三方库、插件等静态资源
│  ├─views  项目静态页面
│  └─generator.xml 生成代码配置文件
│
├──templates 
│  ├─template_1 模版一
│  └─template_2 模版二
│
├──outRoot 代码生成目录 （copy 生成的代码到相应目录下，启动CyApplication）
```

**模版二适用项目**
- spring boot spring mvc mybatis 
- 如：[renren-fast](http://git.oschina.net/babaio/renren-fast/tree/master)

**适用以下数据库生成**
- mysql
- H2
- Oracle
- SQLServer2000
- SQLServer2005
- JTDs for SQLServer  
- PostgreSql
- Sybase
- DB2
- HsqlDB
- Derby

**生成文件**
- html
- js 
- controller 
- entity 
- mapper 
- service 

**生成文件说明**
- controller entity mapper service会继承sdk中的类实现基础增删改查，因此类中不会生成方法
- 分页使用pagehelper
- menu.sql 菜单及功能按钮权限相关

**其它**
- 码云仓库:http://git.oschina.net/bzj/cy-generator
- github仓库：https://github.com/bzjsky/cy-generator 