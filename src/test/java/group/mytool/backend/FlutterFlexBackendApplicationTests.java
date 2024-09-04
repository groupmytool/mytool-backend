package group.mytool.backend;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableFeignClients(basePackages = "group.mytool.backend.*.*.client")
public class FlutterFlexBackendApplicationTests {

  public static final String TEST_SERVICE = "mytool";
  public static final String TEST_URL = "http://localhost:8080";

}
