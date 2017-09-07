# cy-generator
**项目说明** 
- 在rapid-generator项目基础之上功能改进及版本升级
- 优化了mysql表注释获取不到问题，及过期方法功能改进等

**项目使用说明** 
- 修改generator.xml文件，outRoot改为项目根目录，数据库改为对应即可
- 运行GenApplication文件即可生成代码在outRoot目录下（注：修改表名要存在库中）
- 运行CyApplication文件即可启动spring boot项目

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
- controller entity mapper service会继承sdk中的类实现基础增删改查
- 分页使用pagehelper