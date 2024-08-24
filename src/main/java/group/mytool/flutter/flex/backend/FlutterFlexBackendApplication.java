package group.mytool.flutter.flex.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("group.mytool.flutter.flex.backend.*.*.mapper")
@SpringBootApplication
public class FlutterFlexBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(FlutterFlexBackendApplication.class, args);
  }

}
