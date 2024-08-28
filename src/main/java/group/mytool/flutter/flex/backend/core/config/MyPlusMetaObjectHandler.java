package group.mytool.flutter.flex.backend.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author nieqiurong
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MyPlusMetaObjectHandler implements MetaObjectHandler {

  private final ObjectMapper objectMapper;

  @Override
  public void insertFill(MetaObject metaObject) {
    log.info("进入insertFill填充CCUU信息");
    setFieldValByName("createTime", LocalDateTime.now(), metaObject);
    setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    setFieldValByName("operateInfo", "{\"createUser\":\"admin\"}", metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    log.info("进入updateFill填充CCUU信息");
    setFieldValByName("update_time", LocalDateTime.now(), metaObject);
    setFieldValByName("operateInfo", "{\"createUser\":\"admin\",\"updateUser\":\"admin\"}", metaObject);
  }

}
