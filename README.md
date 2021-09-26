Personal Chinese annotations for mybatis-3.  
第一遍请把粒度放大，短时间内建立起大致结构！

##配置文件解析 >
SqlSessionFactory.build()  
XMLConfigBuilder.parse()  
parseConfiguration(XNode root)  

  
##Sql执行流程 > executor
执行器（Executor) 
Sql处理器（StatementHandler)  
参数处理器（ParameterHandler）  
结果处理器（ResultSetHandler）  
  
##映射配置 > 
动态Sql映射  
参数映射  
结果集映射  
  
##扩展支撑 > 
Mapper代理  
会话  
缓存  
插件  
懒加载  
