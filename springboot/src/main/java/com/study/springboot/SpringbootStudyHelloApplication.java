package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootConfiguration： springboot的配置类-》》@Configuration：标明这是一个配置类--》@Components：加载到spring容器
 * @EnableAutoConfiguration： 开启自动配置功能
 *      @AutoConfigurationPackage： 自动配置包
 *          @Import({Registrar.class})： 把启动类所在的包进行默认扫描，该包下所有类都会扫描到spring容器中进行管理
 *      @Import({AutoConfigurationImportSelector.class})： 加载自定类到容器中
 *          AutoConfigurationImportSelector：自动配置导入选择器
 *          根据项目判断项目需要哪些配置信息然后把默认的配置内容导入到spring容器中进行管理
 * @ComponentScan： 组件扫描、自动装配注解
 */
@SpringBootApplication
public class SpringbootStudyHelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStudyHelloApplication.class, args);
    }

}
