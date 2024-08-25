package group.mytool.flutter.flex.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "group.mytool.flutter.flex.backend.*.*.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
@SpringBootApplication
public class FlutterFlexBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(FlutterFlexBackendApplication.class, args);
  }

}
