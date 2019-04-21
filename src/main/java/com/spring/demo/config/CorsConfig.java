package com.spring.demo.config;

import com.spring.demo.config.component.AuthTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import java.util.Arrays;
import java.util.List;

/**
 * 跨域
 */
@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {

  private static List<String> authPaths = Arrays.asList("/api/**");
  private static List<String> authExPaths = Arrays.asList("/api/common/*", "/api/public/*");

  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("*") //host
            .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS");
  }

  @Override
  protected void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new AuthTokenInterceptor()).addPathPatterns(authPaths).excludePathPatterns(authExPaths);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

}
