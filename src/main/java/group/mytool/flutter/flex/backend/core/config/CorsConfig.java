package group.mytool.flutter.flex.backend.core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 参考：
 * https://blog.csdn.net/bingospunky/article/details/80136164
 * https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
 * https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
 */
@Configuration
public class CorsConfig {

  @Bean
  public FilterRegistrationBean corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    // config.addAllowedOrigin("http://localhost:3000");
    config.addAllowedOriginPattern("*");
    config.setAllowCredentials(true);
    config.addAllowedMethod("*");
    config.addAllowedHeader("*");
    UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
    configSource.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(configSource));
    bean.setOrder(0);
    return bean;
  }

}
