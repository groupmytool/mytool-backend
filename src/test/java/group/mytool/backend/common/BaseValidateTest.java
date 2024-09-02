package group.mytool.backend.common;

import group.mytool.backend.FlutterFlexBackendApplicationTests;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public class BaseValidateTest extends FlutterFlexBackendApplicationTests {

  public final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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
