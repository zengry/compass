package org.hk.compass.config;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author zengry
 * @description 整合Swagger2
 * @since 2020/1/3
 */

@Slf4j
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        log.info("加载Swagger2");
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            // 扫描所有有注解的api，用这种方式更灵活
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//            .apis(RequestHandlerSelectors.basePackage("org.hk.compass"))
            .paths(PathSelectors.any())
            .build();
//            .securitySchemes(securitySchemes())
//            .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Compass API Document")
            .description("简单优雅的Restful风格API文档")
            .termsOfServiceUrl("http://localhost:8088/swagger-ui.html")
            .version("1.0")
            .contact(new Contact("zengry", "http://localhost:8088/swagger-ui.html", "zengry@homeking365.com"))
            .build();
    }


//    private List<ApiKey> securitySchemes() {
//        List<ApiKey> apiKeys = new ArrayList<>();
//        apiKeys.add(new ApiKey("token", "token", "header"));
//        return apiKeys;
//    }
//
//    private List<SecurityContext> securityContexts() {
//        List<SecurityContext> securityContexts = new ArrayList<>();
//        securityContexts.add(SecurityContext.builder()
//            .securityReferences(defaultAuth())
//            .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
//        return securityContexts;
//    }
//
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        List<SecurityReference> securityReferences = new ArrayList<>();
//        securityReferences.add(new SecurityReference("token", authorizationScopes));
//        return securityReferences;
//    }


}
