package group.mytool.backend.common;

import group.mytool.backend.FlutterFlexBackendApplicationTests;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;

import java.util.Set;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public class BaseValidateTest extends FlutterFlexBackendApplicationTests {

  /**
   * 此配置仅对单元测试生效，项目运行期间的校验规则配置详见：{@link group.mytool.backend.core.config.ValidateConfig#getValidatorFactory()}
   */
  public static final Validator validator = Validation.byProvider(HibernateValidator.class)
      .configure()
      .failFast(true)
      .buildValidatorFactory()
      .getValidator();

  public <T> Set<ConstraintViolation<T>> validate(T object) {
    return validator.validate(object);
  }

  public <T> String validateMessage(Set<ConstraintViolation<T>> violations) {
    return violations.stream().map(ConstraintViolation::getMessage).reduce((a, b) -> a + ", " + b).orElse("");
  }

  public <T> String validateMessage(T object) {
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (violations.isEmpty()) {
      return "";
    }
    return violations.stream().map(ConstraintViolation::getMessage).reduce((a, b) -> a + ", " + b).orElse("");
  }

}
