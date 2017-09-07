# cy-generator
快速生成项目
    说明：
        在rapid-generator项目基础之上优化及功能改进
        优化了mysql表注释获取不到问题，及过期方法功能改进等
模版二：
    适用以下数据库生成：
        mysql
        H2
        Oracle
        SQLServer2000
        SQLServer2005
        JTDs for SQLServer  
        PostgreSql
        Sybase
        DB2
        HsqlDB
        Derby
	生成文件：
        html js controller entity mapper service 
    适用项目:
        spring boot spring mvc mybatis 如：renren-fast
    生成文件说明：
	    controller entity mapper service会继承sdk中的类实现基础增删改查
	    分页使用pagehelper