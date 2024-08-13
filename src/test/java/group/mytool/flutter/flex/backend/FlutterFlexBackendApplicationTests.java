package group.mytool.flutter.flex.backend;

import com.mybatisflex.core.query.QueryChain;
import group.mytool.flutter.flex.backend.food.material.mapper.MaterialMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlutterFlexBackendApplicationTests {

    @Autowired
    private MaterialMapper materialMapper;


    @Test
    void contextLoads() {
        QueryChain.of(materialMapper)
            .list()
            .forEach(System.out::println);
    }

}
