package group.mytool.backend.core.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import group.mytool.backend.core.dao.CustomSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-Plus 配置
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Configuration
public class MyPlusConfig {

  /**
   * 自定义 SQL 注入器
   *
   * @return ISqlInjector
   */
  @Bean
  public ISqlInjector sqlInjector() {
    return new CustomSqlInjector();
  }

}
