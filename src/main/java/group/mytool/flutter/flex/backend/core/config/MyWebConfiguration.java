package group.mytool.flutter.flex.backend.core.config;

import group.mytool.flutter.flex.backend.core.interceptor.CookieSessionInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;

import static group.mytool.flutter.flex.backend.core.util.Constant.FILE_PREFIX;
import static group.mytool.flutter.flex.backend.core.util.Constant.MEMBER_CONTROLLER;

@Component
@RequiredArgsConstructor
public class MyWebConfiguration implements WebMvcConfigurer {

  private final CookieSessionInterceptor sessionInterceptor;

  // url 结尾处一定要添加 / ，不然会被截取掉最后一层目录
  @Value("${mytool.upload.path}")
  private String uploadFilePath;
  @Value("${mytool.view.page}")
  private String[] views;

  /**
   * 全局拦截，校验session有效性；这个拦截器会拦截跨域的OPTION请求，解决办法是
   * 使用 CorsConfig.java 拦截器进行放行；不能再本类中添加
   * addCorsMappings()，会与本方法冲突导致跨域失效
   *
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    InterceptorRegistration interceptorRegistration = registry.addInterceptor(sessionInterceptor);
    interceptorRegistration
        // 必须配置：默认错误页面放行，方便排查是404还是session没放行
        .excludePathPatterns("/error")
        // 放行注册和登录
        .excludePathPatterns(MEMBER_CONTROLLER + "/*")
        // 放行图片访问
        .excludePathPatterns(FILE_PREFIX + "**")
        // 过滤其他所有路径
        .addPathPatterns("/**");
    if (Objects.nonNull(views) && views.length > 0) {
      // 放行浏览页面
      for (String view : views) {
        interceptorRegistration.excludePathPatterns(view);
      }
    }
  }

  /**
   * 静态资源映射地址
   *
   * @param registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler(FILE_PREFIX + "**")
        .addResourceLocations("file://" + uploadFilePath, "classpath:/images/");
  }

}
