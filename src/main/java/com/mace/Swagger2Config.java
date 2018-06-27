package com.mace;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * description: Swagger配置类
 * <br />
 * Created by mace on 20:06 2018/6/23.
 */
@Configuration
@EnableSwagger2//开启Swagger2
public class Swagger2Config implements WebMvcConfigurer {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**").allowedOrigins("*")
//                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
//                .allowCredentials(false).maxAge(3600);
//    }

    /**
     * 这个地方要重新注入一下资源文件，不然不会注入资源的，也没有注入requestHandlerMappping,相当于xml配置的
     *  <!--swagger资源配置-->
     *  <mvc:resources location="classpath:/META-INF/resources/" mapping="swagger-ui.html"/>
     *  <mvc:resources location="classpath:/META-INF/resources/webjars/" mapping="/webjars/**"/>
     *  不知道为什么，这也是spring boot的一个缺点（菜鸟觉得的）
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * description: 创建API应用
     *
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     * <br /><br />
     * create by mace on 2018/6/23 20:29.
     * @param
     * @return: springfox.documentation.spring.web.plugins.Docket
     */
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                .tags(new Tag("user", "用户相关"),getTags())
                .select()
                /*.apis(RequestHandlerSelectors.basePackage("com.mace.controller"))*/
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build().useDefaultResponseMessages(false);
    }


    private Tag[] getTags() {
        Tag[] tags = {
                new Tag("swagger", "testAPI"),
                new Tag("cart", "购物车相关")
        };
        return tags;
    }


    /**
     * description: 创建该API的基本信息（这些基本信息会展现在文档页面中）
     *
     * 访问地址：http://项目实际地址/swagger-ui.html
     *
     * <br /><br />
     * create by mace on 2018/6/23 20:27.
     * @param
     * @return: springfox.documentation.service.ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot利用swagger2构建api文档")
                .description("简单优雅的restful风格")
                .termsOfServiceUrl("http://项目实际地址/")
                .contact(new Contact("mace","http://127.0.0.1:8088/","18500348251@163.com"))
                .version("1.0")
                .build();
    }
}
