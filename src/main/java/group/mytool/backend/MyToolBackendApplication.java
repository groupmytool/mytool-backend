package group.mytool.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "group.mytool.backend.*.*.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
@SpringBootApplication
public class MyToolBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyToolBackendApplication.class, args);
  }

}
