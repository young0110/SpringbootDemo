package com.spring.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = { "com.spring.demo.controller" })
public class SwaggerConfig {

  @Value("${swagger.enable}")
  private Boolean enable;
  @Value("${swagger.url}")
  private String url;

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
              .title("SpringBoot demo")
              .description("Demo project for Spring Boot")
              .version("1.0.0")
              .build();
  }

  private List<ApiKey> securitySchemes() {
    return Collections.singletonList(new ApiKey("Authorization", "Authorization", "header"));
  }

  private List<SecurityContext> securityContext() {
    return Collections.singletonList(
            SecurityContext.builder()
                           .securityReferences(defaultAuth())
                           //.forPaths(PathSelectors.ant("/api/common/*"))
                           .forPaths(PathSelectors.regex("/api/!common.*"))
                           .build()
    );
  }

  private List<SecurityReference> defaultAuth() {
    return Collections.singletonList(
      new SecurityReference("Authorization", new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")})
    );
  }

  @Bean
  public Docket customImplementation() {
    return new Docket(DocumentationType.SWAGGER_2)
              .host(url)
              .enable(enable)
              .select()
              .apis(RequestHandlerSelectors.basePackage("com.spring.demo.controller"))
              .build()
              .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
              .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
              .securitySchemes(securitySchemes())
              .securityContexts(securityContext())
              .apiInfo(apiInfo());
  }

}
