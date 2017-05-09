# SpringBoot_Maven
**该项目为初学SpringBoot项目所用**

整合了maven、mybatis、freemarker、log4j

同时添加了AOP组件。

2016-10-23 12:57

知识点：
 1复习下mybatis的insert 使用http://www.cnblogs.com/fsjohnhuang/p/4078659.html
  insert元素 属性详解　　　　　　　　　　　　　　　　　　　　　　　　　　　　

   其属性如下：

    parameterType ，入参的全限定类名或类型别名

    keyColumn ，设置数据表自动生成的主键名。对特定数据库（如PostgreSQL），若自动生成的主键不是第一个字段则必须设置

    keyProperty ，默认值unset，用于设置getGeneratedKeys方法或selectKey子元素返回值将赋值到领域模型的哪个属性中

    useGeneratedKeys ，取值范围true|false(默认值)，设置是否使用JDBC的getGenereatedKeys方法获取主键并赋值到keyProperty设置的领域模型属性中。MySQL和SQLServer执行auto-generated key field，因此当数据库设置好自增长主键后，可通过JDBC的getGeneratedKeys方法获取。但像Oralce等不支持auto-generated key field的数据库就不能用这种方法获取主键了

    statementType ，取值范围STATEMENT,PREPARED（默认值）,CALLABLE

    flushCache ，取值范围true(默认值)|false，设置执行该操作后是否会清空二级缓存和本地缓存

    timeout ，默认为unset（依赖jdbc驱动器的设置），设置执行该操作的最大时限，超时将抛异常

    databaseId ，取值范围oracle|mysql等，表示数据库厂家，元素内部可通过`<if test="_databaseId = 'oracle'">`来为特定数据库指定不同的sql语句

<insert id="add" parameterType="EStudent">
  insert into TStudent(name, age) values(#{name}, #{age})
</insert>
至于mapper.xml则分为两种情况了，一种是数据库(如MySQL,SQLServer)支持auto-generated key field，另一种是数据库（如Oracle）不支持auto-generated key field的。

 1. 数据库(如MySQL,SQLServer)支持auto-generated key field的情况

    手段①（推荐做法）：

<insert id="add" parameterType="EStudent" useGeneratedKeys="true" keyProperty="id">
  insert into TStudent(name, age) values(#{name}, #{age})
</insert>

    手段②：


<insert id="add" parameterType="EStudent">
  // 下面是SQLServer获取最近一次插入记录的主键值的方式
  <selectKey resultType="_long" keyProperty="id" order="AFTER">
    select @@IDENTITY as id
  </selectKey>
  insert into TStudent(name, age) values(#{name}, #{age})
</insert>



  由于手段②获取主键的方式依赖数据库本身，因此推荐使用手段①。

 2. 数据库(如Oracle)不支持auto-generated key field的情况
复制代码

<insert id="add" parameterType="EStudent">
  <selectKey keyProperty="id" resultType="_long" order="BEFORE">
    select CAST(RANDOM * 100000 as INTEGER) a FROM SYSTEM.SYSDUMMY1
  </selectKey>
  insert into TStudent(id, name, age) values(#{id}, #{name}, #{age})
</insert

复制代码

  注意：mapper接口返回值依然是成功插入的记录数，但不同的是主键值已经赋值到领域模型实体的id中了。

//开启定时任务
 1在启动类中加上注解@EnableScheduling
 2在任务类上加上@Scheduled(cron = "30 * * * * ?")

 从maven的项目结构上来看：
   src
     main
      java
        com.tingmall.lsx
      resources
        xxx.xml
     test
      java
        com.tingmall.lsx
   pom.xml

   其中：resources目录为classpath目录