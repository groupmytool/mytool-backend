package group.mytool.backend;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import group.mytool.backend.core.dao.CustomSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan(basePackages = "group.mytool.backend.*.*.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
@SpringBootApplication
public class MyToolBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyToolBackendApplication.class, args);
  }

  @Bean
  public ISqlInjector sqlInjector() {
    return new CustomSqlInjector();
  }

}
