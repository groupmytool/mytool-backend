package group.mytool.flutter.flex.backend.core.config;

import group.mytool.flutter.flex.backend.common.user.dao.SessionRecordDao;
import group.mytool.flutter.flex.backend.common.user.dao.UserDao;
import group.mytool.flutter.flex.backend.food.material.dao.MaterialDao;
import group.mytool.flutter.flex.backend.food.material.dao.MaterialGroupDao;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeSerialization;

/**
 * 这里需要将lambda表达式所使用的成员类都注册上来,具体情况视项目情况而定,一般扫描@Controller和@Service的会多点.
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
public class MyPlusLambdaRegistrationFeature implements Feature {

  @Override
  public void duringSetup(DuringSetupAccess access) {
    RuntimeSerialization.registerLambdaCapturingClass(MaterialDao.class);
    RuntimeSerialization.registerLambdaCapturingClass(MaterialGroupDao.class);
    RuntimeSerialization.registerLambdaCapturingClass(UserDao.class);
    RuntimeSerialization.registerLambdaCapturingClass(SessionRecordDao.class);
  }

}
