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
