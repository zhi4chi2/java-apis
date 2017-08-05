```xml
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.37</version>
    </dependency>
```


# url
```
jdbc:mysql://192.168.1.107:3306/gqfshop?useUnicode=true&characterEncoding=UTF-8
```


Driver: com.mysql.jdbc.Driver


# bug
## 同一 IP 不同端口
如果同一 IP(10.99.77.48) 上开两个端口 3306/3307 ，上面各有一个名叫 test 的数据库，则 mysql-connector-java 5.1.9 总是连接到 3306 的数据库，即使配置为连接 3307 的数据库！


升级到 mysql-connector-java 5.1.37 则 OK


## performance_schema.session_variables
当数据库中 performance_schema.session_variables 表不存在时，通过 MySQL-Front 连接虽然会报
```
Table 'performance_schema.session_variables' doesn't exist
```
但还是可以连接。


但通过 mysql-connector-java 5.1.7 连接会抛异常
```
Table 'performance_schema.session_variables' doesn't exist
```


升级到 mysql-connector-java 5.1.37 则 OK
