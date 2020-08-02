# springboot
## 1.springboot原理
### 1.1 Springboot启动类解析
- @SpringBootConfiguration： springboot的配置类-》》@Configuration：标明这是一个配置类--》@Components：加载到spring容器  
- @EnableAutoConfiguration： 开启自动配置功能  
    1.@AutoConfigurationPackage： 自动配置包  
         1.1@Import({Registrar.class})： 把启动类所在的包进行默认扫描，该包下所有类都会扫描到spring容器中进行管理  
    2.@Import({AutoConfigurationImportSelector.class})： 加载自定类到容器中  
           2.1AutoConfigurationImportSelector：自动配置导入选择器  
           根据项目判断项目需要哪些配置信息然后把默认的配置内容导入到spring容器中进行管理
- @ComponentScan： 组件扫描、自动装配注解

### 1.2 Springboot中配置
- Springboot中的配置文件
    1. 方便修改默认配置
    2. 其他信息保存在配置文件中
    3. 配置文件有：  
    1）properties配置文件  
    2）yml配置文件
    4. 配置文件使用注意事项  
    1）文件放在src/main/resource目录或者类路径config目录下  
    2）springboot默认读取以application开头的配置文件
- yml文件的语法说明（https://yaml.org）  
yml文件介绍：全称为YAML（YAML Ain't Markup Language）它是一种标记语言，直观的被电脑识别的数据序列化格式，并且容易被人类阅读，
这种标记语言与其他标记语言不同的是，它是以数据为中心，比json、xml等更适合做配置文件。  
yml的基本语法：  
    1. key:value的格式（value前面的空格不能少，可以有多个，不能用tab替代）
    2. 大小写敏感
    3. 字符串默认不需要使用引号，单引号和双引号的区别在于是否能用转义字符
    4. 注释方式:#
    5. 支持的三种数据类型：
        - 字面量：直接量，单个不能被拆分的值（数字、字符串、布尔）
        - 对象：键值对形式存在
        - 数组：字面量/对象的集合
        
    yml文件的读取操作
    1. 三种书写方式：${字面量}、${配置文件中的取值}以及${spEL表达式}
- 自定义properties的读取
    1. 自定义一个properties文件，并且让key和之前的properties不一样;
    2. 使用propertysource注解来标明你要读取的properties文件名;
    3. 跟之前一样的读取方式，注意修改prefix的值;

### 1.3 使用Springboot进行Web开发
构建一个Springboot项目，并把静态资源放到项目中：
1.css、js、img等资源放到static目录中  
2.html等模板代码放到template目录  
3.修改配置文件  
4.启动，测试是否正常  

设置主页的默认访问方式  
通过修改WebMvcConfigure的默认设置来指定页面的默认访问方式
1、构建一个配置类，实现WebMvcConfigure接口，重写addViewControllers方法；  
2、添加自定义页面的默认主页映射：addViewController().setViewName();  

Springboot设置欢迎页面：  
一、使用index.html作为欢迎页面
- 静态页面  
Spring boot项目在启动后，首先会去静态资源路径（resource/static）下查找index.html作为首页文件
- 动态页面  
如果在静态资源路径（resource/static）下找不到index.html,则会在resource/templates目录下找index.html作为首页文件  

二、使用其他页面作为欢迎页面
```
@Controller
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("forward:login.html");
    }
}
```

###1.4 Restful介绍及使用
URI：统一资源标识符，服务器上的每一种资源比如文档、图像、视频片段、程序都由一个通用资源标识符（Uniform Resource Identifier，简称URL）进行定位。  
Restful通过不同的注解支持前端的请求
- @GetMapping，处理Get请求
- @PostMapping，处理Post请求
- @PutMapping，用于更新资源
- @DeleteMapping，用于删除资源
- @PathMapping,用于更新部分资源

Controller中的映射注解
@PathVariable，用于接收url路径上的参数
@ModelAttribute，用于接收url?后面的参数，如uri?id=123&name=46,然后直接转为pojo

###1.5 Spring Data Rest介绍
Spring Data项目的子集，开发者只需要使用注解@RepositoryRestResource标记，就可以把整个Repository转换为HAL风格的Rest资源，
目前已支持Spring Data JPA，Spring Data MongoDB，Spring Data Neoj4等等。  
简单的说，Spring Data Rest把我们需要编写的大量Rest模板接口做了自动化实现，并符合HAL的规范。  
HAL(Hypertxt Application Language)是一个被广泛采用的超文本表达的规范。
官方文档：https://www.springcloud.cc/spring-data-rest-zhcn.html  

##2 数据库访问中间件
###2.1 MyBatis
####2.1.1 ORM框架和MyBatis介绍
ORM(Object Related Mapping)：对象映射关系，用于实现面向对象编程语言里不同类型系统的数据之间的转换。
简单的说，ORM是通过使用描述对象和数据库之间映射的元数据，将程序中的对象与关系数据库相互映射。
- 异构性：ORM可以解决数据库与程序之间的异构性，比如在Java中我们使用String表示字符串，
而Oracle中可使用varchar2，MySQL中使用varchar，SQLServer中使用nvarchar  
- 映射：ORM提供了实现持久层的另一种模式，采用映射数据元（XML）来描述对象-关系的映射细节，
使得ORM中间件能在任何一个Java应用的业务逻辑层和数据库之间充当桥梁。

经典的ORM框架
- Hibernate：全自动的框架，强大、复杂、笨重、学习成本高
- MyBatis：半自动框架（懂数据库的人才能操作）必须要自己写sql
- JPA：Java Persistence API，Java自带的框架

MyBatis介绍：
优秀的持久层框架（前身是Apache的一个开源项目iBatis），支持定制化SQL、存储过程及高级映射。  
可以使用简单的XML或注解来配置和映射原生信息，将接口和Java的POJOs（Plain Old Java Objects，普通的java对象）映射成数据库中的记录。

####2.1.2 MyBatis工作流程及架构说明
- 接口层：主要定义的是与数据库进行交互的方式（增删改查，接口调用方式基于StatementID/基于Mapper接口）
- 数据处理层：MyBatis核心，负责参数映射和动态SQL生成，生成之后MyBatis执行SQL语句，将返回的结果映射成自定义的类型
    1. 参数映射（ParameterHandler）：参数映射配置、参数映射解析、参数类型解析
    2. SQL解析（SqlSource）：SQL语句配置、SQL语句解析、SQL语句动态生成
    3. SQL执行（Executor）：SimpleExecutor、BatchExecutor、ReuseExecutor
    4. 结果处理和映射（ResultSetHandler）：结果映射配置、结果类型转换
- 框架支撑层：负责最基础的功能支撑，包括连接管理、事务管理、配置加载和缓存处理，为上层的数据处理层提供最基础的支撑。  
    SQL语句配置方式：基于XML配置、基于注解
- 引导层：基于XML配置方式、基于Java API方式

####2.1.3 MyBatis主要成员及层次结构
- Configuration
全局配置文件
映射配置文件：存储相关sql操作
- MappedStatement
    通过SqlSource生成动态SQL语句，并封装到BoundSql中
    ResultMap：自定义结果集
- BoundSql

层次结构：
SqlSession：作为MyBatis最顶层API，作为会话访问，完成数据库增删改查功能
Executor：MyBatis执行器，调度的核心，负责SQL语句的生成和查询缓存的维护
StatementHandler：负责所有处理JDBC的statement的交互
####2.1.4 MyBatis的基本使用
1.pom引入MyBatis依赖：
```
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.6</version>
</dependency>
```
2.编写全局配置文件mybatis-config.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
<!--            事务管理器-->
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
<!--                高版本的mysql需要使用到新的驱动写法-->
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
<!--                如果有中文的话，是需要加上serverTimezone=UTC的时区，否则会报错-->
                <property name="url" value="jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="mapper/studentMapper.xml"/>
    </mappers>
</configuration>
```
3.编写实体类对应的mapper文件，记录对该实体类进行的SQL操作
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.study.springboot.dao.StudentMapper">
    <resultMap id="BaseResultMap" type="com.study.springboot.entity.Student" >
        <!--主键设置id，其他用result，column就是数据库的字段名，property就是实体类的属性名
        jdbcType就是该类型的字段名-->
        <id column="id" property="id" jdbcType="INTEGER" />
        <result column="name" property="name" jdbcType="VARCHAR" />
        <result column="sex" property="sex" jdbcType="VARCHAR" />
    </resultMap>
<!--根据id来查找对象-->
    <select id="findStudentById" resultType="com.study.springboot.entity.Student">
    SELECT id,name,sex from student where id=#{id}
    </select>
<!--根据用户名来模糊查找对象-->
    <select id="findStudentsByName" parameterType="String" resultType="com.study.springboot.entity.Student">
    SELECT id,name,sex from student where name like '%${value}%';
    </select>
<!--查找所有，这里的返回值使用resultType，放泛型里面的类型-->
    <select id="findAll" resultType="com.study.springboot.entity.Student">
        SELECT id,name,sex from student;
    </select>
<!--查找所有，这里的返回值用自定义的resultMap对象-->
    <select id="selectAll" resultMap="BaseResultMap">
        SELECT id,name,sex from student;
    </select>
<!--添加一个用户-->
    <insert id="insertStudent" parameterType="com.study.springboot.entity.Student">
    insert into student(name,sex) VALUES(#{name},'${sex}')
    </insert>
<!--修改一个用户-->
    <update id="updateStudent" parameterType="com.study.springboot.entity.Student">
    update student set name=#{name},sex=#{sex} where id=#{id}
    </update>
<!--删除一个用户-->
    <delete id="deleteStudent" parameterType="int">
    delete from student where id=#{id}
    </delete>
</mapper>
```
4.写入Mapper接口中
5.构建实体类

